package com.kruger.vaccineInventory.demo.domain.service;

import com.kruger.vaccineInventory.demo.domain.User;
import com.kruger.vaccineInventory.demo.domain.repository.UserRepository;
import com.kruger.vaccineInventory.demo.persistence.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public List<User> getAll(){
        return userRepository.getAll();
    }

    public Optional<List<User>> getByRole(int roleId){
        return userRepository.getByRole(roleId);
    }

    public Optional<User> getUser(int userId){
        return userRepository.getUser(userId);
    }

    public Optional<List<User>> getVaccinatedUsers(boolean vaccinationStatus){
        return userRepository.getVaccinatedUsers(vaccinationStatus);
    }

    public User save(User user){
        return userRepository.save(user);
    }

    public boolean delete(int userId){
        return getUser(userId).map(user -> {
            userRepository.delete(userId);
            return true;
        }).orElse(false);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUserName(username);
        if(user.isEmpty()){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        Collection<GrantedAuthority> auths = new ArrayList();
        GrantedAuthority grantedAuthorities =  new SimpleGrantedAuthority("ROLE_" + roleRepository.getRoleDescription(user.get().getRoleId()).toUpperCase());
        auths.add(grantedAuthorities);
        return new org.springframework.security.core.userdetails.User(user.get().getUserName(), user.get().getPassword(), auths);

    }

}
