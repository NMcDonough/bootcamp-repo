package com.bootcamp.web.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="posts")
public class Post {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Column(updatable=false)
	private Date createdAt;
	private Date updatedAt;	
	@NotNull(message="Message cannot by empty!")
    private String text;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="thread_id")
    private Thread thread;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="bootcamp_id")
	private Bootcamp bootcamp;
	
	
	public Post() {}
	
	public Post(User user, Thread thread, String text) {
		this.user = user;
		this.thread = thread;
		this.text = text;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Thread getThread() {
		return thread;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	} 
	
	public Bootcamp getBootcamp() {
		return bootcamp;
	}

	public void setBootcamp(Bootcamp bootcamp) {
		this.bootcamp = bootcamp;
	}

	@PrePersist
	protected void onCreate(){
		this.createdAt = new Date();
	}
	
	@PreUpdate
	protected void onUpdate(){
		this.updatedAt = new Date();
	}
}
