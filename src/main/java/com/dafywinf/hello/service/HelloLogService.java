package com.dafywinf.hello.service;

import com.dafywinf.hello.domain.HelloLog;
import com.dafywinf.hello.repository.HelloLogRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class HelloLogService {

    private final HelloLogRepository helloLogRepository;

    public HelloLogService(HelloLogRepository helloLogRepository) {
        this.helloLogRepository = helloLogRepository;
    }

    public HelloLog createLog() {
        HelloLog helloLog = new HelloLog();
        return helloLogRepository.save(helloLog);
    }
}
