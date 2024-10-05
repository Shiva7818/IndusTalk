package com.indusTalk.service;

import java.util.*;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indusTalk.Repository.PostRepository;
import com.indusTalk.Repository.UserRepository;
import com.indusTalk.models.Post;
import com.indusTalk.models.User;

@Service
public class PostServiceImplementation implements PostService{
    @Autowired
    UserService userService;
    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;

    public Post createNewPost(Post post,Integer userId)throws Exception{
      
		User user = userService.findUserById(userId);
		

		Post newPost =new Post();
		newPost.setUser(user);
		newPost.setCaption(post.getCaption());
		newPost.setCreatedAt(LocalDateTime.now());
		newPost.setImage(post.getImage());
		newPost.setVideo(post.getVideo());
			
		
		return postRepository.save(newPost);
    }

    public String deletePost(Integer postId,Integer userId)throws Exception{
        Post post =findPostById(postId);
		
		User user=userService.findUserById(userId);
		System.out.println(post.getUser().getId()+" ------ "+user.getId());
		if(post.getUser().getId()==user.getId()) {
			System.out.println("inside delete");
			postRepository.deleteById(postId);
		
		return "Post Deleted Successfully";
		}
	
		
		throw new Exception("You Dont have access to delete this post");
		
    }
    
    public List<Post> findPostByUserId(Integer userId){
        return postRepository.findPostByUserId(userId);
    }
    
    public Post findPostById(Integer postId)throws Exception{
        Optional<Post> opt = postRepository.findById(postId);
		if(opt.isPresent()) {
			return opt.get();
		}
		throw new Exception("Post not exist with id: "+postId);
    }
    
    public List<Post> findAllPost(){
        return postRepository.findAll();
    }

    public Post savedPost(Integer postId,Integer userId)throws Exception{
      User user = userService.findUserById(userId);
      Post post = findPostById(postId);
      if(user.getSavedPost().contains(post))
      user.getSavedPost().remove(post);
      else
      user.getSavedPost().add(post);
      userRepository.save(user);
      return post;
    }

    public Post likedPost(Integer postId,Integer userId)throws Exception{
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);
        if(post.getLiked().contains(user))
        post.getLiked().remove(user);
        else
        post.getLiked().add(user);

        return postRepository.save(post);
    }

  
    
}
