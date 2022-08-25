package com.kruger.vaccineInventory.demo.domain.repository;

import com.kruger.vaccineInventory.demo.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> getAll();
    Optional<List<User>> getByRole(int roleId);
    Optional<User> getUser(int userId);
    Optional<List<User>> getVaccinatedUsers(boolean vaccinationStatus);
    //Optional<List<User>> getByVaccineType(int vaccineId);
    User save(User user);
    void delete(int userId);
}
