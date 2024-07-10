package kr.re.kitri.devopsdemoapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "안녕하세요";
    }

    @GetMapping("/about")
    public String about() {
        return "두시 정각에 커밋한 내용";
    }
}
