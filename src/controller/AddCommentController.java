package controller;

import entity.User;
import service.CommentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddCommentController", urlPatterns = "/addComment")
public class AddCommentController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        int postId = Integer.parseInt(req.getParameter("postId"));
        String text = req.getParameter("text");
        User user = (User) req.getSession().getAttribute("User");
        int userId = user.getId();

        CommentService commentService = new CommentService();
        boolean i = commentService.addComment(userId,postId,text);
        if(i){
            resp.getWriter().write("success");
        }else{
            resp.getWriter().write("评论失败");
        }
    }
}
