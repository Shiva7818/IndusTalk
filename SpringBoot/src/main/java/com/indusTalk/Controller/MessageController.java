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

import com.indusTalk.models.Chat;
import com.indusTalk.models.Message;
import com.indusTalk.models.User;
import com.indusTalk.service.MessageService;
import com.indusTalk.service.UserService;

@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;
    @PostMapping("/api/messages/chat/{chatId}")
    public Message createMessage(@RequestBody Message reqMessage,@PathVariable Integer chatId,@RequestHeader("Authorization")String jwt)throws Exception{
     User user = userService.findUserByJwt(jwt);
     return messageService.createMessage(user, chatId, reqMessage);
    
    }
    @GetMapping("/api/messages/chat/{chatId}")
    public List<Message> findChatsMessages(@PathVariable Integer chatId)throws Exception{
     
        return messageService.findChatsMessages(chatId);
    }
    
}
