package com.indusTalk.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.indusTalk.Repository.UserRepository;
import com.indusTalk.models.User;
import com.indusTalk.service.UserService;

@RestController
public class UserController {

	@Autowired
    UserRepository userRepository;
	@Autowired
	UserService userService;


	@GetMapping("/api/users")
	public List<User> getUsers() {
		
	    List<User> users = userRepository.findAll();
		
		return users;
	}
	
	
	@GetMapping("api/users/{userId}")
	public User getUserById(@PathVariable("userId") Integer id)throws Exception {
		User user = userService.findUserById(id);
		return user;
		
	}
	
	
	
	@PutMapping("/api/users")
	public User updateUser(@RequestHeader("Authorization")String jwt,@RequestBody User newUser)throws Exception {
		User reqUser = userService.findUserByJwt(jwt); 
		User updatedUser = userService.updateUser(newUser,reqUser.getId());
		return updatedUser;
		
	}
   
	@PutMapping("/api/users/{userId2}")
	public User followUserHandler(@RequestHeader("Authorization")String jwt,@PathVariable Integer userId2)throws Exception
{
	User reqUser = userService.findUserByJwt(jwt);
     User user = userService.followUser(reqUser.getId(),userId2);
	 return user;
}	 
    @GetMapping("/api/users/search")
    public List<User> searchUser(@Param("query") String query)
	{
      List<User> users = userService.searchUser(query);
	  return users;
	}

    @GetMapping("/api/users/profile")
	public User getUserFromToken(@RequestHeader("Authorization")String jwt){
		
		// System.out.println("jwt ------"+jwt);
		User user = userService.findUserByJwt(jwt);
		return user;
	}

	// @DeleteMapping("users/{userId}")
	// public String deleteUser(@PathVariable("userId") Integer id) throws Exception{
			
	// 	Optional<User> user1 = userRepository.findById(id);
	//     if(user1.isEmpty())
    //     throw new Exception("User does not exist with id "+id); 
    //     userRepository.delete(user1.get());
	// 	return "User deleted with id"+id;
	// }

}