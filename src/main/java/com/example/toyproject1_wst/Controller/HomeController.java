package com.example.toyproject1_wst.Controller;

import com.example.toyproject1_wst.Model.Account;
import com.example.toyproject1_wst.Service.AccountService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final AccountService accountService;

    @GetMapping("/")
    public String index(HttpSession session, Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username1 = authentication.getName();  // 세션에서 데이터 가져오기 현재 로그인된 사용자의 아이디

        //Spring security는 기본적으로 anonymousUser 를 리턴해주기 때문에 th : if 조건문만으로는 거를 수 없기 때문에
        //anonymousUser 를 리턴하면 모델에 null 값을 넣어서 값을 없게 만들어 준다.
       if (authentication != null && authentication.isAuthenticated() && !"anonymousUser".equals(authentication.getName())) {
            String username = authentication.getName();  // 세션에서 데이터 가져오기 현재 로그인된 사용자의 이름 (username)
            model.addAttribute("sessionName", username);
        } else {
            model.addAttribute("sessionName", null);  // 로그인되지 않은 경우 null로 설정
        }

       log.info("현재 세션" + username1 );
        return "home";
    }

    @GetMapping("/register")
    public String signUpPage(){
        return "signup";
    }

}
