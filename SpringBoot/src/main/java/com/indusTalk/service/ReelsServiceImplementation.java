package com.indusTalk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indusTalk.Repository.ReelsRepository;
import com.indusTalk.models.Reels;
import com.indusTalk.models.User;

@Service
public class ReelsServiceImplementation implements ReelsService {
    @Autowired
    private ReelsRepository reelsRepository;
    @Autowired
    private UserService userService;
    public Reels createReel(Reels reel,User user)throws Exception
    {
        Reels createReel = new Reels();
        createReel.setTitle(reel.getTitle());
        createReel.setUser(user);
        createReel.setVideo(reel.getVideo());
        return reelsRepository.save(createReel);
    }
    
    public List<Reels> findAllReels()throws Exception{
        return reelsRepository.findAll();
    }
    
    public List<Reels> findUsersReels(Integer userId)throws Exception{
        userService.findUserById(userId);
        return reelsRepository.findByUserId(userId);
    }

    
}