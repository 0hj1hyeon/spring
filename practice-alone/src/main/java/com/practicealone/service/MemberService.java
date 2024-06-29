package com.practicealone.service;

import com.practicealone.domain.Member;
import com.practicealone.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;


    //회원가입
    public void join(Member member){
        memberRepository.save(member);
    }
    // 로그인
    public boolean login(String uid, String upw) {
        // 아이디로 회원을 찾습니다.
        Member member = memberRepository.findByUid(uid);
        // 회원이 존재하고, 입력된 비밀번호와 회원의 비밀번호가 일치하는지 확인합니다.
        if (member != null && member.getUpw().equals(upw)) {
            System.out.println("login success in memberservice");
            return true; // 로그인 성공
        } else {
            System.out.println("login fail in memberservice");
            return false; // 로그인 실패
        }
    }

}
