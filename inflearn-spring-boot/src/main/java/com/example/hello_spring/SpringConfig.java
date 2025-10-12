package com.example.hello_spring;

import com.example.hello_spring.aop.TimeTraceAop;
import com.example.hello_spring.domain.Member;
import com.example.hello_spring.repository.JpaMemberRepository;
import com.example.hello_spring.repository.MemberRepository;
import com.example.hello_spring.repository.MemoryMemberRepository;
import com.example.hello_spring.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// JpaMemberRepository를 스프링 컨테이너에 Bean으로 등록하는 코드

@Configuration // Spring 설정 정보 어노테이션
public class SpringConfig {
    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // MemberService Bean 등록
    @Bean
    public MemberService memberService() {

        return new MemberService(memberRepository);
    }

//    @Bean
//    public TimeTraceAop timeTraceAop() {
//        return new TimeTraceAop();
//    }

    // MemberRepository Bean 등록
//    @Bean
//    public MemberRepository memberRepository() {
//        //return new JpaMemberRepository(em);
//    }


}
