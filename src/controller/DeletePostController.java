package controller;

import service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeletePostController", urlPatterns = "/deletePost")
public class DeletePostController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        int postId = Integer.parseInt(req.getParameter("postId"));
        PostService postService = new PostService();
        boolean b = postService.deletePost(postId);
        resp.getWriter().write(String.valueOf(b));
    }
}
