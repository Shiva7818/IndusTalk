package com.indusTalk.service;

import java.util.List;

import com.indusTalk.models.Chat;
import com.indusTalk.models.Message;
import com.indusTalk.models.User;

public interface MessageService {
    
    public Message createMessage(User user,Integer chatId,Message req)throws Exception;
    public List<Message> findChatsMessages(Integer chatId)throws Exception;

}   
