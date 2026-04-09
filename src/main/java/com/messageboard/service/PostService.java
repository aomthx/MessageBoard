package com.messageboard.service;

import com.messageboard.entity.Post;
import com.messageboard.entity.User;
import com.messageboard.mapper.PostMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostService {
    private final PostMapper postMapper;
    private final UserService userService;

    public PostService(PostMapper postMapper, UserService userService) {
        this.postMapper = postMapper;
        this.userService = userService;
    }

    public List<Post> queryAllPosts() {
        return postMapper.findAllWithComments();
    }

    public List<Post> searchPost(String title) {
        return postMapper.findByTitleWithComments(title);
    }

    public List<Post> showMyPosts(Integer userId) {
        return postMapper.findByUserIdWithComments(userId);
    }

    public List<Post> showMyPosts(String email) {
        User user = userService.findByEmail(email);
        if (user == null) {
            return List.of();
        }
        return postMapper.findByUserIdWithComments(user.getId());
    }

    public int addPost(Integer userId, String userName, String title, String text) {
        return postMapper.insert(userId, userName, title, text);
    }

    @Transactional
    public boolean deletePost(Integer postId) {
        postMapper.deleteCommentsByPostId(postId);
        return postMapper.deletePost(postId) > 0;
    }
}
