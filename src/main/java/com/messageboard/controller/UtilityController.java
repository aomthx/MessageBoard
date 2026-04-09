package com.messageboard.controller;

import com.messageboard.dto.ApiResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
public class UtilityController {

    @RequestMapping(value = "/sendEmailCode", method = {RequestMethod.GET, RequestMethod.POST})
    public ApiResponse sendEmailCode(@RequestParam String email, HttpSession session) {
        String code = String.valueOf(new Random().nextInt(900000) + 100000);
        session.setAttribute("code", code);
        return ApiResponse.ok("验证码已生成（重构后端阶段暂不发送邮件）", email);
    }

    @RequestMapping(value = "/createCode", method = {RequestMethod.GET, RequestMethod.POST})
    public ApiResponse createCode(HttpSession session) {
        String code = String.valueOf(new Random().nextInt(9000) + 1000);
        session.setAttribute("codes", code);
        return ApiResponse.ok("success", code);
    }
}
