package project.nightletter.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import project.nightletter.dto.replydto.ReplyRequestDto;
import project.nightletter.model.User;
import project.nightletter.security.UserDetailsImpl;
import project.nightletter.service.ReplyService;

@RestController
public class ReplyController {

    private final ReplyService replyService;

    public ReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }


    // 댓글 작성
    // 게시글 id 로 댓글 작성
    @PostMapping("/api/reply/{postId}")
    public void createReply(@PathVariable Long postId, @RequestBody ReplyRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) throws IllegalAccessException {

       User user = userDetails.getUser();
       replyService.createReply(postId, requestDto, user);
    }



    //댓글 삭제
    @DeleteMapping("/api/reply/{replyId}")
    public void deleteReply(@PathVariable Long replyId) {
        replyService.deleteReply(replyId);
    }

}
