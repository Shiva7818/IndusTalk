package com.indusTalk.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.indusTalk.models.Chat;
import com.indusTalk.models.User;

public interface ChatRepository extends JpaRepository<Chat,Integer> {

    @Query("select c from Chat c join c.users u where u.id=:userId")
	public List<Chat> findChatByUserId(Integer userId);
    
    @Query("select c from Chat c where :user Member of c.users And :reqUser Member of c.users")
    public Chat findSingleChatByUsersId(@Param("user") User user,@Param("reqUser") User reqUser);
    
}