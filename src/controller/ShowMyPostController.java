package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Post;
import entity.User;
import service.PostService;
import service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@WebServlet(name = "ShowMyPostController", urlPatterns = "/homePage")
public class ShowMyPostController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");

        PostService postService = new PostService();
        ObjectMapper objectMapper = new ObjectMapper();
        UserService userService = new UserService();
        Map<String,Object> data = new HashMap<>();

        User user;
        String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        String param = req.getParameter("key");
        List<Post> postList;
        if(!Pattern.matches(emailPattern, param)){
            System.out.println("id"+param);
            postList = postService.showMyPosts(Integer.parseInt(param));
            user = userService.queryUserById(Integer.parseInt(param));
        }else{
            System.out.println("email"+param);
            postList = postService.showMyPosts(param);
            user = userService.queryUserByEmail(param);
        }

        data.put("user",user);
        data.put("posts",postList);
        String json = objectMapper.writeValueAsString(data);
        resp.getWriter().write(json);
    }

}
