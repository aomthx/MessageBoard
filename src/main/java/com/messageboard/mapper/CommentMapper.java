package com.messageboard.mapper;

import com.messageboard.entity.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Insert("insert into comment (user_id, post_id, text) values (#{userId}, #{postId}, #{text})")
    int insert(@Param("userId") Integer userId,
               @Param("postId") Integer postId,
               @Param("text") String text);

    @Delete("delete from comment where id = #{commentId}")
    int deleteById(@Param("commentId") Integer commentId);

    @Select("select id, user_id as userId, post_id as postId, text, time from comment where post_id = #{postId} order by time desc")
    List<Comment> findByPostId(@Param("postId") Integer postId);
}
