package pl.empik.recruitmenttask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.empik.recruitmenttask.model.User;
import pl.empik.recruitmenttask.service.GithubService;
import pl.empik.recruitmenttask.service.RequestLogServiceImpl;

@RestController
public class UserFromGithubController {

    private GithubService githubService;
    private RequestLogServiceImpl requestLogService;

    @Autowired
    public UserFromGithubController(GithubService githubService, RequestLogServiceImpl requestLogService) {
        this.githubService = githubService;
        this.requestLogService = requestLogService;
    }

    @GetMapping(value = "/users/{login}")
    ResponseEntity<User> getUserFromGithub(@PathVariable String login) {
        requestLogService.incrementRequestLog(login);
        return githubService.getUserFromGithub(login);
    }
}
