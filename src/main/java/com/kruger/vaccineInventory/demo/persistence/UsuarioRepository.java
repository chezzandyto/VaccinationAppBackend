package com.kruger.vaccineInventory.demo.persistence;

import com.kruger.vaccineInventory.demo.domain.User;
import com.kruger.vaccineInventory.demo.domain.repository.UserRepository;
import com.kruger.vaccineInventory.demo.persistence.crud.UsuarioCrud;
import com.kruger.vaccineInventory.demo.persistence.entity.Usuario;
import com.kruger.vaccineInventory.demo.persistence.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioRepository implements UserRepository {
    @Autowired
    private UsuarioCrud usuarioCrud;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getAll() {
        return userMapper.toUsers((List<Usuario>) usuarioCrud.findAll());
    }

    @Override
    public Optional<List<User>> getByRole(int roleId) {
        List<Usuario> usuarios = usuarioCrud.findAllByIdRol(roleId);
        return Optional.of(userMapper.toUsers(usuarios));
    }

    @Override
    public Optional<User> getUser(int userId) {
        return usuarioCrud.findById(userId).map(usuario -> userMapper.toUser(usuario));
    }

    @Override
    public Optional<List<User>> getVaccinatedUsers(boolean vaccinationStatus) {
        Optional<List<Usuario>> usuarios = usuarioCrud.findAllByEstadoVacunacionOrderByApellidosAsc(vaccinationStatus);
        return usuarios.map(users -> userMapper.toUsers(users));
    }

    @Override
    public User save(User user) {
        Usuario usuario = userMapper.toUsuario(user);
        return userMapper.toUser(usuarioCrud.save(usuario));
    }

    @Override
    public void delete(int userId) {
        usuarioCrud.deleteById(userId);
    }
}
