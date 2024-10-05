package com.indusTalk.service;

import java.util.List;

import com.indusTalk.models.Chat;
import com.indusTalk.models.User;

public interface ChatService {

    public Chat createChat(User reqUser,User user2);
    
    public Chat findChatById(Integer chatId)throws Exception;

    public List<Chat> findUsersChat(Integer userId);

    
}
