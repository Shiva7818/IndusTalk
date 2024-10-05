package com.indusTalk.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.indusTalk.models.Comment;
import com.indusTalk.models.User;
import com.indusTalk.service.CommentService;
import com.indusTalk.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;


@RestController
public class CommentController {
    @Autowired
    UserService userService;
    @Autowired
    private CommentService commentService;
    @PostMapping("api/comment/post/{postId}")
    public Comment createComment(@RequestHeader("Authorization")String jwt,@RequestBody Comment content,@PathVariable Integer postId)throws Exception {
        User user = userService.findUserByJwt(jwt);
        Comment createdComment = commentService.createComment(content, postId,user.getId());
        return createdComment;
    }
    @GetMapping("api/comment/find/{commentId}")
    public Comment findComment(@PathVariable Integer commentId)throws Exception {
        Comment foundComment = commentService.findCommentById(commentId);
        return foundComment;
    }
    @PutMapping("api/comment/like/{commentId}")
    public Comment likeComment(@RequestHeader("Authorization")String jwt,@PathVariable Integer commentId)throws Exception {
        User user = userService.findUserByJwt(jwt);
        Comment likeComment = commentService.likeComment(commentId,user.getId());
        return likeComment;
    }
    
}
