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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name="categories")
public class Category {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    @Column(updatable=false)
    private Date createdAt;
    
    private Date updatedAt;
    
    private String name;
    
    @Column
    private int accessLevel;
    
    @OneToMany(mappedBy="category", fetch = FetchType.LAZY)
    private List<Thread> threads;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "bootcamps_categories", 
        joinColumns = @JoinColumn(name = "bootcamp_id"), 
        inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    
    private List<Bootcamp> bootcamps;
    
    public Category() {}
    
    public Category(String name, int access) {
    	this.name = name;
    	this.accessLevel = access;
    }
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Thread> getThreads() {
		return threads;
	}

	public void setThreads(List<Thread> threads) {
		this.threads = threads;
	}

	public List<Bootcamp> getBootcamps() {
		return bootcamps;
	}

	public void setBootcamps(List<Bootcamp> bootcamps) {
		this.bootcamps = bootcamps;
	}

	public Long getId() {
		return id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public int getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(int accessLevel) {
		this.accessLevel = accessLevel;
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
