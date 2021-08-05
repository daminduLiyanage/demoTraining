package com.springhow.example.helloworld;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

    @RequestMapping("/")
    public String index() {
        return "Gradle : Hello Spring Boot!";
    }

}


ms auth setup
call & verify



last offshore doc