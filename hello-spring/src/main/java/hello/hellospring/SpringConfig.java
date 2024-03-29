package hello.hellospring;

import hello.hellospring.repository.*;

import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    private final MemberRepository memberRepository;
    public SpringConfig(DataSource dataSource, EntityManager em, MemberRepository memberRepository) {
        this.memberRepository = memberRepository;

    }
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }
    //@Bean
    //public MemberRepository memberRepository() {

       // return new JdbcTemplateMemberRepository(dataSource);
       // return new JpaMemberRepository(em);
  //  }


}

