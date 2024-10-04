package com.example.toyproject1_wst.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class BoardController {

    @GetMapping("/board") //게시판 컨트롤러
    public String boardPage (){
        return "board";
    }

}
