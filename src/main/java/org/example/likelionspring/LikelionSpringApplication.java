package org.example.likelionspring;

import org.example.likelionspring.service.MemberService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class LikelionSpringApplication {

    public static void main(String[] args) {
        // run 메서드의 반환값을 받아 컨텍스트를 저장합니다.
        ApplicationContext context = SpringApplication.run(LikelionSpringApplication.class, args);

        // 생성된 context 인스턴스를 통해 빈을 가져옵니다.
        MemberService memberService = context.getBean(MemberService.class);
    }
}