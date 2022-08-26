package com.kruger.vaccineInventory.demo.web.controller;

import com.auth0.jwt.JWT;
import com.kruger.vaccineInventory.demo.domain.User;
import com.kruger.vaccineInventory.demo.domain.Vaccine;
import com.kruger.vaccineInventory.demo.domain.service.VaccineService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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
    @ApiOperation("Get All Vaccination registers, this endpoint requires an Authorization as ADMIN")
    public ResponseEntity<List<Vaccine>> getAll(){
        return new ResponseEntity<>(vaccineService.getAll(), HttpStatus.OK);
    }

    @Secured({ROLE_ADMIN})
    @GetMapping("/vaccine/{id}")
    @ApiOperation("Get a Vaccination register by Id, this endpoint requires an Authorization as ADMIN")
    public ResponseEntity<Vaccine> getById(@PathVariable("id") int regVaccineId){
        return vaccineService.getById(regVaccineId).map(vaccine -> new ResponseEntity<>(vaccine, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Secured({ROLE_ADMIN})
    @PostMapping("/save")
    @ApiOperation("Save a Vaccination Register, a User can save a register. Required vaccineId(1,2,3,4) and Date in yyyy-MM-dd format")
    @ApiResponses({
            @ApiResponse(code = 201, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Fields")
    })
    public ResponseEntity<Vaccine> savebyId(@RequestBody Vaccine vaccine){
        if(vaccine.getUserId() == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        boolean response = vaccineService.saveById(vaccine, vaccine.getUserId());
        if(response) return new ResponseEntity<>(vaccine, HttpStatus.CREATED);
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Secured({ROLE_ADMIN, ROLE_USER})
    @PostMapping("/save/employee")
    @ApiOperation("Save a Vaccination Register, an ADMIN can save a register. Required vaccineId(1,2,3,4), UserID and Date in yyyy-MM-dd format")
    @ApiResponses({
            @ApiResponse(code = 201, message = "OK"),
            @ApiResponse(code = 400, message = "Bad fields")
    })
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
    @ApiOperation("List by VaccineType(id), an Admin can get all users with a (id) vaccine type")
    @ApiResponses({
            @ApiResponse(code = 302, message = "FOUND"),
            @ApiResponse(code = 400, message = "Users not found")
    })
    public ResponseEntity<List<User>> getByVaccineType(@PathVariable("typeId") int vaccineTypeId){
        Optional<List<User>> users = vaccineService.getByVaccineType(vaccineTypeId);
        if(users.get().isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(users.get(), HttpStatus.FOUND);
    }

    @Secured({ROLE_ADMIN})
    @GetMapping("/getUsersVaccinatedByDateRange")
    @ApiOperation("List by VaccineType(Date Range), an Admin can get all users with Date Range Filter. Date in yyyy-MM-dd format")
    @ApiResponses({
            @ApiResponse(code = 302, message = "FOUND"),
            @ApiResponse(code = 400, message = "Users not found")
    })
    public ResponseEntity<List<User>> getUsersByDateRange(@RequestParam(name = "from") String fromDate, @RequestParam(name = "to") String toDate) throws ParseException {
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        Date dateBefore = parser.parse(fromDate);
        Date dateAfter = parser.parse(toDate);
        Optional<List<User>> users = vaccineService.getUsersVaccinatedByDateRange(dateBefore, dateAfter);
        if(users.get().isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(users.get(), HttpStatus.FOUND);
    }
}
