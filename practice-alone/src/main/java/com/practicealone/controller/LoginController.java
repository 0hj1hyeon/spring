package com.practicealone.controller;


import com.practicealone.domain.Member;
import com.practicealone.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final MemberService memberService;

    // 로그인 페이지로 이동
    @GetMapping("/login")
    public String loginForm() {
        return "login/loginForm";
    }

    // 로그인 요청 처리
    @PostMapping("/login")
    public String login(@RequestParam String uid, @RequestParam String upw, HttpSession session, Model model) {
        // 로그인 서비스 호출
        boolean loginResult = memberService.login(uid, upw);

        // 로그인 성공 여부에 따라 적절한 동작을 수행
        if (loginResult) {
            // 세션에 로그인 정보 저장 (로그인 상태 유지)
            System.out.println("login success in logincontroller");
            session.setAttribute("loginMember", uid);

            return "redirect:/"; // 로그인 성공 시 홈 페이지로 이동
        } else {
            model.addAttribute("loginError", true);
            System.out.println("login fail in logincontroller");
            return "login/loginForm"; // 로그인 폼으로 다시 이동
        }
    }

    // 로그아웃 처리
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 세션에서 로그인 정보 삭제
        session.removeAttribute("loginMember");
        return "redirect:/"; // 로그아웃 후 홈 페이지로 이동
    }
}

