package com.kruger.vaccineInventory.demo.web.controller;

import com.kruger.vaccineInventory.demo.domain.User;
import com.kruger.vaccineInventory.demo.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import com.auth0.jwt.JWT;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";

    @Autowired
    private UserService userService;

    @Secured({ROLE_ADMIN})
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAll(){
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @Secured({ROLE_ADMIN})
    @GetMapping("/role/{idRole}")
    public ResponseEntity<List<User>> getByRole(@PathVariable("idRole") int roleId){
        Optional<List<User>> response = userService.getByRole(roleId);
        if(response.get().isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(response.get(), HttpStatus.OK);
        }
    }

    @Secured({ROLE_ADMIN})
    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") int userId){
        Optional<User> response = userService.getUser(userId);
        if(response.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(response.get(), HttpStatus.OK);
        }
    }

    @Secured({ROLE_ADMIN})
    @GetMapping("/vaccinated/{status}")
    public ResponseEntity<List<User>> getVaccinatedUsers(@PathVariable("status") boolean vaccinationStatus){
        Optional<List<User>> response = userService.getVaccinatedUsers(vaccinationStatus);
        if(response.get().isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(response.get(), HttpStatus.OK);
    }

    @Secured({ROLE_ADMIN, ROLE_USER})
    @PostMapping("/update/employee")
    public ResponseEntity<Boolean> updateUser(@RequestBody User user, @RequestHeader(name = "Authorization") String token){
        if (token.startsWith("Bearer ")){
            token = token.substring(7, token.length());
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        String userId = JWT.decode(token).getClaim("user_name").asString();
        return new ResponseEntity<>(userService.updateUser(user, userId) ? HttpStatus.ACCEPTED : HttpStatus.BAD_REQUEST);
    }

    @Secured({ROLE_ADMIN})
    @PostMapping("/update")
    public ResponseEntity<Boolean> updateAny(@RequestBody User user){
        if(user.getUserName() == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(userService.updateUser(user, user.getUserName()) ? HttpStatus.ACCEPTED : HttpStatus.BAD_REQUEST);
    }
    @Secured({ROLE_ADMIN})
    @PostMapping("/new")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        if (userService.save(user)) {
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
