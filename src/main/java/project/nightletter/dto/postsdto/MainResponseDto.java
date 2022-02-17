package project.nightletter.dto.postsdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import project.nightletter.model.Reply;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Data
public class MainResponseDto {

    private Long postId;

    private String username;

    private String nickname;

    private String content;

    private Boolean anonymous;

    private LocalDateTime localDateTime;

    private Integer replyCount;

}
