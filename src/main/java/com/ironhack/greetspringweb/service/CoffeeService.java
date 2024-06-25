package com.ironhack.greetspringweb.service;

import com.ironhack.greetspringweb.model.Coffee;
import com.ironhack.greetspringweb.model.CoffeeRequest;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoffeeService {

    public CoffeeService() {
        coffees = new ArrayList<>();
    }

    private Long lastId = 0L;

    private List<Coffee> coffees;


    public Coffee createCoffee(CoffeeRequest coffeeRequest) {
        var newCoffee = new Coffee(
                ++lastId,
                ZonedDateTime.now(),
                coffeeRequest.getName()
        );

        coffees.add(newCoffee);
        return newCoffee;
    }


    public List<Coffee> returnAllCoffees() {
        return coffees;
    }
}
