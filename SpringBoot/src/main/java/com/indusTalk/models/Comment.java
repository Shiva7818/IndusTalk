package com.indusTalk.models;

import java.util.*;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Integer commentId;

private String content;
@ManyToOne
private User user;
@ManyToMany
private List<User> liked = new ArrayList<>();

private LocalDateTime createdAt;

    
}
