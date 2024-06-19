package com.keycloak.UserIdBasedAuthentication.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurant")
@SecurityRequirement(name = "Keycloak")
public class TestingController {

    // @PreAuthorize("hasRole('manager')")
    @GetMapping("/welcome")
    public String getWelcomeMessage(){
        return "welcome";
    }

    // @PreAuthorize("hasRole('admin')")
    @GetMapping("/thankyou")
    public String getThankYouMessage(){
        return "Thank you";
    }
}
