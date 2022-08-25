package com.kruger.vaccineInventory.demo.web.controller;

import com.kruger.vaccineInventory.demo.domain.User;
import com.kruger.vaccineInventory.demo.domain.Vaccine;
import com.kruger.vaccineInventory.demo.domain.service.VaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(("/vaccine"))
public class VaccineController {

    @Autowired
    private VaccineService vaccineService;

    @GetMapping("/all")
    public ResponseEntity<List<Vaccine>> getAll(){
        return new ResponseEntity<>(vaccineService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/vaccine/{id}")
    public ResponseEntity<Vaccine> getById(@PathVariable("id") int regVaccineId){
        return vaccineService.getById(regVaccineId).map(vaccine -> new ResponseEntity<>(vaccine, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    public ResponseEntity<Vaccine> save(@RequestBody Vaccine vaccine){
        Optional<Vaccine> response = vaccineService.save(vaccine);
        if(response.isEmpty()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        else return new ResponseEntity<>(vaccine, HttpStatus.CREATED);
    }

    @GetMapping("/getusersByVaccineType/{typeId}")
    public ResponseEntity<List<User>> getByVaccineType(@PathVariable("typeId") int vaccineTypeId){
        return vaccineService.getByVaccineType(vaccineTypeId)
                .map(response -> new ResponseEntity<>(response, HttpStatus.FOUND))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
