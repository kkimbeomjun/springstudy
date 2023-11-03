package beomjun.preject1.repositorty;

import beomjun.preject1.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

        Member save(Member member);
        Optional <Member> findById(long id);
        Optional <Member> findByName(String name);
        List<Member> findAll();

}
