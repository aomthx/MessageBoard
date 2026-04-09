package com.messageboard.controller;

import com.messageboard.dto.ApiResponse;
import com.messageboard.service.AdminService;
import com.messageboard.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    private final AdminService adminService;
    private final UserService userService;

    public AdminController(AdminService adminService, UserService userService) {
        this.adminService = adminService;
        this.userService = userService;
    }

    @PostMapping("/ad_login")
    public ApiResponse adminLogin(@RequestParam String user,
                                  @RequestParam String password,
                                  @RequestParam String code,
                                  jakarta.servlet.http.HttpSession session) {
        String sessionCode = String.valueOf(session.getAttribute("codes"));
        if (code.isEmpty() || !sessionCode.equalsIgnoreCase(code)) {
            return ApiResponse.fail("验证码错误");
        }
        return adminService.login(user, password)
                ? ApiResponse.ok("success") : ApiResponse.fail("管理员账号或密码错误");
    }

    @PostMapping("/banUser")
    public ApiResponse banUser(@RequestParam Integer id, @RequestParam Integer status) {
        return userService.setStatus(id, status)
                ? ApiResponse.ok("success") : ApiResponse.fail("更新失败");
    }
}
