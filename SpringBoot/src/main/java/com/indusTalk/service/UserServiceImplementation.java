package com.indusTalk.service;

import java.util.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indusTalk.Repository.UserRepository;
import com.indusTalk.config.JwtProvider;
import com.indusTalk.models.User;
 
@Service
public class UserServiceImplementation implements UserService {
    @Autowired
    UserRepository userRepository;
    //   public User registerUser(User user){
    //     User newUser=new User();
	// 	newUser.setEmail(user.getEmail());
	// 	newUser.setFirstName(user.getFirstName());
	// 	newUser.setLastName(user.getLastName());
	// 	newUser.setPassword(user.getPassword());
    //     newUser.setGender(user.getGender());
	// 	newUser.setId(user.getId());
	// 	User saveUser = userRepository.save(newUser);
	// 	return saveUser;
    //   }
    public User findUserById(Integer userId)throws Exception{
        Optional<User> user = userRepository.findById(userId);//Optional is used in a case if user is not found
		if(user.isPresent())
        return user.get();
		throw new Exception("No user with given id");
    }

    public User findUserByEmail(String email){
        User user = userRepository.findByEmail(email);
        return user;
    }
    public User followUser(Integer reqUserId,Integer userId2)throws Exception
    {
        User reqUser = findUserById(reqUserId);
        User user2 = findUserById(userId2);
        user2.getFollowers().add(reqUserId);
        reqUser.getFollowings().add(userId2);

        userRepository.save(reqUser);
        userRepository.save(user2);
        return reqUser;
    }
    public User updateUser(User newUser,Integer userId)throws Exception{
        Optional<User> user1 = userRepository.findById(userId);
	    if(user1.isEmpty())
        throw new Exception("User does not exist with id "+userId); 
        
        User oldUser = user1.get();
        if(newUser.getFirstName()!=null)
        oldUser.setFirstName(newUser.getFirstName());
        if(newUser.getLastName()!=null)
        oldUser.setLastName(newUser.getLastName());
        if(newUser.getEmail()!=null)
        oldUser.setEmail(newUser.getEmail());
        if(newUser.getPassword()!=null)
        oldUser.setPassword(newUser.getPassword());
        if(newUser.getGender()!=null)
        oldUser.setGender(newUser.getGender());
        User updatedUser = userRepository.save(oldUser);
		return updatedUser;
    }
    public List<User> searchUser(String query){
        return userRepository.searchUser(query);
    }
    
    public User findUserByJwt(String jwt){
        String email = JwtProvider.getEmailFromJwtToken(jwt);
        User user = userRepository.findByEmail(email);
        user.setPassword(null);
        return user;

    }
}
