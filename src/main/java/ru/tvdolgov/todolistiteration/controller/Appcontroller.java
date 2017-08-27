package ru.tvdolgov.todolistiteration.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Appcontroller {
    @RequestMapping("/")
    public String testHello(Model model){
        model.addAttribute("name", "Iteration Test Project");
        return "hello";
    }
}
