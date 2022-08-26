package com.kruger.vaccineInventory.demo.web.controller;

import com.auth0.jwt.JWT;
import com.kruger.vaccineInventory.demo.domain.User;
import com.kruger.vaccineInventory.demo.domain.Vaccine;
import com.kruger.vaccineInventory.demo.domain.service.VaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(("/vaccine"))
public class VaccineController {

    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";

    @Autowired
    private VaccineService vaccineService;

    @Secured({ROLE_ADMIN})
    @GetMapping("/all")
    public ResponseEntity<List<Vaccine>> getAll(){
        return new ResponseEntity<>(vaccineService.getAll(), HttpStatus.OK);
    }

    @Secured({ROLE_ADMIN})
    @GetMapping("/vaccine/{id}")
    public ResponseEntity<Vaccine> getById(@PathVariable("id") int regVaccineId){
        return vaccineService.getById(regVaccineId).map(vaccine -> new ResponseEntity<>(vaccine, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Secured({ROLE_ADMIN})
    @PostMapping("/save")
    public ResponseEntity<Vaccine> savebyId(@RequestBody Vaccine vaccine){
        if(vaccine.getUserId() == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        boolean response = vaccineService.saveById(vaccine, vaccine.getUserId());
        if(response) return new ResponseEntity<>(vaccine, HttpStatus.CREATED);
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Secured({ROLE_ADMIN, ROLE_USER})
    @PostMapping("/save/employee")
    public ResponseEntity<Vaccine> saveByUserName(@RequestBody Vaccine vaccine, @RequestHeader(name = "Authorization") String token){
        if (token.startsWith("Bearer ")){
            token = token.substring(7, token.length());
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        String userName = JWT.decode(token).getClaim("user_name").asString();
        boolean response = vaccineService.saveByUserName(vaccine, userName);
        if(response) return new ResponseEntity<>(vaccine, HttpStatus.CREATED);
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Secured({ROLE_ADMIN})
    @GetMapping("/getusersByVaccineType/{typeId}")
    public ResponseEntity<List<User>> getByVaccineType(@PathVariable("typeId") int vaccineTypeId){
        Optional<List<User>> users = vaccineService.getByVaccineType(vaccineTypeId);
        if(users.get().isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(users.get(), HttpStatus.FOUND);
    }
}
