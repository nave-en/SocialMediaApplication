package com.Udemy.SocialMedia.User;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.Udemy.SocialMedia.UserNotFoundException;
import com.Udemy.SocialMedia.Post.Post;

import jakarta.validation.Valid;

@RestController
public class UserResource {
    private UserDaoService userDaoService;
    public UserResource() {
        this.userDaoService = new UserDaoService();
    }

    @GetMapping(path = "/users")
    public List<User> getAllUsers() {
        return userDaoService.findAll();
    }

    @GetMapping(path = "/users/{id}")
    public User getUserById(@PathVariable int id) {
        User user = userDaoService.findById(id);
        if (user == null) {
            throw new UserNotFoundException("id-" + id);
        }

        return userDaoService.findById(id);
    }

    @PostMapping(path = "/users")
    public ResponseEntity<Object> creatUser(@Valid @RequestBody User user) {
        userDaoService.addUser(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/users/{id}")
    public void deleteUserById(@PathVariable int id){
        User user = userDaoService.findById(id);
        if (user == null) {
            throw new UserNotFoundException("id-" + id);
        }

        userDaoService.deleteUserById(id);
    }
}
