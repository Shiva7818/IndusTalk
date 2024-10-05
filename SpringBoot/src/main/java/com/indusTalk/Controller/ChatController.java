package com.indusTalk.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.indusTalk.models.Chat;
import com.indusTalk.models.User;
import com.indusTalk.request.CreateChatRequest;
import com.indusTalk.service.ChatService;
import com.indusTalk.service.UserService;

@RestController
public class ChatController {
    
    @Autowired
    private ChatService chatService;
    @Autowired
    private UserService userService;
    @PostMapping("/api/chats")
     public Chat createChat(@RequestHeader("Authorization") String jwt,@RequestBody CreateChatRequest req)throws Exception{
        User reqUser = userService.findUserByJwt(jwt);
        User user2 = userService.findUserById(req.getUserId());
        Chat chat = chatService.createChat(reqUser,user2);

        return chat;
     }
    
    @GetMapping("/api/")
    public Chat findChatById(Integer chatId)throws Exception{
       
        return null;
    }
    
    @GetMapping("api/chats")
    public List<Chat> findUsersChat(@RequestHeader("Authorization") String jwt){
        User user = userService.findUserByJwt(jwt);

        return  chatService.findUsersChat(user.getId());
    }
}
