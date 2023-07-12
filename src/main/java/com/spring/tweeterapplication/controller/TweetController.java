package com.spring.tweeterapplication.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.spring.tweeterapplication.entity.Tweet;
import com.spring.tweeterapplication.service.TweetService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/tweets")
public class TweetController {
    private final TweetService tweetService;

    public TweetController(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @PostMapping
    public ResponseEntity<Tweet> createTweet(@RequestBody Tweet tweet) {
        Tweet createdTweet = tweetService.createTweet(tweet);
        return ResponseEntity.ok(createdTweet);
    }
    
    @GetMapping
    public ResponseEntity<List<Tweet>> getAllTweets() {
        List<Tweet> tweets = tweetService.getAllTweets();
        return ResponseEntity.ok(tweets);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getTweetById(@PathVariable Long id) {
        Optional<Tweet> tweetOptional = tweetService.getTweetById(id);
        if (tweetOptional.isPresent()) {
            Tweet tweet = tweetOptional.get();
            return ResponseEntity.ok(tweet);
        } else {
            String errorMessage = "Tweet not found with ID: " + id;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateTweet(@PathVariable Long id, @RequestBody Tweet updatedTweet) {
        try {
            ResponseEntity<?> responseEntity = tweetService.updateTweet(id, updatedTweet);
            return responseEntity;
        } catch (NoSuchElementException e) {
            String errorMessage = "Tweet not found with ID: " + id;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }
     

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTweet(@PathVariable Long id) {
        try {
            boolean deleted = tweetService.deleteTweet(id);
            if (deleted) {
                String successMessage = "Tweet deleted successfully. ID: " + id;
                return ResponseEntity.ok(successMessage);
            } else {
                String errorMessage = "Tweet not found with ID: " + id;
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
            }
        } catch (NoSuchElementException e) {
            String errorMessage = "Tweet not found with ID: " + id;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }

    }
}