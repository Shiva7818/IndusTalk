package com.indusTalk.service;

import java.util.List;

import com.indusTalk.models.Reels;
import com.indusTalk.models.User;

public interface ReelsService {
    public Reels createReel(Reels reel,User user)throws Exception;
    
    public List<Reels> findAllReels()throws Exception;
    
    public List<Reels> findUsersReels(Integer userId)throws Exception;

}
