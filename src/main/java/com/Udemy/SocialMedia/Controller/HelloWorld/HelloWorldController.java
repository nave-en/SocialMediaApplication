package com.Udemy.SocialMedia.Controller.HelloWorld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Udemy.SocialMedia.Utils.HelloWorld;

@RestController
public class HelloWorldController {
    // @RequestMapping(path = "/hello", method = RequestMethod.GET)
    @GetMapping(path = "/hello")
    public String helloWorld() {
        return "Hello, World!";
    }

    @GetMapping(path = "/hello-bean")
    public HelloWorld helloWorldBean() {
        return new HelloWorld("Welcome to Your APP");
    }
}
