package com.example.gestion_livres.controller;

import com.example.gestion_livres.entities.User;
import com.example.gestion_livres.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping
    ResponseEntity<User> addUser(@Valid @RequestBody User user){
        User response  = userService.addUser(user);
        return  ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        User response = userService.getUserByID(id);
        if(response != null){
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> response = userService.getAllUser();
        return ResponseEntity.ok(response);
    }


}
