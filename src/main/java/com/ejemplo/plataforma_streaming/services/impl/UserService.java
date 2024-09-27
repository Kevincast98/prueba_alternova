package com.ejemplo.plataforma_streaming.services.impl;

import com.ejemplo.plataforma_streaming.models.Usuario;
import com.ejemplo.plataforma_streaming.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Loads a user by their username.
     *
     * @param username The username of the user to be loaded.
     * @return UserDetails object containing user information for authentication.
     * @throws UsernameNotFoundException if the user is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Usuario> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Error: User not found.");
        }
        return org.springframework.security.core.userdetails.User
                .withUsername(user.get().getEmail())
                .password(String.valueOf(user.get().getPassword()))
                .build();
    }
}
