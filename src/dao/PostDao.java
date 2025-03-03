package dao;

import DBUtil.C3p0Utils;
import entity.Comment;
import entity.Post;
import org.apache.commons.dbutils.*;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.*;

public class PostDao {
    private QueryRunner queryRunner = new QueryRunner(C3p0Utils.getDataSource());
    public Integer addPost(int userId, String userName, String title, String text){
        try{
            return queryRunner.update("insert into post (user_id,user_name,title,text) value (?,?,?,?)", userId, userName, title, text);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Post> queryAllPost(){
        //重写handle
        ResultSetHandler<List<Post>> handler = rs -> {
            Map<Integer, Post> postMap = new LinkedHashMap<>();
            while (rs.next()) {
                // 解析 Post 对象
                int postId = rs.getInt("post_id");
                Post post = postMap.get(postId);
                if (post == null) {
                    post = new Post();
                    post.setId(postId);
                    post.setUserId(rs.getInt("post_userId"));
                    post.setUserName(rs.getString("post_userName"));
                    post.setTitle(rs.getString("post_title"));
                    post.setText(rs.getString("post_text"));
                    post.setTime(rs.getTimestamp("post_time"));
                    post.setComments(new ArrayList<>());
                    postMap.put(postId, post);
                }

                // 解析 Comment 对象
                int commentId = rs.getInt("comment_id");
                if (commentId > 0) { // 如果 Comment 数据不为空
                    Comment comment = new Comment();
                    comment.setId(commentId);
                    comment.setPostId(rs.getInt("comment_postId"));
                    comment.setUserId(rs.getInt("comment_userId"));
                    comment.setText(rs.getString("comment_text"));
                    comment.setTime(rs.getTimestamp("comment_time"));
                    post.getComments().add(comment);
                }
            }

            return new ArrayList<>(postMap.values());
        };
        String sql = "SELECT p.id AS post_id, p.user_id AS post_userId, p.user_name AS post_userName, p.title AS post_title, p.text AS post_text, p.time AS post_time, c.id AS comment_id, c.post_id AS comment_postId, c.user_id AS comment_userId, c.text AS comment_text, c.time AS comment_time FROM post p LEFT JOIN comment c ON p.id = c.post_id order by p.time DESC,c.time DESC";
        try {
            return queryRunner.query(sql, handler);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Post> queryPostByTitle(String title){
        //重写handle
        ResultSetHandler<List<Post>> handler = rs -> {
            Map<Integer, Post> postMap = new LinkedHashMap<>();
            while (rs.next()) {
                // 解析 Post 对象
                int postId = rs.getInt("post_id");
                Post post = postMap.get(postId);
                if (post == null) {
                    post = new Post();
                    post.setId(postId);
                    post.setUserId(rs.getInt("post_userId"));
                    post.setUserName(rs.getString("post_userName"));
                    post.setTitle(rs.getString("post_title"));
                    post.setText(rs.getString("post_text"));
                    post.setTime(rs.getTimestamp("post_time"));
                    post.setComments(new ArrayList<>());
                    postMap.put(postId, post);
                }

                // 解析 Comment 对象
                int commentId = rs.getInt("comment_id");
                if (commentId > 0) { // 如果 Comment 数据不为空
                    Comment comment = new Comment();
                    comment.setId(commentId);
                    comment.setPostId(rs.getInt("comment_postId"));
                    comment.setUserId(rs.getInt("comment_userId"));
                    comment.setText(rs.getString("comment_text"));
                    comment.setTime(rs.getTimestamp("comment_time"));
                    post.getComments().add(comment);
                }
            }

            return new ArrayList<>(postMap.values());
        };
        String sql = "SELECT p.id AS post_id, p.user_id AS post_userId, p.user_name AS post_userName, p.title AS post_title, p.text AS post_text, p.time AS post_time, c.id AS comment_id, c.post_id AS comment_postId, c.user_id AS comment_userId, c.text AS comment_text, c.time AS comment_time FROM post p LEFT JOIN comment c ON p.id = c.post_id WHERE p.title LIKE ? order by p.time DESC,c.time DESC";
        try {
            return queryRunner.query(sql, handler,"%"+title+"%");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Post> queryPostByUserId(int id){
        //重写handle
        ResultSetHandler<List<Post>> handler = rs -> {
            Map<Integer, Post> postMap = new LinkedHashMap<>();
            while (rs.next()) {
                // 解析 Post 对象
                int postId = rs.getInt("post_id");
                Post post = postMap.get(postId);
                if (post == null) {
                    post = new Post();
                    post.setId(postId);
                    post.setUserName(rs.getString("post_userName"));
                    post.setUserId(rs.getInt("post_userId"));
                    post.setTitle(rs.getString("post_title"));
                    post.setText(rs.getString("post_text"));
                    post.setTime(rs.getTimestamp("post_time"));
                    post.setComments(new ArrayList<>());
                    postMap.put(postId, post);
                }

                // 解析 Comment 对象
                int commentId = rs.getInt("comment_id");
                if (commentId > 0) { // 如果 Comment 数据不为空
                    Comment comment = new Comment();
                    comment.setId(commentId);
                    comment.setPostId(rs.getInt("comment_postId"));
                    comment.setUserId(rs.getInt("comment_userId"));
                    comment.setText(rs.getString("comment_text"));
                    comment.setTime(rs.getTimestamp("comment_time"));
                    post.getComments().add(comment);
                }
            }

            return new ArrayList<>(postMap.values());
        };
        String sql = "SELECT p.id AS post_id, p.user_id AS post_userId, p.user_name AS post_userName, p.title AS post_title, p.text AS post_text, p.time AS post_time, c.id AS comment_id, c.post_id AS comment_postId, c.user_id AS comment_userId, c.text AS comment_text, c.time AS comment_time FROM post p LEFT JOIN comment c ON p.id = c.post_id WHERE p.user_id = ? order by p.time DESC,c.time DESC";
        try {
            return queryRunner.query(sql, handler,id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

     public Integer deletePost(int id){
        try {
            return queryRunner.update("delete p,c from post as p left join comment as c on p.id = c.post_id where p.id = ?", id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
     }
}
