package com.kruger.vaccineInventory.demo.persistence.crud;

import com.kruger.vaccineInventory.demo.persistence.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioCrud extends CrudRepository<Usuario, Integer> {
    List<Usuario> findAllByIdRol(int idRol);
    Optional<List<Usuario>> findAllByEstadoVacunacionOrderByApellidosAsc(boolean estadoVacunacion);
}
