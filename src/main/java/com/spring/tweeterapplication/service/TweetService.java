package com.spring.tweeterapplication.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.spring.tweeterapplication.entity.Tweet;
import com.spring.tweeterapplication.repository.TweetRepository;
import com.spring.tweeterapplication.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TweetService {
    private final TweetRepository tweetRepository;
    private final UserRepository userRepository;

    public TweetService(TweetRepository tweetRepository, UserRepository userRepository) {
        this.tweetRepository = tweetRepository;
        this.userRepository = userRepository;
    }

    public Tweet createTweet(Tweet tweet) {
        tweet.setTimestamp(LocalDateTime.now());
        return tweetRepository.save(tweet);
    }

    public List<Tweet> getAllTweets() {
        return tweetRepository.findAll();
    }

    public Optional<Tweet> getTweetById(Long id) {
        return tweetRepository.findById(id);
    }

    public Tweet saveTweet(Tweet tweet) {
        return tweetRepository.save(tweet);
    }

    public ResponseEntity<?> updateTweet(Long id, Tweet updatedTweet) {
        try {
            Optional<Tweet> existingTweetOptional = getTweetById(id);
            if (existingTweetOptional.isPresent()) {
                Tweet existingTweet = existingTweetOptional.get();
                existingTweet.setContent(updatedTweet.getContent());
               Tweet savedTweet = saveTweet(existingTweet);
                
//                String successMessage = "Tweet updated successfully. ID: " + id;
//                return ResponseEntity.ok(successMessage);
                return ResponseEntity.ok(savedTweet);
            } else {
                String errorMessage = "Tweet not found with ID: " + id;
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
            }
        } catch (NoSuchElementException e) {
            String errorMessage = "Tweet not found with ID: " + id;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }
    public boolean deleteTweet(Long id) {
        Optional<Tweet> tweetOptional = tweetRepository.findById(id);
        if (tweetOptional.isPresent()) {
            tweetRepository.delete(tweetOptional.get());
            return true;
        }
        return false;
    }

	public UserRepository getUserRepository() {
		return userRepository;
	}
}