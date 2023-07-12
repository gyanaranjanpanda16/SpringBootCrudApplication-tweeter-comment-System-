package com.spring.tweeterapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.tweeterapplication.entity.Tweet;


public interface TweetRepository extends JpaRepository<Tweet, Long> {
}