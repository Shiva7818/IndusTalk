package com.indusTalk.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    // @Column(name="yourFirstName")
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String gender;
    private List<Integer> followers = new ArrayList<>();
    private List<Integer> followings = new ArrayList<>();
    @JsonIgnore
    @ManyToMany
    private List<Post> savedPost = new ArrayList<>();
    
   
    
    
    
    
}
