package project.nightletter.controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import project.nightletter.dto.postsdto.PostsRequestDto;
import project.nightletter.dto.postsdto.PostsResponseDto;
import project.nightletter.model.Posts;
import project.nightletter.security.UserDetailsImpl;
import project.nightletter.service.PostsService;

@RestController
public class PostsController {

    private final PostsService postsService;

    public PostsController(PostsService postsService) {
        this.postsService = postsService;
    }

    //편지 작성.
    @PostMapping("/api/posts")
    public Boolean writeLetter(@RequestBody PostsRequestDto requestDto,
                               @AuthenticationPrincipal UserDetailsImpl userDetails) {
        System.out.println(requestDto.getContent());
        return postsService.writeLetter(requestDto, userDetails.getUser());

    }

    @GetMapping("/api/posts/{postId}")
    public PostsResponseDto getDetails(@PathVariable Long postId) {
        return postsService.getdetails(postId);
    }

    @PutMapping("/api/posts/{postId}")
    public boolean editLetter(@PathVariable Long postId,
                              PostsRequestDto requestDto) {
        return postsService.editLetter(requestDto,postId);
    }

    @DeleteMapping("/api/posts/{postId}")
    public boolean deleteLetter(@PathVariable Long postId) {
        return postsService.deleteLetter(postId);
    }
}
