package com.company.project.controllers;

import com.company.project.entity.Greeting;
// import com.company.project.repository.GreetingRepository;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

 
    @GetMapping("/")
    public Greeting showHome(String name, Model model) {
        return new Greeting("Not Found 😕");
    }

}