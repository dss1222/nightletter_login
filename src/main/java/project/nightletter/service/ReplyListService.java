package project.nightletter.service;

import org.springframework.stereotype.Service;
import project.nightletter.dto.postsdto.PostsResponseItem;
import project.nightletter.model.Posts;
import project.nightletter.model.Reply;
import project.nightletter.repository.ReplyRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReplyListService {

    private final ReplyRepository replyRepository;

    public ReplyListService(ReplyRepository replyRepository) {
        this.replyRepository = replyRepository;
    }

    public List<PostsResponseItem> getPostsResponseItemList(Posts posts) {

        List<Reply> replys = replyRepository.findAllByPosts(posts);

        List<PostsResponseItem> postsResponseItemList = new ArrayList<>();
        for(Reply reply : replys) {
            PostsResponseItem postsResponseItem = new PostsResponseItem(
                    reply.getId(),
                    reply.getUser().getNickname(),
                    reply.getUser().getUsername(),
                    reply.getComment(),
                    reply.getCreatedAt(),
                    reply.isAnonymous()
            );
            postsResponseItemList.add(postsResponseItem);
        }
        return postsResponseItemList;
    }

}
