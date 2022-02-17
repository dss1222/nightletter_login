package project.nightletter.dto.replydto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ReplyResponseDto {

    private Long commentId;

    private String nickname;

    private String username;

    private String content;

    private LocalDateTime localDateTime;

    private boolean anonymous;

}
