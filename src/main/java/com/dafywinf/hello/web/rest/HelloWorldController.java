package com.dafywinf.hello.web.rest;

import com.dafywinf.hello.domain.HelloLog;
import com.dafywinf.hello.service.HelloLogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloWorldController {


    private final HelloLogService helloLogService;

    public HelloWorldController(HelloLogService helloLogService) {
        this.helloLogService = helloLogService;
    }

    @GetMapping("/hello")
    public HelloLog hello() {
        return this.helloLogService.createLog();
    }

}
