package pl.empik.recruitmenttask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.empik.recruitmenttask.repository.RequestLogRepositoryImpl;

@Service
public class RequestLogServiceImpl {

    private RequestLogRepositoryImpl requestLogRepository;

    @Autowired
    public RequestLogServiceImpl(RequestLogRepositoryImpl requestLogRepository){
        this.requestLogRepository = requestLogRepository;
    }

    public void incrementRequestLog(String login){
        requestLogRepository.incrementRequestLog(login);
    }
}
