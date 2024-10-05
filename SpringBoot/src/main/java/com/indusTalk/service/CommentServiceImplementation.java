package com.indusTalk.service;

import java.lang.StackWalker.Option;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indusTalk.Repository.CommentRepository;
import com.indusTalk.Repository.PostRepository;
import com.indusTalk.models.Comment;
import com.indusTalk.models.Post;
import com.indusTalk.models.User;

@Service
public class CommentServiceImplementation implements CommentService{

    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;
    public Comment createComment(Comment comment,Integer postId,Integer userId)throws Exception{
        User user = userService.findUserById(userId);
        Post post = postService.findPostById(postId);
        Comment createComment = new Comment();
        createComment.setUser(user);
        createComment.setContent(comment.getContent());
        createComment.setCreatedAt(LocalDateTime.now());

        Comment savedComment = commentRepository.save(createComment);
        
        post.getComments().add(savedComment);

        postRepository.save(post);

        return savedComment;
    }

    public Comment findCommentById(Integer commentId)throws Exception{
        Optional<Comment> opt = commentRepository.findById(commentId);
        if(opt.isEmpty()){
        throw new Exception("Comment doesn't exist");
        }

        return opt.get();
    }
    
    public Comment likeComment(Integer commentId,Integer userId)throws Exception{
        Comment comment = findCommentById(commentId);
        User user = userService.findUserById(userId);

        if(!comment.getLiked().contains(user)){
            comment.getLiked().add(user);
        }
        else
            comment.getLiked().remove(user);

        return commentRepository.save(comment);
    }
}
