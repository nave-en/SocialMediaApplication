package com.Udemy.SocialMedia.Versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {
    @GetMapping(path = "/v1/person", produces = "application/json")
    public PersonV1 personV1() {
        return new PersonV1("Bob Charlie");
    }
    @GetMapping(path = "/v2/person", produces = "application/json")
    public PersonV2 personV2() {
        return new PersonV2("Bob", "Charlie");
    }
}
