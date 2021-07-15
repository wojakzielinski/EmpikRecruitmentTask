package pl.empik.recruitmenttask.model;

import lombok.Data;
import org.joda.time.DateTime;

@Data
public class User {
    private long id;
    private String login;
    private String name;
    private String type;
    private String avatarUrl;
    private DateTime createdAt;
    private int calculations;
}
