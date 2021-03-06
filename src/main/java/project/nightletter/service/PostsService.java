package project.nightletter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.nightletter.dto.postsdto.MainResponseDto;
import project.nightletter.dto.postsdto.PostsRequestDto;
import project.nightletter.dto.postsdto.PostsResponseDto;
import project.nightletter.model.Posts;
import project.nightletter.model.Reply;
import project.nightletter.model.User;
import project.nightletter.repository.PostsRepository;
import project.nightletter.repository.ReplyRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostsService {
    private final PostsRepository postsRepository;
    private final ReplyRepository replyRepository;

    @Autowired
    public PostsService(PostsRepository postsRepository, ReplyRepository replyRepository) {
        this.postsRepository = postsRepository;
        this.replyRepository = replyRepository;
    }

    public Long writeLetter(PostsRequestDto requestDto, User user) {

        Posts posts = new Posts(requestDto, user);

        return postsRepository.save(posts).getId();
    }

    @Transactional
    public void editLetter(PostsRequestDto requestDto, Long postId) {
//        Posts posts = new Posts(requestDto, user);

        Posts posts = postsRepository.findById(postId).orElseThrow(
                () -> new NullPointerException("해당 게시물이 존재하지 않습니다.")
        );
        posts.update(requestDto.getContent(), requestDto.isAnonymous(), posts.getUser());
    }

    public boolean deleteLetter(Long postId) {
        Posts posts = postsRepository.findById(postId).orElseThrow(
                () -> new NullPointerException("해당 게시물이 존재하지 않습니다.")
        );
        List<Reply> replys = replyRepository.findAllByPosts(posts);
        for (Reply reply : replys) {
            replyRepository.deleteById(reply.getId());
        }
        postsRepository.deleteById(postId);
        return true;
    }

    public PostsResponseDto getdetails(Long postId) {
        Posts posts = postsRepository.findById(postId).orElseThrow(
                () -> new NullPointerException("해당 게시물이 존재하지 않습니다.")
        );

        return new PostsResponseDto(
                postId,
                posts.getUser().getUsername(),
                posts.getUser().getNickname(),
                posts.getContent(),
                posts.isAnonymous(),
                posts.getCreatedAt()
        );
    }

    public List<MainResponseDto> getAllLetter() {

        LocalDateTime start = LocalDateTime.now().minusDays(1);
        LocalDateTime end = LocalDateTime.now();

        List<Posts> postsList = postsRepository.findAllByUpdatedAtBetweenOrderByUpdatedAtDesc(start, end);
        List<MainResponseDto> mainResponseDtoList = new ArrayList<>();
        for (Posts posts : postsList) {

            MainResponseDto mainResponseDto = new MainResponseDto(
                    posts.getId(),
                    posts.getUser().getUsername(),
                    posts.getUser().getNickname(),
                    posts.getContent(),
                    posts.isAnonymous(),
                    posts.getCreatedAt(),
                    posts.getReply().size()

            );
            mainResponseDtoList.add(mainResponseDto);
        }

        return mainResponseDtoList;
    }

}