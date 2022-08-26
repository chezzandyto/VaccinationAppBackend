package com.kruger.vaccineInventory.demo.domain.repository;

import com.kruger.vaccineInventory.demo.domain.User;
import com.kruger.vaccineInventory.demo.domain.Vaccine;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface VaccineRepository {
    List<Vaccine> getAll();
    Optional<Vaccine> getById(int vaccineId);
    Vaccine save(Vaccine vaccine);
    Optional<List<User>> getByVaccineType(int vaccineId);

    Optional<List<User>> getUsersVaccinatedByDateRange(Date from, Date to);
    void delete(int vaccineId);
}