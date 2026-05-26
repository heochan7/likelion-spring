package org.example.likelionspring;

import org.example.likelionspring.service.MemberService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class LikelionSpringApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(LikelionSpringApplication.class, args);

        MemberService memberService = context.getBean(MemberService.class);
    }
}