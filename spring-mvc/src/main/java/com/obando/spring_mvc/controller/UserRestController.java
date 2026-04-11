package com.obando.spring_mvc.controller;

import com.obando.spring_mvc.model.User;
import com.obando.spring_mvc.model.dto.UserDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @GetMapping("/details")
    public UserDto userDtoDetails(){
        UserDto userDto = new UserDto();

        //Using a class
        User newUser = new User();
        newUser.setName("Anna Esmeralda");
        newUser.setLastname("Castillo");

        userDto.setTitle("User with Dto");
        userDto.setUser(newUser);

        return userDto;
    }

    @GetMapping("/user/list")
    public List<User> userList(){
        User jose = new User();
        jose.setName("jose");
        jose.setLastname("Obando");

        User john = new User();
        john.setName("john");
        john.setLastname("Castillo");

        User catarina = new User();
        catarina.setName("catarina");
        catarina.setLastname("Hernandez");

        List<User> createdUserList = new ArrayList<>();
        createdUserList.add(jose);
        createdUserList.add(john);
        createdUserList.add(catarina);

        return createdUserList;
    }
}
