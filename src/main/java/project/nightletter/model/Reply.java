package project.nightletter.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.nightletter.dto.replydto.ReplyRequestDto;

import javax.persistence.*;

@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
public class Reply extends Timestamped {

    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;  //댓글 아이디값

    // 반드시 값을 가지도록 합니다.
    @Column(nullable = false)
    private String comment;

    // 반드시 값을 가지도록 합니다.
    @Column(nullable = false)
    private boolean anonymous;

    @JoinColumn(name = "UserId")  //User 테이블 참조를 위한 외래키 설정
    @ManyToOne
    private User user;

    @JsonManagedReference
    @JoinColumn(name = "PostId") //Post 테이블 참조를 위한 외래키 설정
    @ManyToOne
    private Posts posts;





    public Reply(ReplyRequestDto replyRequestDto, User user , Posts posts){
        this.comment = replyRequestDto.getComment();
        this.anonymous = replyRequestDto.isAnonymous();
        this.user = user;
        this.posts = posts;
    }

    public void update(ReplyRequestDto replyRequestDto) {

        this.comment = replyRequestDto.getComment();
    }

}
