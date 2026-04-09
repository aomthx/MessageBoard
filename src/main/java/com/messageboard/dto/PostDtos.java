package com.messageboard.dto;

public final class PostDtos {
    private PostDtos() {
    }

    public record AddPostRequest(String title, String text) {
    }

    public record SearchPostRequest(String title) {
    }

    public record HomeQuery(String key) {
    }

    public record DeletePostRequest(Integer postId) {
    }
}
