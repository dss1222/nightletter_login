package project.nightletter.dto.postsdto;

import lombok.Data;

@Data
public class PostsRequestDto {

    private String content;

    private boolean anonymous;

}
