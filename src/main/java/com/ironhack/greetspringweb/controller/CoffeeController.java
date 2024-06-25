package com.ironhack.greetspringweb.controller;

import com.ironhack.greetspringweb.model.Coffee;
import com.ironhack.greetspringweb.model.CoffeeRequest;
import com.ironhack.greetspringweb.service.CoffeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/coffee")
@RestController
@RequiredArgsConstructor
public class CoffeeController {

    private final CoffeeService coffeeService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Coffee createCoffee(@RequestBody CoffeeRequest coffeeRequest) {
        return coffeeService.createCoffee(coffeeRequest);
    }

    @GetMapping
    public List<Coffee> returnAllCoffees() {
        return coffeeService.returnAllCoffees();
    }

}
