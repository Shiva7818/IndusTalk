package com.indusTalk.Controller;

import java.util.*;

import com.indusTalk.service.PostService;
import com.indusTalk.service.UserService;
import com.indusTalk.models.Post;
import com.indusTalk.models.User;
import com.indusTalk.response.ApiResponse;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {
  @Autowired
  PostService postService;
  @Autowired
  UserService userService;
  @PostMapping("api/post")
  public ResponseEntity<Post> createPost(@RequestHeader("Authorization")String jwt,@RequestBody Post post)throws Exception
  {
    User adminUser = userService.findUserByJwt(jwt);
   Post createdPost = postService.createNewPost(post, adminUser.getId());

   return new ResponseEntity<>(createdPost,HttpStatus.ACCEPTED);
  }
  @DeleteMapping("post/{postId}")
  public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId,@RequestHeader("Authorization")String jwt)throws Exception{
    User adminUser = userService.findUserByJwt(jwt);
    String message = postService.deletePost(postId, adminUser.getId());
    ApiResponse res = new ApiResponse(message,true);

    return new ResponseEntity<>(res,HttpStatus.OK);
  }

  @GetMapping("post/byPostId/{postId}")
  public ResponseEntity<Post> findPostByIdHandler(@PathVariable Integer postId)throws Exception
  {
    Post post = postService.findPostById(postId);
    return new ResponseEntity<>(post,HttpStatus.OK);
  }

  @GetMapping("post/byUserId/{userId}")
  public ResponseEntity<List<Post>> findPostByUserIdHandler(@PathVariable Integer userId)throws Exception
  {
   List<Post> posts = postService.findPostByUserId(userId);

   return new ResponseEntity<>(posts,HttpStatus.OK);
  }

  @GetMapping("allPosts")
  public ResponseEntity<List<Post>> findAllPost()throws Exception
  {
   List<Post> posts = postService.findAllPost();

   return new ResponseEntity<List<Post>>(posts,HttpStatus.OK);
  }

  @PutMapping("post/save/{postId}")
  public ResponseEntity<Post> savedPost(@PathVariable Integer postId,@RequestHeader("Authorization")String jwt)throws Exception
  {
    User adminUser = userService.findUserByJwt(jwt);
    Post savedPost = postService.savedPost(postId,adminUser.getId());
    return new ResponseEntity<Post>(savedPost,HttpStatus.OK);
  }

  @PutMapping("post/like/{postId}")
  public ResponseEntity<Post> likedPost(@PathVariable Integer postId,@RequestHeader("Authorization")String jwt)throws Exception
  {
    User adminUser = userService.findUserByJwt(jwt);
    Post post = postService.likedPost(postId,adminUser.getId());

    return new ResponseEntity<Post>(post,HttpStatus.OK);
  }
  

}
