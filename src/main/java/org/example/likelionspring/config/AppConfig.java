package org.example.likelionspring.config;

import org.example.likelionspring.repository.MemberRepository;
import org.example.likelionspring.repository.MemoryMemberRepository;
import org.example.likelionspring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class AppConfig {
    @Bean
    public MemberRepository memberRepository() {
        // 구현체 객체를 생성해서 반환합니다.
        return new MemoryMemberRepository();
    }
    @Bean
    public MemberService memberService() {
        // 위에서 정의한 memberRepository() 메서드를 호출하여 주입합니다.
        return new MemberService(memberRepository());
    }
}