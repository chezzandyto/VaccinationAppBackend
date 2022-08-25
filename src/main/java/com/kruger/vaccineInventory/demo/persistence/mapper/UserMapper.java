package com.kruger.vaccineInventory.demo.persistence.mapper;

import com.kruger.vaccineInventory.demo.domain.User;
import com.kruger.vaccineInventory.demo.persistence.entity.RegistroVacuna;
import com.kruger.vaccineInventory.demo.persistence.entity.Rol;
import com.kruger.vaccineInventory.demo.persistence.entity.Usuario;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mappings({
            @Mapping(source = "nombres", target = "name"),
            @Mapping(source = "apellidos", target = "lastName"),
            @Mapping(source = "correo", target = "email"),
            @Mapping(source = "nombreUsuario", target = "userName"),
            @Mapping(source = "estadoVacunacion", target = "vaccineState"),
            @Mapping(source = "idRol", target = "roleId"),
            @Mapping(source = "cedula", target = "countryId"),
            @Mapping(source = "telefono", target = "phone"),
            @Mapping(source = "contrasena", target = "password"),
    })
    User toUser(Usuario usuario);
    List<User> toUsers(List<Usuario> usuarios);

    @InheritInverseConfiguration
    @Mapping(target = "direccion", ignore = true)
    @Mapping(target = "fechaNacimiento", ignore = true)
    Usuario toUsuario(User user);

}
