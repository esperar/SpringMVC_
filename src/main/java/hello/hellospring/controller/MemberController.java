package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService){

        this.memberService = memberService;
    }

    // creatMemberForm.html을 띄워줌
    @GetMapping("/members/new")
    public String createForm(){

        return "members/createMemberForm";
    }

    //
    @PostMapping("/members/new")
    public String create(MemberForm form){

        Member member = new Member();
        member.setName(form.getName());
        memberService.join(member);
        // 그 전에 있었던 화면 띄우기
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){

        List<Member> members = memberService.findMember();
        model.addAttribute("members",members);
        return "members/memberList";
    }
}

