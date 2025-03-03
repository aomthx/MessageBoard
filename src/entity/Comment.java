package entity;

import java.util.Date;

public class Comment {
    private int id;
    private int userId;
    private int postId;
    private String text;
    private Date time;

    public Comment (){

    }

    public Comment(int id, int userId, int postId, String text, Date time) {
        this.id = id;
        this.userId = userId;
        this.postId = postId;
        this.text = text;
        this.time = time;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getTime() {
        return time;
    }

}
