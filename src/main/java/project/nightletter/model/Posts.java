package project.nightletter.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.nightletter.dto.postsdto.PostsRequestDto;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
public class Posts extends Timestamped {

    //모델 post(게시글)

    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;  //post 아이디값

    // 반드시 값을 가지도록 합니다.
    @Column(nullable = false)
    private String content; //post content 글 내용 칼럼

    // 반드시 값을 가지도록 합니다.
    @Column(nullable = false)
    private boolean anonymous; //유저 익명성 여부를 확인하기 위한 칼럼

    @JoinColumn(name = "username_id")
    @ManyToOne
    private User user;  //외래키를 이용해서 유저 테이블 참조

    @JsonBackReference
    @OneToMany(mappedBy = "posts")
    private List<Reply> reply; //외래키를 이용해서 유저 테이블 참조 양방향으로 맺었기 때문에 mappedBy로 설정해준다.

    public Posts(PostsRequestDto requestDto, User user) {
        this.content = requestDto.getContent();
        this.anonymous = requestDto.isAnonymous();
        this.user = user;
    }

}
