package com.messageboard.controller;

import com.messageboard.dto.ApiResponse;
import com.messageboard.entity.User;
import com.messageboard.service.CommentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/addComment")
    public ApiResponse addComment(@RequestParam Integer postId,
                                  @RequestParam String text,
                                  HttpSession session) {
        User user = (User) session.getAttribute("User");
        if (user == null) {
            return ApiResponse.fail("未登录");
        }
        return commentService.addComment(user.getId(), postId, text)
                ? ApiResponse.ok("success") : ApiResponse.fail("评论失败");
    }

    @PostMapping("/deleteComment")
    public ApiResponse deleteComment(@RequestParam Integer commentId) {
        return commentService.deleteComment(commentId)
                ? ApiResponse.ok("success") : ApiResponse.fail("删除失败");
    }
}
