package com.example.demo;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
 
import org.springframework.web.bind.annotation.*;
 
@RestController
public class UserController {
 
    @Autowired
    private UserService service;
     
    // RESTful API methods for Retrieval operations
    @GetMapping("/user")
    public List<User> list() { //name of table
        return service.listAll();
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<User> get(@PathVariable Integer id) {
        try {
        	User user = service.get(id);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }      
    }
    // RESTful API method for Create operation
    @PostMapping("/user")
    public void add(@RequestBody User user) {
        service.save(user);
    }
     
    // RESTful API method for Update operation
    @PutMapping("/user/{id}")
    public ResponseEntity<?> update(@RequestBody User user, @PathVariable Integer id) {
        try {
        	User existuser = service.get(id);
            service.save(user);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }      
    }
    // RESTful API method for Delete operation
    @DeleteMapping("/user/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
    
   
}