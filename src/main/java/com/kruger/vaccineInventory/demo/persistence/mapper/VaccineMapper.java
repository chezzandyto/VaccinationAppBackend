package com.kruger.vaccineInventory.demo.persistence.mapper;

import com.kruger.vaccineInventory.demo.domain.Vaccine;
import com.kruger.vaccineInventory.demo.persistence.entity.RegistroVacuna;
import com.kruger.vaccineInventory.demo.persistence.entity.Usuario;
import com.kruger.vaccineInventory.demo.persistence.entity.Vacuna;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Mapper(componentModel = "spring")
public interface VaccineMapper {

    @Mappings({
            @Mapping(source = "id", target = "vaccineId"),
            @Mapping(source = "vacuna.nombre", target = "name"),
            @Mapping(source = "idUsuario", target = "userId"),
            @Mapping(source = "fechaVacuna", target = "date"),
            @Mapping(source = "usuario", target = "user"),
    })
    Vaccine toVaccine(RegistroVacuna registroVacuna);
    List<Vaccine> toVaccines(List<RegistroVacuna> registroVacunas);

    @InheritInverseConfiguration
    @Mapping(target = "vacuna", ignore = true)
    RegistroVacuna toRegistroVacunas(Vaccine vaccine);
}
