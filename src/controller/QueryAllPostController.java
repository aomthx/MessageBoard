package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Post;
import entity.User;
import service.PostService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "QueryAllPostController", urlPatterns = "/printMessages")
public class QueryAllPostController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");

        PostService postService = new PostService();
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String,Object> data = new HashMap<>();

        User user = (User) req.getSession().getAttribute("User");
        List<Post> postList = postService.queryAllPosts();

        if(user != null){
            data.put("username",user.getUsername());
            data.put("userId",user.getId());
        }
        data.put("posts",postList);

        String json = objectMapper.writeValueAsString(data);
        resp.getWriter().write(json);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doGet(req, resp);
    }
}
