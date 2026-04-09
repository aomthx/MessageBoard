package com.messageboard.mapper;

import com.messageboard.entity.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostMapper {

    int insert(@Param("userId") Integer userId,
               @Param("userName") String userName,
               @Param("title") String title,
               @Param("text") String text);

    List<Post> findAllWithComments();

    List<Post> findByTitleWithComments(@Param("title") String title);

    List<Post> findByUserIdWithComments(@Param("userId") Integer userId);

    int deletePost(@Param("id") Integer id);

    int deleteCommentsByPostId(@Param("postId") Integer postId);
}
