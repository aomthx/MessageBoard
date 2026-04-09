package com.messageboard.service;

import com.messageboard.mapper.CommentMapper;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private final CommentMapper commentMapper;

    public CommentService(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    public boolean addComment(Integer userId, Integer postId, String text) {
        return commentMapper.insert(userId, postId, text) > 0;
    }

    public boolean deleteComment(Integer commentId) {
        return commentMapper.deleteById(commentId) > 0;
    }
}
