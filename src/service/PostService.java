package service;

import dao.PostDao;
import dao.UserDao;
import entity.Post;
import entity.User;

import java.util.List;


public class PostService {
    private PostDao  postDao = new PostDao();
    private UserDao userDao = new UserDao();
    public List<Post> queryAllPosts(){
        List<Post> postList = postDao.queryAllPost();
        return postList;
    }
    public List<Post> searchPost(String title){
        List<Post> postList = postDao.queryPostByTitle(title);
        return postList;
    }
    public List<Post> showMyPosts(int id){
        List<Post> postList = postDao.queryPostByUserId(id);
        return postList;
    }
    public List<Post> showMyPosts(String email){
        User user = userDao.queryUserByEmail(email);
        int id = user.getId();
        List<Post> postList = postDao.queryPostByUserId(id);
        return postList;
    }
    public Integer addPost(int userId, String userName, String title, String text){
        return postDao.addPost(userId, userName, title, text);
    }
    public boolean deletePost(int postId){
        int i = postDao.deletePost(postId);
        return i > 0;
    }
}
