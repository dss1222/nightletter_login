package project.nightletter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import project.nightletter.dto.postsdto.MainResponseDto;
import project.nightletter.service.PostsService;

import java.util.List;

@RestController
public class HomeController {

    private final PostsService postsService;

    @Autowired
    public HomeController(PostsService postsService) {

        this.postsService = postsService;
    }

    @GetMapping("/api/mains") //메인으로 넘어감
    public List<MainResponseDto> letterList() {

        return postsService.getAllLetter();
    }

    // security 가 전달하는 userDetailsImpl 이다.
    @GetMapping("/")
    public String home() {
        return "index";
    }
}