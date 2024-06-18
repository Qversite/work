package com.example.demo.Service;

import com.example.demo.Entity.User;
import com.example.demo.Repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException{
        User user = userRepo.findByUsername(username);
        if (username==null){
            throw new UsernameNotFoundException("Пользователь не найден"+username);
        }
        List<SimpleGrantedAuthority>authorities=user.getRoles().stream().map(role -> new SimpleGrantedAuthority("Role_"+role.name())).collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
    }
}
