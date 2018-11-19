package com.bootcamp.web.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="threads")
public class Thread {
	@Id
	@GeneratedValue
	private Long id;
	
	@Size(min=5, message="Title must be 5 or more characters")
	private String title;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="author_id")
    private User author;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id")
    private Category category;
	
	@JoinTable(
	        name = "posts_threads", 
	        joinColumns = @JoinColumn(name = "thread_id"), 
	        inverseJoinColumns = @JoinColumn(name = "post_id")
	    )
	private List<Post> posts;
	
	@Column(updatable=false)
    private Date createdAt;
	
    private Date updatedAt;
    
    public Thread() {}
    
    public Thread(Category category, String title, User author) {
    	this.category = category;
    	this.title = title;
    	this.author = author;
    }
    
    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

}
