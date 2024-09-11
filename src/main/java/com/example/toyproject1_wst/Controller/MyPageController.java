package com.example.toyproject1_wst.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MyPageController {  //회원만?

    @GetMapping("/myPage")  //나중엔 계정 코드도 같이 뜨도록 일단은 자격이 있으면 이동할수 있게끔하자
    public String myPageOn(){
        return "MyPage";
    }
}
