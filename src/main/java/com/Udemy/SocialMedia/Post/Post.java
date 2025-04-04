package com.Udemy.SocialMedia.Post;

import com.Udemy.SocialMedia.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;

@Entity
public class Post {
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
	@Size(min = 10,max = 100, message = "Description limit should between 10 and 100")
	private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;
    public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Integer getId() {
		return Id;
	}
	@Override
	public String toString() {
		return "Post [Id=" + Id + ", description=" + description + "]";
	}
	public Post(Integer id, String description) {
		super();
		Id = id;
		this.description = description;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Post() {}
}
