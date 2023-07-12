package com.spring.tweeterapplication.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Tweet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private LocalDateTime timestamp;
    
    private String userinformation;
    
  


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	

	public String getUserinformation() {
		return userinformation;
	}

	public void setUserinformation(String userinformation) {
		this.userinformation = userinformation;
	}

	public Tweet() {
    }

	/**
	 * @param id
	 * @param content
	 * @param timestamp
	 * @param username
	 * @param user
	 */
	public Tweet(Long id, String content, LocalDateTime timestamp, String username) {
		super();
		this.id = id;
		this.content = content;
		this.timestamp = timestamp;
		this.userinformation = username;

	}

    
    
}