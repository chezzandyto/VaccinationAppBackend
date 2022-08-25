package com.kruger.vaccineInventory.demo.domain.service;

import com.kruger.vaccineInventory.demo.domain.User;
import com.kruger.vaccineInventory.demo.domain.Vaccine;
import com.kruger.vaccineInventory.demo.domain.repository.UserRepository;
import com.kruger.vaccineInventory.demo.domain.repository.VaccineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VaccineService {
    @Autowired
    private VaccineRepository vaccineRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Vaccine> getAll(){
        return vaccineRepository.getAll();
    }

    public Optional<Vaccine> getById(int vaccineId){
        return vaccineRepository.getById(vaccineId);
    }

    public Optional<Vaccine> save(Vaccine vaccine){
        Integer userId = vaccine.getUserId();
        Optional<User> user = userRepository.getUser(userId);
        if(!user.isEmpty()){
            user.get().setVaccineState(true);
            userRepository.save(user.get());
            return Optional.of(vaccineRepository.save(vaccine));
        }else {
            return Optional.empty();
        }
    }

    public Optional<List<User>> getByVaccineType(int vaccineId){
        return vaccineRepository.getByVaccineType(vaccineId);
    }

    void delete(int vaccineId){
        vaccineRepository.delete(vaccineId);
    }

}
