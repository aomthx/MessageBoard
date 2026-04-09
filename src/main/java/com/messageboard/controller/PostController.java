package com.messageboard.controller;

import com.messageboard.dto.ApiResponse;
import com.messageboard.entity.Post;
import com.messageboard.entity.User;
import com.messageboard.service.PostService;
import com.messageboard.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@RestController
public class PostController {

    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\\\.[a-zA-Z]{2,}$";

    private final PostService postService;
    private final UserService userService;

    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @RequestMapping(value = "/printMessages", method = {RequestMethod.GET, RequestMethod.POST})
    public Map<String, Object> printMessages(HttpSession session) {
        Map<String, Object> data = new HashMap<>();
        User user = (User) session.getAttribute("User");
        if (user != null) {
            data.put("username", user.getUsername());
            data.put("userId", user.getId());
        }
        data.put("posts", postService.queryAllPosts());
        return data;
    }

    @RequestMapping(value = "/searchPost", method = {RequestMethod.GET, RequestMethod.POST})
    public ApiResponse searchPost(@RequestParam String title, HttpSession session) {
        List<Post> postList = postService.searchPost(title);
        User user = (User) session.getAttribute("User");
        Map<String, Object> data = new HashMap<>();
        data.put("posts", postList);
        data.put("userId", user == null ? null : user.getId());
        return ApiResponse.ok("success", data);
    }

    @GetMapping("/homePage")
    public ApiResponse homePage(@RequestParam("key") String key) {
        List<Post> postList;
        User user;
        if (Pattern.matches(EMAIL_PATTERN, key)) {
            user = userService.findByEmail(key);
            postList = postService.showMyPosts(key);
        } else {
            Integer userId = Integer.valueOf(key);
            user = userService.findById(userId);
            postList = postService.showMyPosts(userId);
        }
        Map<String, Object> data = new HashMap<>();
        data.put("user", user);
        data.put("posts", postList);
        return ApiResponse.ok("success", data);
    }

    @PostMapping("/addPost")
    public ApiResponse addPost(@RequestParam String title,
                               @RequestParam String text,
                               HttpSession session) {
        User user = (User) session.getAttribute("User");
        if (user == null) {
            return ApiResponse.fail("未登录");
        }
        int result = postService.addPost(user.getId(), user.getUsername(), title, text);
        return result > 0 ? ApiResponse.ok("success") : ApiResponse.fail("发布失败");
    }

    @PostMapping("/deletePost")
    public ApiResponse deletePost(@RequestParam Integer postId) {
        return postService.deletePost(postId) ? ApiResponse.ok("success") : ApiResponse.fail("删除失败");
    }
}
