package org.example.likelionspring.config;

import org.example.likelionspring.repository.MemberRepository;
import org.example.likelionspring.repository.MemoryMemberRepository;
import org.example.likelionspring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }
}