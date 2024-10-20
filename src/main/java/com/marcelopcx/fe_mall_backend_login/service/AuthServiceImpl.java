package com.marcelopcx.fe_mall_backend_login.service;

import com.marcelopcx.fe_mall_backend_login.controller.models.AuthResponse;
import com.marcelopcx.fe_mall_backend_login.controller.models.AuthenticateRequest;
import com.marcelopcx.fe_mall_backend_login.controller.models.RegisterRequest;
import com.marcelopcx.fe_mall_backend_login.entity.Role;
import com.marcelopcx.fe_mall_backend_login.entity.User;
import com.marcelopcx.fe_mall_backend_login.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse registerAdmin(RegisterRequest request) {
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .address(request.getAddress())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ADMIN)
                .build();
        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);
        var tokenDetails = generateTokenDetails(jwtToken);

        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthResponse authenticateAdmin(AuthenticateRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findUserByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);

        var tokenDetails = generateTokenDetails(jwtToken);

        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthResponse registerEmployee (RegisterRequest request) {
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .address(request.getAddress())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.EMPLOYEE)
                .build();
        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);

        var tokenDetails = generateTokenDetails(jwtToken);

        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthResponse authenticateEmployee (AuthenticateRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findUserByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);

        var tokenDetails = generateTokenDetails(jwtToken);

        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

    private Map<String, Object> generateTokenDetails(String jwtToken) {
        Map<String, Object> tokenDetails = new HashMap<>();
        tokenDetails.put("issuedAt", jwtService.getIssuedAt(jwtToken));
        tokenDetails.put("expiration", jwtService.getExpiration(jwtToken));
        return tokenDetails;
    }
}