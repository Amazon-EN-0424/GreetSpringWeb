package com.ironhack.greetspringweb.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

@Service
public class GreetService {



    public String greet(String name, String language) {
        String greet = switch (language) {
            case "es" -> "Hola " + name;
            case "en" -> "Hello " + name;
            case "fr" -> "Bonjour " + name;
            case "de" -> "Hallo " + name;
            case "it" -> "Ciao " + name;
            case "pt" -> "Olá " + name;
            case "ru" -> "Привет " + name;
            case "zh" -> "你好 " + name;
            case "ja" -> "こんにちは " + name;
            case "ko" -> "안녕하세요 " + name;
            default -> {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Language not supported");
//                throw new RuntimeException("Language not supported");
            }

        };
        return greet;
    }

    public String greet(String name) {
        return greet(name, "en");
    }

}
