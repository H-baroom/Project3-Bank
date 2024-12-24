package com.example.project3bankw10day3.Service;

import com.example.project3bankw10day3.Api.ApiException;
import com.example.project3bankw10day3.Model.MyUser;
import com.example.project3bankw10day3.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final AuthRepository authRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser myUser = authRepository.findMyUserByUsername(username);
        if (myUser == null) {
            throw new ApiException("user not found");
        }
        return myUser;
    }
}
