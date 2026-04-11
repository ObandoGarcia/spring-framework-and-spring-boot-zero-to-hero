package com.obando.spring_mvc.controller;

import com.obando.spring_mvc.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    @GetMapping("/details")
    public String details(Model model){
        model.addAttribute("title", "User details service app");
        model.addAttribute("name", "Jose Manuel");
        model.addAttribute("lastname", "Obando Garcia");

        //Using a class
        User newUser = new User();
        newUser.setName("Anna Esmeralda");
        newUser.setLastname("Castillo");
        newUser.setEmail("obandogarcia10635@gmail.com");

        model.addAttribute("user", newUser);

        return "details";
    }

}
