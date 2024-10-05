package com.indusTalk.service;

import java.util.*;

import com.indusTalk.models.Post;

public interface PostService {
    Post createNewPost(Post post,Integer userId)throws Exception;

    String deletePost(Integer postid,Integer usrid)throws Exception;
    
    List<Post> findPostByUserId(Integer userId);
    
    Post findPostById(Integer postId)throws Exception;
    
    List<Post> findAllPost();

    Post savedPost(Integer postId,Integer userId)throws Exception;

    Post likedPost(Integer postId,Integer userId)throws Exception;

}
