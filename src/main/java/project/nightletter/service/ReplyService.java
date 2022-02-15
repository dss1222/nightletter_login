package project.nightletter.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.nightletter.dto.replydto.ReplyRequestDto;
import project.nightletter.dto.replydto.ReplyResponseDto;
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
    public ReplyResponseDto createReply(Long PostId, ReplyRequestDto replyRequestDto, User user) {

        ReplyResponseDto result = new ReplyResponseDto();

        try {
            Posts posts = postsRepository.findById(PostId).orElseGet(null);
            if (posts == null) {
                result.setResult(false); //게시물 없음을 의미
                return result;
            }

            Reply reply = new Reply(replyRequestDto,user,posts);
            replyRepository.save(reply);
            //Reply reply = replyRequestDto.
            // replyRepository.save(reply);

        } catch (Exception e) {
            result.setResult(false);


        }

        return result;


    }
    @Transactional
    public void update(Long id, ReplyRequestDto replyRequestDto) {
        Reply Reply = replyRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("댓글이 존재하지 않습니다.")
        );
        Reply.update(replyRequestDto);
    }

}