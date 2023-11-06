package beomjun.preject1.controller;

import beomjun.preject1.domain.Member;
import beomjun.preject1.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private  final MemberService memberService;


// 컨트롤러는 오토 써야함
    @Autowired // 스프링이 멤버 서비스를 연결해준다 의존관계를 넣어 준다
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

   @GetMapping("/members/new")
   public String createForm(){
        return "members/createMemberForm";
   }

   @PostMapping("/members/new")
   public String create(MemberForm form){
       Member member = new Member();
       member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
   }
   @GetMapping("/members")
    public String list(Model model){
        List<Member> members= memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
   }

}
