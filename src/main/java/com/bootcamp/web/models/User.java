package com.bootcamp.web.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;


@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(updatable=false)
    private Date createdAt;
    
    private Date updatedAt;
    
    @Size(min = 2, message="Name must have at least 2 characters")
    private String fname;
    
    @Size(min = 2, message="Name must have at least 2 characters")
    private String lname;
    
    private String title;
    
    private String status;
    
    @Column(unique = true)
    @Size(max = 50, message="Email address field must be 50 characters or fewer")
    @Email(message="{exists.email}")
    private String email;
    
    @Size(min=8, message="Password must be at least 8 characters")
	private String password;
    
    @Transient
    private String confirm;
    
    private int userlevel;
        
    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
    private List<Thread> threads;
    
    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
    private List<Post> posts;
    
    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
    private List<Review> reviews;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="bootcamp_id")
    private Bootcamp bootcamp;

    public User() {}

	public User(String fname, String lname, String title, String status, String email, String password, Bootcamp bootcamp) {
		this.fname = fname;
		this.lname = lname;
		this.title = title;
		this.status = status;
		this.email = email;
		this.password = password;
		this.bootcamp = bootcamp;
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public String getFname() {
		return fname;
	}
	
	public void setFname(String fname) {
		this.fname = fname;
	}
	
	public String getLname() {
		return lname;
	}
	
	public void setLname(String lname) {
		this.lname = lname;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
    public List<Thread> getThreads() {
		return threads;
	}

	public void setThreads(List<Thread> threads) {
		this.threads = threads;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public Bootcamp getBootcamp() {
		return bootcamp;
	}

	public void setBootcamp(Bootcamp bootcamp) {
		this.bootcamp = bootcamp;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public int getUserlevel() {
		return userlevel;
	}

	public void setUserlevel(int userlevel) {
		this.userlevel = userlevel;
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