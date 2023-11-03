package beomjun.preject1.service;

import beomjun.preject1.domain.Member;
import beomjun.preject1.repositorty.MemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    // 외부에서 넣어줄 수 있게 바꾸기


    public Long join(Member member){
        // 중복 제거
//        Optional<Member> result = memberRepository.findByName(member.getName()); // 컨트롤 알트 v
        vaildateDuplicateMember(member); // 메소드로 바로 만들수 있다
        // 컨트롤 알트 스프트 T
        memberRepository.save(member);
        return member.getId();
    }

    private void vaildateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
            .ifPresent(m ->{
            throw new IllegalStateException("이미 존재하는 회원입니다");
        });
    }
    // 전체 회원 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public  Optional<Member> findOne(Long memberId){
        return  memberRepository.findById(memberId);
    }
}
