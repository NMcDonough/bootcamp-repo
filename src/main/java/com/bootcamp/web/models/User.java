package com.bootcamp.web.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    @Size(min = 3, message="Name must have at least 3 characters")
    private String name;
    @Column(unique = true)
    @Size(max = 50, message="Email address field must be 50 characters or fewer")
    @Email(message="{exists.email}")
    private String email;
    @Size(min=8, message="Password must be at least 8 characters")
	private String password;
    @Transient
    private String confirm;
    @Column(unique=true)
    private String username;
    
    @Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;

    
    public User() {}

	public User(String name, String email, String password, String confirm, String username){
        this.name = name;
        this.email = email;
        this.password = password;
        this.username = username;
        this.confirm = confirm;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
}