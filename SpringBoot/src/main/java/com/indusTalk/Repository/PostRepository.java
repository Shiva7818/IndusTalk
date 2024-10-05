package com.indusTalk.Repository;

import java.util.*;

import com.indusTalk.models.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post,Integer> {
    @Query("select p from Post p where p.user.id=:userId")
    List<Post> findPostByUserId(Integer userId);
}
