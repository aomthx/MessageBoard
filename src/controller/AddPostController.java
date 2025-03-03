package controller;

import entity.User;
import service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddPostController", urlPatterns = "/addPost")
public class AddPostController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        String title = req.getParameter("title");
        String text = req.getParameter("text");
        User user = (User) req.getSession().getAttribute("User");
        PostService postService = new PostService();
        int i = postService.addPost(user.getId(), user.getUsername(), title, text);
        if(i > 0)
            resp.getWriter().write("success");
    }
}
