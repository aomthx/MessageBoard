package com.messageboard.dto;

public final class AuthDtos {
    private AuthDtos() {
    }

    public record LoginRequest(String email, String password, String code) {
    }

    public record RegisterRequest(String username, String email, String password, String code) {
    }

    public record ResetPasswordRequest(String email, String password, String code) {
    }

    public record AdminLoginRequest(String user, String password, String code) {
    }
}
