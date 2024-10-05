package com.indusTalk.service;

import java.util.List;

import com.indusTalk.models.Story;
import com.indusTalk.models.User;

public interface StoryService {
    
    public Story createStory(Story story,User user);
    
    public List<Story> findStoryByUserId(Integer userId)throws Exception;
}
