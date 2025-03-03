package controller;

import service.CommentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteCommentController", urlPatterns = "/deleteComment")
public class DeleteCommentController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        CommentService commentService = new CommentService();
        int id = Integer.parseInt(req.getParameter("commentId"));
        boolean b = commentService.deleteComment(id);
        if (b){
            resp.getWriter().write("success");
        }
    }
}
