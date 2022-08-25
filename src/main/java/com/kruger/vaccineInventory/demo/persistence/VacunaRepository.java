package com.kruger.vaccineInventory.demo.persistence;

import com.kruger.vaccineInventory.demo.domain.User;
import com.kruger.vaccineInventory.demo.domain.Vaccine;
import com.kruger.vaccineInventory.demo.domain.repository.VaccineRepository;
import com.kruger.vaccineInventory.demo.persistence.crud.VaccineCrud;
import com.kruger.vaccineInventory.demo.persistence.entity.RegistroVacuna;
import com.kruger.vaccineInventory.demo.persistence.entity.Usuario;
import com.kruger.vaccineInventory.demo.persistence.mapper.UserMapper;
import com.kruger.vaccineInventory.demo.persistence.mapper.VaccineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class VacunaRepository implements VaccineRepository {

    @Autowired
    private VaccineCrud vaccineCrud;

    @Autowired
    private VaccineMapper vaccineMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Vaccine> getAll() {
        return vaccineMapper.toVaccines((List<RegistroVacuna>) vaccineCrud.findAll());
    }

    @Override
    public Optional<Vaccine> getById(int vaccineId) {
        return vaccineCrud.findById(vaccineId).map(vaccine -> vaccineMapper.toVaccine(vaccine));
    }

    @Override
    public Vaccine save(Vaccine vaccine) {
        RegistroVacuna registroVacuna = vaccineMapper.toRegistroVacunas(vaccine);
        return vaccineMapper.toVaccine(registroVacuna);
    }

    @Override
    public Optional<List<User>> getByVaccineType(int vaccineId) {
        return vaccineCrud.findAllByIdVacuna(vaccineId).map(usuarios -> userMapper.toUsers(usuarios));
    }

    @Override
    public void delete(int vaccineId) {
        vaccineCrud.deleteById(vaccineId);
    }
}