package beomjun.preject1.service;

import beomjun.preject1.repositorty.JdbcMemberRepositroy;
import beomjun.preject1.repositorty.MemberRepository;
import beomjun.preject1.repositorty.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public  MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
       // return new MemoryMemberRepository();
        return new JdbcMemberRepositroy(dataSource);
    }
}
