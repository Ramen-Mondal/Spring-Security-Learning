package com.hm.hospitalmanagement.security;

import com.hm.hospitalmanagement.dto.LoginRequestDto;
import com.hm.hospitalmanagement.dto.LoginResponseDto;
import com.hm.hospitalmanagement.dto.SignupResponseDto;
import com.hm.hospitalmanagement.entity.User;
import com.hm.hospitalmanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final  AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;
    private final PasswordEncoder passwordEncoder;

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword())
        );

        User user = (User) authentication.getPrincipal();
        String token = authUtil.generateAccessToken(user);
        return new LoginResponseDto(token,user.getId());
    }

    public SignupResponseDto signup(LoginRequestDto signupRequestDto) {
        User user = userRepository.findByUsername(signupRequestDto.getUsername()).orElse(null);
        if (user !=null) throw new IllegalArgumentException("User already exists");
        user = userRepository.save(User.builder()
                .username(signupRequestDto.getUsername())
                .password(passwordEncoder.encode(signupRequestDto.getPassword()))
                .build()
        );
        return new SignupResponseDto(user.getId(),user.getUsername());
    }


}
