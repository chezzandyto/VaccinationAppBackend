package com.kruger.vaccineInventory.demo.persistence;

import com.kruger.vaccineInventory.demo.persistence.crud.RoleCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleRepository {

    @Autowired
    private RoleCrud roleCrud;

    public String getRoleDescription(int idRole){
        return roleCrud.findById(idRole).get().getDescripcion();
    }
}
