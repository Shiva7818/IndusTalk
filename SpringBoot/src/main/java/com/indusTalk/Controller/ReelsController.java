package com.indusTalk.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.indusTalk.models.Reels;
import com.indusTalk.models.User;
import com.indusTalk.service.ReelsService;
import com.indusTalk.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
public class ReelsController {
    @Autowired
    private ReelsService reelsService;
    @Autowired
    private UserService userService;
    @PostMapping("/api/reels")
     public Reels createReel(@RequestBody Reels reel,@RequestHeader("Authorization") String jwt)throws Exception{
       User reqUser = userService.findUserByJwt(jwt);

       return reelsService.createReel(reel, reqUser);
    }
    @GetMapping("/api/reels")
    public List<Reels> findAllReels()throws Exception{
       
        return reelsService.findAllReels();
    }
    @GetMapping("/api/reels/user/{userId}")
    public List<Reels> findUsersReel(@PathVariable Integer userId)throws Exception{
        List<Reels> reels = reelsService.findUsersReels(userId);
        return reels;
    }
    
   
}
