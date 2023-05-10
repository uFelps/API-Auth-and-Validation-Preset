package com.api.test.services.auth;

import com.api.test.dto.token.TokenDTO;
import com.api.test.dto.user.UserLoginDTO;
import com.api.test.dto.user.UserSignupDTO;
import com.api.test.entities.User;
import com.api.test.enums.Role;
import com.api.test.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    public TokenDTO login(UserLoginDTO dto){
        var authenticationToken = new UsernamePasswordAuthenticationToken(dto.login(), dto.senha());
        var authentication = authenticationManager.authenticate(authenticationToken);

        var tokenJWT = tokenService.gerarToken((User) authentication.getPrincipal());

        return new TokenDTO(tokenJWT);
    }

    public TokenDTO signUp(UserSignupDTO dto) {

        User user = new User();
        user.setName(dto.name());
        user.setLogin(dto.login());
        user.setSenha(passwordEncoder.encode(dto.senha()));

        if(dto.role().equals(Role.COORDENADOR.name())){
            user.setRole(Role.COORDENADOR);
        }
        if(dto.role().equals(Role.PROFESSOR.name())){
            user.setRole(Role.PROFESSOR);
        }

        userRepository.save(user);

        var tokenJWT = tokenService.gerarToken(user);

        return new TokenDTO(tokenJWT);

    }
}
