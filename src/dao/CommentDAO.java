package dao;

import DBUtil.C3p0Utils;
import entity.Comment;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;
import java.util.List;

public class CommentDAO {
    private QueryRunner queryRunner = new QueryRunner(C3p0Utils.getDataSource());
    public Integer addComment(int userId, int postId, String text) {
        String sql = "insert into comment (user_id, post_id, text) values (?,?,?)";
        try{
            return queryRunner.update(sql,userId,postId,text);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Integer deleteComment(int commentId) {
        String sql = "delete from comment where id = ?";
        try{
            return queryRunner.update(sql,commentId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
