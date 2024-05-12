package com.practicealone.controller;

import com.practicealone.domain.Member;
import com.practicealone.repository.MemberRepository;
import com.practicealone.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    @GetMapping("/save")
    public String createForm(){
        System.out.println("hi");
        return "members/creatememberform";
    }
    @PostMapping("/save")
    public String memberCreate(@RequestBody Member member){
        memberService.join(member);
        return "redirect:/";
    }
}
