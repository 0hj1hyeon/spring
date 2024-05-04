package com.practicealone.controller;

import com.practicealone.domain.Member;
import com.practicealone.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository memberRepository;
    @PostMapping("/users/save")
    public void memberSave(@RequestBody Member member){
        memberRepository.save(member);
    }
}
