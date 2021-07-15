package pl.empik.recruitmenttask.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RequestLogRepositoryImpl {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public RequestLogRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void incrementRequestLog(String login){
        jdbcTemplate.update("INSERT INTO public.request_logs(login, request_count)" +
                " VALUES(?, 1)" +
                " ON CONFLICT(login) DO" +
                " UPDATE SET request_count = EXCLUDED.request_count + 1", login);
    }
}
