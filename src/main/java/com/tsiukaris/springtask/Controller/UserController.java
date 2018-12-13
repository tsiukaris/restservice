package com.tsiukaris.springtask.Controller;

import com.tsiukaris.springtask.Entity.User;
import com.tsiukaris.springtask.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/add")
    public User createCustomer(@Valid @RequestBody User user){
        return userRepository.save(user);
    }

    @GetMapping("/users/{userid}")
    public User findUserById(@PathVariable String userid){
        if(userid.contains("@")){
            Optional<User> user = userRepository.findByEmail(userid);
            return user.orElse(new User());
        } else if(Integer.parseInt(userid) > 0){
            Optional<User> user = userRepository.findById(Integer.parseInt(userid));
            return user.orElse(new User());
        } else {
            return new User();
        }
    }

    @GetMapping("/users")
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @DeleteMapping("/users/{userid}")
    public ResponseEntity<?> deleteCustomer(@PathVariable int userid){
        return userRepository.findById(userid)
                .map((User user) -> {
                    userRepository.delete(user);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new NoSuchElementException("Customer not found by id" + userid));
    }
}
