package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Post;
import entity.User;
import service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "searchPostController", urlPatterns = "/searchPost")
public class SearchPostController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json");


        PostService postService = new PostService();
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String,Object> data = new HashMap<>();
        List<Post> postList;
        String title = req.getParameter("title");
        User user = (User) req.getSession().getAttribute("User");

        if(!title.isEmpty()) {
            postList = postService.searchPost(title);
            data.put("posts",postList);
            data.put("userId",user.getId());
            String json = objectMapper.writeValueAsString(data);
            resp.getWriter().write(json);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
