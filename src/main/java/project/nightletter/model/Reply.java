package project.nightletter.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import project.nightletter.dto.replydto.ReplyRequestDto;

import javax.persistence.*;

@Setter
@Getter
@Entity // DB 테이블 역할을 합니다.
public class Reply extends Timestamped {

    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    // 반드시 값을 가지도록 합니다.
    @Column(nullable = false, columnDefinition = "TEXT")
    private String comment;

    @Column(nullable = false)
    private boolean anonymous;

    @JoinColumn(name = "UserId")
    @ManyToOne
    private User user;

    @JsonManagedReference
    @JoinColumn(name = "PostId")
    @ManyToOne
    private Posts posts;


    public Reply(){

    }


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
