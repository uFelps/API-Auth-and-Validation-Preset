package com.api.test.services;

import com.api.test.dto.user.UserDTO;
import com.api.test.dto.user.UserUpdateDTO;
import com.api.test.entities.User;
import com.api.test.enums.Role;
import com.api.test.repositories.UserRepository;
import com.api.test.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findByLogin(email);
    }

    public List<UserDTO> findAll() {

        List<User> users = repository.findAll();

        return users.stream().map(UserDTO::new).collect(Collectors.toList());

    }

    @Transactional
    public UserDTO atualizar(Long id,UserUpdateDTO dto){

       try{
           User user = repository.getReferenceById(id);
           copyDtoToEntity(dto, user);

           user = repository.save(user);

           return new UserDTO(user);
       }

       catch (EntityNotFoundException e){
           throw new ResourceNotFoundException("Id not found");
       }

    }

    private void copyDtoToEntity(UserUpdateDTO dto, User entity){
        entity.setName(dto.name());
        entity.setLogin(dto.login());
        entity.setSenha(passwordEncoder.encode(dto.senha()));

        if(dto.role().equals(Role.COORDENADOR.name())){
            entity.setRole(Role.COORDENADOR);
        }
        if(dto.role().equals(Role.PROFESSOR.name())){
            entity.setRole(Role.PROFESSOR);
        }
    }


}
