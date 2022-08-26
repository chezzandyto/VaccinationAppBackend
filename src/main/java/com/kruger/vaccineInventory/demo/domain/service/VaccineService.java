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

    public boolean saveById(Vaccine vaccine, int userId){
        Optional<User> user = userRepository.getUser(userId);
        if(vaccine.getDate() == null ) return false;
        if(vaccine.getUserId() == null) return false;
        if(vaccine.getVaccineId() == null) return false;
        if(vaccine.getName() == null) return false;
        if(!user.isEmpty()){
            user.get().setVaccineState(true);
            userRepository.save(user.get());
            vaccineRepository.save(vaccine);
            return true;
        }else {
            return false;
        }
    }

    public boolean saveByUserName(Vaccine vaccine, String userName){
        Optional<User> user = userRepository.findByUserName(userName);
        if(user.isEmpty()) return false;
        else{
            vaccine.setUserId(user.get().getId());
            user.get().setVaccineState(true);
            userRepository.save(user.get());
            vaccineRepository.save(vaccine);
            return true;
        }
    }

    public Optional<List<User>> getByVaccineType(int vaccineId){
        return vaccineRepository.getByVaccineType(vaccineId);
    }

    void delete(int vaccineId){
        vaccineRepository.delete(vaccineId);
    }

}
