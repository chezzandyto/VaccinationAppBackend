package com.kruger.vaccineInventory.demo.domain.repository;

import com.kruger.vaccineInventory.demo.domain.User;
import com.kruger.vaccineInventory.demo.domain.Vaccine;

import java.util.List;
import java.util.Optional;

public interface VaccineRepository {
    List<Vaccine> getAll();
    Optional<Vaccine> getById(int vaccineId);
    Vaccine save(Vaccine vaccine);
    Optional<List<User>> getByVaccineType(int vaccineId);
    void delete(int vaccineId);
}