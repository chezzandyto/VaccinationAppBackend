package com.kruger.vaccineInventory.demo.persistence.crud;

import com.kruger.vaccineInventory.demo.persistence.entity.Rol;
import org.springframework.data.repository.CrudRepository;

public interface RoleCrud extends CrudRepository<Rol, Integer> {
}
