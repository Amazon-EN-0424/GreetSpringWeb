package com.ironhack.greetspringweb.controller;

import com.ironhack.greetspringweb.service.GreetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greet")
@RequiredArgsConstructor
public class GreetController {

    private final GreetService greetService;

    @GetMapping
    public String greet(@RequestParam String name, @RequestParam(required = false) String language) {
        if (language == null) {
            return greetService.greet(name);
        } else {
            return greetService.greet(name, language);
        }
    }

}
