package project.nightletter.dto.postsdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@Setter
public class PostsResponseDto {

    private Long postId;

    private String username;

    private String nickName;

    private String content;

    private Boolean anonymous;

    private LocalDateTime localDateTime;

    private List<PostsResponseItem> replys;

}
