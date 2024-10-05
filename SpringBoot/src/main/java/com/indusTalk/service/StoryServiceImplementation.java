package com.indusTalk.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indusTalk.Repository.StoryRepository;
import com.indusTalk.models.Story;
import com.indusTalk.models.User;

@Service
public class StoryServiceImplementation implements StoryService {
    @Autowired
    private StoryRepository storyRepository;
    @Autowired
    private UserService userService;
    public Story createStory(Story story,User user){
        Story createdStory = new Story();

        createdStory.setCaption(story.getCaption());
        createdStory.setImage(story.getImage());
        createdStory.setUser(user);
        createdStory.setTimestamp(LocalDateTime.now());

        return storyRepository.save(createdStory);
    }
    
    public List<Story> findStoryByUserId(Integer userId)throws Exception{
        userService.findUserById(userId);
        
        return storyRepository.findByUserId(userId);
    }
    
}
