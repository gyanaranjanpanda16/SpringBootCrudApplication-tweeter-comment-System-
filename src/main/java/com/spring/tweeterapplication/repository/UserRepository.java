package com.spring.tweeterapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.tweeterapplication.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}