package com.indusTalk.service;

import com.indusTalk.models.Comment;

public interface CommentService {
    public Comment createComment(Comment comment,Integer postId,Integer uerid)throws Exception;
    public Comment findCommentById(Integer commentId)throws Exception;
    public Comment likeComment(Integer commentId,Integer userId)throws Exception; 
}
