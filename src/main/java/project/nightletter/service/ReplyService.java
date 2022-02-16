package project.nightletter.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.nightletter.dto.replydto.ReplyRequestDto;
import project.nightletter.model.Posts;
import project.nightletter.model.Reply;
import project.nightletter.model.User;
import project.nightletter.repository.PostsRepository;
import project.nightletter.repository.ReplyRepository;
import project.nightletter.repository.UserRepository;

import javax.transaction.Transactional;

@RequiredArgsConstructor //롬북을 통해서 간단하게 생성자 주입 방식의 어노테이션으로 fjnal이 붙거나 @notNull이 붙은 생성자들을 자동 생성해준다.
@Service
public class ReplyService {


    private final UserRepository userRepository;
    private final ReplyRepository replyRepository;
    private final PostsRepository postsRepository;

    // 댓글 작성
    @Transactional
    public void createReply(Long PostId, ReplyRequestDto replyRequestDto, User user) throws IllegalAccessException {

        try { //아이디를 조회
            Posts posts = postsRepository.findById(PostId).orElseGet(null);
            //찾은 아이디와 함께 replyRequestDto.getComment(), replyRequestDto.isAnonymous() 입력해주고 reply에 넣어준다
            Reply reply = new Reply(replyRequestDto,user,posts);
            replyRepository.save(reply); //그리고 Reply Repository 에 저장
            //Reply reply = replyRequestDto.
            // replyRepository.save(reply);

        } catch (Exception e) {//없다면 예외 처리
            throw new IllegalAccessException("게시물이 존재하지 않습니다.");


        }


    }

    //댓글 삭제
    @Transactional
    public void deleteReply(Long id) {
            //Reply id를 조회
        Reply reply = replyRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("댓글이 존재하지 않습니다.")
        );

        //그리고 삭제
        replyRepository.delete(reply);
    }

}