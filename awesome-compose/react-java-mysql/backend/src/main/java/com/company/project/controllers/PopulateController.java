package com.company.project.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.project.services.PopulateDatabase;



@RestController
@RequestMapping("/populate")
public class PopulateController {
    private final PopulateDatabase populator;

    public PopulateController(PopulateDatabase populator) {
        this.populator = populator;
    }

    @PostMapping("/sqldatabase")
    public void populateAll() {
        populator.populateAll();
    }
}