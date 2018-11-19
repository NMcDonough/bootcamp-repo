package com.bootcamp.web.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	@Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
	@Size(min=5, message="Title must be 5 or more characters")
	private String title;
	private String text;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="bootcamp_id")
	private Bootcamp bootcamp;	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id")
    private Category category;
	@OneToMany(mappedBy="thread", fetch = FetchType.LAZY)
    private List<Post> posts;
    
    public Thread() {}
    
    public Thread(Category category, Bootcamp bootcamp, String title, User user) {
    	this.category = category;
    	this.title = title;
    	this.user = user;
    	this.bootcamp = bootcamp;
    }
    
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Bootcamp getBootcamp() {
		return bootcamp;
	}

	public void setBootcamp(Bootcamp bootcamp) {
		this.bootcamp = bootcamp;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
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
