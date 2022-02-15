package project.nightletter.dto.postsdto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PostsResponseItem {

    private Long commentId;

    private String nickname;

    private String comment;

    private LocalDateTime localDateTime;

    private boolean anonymous;

}
