package kr.re.kitri.devopsdemoapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "젠킨스파일 파일로 저장 테스트";
    }

    @GetMapping("/about")
    public String about() {
        return "두시 15분에 커밋한 내용";
    }
}
