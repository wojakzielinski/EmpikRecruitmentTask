package pl.empik.recruitmenttask.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import org.joda.time.DateTime;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubUserDto {
    private long id;
    private String login;
    private String name;
    private String type;
    private String avatarUrl;
    private DateTime createdAt;
    private int followers;
    private int publicRepos;
}
