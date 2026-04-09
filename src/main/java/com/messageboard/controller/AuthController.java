package com.messageboard.controller;

import com.messageboard.dto.ApiResponse;
import com.messageboard.entity.User;
import com.messageboard.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ApiResponse login(@RequestParam String email,
                             @RequestParam String password,
                             @RequestParam String code,
                             HttpSession session) {
        String sessionCode = String.valueOf(session.getAttribute("codes"));
        if (code.isEmpty() || !sessionCode.equalsIgnoreCase(code)) {
            return ApiResponse.fail("验证码错误");
        }

        User user = userService.login(email, password);
        if (user == null) {
            return ApiResponse.fail("账号或密码错误");
        }
        if (user.getStatus() != null && user.getStatus() != 0) {
            return ApiResponse.fail("账号已被封禁");
        }

        session.setAttribute("User", user);
        return ApiResponse.ok("success", user);
    }

    @PostMapping("/register")
    public ApiResponse register(@RequestParam String username,
                                @RequestParam String email,
                                @RequestParam String password,
                                @RequestParam String code,
                                HttpSession session) {
        if (userService.findByEmail(email) != null) {
            return ApiResponse.fail("用户邮箱已被注册");
        }

        String sessionCode = String.valueOf(session.getAttribute("code"));
        if (code.isEmpty() || !sessionCode.equalsIgnoreCase(code)) {
            return ApiResponse.fail("验证码错误");
        }

        int result = userService.register(username, email, password);
        return result > 0 ? ApiResponse.ok("注册成功") : ApiResponse.fail("注册失败");
    }

    @PostMapping("/resetPassword")
    public ApiResponse resetPassword(@RequestParam String email,
                                     @RequestParam String password,
                                     @RequestParam String code,
                                     HttpSession session) {
        if (userService.findByEmail(email) == null) {
            return ApiResponse.fail("邮箱未注册");
        }

        String sessionCode = String.valueOf(session.getAttribute("code"));
        if (code.isEmpty() || !sessionCode.equalsIgnoreCase(code)) {
            return ApiResponse.fail("验证码错误");
        }

        int result = userService.setPassword(email, password);
        return result > 0 ? ApiResponse.ok("success") : ApiResponse.fail("重置失败");
    }

    @GetMapping("/signOut")
    public ApiResponse signOut(HttpSession session) {
        session.invalidate();
        return ApiResponse.ok("success");
    }
}
