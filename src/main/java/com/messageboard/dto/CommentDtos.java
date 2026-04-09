package com.messageboard.dto;

public final class CommentDtos {
    private CommentDtos() {
    }

    public record AddCommentRequest(Integer postId, String text) {
    }

    public record DeleteCommentRequest(Integer commentId) {
    }
}
