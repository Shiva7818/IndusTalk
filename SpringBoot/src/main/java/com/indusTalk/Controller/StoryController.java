package com.indusTalk.Controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.indusTalk.models.Story;
import com.indusTalk.models.User;
import com.indusTalk.service.StoryService;
import com.indusTalk.service.UserService;

@RestController
public class StoryController {

    @Autowired
    private StoryService storyService;
    @Autowired
    private UserService userService;

    @PostMapping("/api/story")
    public Story createStory(@RequestBody Story story,@RequestHeader("Authorization") String jwt){
    
        User reqUser = userService.findUserByJwt(jwt);
        return storyService.createStory(story, reqUser);
    }
    
    @GetMapping("/api/story/user/{userid}")
    public List<Story> findStoryByUserId(@PathVariable Integer userId,@RequestHeader("Authorization") String jwt)throws Exception{
         return storyService.findStoryByUserId(userId);
    }
}
