package com.example.demo.Service;

import com.example.demo.Entity.Role;
import com.example.demo.Entity.User;
import com.example.demo.Repo.UserRepo;
import com.example.demo.UsersDt.UsersDt;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;


    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public void registration(UsersDt usersDt) {
        if (userRepo.findByUsername(usersDt.getUsername()) != null) {
            throw new RuntimeException("Пользователь с таким именем уже зарегистрирован");
        }

        User user = new User();
        user.setUsername(usersDt.getUsername());
        user.setPassword(passwordEncoder.encode(usersDt.getPassword()));
        user.setPhoneNumber(usersDt.getPhoneNumber());
        user.setFullName(usersDt.getFullName());
        user.setEmail(usersDt.getEmail());
        user.setRoles(Set.of(Role.USER));
        userRepo.save(user);
    }

    public User findUser(String name){
        return userRepo.findByUsername(name);}

}
