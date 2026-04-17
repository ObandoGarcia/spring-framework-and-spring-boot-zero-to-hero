package com.obando.crud_users.service.impl;

import com.obando.crud_users.model.Role;
import com.obando.crud_users.model.User;
import com.obando.crud_users.repository.RoleRepository;
import com.obando.crud_users.repository.UserRepository;
import com.obando.crud_users.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public User save(User user) {
        Optional<Role> optionalRole = roleRepository.findByName("ROLE_USER");

        List<Role> roles = new ArrayList<>();
        optionalRole.ifPresent(roles::add);

        if (user.isAdmin()){
            Optional<Role> optionalRoleAdmin = roleRepository.findByName("ROLE_ADMIN");
            optionalRoleAdmin.ifPresent(roles::add);
        }

        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);

        return userRepository.save(user);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
