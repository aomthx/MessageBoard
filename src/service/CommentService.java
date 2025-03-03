package service;

import dao.CommentDAO;

public class CommentService {
    CommentDAO commentDAO = new CommentDAO();
    public boolean addComment(int userId, int postId, String text) {
        int i = commentDAO.addComment(userId, postId, text);
        return i > 0;
    }
    public boolean deleteComment(int commentId){
        int i = commentDAO.deleteComment(commentId);
        return i > 0;
    }
}
