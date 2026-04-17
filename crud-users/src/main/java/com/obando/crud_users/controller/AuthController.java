package com.obando.crud_users.controller;

import com.obando.crud_users.model.User;
import com.obando.crud_users.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> list(){
        return userService.findAll();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody User user, BindingResult result){
        if (result.hasFieldErrors()){
            return validation(result);
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.save(user));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody User user, BindingResult result){
        if (result.hasFieldErrors()){
            return validation(result);
        }
        user.setAdmin(false);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.save(user));
    }

    private ResponseEntity<?> validation(BindingResult result){
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(error -> {
            errors.put(error.getField(), "El campo " + error.getField() + " " + error.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errors);
    }
}
