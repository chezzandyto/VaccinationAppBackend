package com.kruger.vaccineInventory.demo.web.controller;

import com.kruger.vaccineInventory.demo.domain.User;
import com.kruger.vaccineInventory.demo.domain.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    @ApiOperation("Get All Users, this endpoint requires an Authorization as ADMIN")
    public ResponseEntity<List<User>> getAll(){
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @Secured({ROLE_ADMIN})
    @GetMapping("/role/{idRole}")
    @ApiOperation("List Users by RoleID, this endpoint requires an Authorization as ADMIN")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Users not found")
    })
    public ResponseEntity<List<User>> getByRole(@ApiParam(value = "default", required = true, example = "2") @PathVariable("idRole") int roleId){
        Optional<List<User>> response = userService.getByRole(roleId);
        if(response.get().isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(response.get(), HttpStatus.OK);
        }
    }

    @Secured({ROLE_ADMIN})
    @GetMapping("/user/{id}")
    @ApiOperation("Get a User by ID, this endpoint requires an Authorization as ADMIN")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "User not found")
    })
    public ResponseEntity<User> getUser(@ApiParam(value = "default", required = true, example = "2") @PathVariable("id") int userId){
        Optional<User> response = userService.getUser(userId);
        if(response.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(response.get(), HttpStatus.OK);
        }
    }

    @Secured({ROLE_ADMIN})
    @GetMapping("/vaccinated/{status}")
    @ApiOperation("Get a User Vaccination Status (1 or 0), this endpoint requires an Authorization as ADMIN")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "User not found")
    })
    public ResponseEntity<List<User>> getVaccinatedUsers(@ApiParam(value = "defaultBool", required = true, example = "1") @PathVariable("status") boolean vaccinationStatus){
        Optional<List<User>> response = userService.getVaccinatedUsers(vaccinationStatus);
        if(response.get().isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(response.get(), HttpStatus.OK);
    }

    @Secured({ROLE_ADMIN, ROLE_USER})
    @PostMapping("/update/employee")
    @ApiOperation("Update User fields, this endpoint updates just whom has been authorized by token")
    @ApiResponses({
            @ApiResponse(code = 202, message = "ACCEPTED"),
            @ApiResponse(code = 400, message = "Bad Fields"),
    })
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
    @ApiOperation("Update User fields, this endpoint is used by ADMIN role. Can be update only by an Admin")
    @ApiResponses({
            @ApiResponse(code = 202, message = "ACCEPTED"),
            @ApiResponse(code = 400, message = "Bad Fields"),
    })
    public ResponseEntity<Boolean> updateAny(@RequestBody User user){
        if(user.getUserName() == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(userService.updateUser(user, user.getUserName()) ? HttpStatus.ACCEPTED : HttpStatus.BAD_REQUEST);
    }


    @Secured({ROLE_ADMIN})
    @PostMapping("/new")
    @ApiOperation("Create a new User, this endpoint is used by ADMIN role. Can be update only by an Admin")
    @ApiResponses({
            @ApiResponse(code = 201, message = "CREATED"),
            @ApiResponse(code = 400, message = "Bad Fields"),
    })
    public ResponseEntity<User> saveUser(@RequestBody User user){
        if (userService.save(user)) {
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Secured({ROLE_ADMIN})
    @DeleteMapping("/delete/{id}")
    @ApiOperation("Delete a User, this endpoint is used by ADMIN role. Can be update only by an Admin")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "User not found"),
    })
    public ResponseEntity deleteUser(@PathVariable("id") int id){
        return new ResponseEntity(userService.delete(id) ? HttpStatus.OK : HttpStatus.NOT_FOUND );
    }

}
