package com.indusTalk.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.indusTalk.models.Comment;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
    
}
