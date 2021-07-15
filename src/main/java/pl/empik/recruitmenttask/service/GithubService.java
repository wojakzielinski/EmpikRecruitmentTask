package pl.empik.recruitmenttask.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import pl.empik.recruitmenttask.model.GithubUserDto;
import pl.empik.recruitmenttask.model.User;
import pl.empik.recruitmenttask.util.UserMapper;

@Log4j2
@Service
public class GithubService {
    private String GITHUB_API_URL;
    private RestTemplate restTemplate;

    @Autowired
    public GithubService(@Value("${api.url.github}") String githubUrl, RestTemplate restTemplate) {
        this.GITHUB_API_URL = githubUrl;
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<User> getUserFromGithub(@PathVariable String login) {
        String url = GITHUB_API_URL + login;
        ResponseEntity<GithubUserDto> githubUserResponse = restTemplate.getForEntity(url, GithubUserDto.class);
        if (githubUserResponse.getStatusCode() == HttpStatus.OK) {
            GithubUserDto githubUserDto = githubUserResponse.getBody();
            if (githubUserDto != null) {
                return retrieveProperData(githubUserDto);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } else {
            return new ResponseEntity<>(githubUserResponse.getStatusCode());
        }
    }

    private ResponseEntity<User> retrieveProperData(GithubUserDto githubUserDto) {
        try {
            User user = UserMapper.userFromGithubUser(githubUserDto);
            user.setCalculations(doCalculations(githubUserDto));
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (ArithmeticException e) {
            log.error("No specification is described for this situation");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private int doCalculations(GithubUserDto githubUserDto) {
        return 6 / githubUserDto.getFollowers() * (2 + githubUserDto.getPublicRepos());
    }
}
