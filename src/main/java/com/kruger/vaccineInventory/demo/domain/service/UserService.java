package com.kruger.vaccineInventory.demo.domain.service;

import com.kruger.vaccineInventory.demo.domain.User;
import com.kruger.vaccineInventory.demo.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAll(){
        return userRepository.getAll();
    }

    public Optional<List<User>> getByRole(int roleId){
        return userRepository.getByRole(roleId);
    }

    public Optional<User> getUser(int userId){
        return userRepository.getUser(userId);
    }

    public Optional<List<User>> getVaccinatedUsers(boolean vaccinationStatus){
        return userRepository.getVaccinatedUsers(vaccinationStatus);
    }

    public User save(User user){
        return userRepository.save(user);
    }

    public boolean delete(int userId){
        return getUser(userId).map(user -> {
            userRepository.delete(userId);
            return true;
        }).orElse(false);
    }
}
