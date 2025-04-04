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
import com.Udemy.SocialMedia.Post.PostRepository;

import jakarta.validation.Valid;

@RestController
public class userDBResources {
    private UserDaoService userDaoService;
    private UserRepository userRepository;
    private PostRepository postRepository;
    public userDBResources(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @GetMapping(path = "/jpa/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping(path = "/jpa/users/{id}")
    public User getUserById(@PathVariable int id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new UserNotFoundException("id-" + id);
        }

        return user;
    }

    @PostMapping(path = "/jpa/users")
    public ResponseEntity<Object> creatUser(@Valid @RequestBody User user) {
        userRepository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/jpa/users/{id}")
    public void deleteUserById(@PathVariable int id){
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new UserNotFoundException("id-" + id);
        }

        userDaoService.deleteUserById(id);
    }
    
    @GetMapping(path = "users/posts/{id}")
    public List<Post> retrivePostsForUser(@PathVariable int id) {
    	User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new UserNotFoundException("id-" + id);
        }
        
        return user.getPost();
    }
   
    @PostMapping(path = "users/posts/{id}")
    public ResponseEntity<Object> createPostForUser(@PathVariable int id, @Valid @RequestBody Post post) {
    	User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new UserNotFoundException("id-" + id);
        }
        
        System.out.println(post);
        post.setUser(user);
        Post savedPost = postRepository.save(post);
        
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
