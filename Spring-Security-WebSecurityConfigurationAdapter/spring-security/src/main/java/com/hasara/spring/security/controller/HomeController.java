package com.hasara.spring.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/home")
public class HomeController {

   /*
    @GetMapping
    public String getString()
    {
        return "AtHome";
    }
    */

    @GetMapping("/user")
    public String getUser()
    {
        return "At User";
    }

    /*
    @GetMapping("/moderator")
    public String getModerator()
    {
        return "At Moderator";
    }

    @GetMapping("/admin")
    public String getAdmin()
    {
        return "At Admin";
    }
     */
}
