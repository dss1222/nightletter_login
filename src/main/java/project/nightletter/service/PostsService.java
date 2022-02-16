package project.nightletter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.nightletter.dto.postsdto.MainResponseDto;
import project.nightletter.dto.postsdto.PostsRequestDto;
import project.nightletter.dto.postsdto.PostsResponseDto;
import project.nightletter.dto.postsdto.PostsResponseItem;
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


    //편지(게시물) 작성
    public boolean writeLetter(PostsRequestDto requestDto, User user) {


        //DTO를 통해 GET으로 입력 받고 post로 넘김
        Posts posts = new Posts(requestDto, user);

        //postsRepository에 저장
        postsRepository.save(posts);

        return true;
    }

    //편지(게시물) 수정
    @Transactional
    public boolean editLetter(PostsRequestDto requestDto, Long postId) {
        //Posts posts = new Posts(requestDto, user);

        //postId가 만약 postsRepository에 있다면 throw new IllegalArgumentException처리
        Posts posts = postsRepository.findById(postId).orElseThrow(
                ()-> new NullPointerException("해당 게시물이 존재하지 않습니다.")
        );


        //입력 받고 저장
        posts.setContent(requestDto.getContent());
        posts.setAnonymous(requestDto.isAnonymous());
        posts.setUser(posts.getUser());
        posts.setReply(posts.getReply());
        return true;
    }


    //편지(게시물) 삭제
    public boolean deleteLetter(Long postId) {
        //삭제
        postsRepository.deleteById(postId);
        return true;
    }

    //편지(게시물) 상세 페이지로 이동하고 게시물과 댓글 같이 나오게 함
    public PostsResponseDto getdetails(Long postId) {

        //postId가 만약 postsRepository에 있다면 throw new IllegalArgumentException처리
        Posts posts = postsRepository.findById(postId).orElseThrow(
                ()-> new NullPointerException("해당 post가 존재하지 않습니다.")
        );


        //username,nickName 가져옴
        String nickName = posts.getUser().getNickname();
        String username = posts.getUser().getUsername();


        //게시물과 같이 나오게 할 댓글을 리스트로 만들고 반복문을 통해서 해당 내용을 가져옴
        List<Reply> replys = replyRepository.findAllByPosts(posts);
        List<PostsResponseItem> postsResponseItemList = new ArrayList<>();
        for(Reply reply : replys) {
            PostsResponseItem postsResponseItem = new PostsResponseItem(
                    reply.getId(),
                    nickName,
                    username,
                    reply.getComment(),
                    reply.getCreatedAt(),
                    reply.isAnonymous()
            );

            //그리고 추가
            postsResponseItemList.add(postsResponseItem);
        }

        return new PostsResponseDto( //post 내용과 같이 넣어서 리턴
                postId,
                posts.getUser().getUsername(),
                posts.getUser().getNickname(),
                posts.getContent(),
                posts.isAnonymous(),
                posts.getCreatedAt(),
                postsResponseItemList
        );
    }

    public List<MainResponseDto> getAllLetter() { //메인 페이지 편지 모두 조회

        LocalDateTime start = LocalDateTime.now().minusDays(1);
        LocalDateTime end = LocalDateTime.now();

        //localdatetime 순서로 정렬시킴
        List<Posts> postsList = postsRepository.findAllByUpdatedAtBetweenOrderByUpdatedAtDesc(start, end);
        List<MainResponseDto> mainResponseDtoList= new ArrayList<>();


        //반복문을 통해서 해당 내용들 다 가져오고 추가시킨 다음에 리턴.
        for(Posts posts : postsList) {
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