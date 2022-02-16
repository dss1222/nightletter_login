package project.nightletter.dto.postsdto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class MainResponseDto {

    private Long postId;

    private String username;

    private String nickName;

    private String content;

    private Boolean anonymous;

    private LocalDateTime localDateTime;

    private Integer replyCount;

}
