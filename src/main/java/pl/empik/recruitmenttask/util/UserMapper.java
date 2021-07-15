package pl.empik.recruitmenttask.util;

import pl.empik.recruitmenttask.model.GithubUserDto;
import pl.empik.recruitmenttask.model.User;

public class UserMapper {
    private UserMapper() {
    }

    public static User userFromGithubUser(GithubUserDto githubUserDto) {
        User user = new User();
        user.setId(githubUserDto.getId());
        user.setLogin(githubUserDto.getLogin());
        user.setName(githubUserDto.getName());
        user.setType(githubUserDto.getType());
        user.setAvatarUrl(githubUserDto.getAvatarUrl());
        user.setCreatedAt(githubUserDto.getCreatedAt());
        return user;
    }
}
