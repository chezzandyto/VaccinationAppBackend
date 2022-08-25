package com.kruger.vaccineInventory.demo.persistence.crud;

import com.kruger.vaccineInventory.demo.persistence.entity.RegistroVacuna;
import com.kruger.vaccineInventory.demo.persistence.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface VaccineCrud extends CrudRepository<RegistroVacuna, Integer> {
    Optional<List<Usuario>> findAllByIdVacuna(int idVacuna);
    Optional<List<Usuario>> findAllByFechaVacunaBetween(
            Date desde, Date hasta);
}
