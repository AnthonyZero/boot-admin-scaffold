package com.anthonyzero.scaffold.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ViewController {

    @GetMapping("/login")
    public String login(HttpServletRequest request) {
        return "login";
    }
}
