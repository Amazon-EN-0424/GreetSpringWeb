package com.ironhack.greetspringweb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coffee {

    private Long id;
    private ZonedDateTime createdAt;
    private String name;
}
