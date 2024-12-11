package com.marcelopcx.fe_mall_backend_login.service;

import com.marcelopcx.fe_mall_backend_login.controller.models.AuthResponse;
import com.marcelopcx.fe_mall_backend_login.controller.models.AuthenticateRequest;
import com.marcelopcx.fe_mall_backend_login.controller.models.RegisterRequest;
import com.marcelopcx.fe_mall_backend_login.entity.Store;
import com.marcelopcx.fe_mall_backend_login.entity.UserRole;
import com.marcelopcx.fe_mall_backend_login.entity.User;
import com.marcelopcx.fe_mall_backend_login.repository.StoreRepository;
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
    private final StoreRepository storeRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse registerAdmin(RegisterRequest request) {
        var store = getStoreById(request.getStoreId());

        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .address(request.getAddress())
                .password(passwordEncoder.encode(request.getPassword()))
                .userRole(UserRole.ADMIN)
                .store(store)
                .build();
        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);

        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthResponse authenticateAdmin(AuthenticateRequest request) {
        // Autenticar las credenciales
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        // Buscar el usuario autenticado
        var user = userRepository.findUserByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con el email: " + request.getEmail()));

        // Obtener la tienda asociada al usuario
        var store = user.getStore();
        if (store == null) {
            throw new RuntimeException("El usuario no tiene una tienda asociada.");
        }

        // Generar el token JWT
        var jwtToken = jwtService.generateToken(user);

        // Construir y devolver la respuesta de autenticación
        return AuthResponse.builder()
                .token(jwtToken)
                .storeId(store.getId())        // ID de la tienda asociada
                .storeRole(store.getRole().name()) // Rol de la tienda como cadena
                .build();
    }


    private Long getStoreId(User user) {
        var store = user.getStore();
        if (store == null) {
            throw new RuntimeException("El usuario no tiene una tienda asociada");
        }
        return store.getId();
    }

    @Override
    public AuthResponse registerEmployee(RegisterRequest request) {
        var store = getStoreById(request.getStoreId());

        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .address(request.getAddress())
                .password(passwordEncoder.encode(request.getPassword()))
                .userRole(UserRole.EMPLOYEE)
                .store(store)
                .build();
        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);

        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthResponse authenticateEmployee(AuthenticateRequest request) {
        // Autenticar las credenciales
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        // Buscar el usuario autenticado
        var user = userRepository.findUserByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con el email: " + request.getEmail()));

        // Obtener la tienda asociada al usuario
        var store = user.getStore();
        if (store == null) {
            throw new RuntimeException("El usuario no tiene una tienda asociada.");
        }

        // Generar el token JWT
        var jwtToken = jwtService.generateToken(user);

        // Construir y devolver la respuesta de autenticación
        return AuthResponse.builder()
                .token(jwtToken)
                .storeId(store.getId())        // ID de la tienda asociada
                .storeRole(store.getRole().name()) // Rol de la tienda como cadena
                .build();
    }

    private Map<String, Object> generateTokenDetails(String jwtToken) {
        Map<String, Object> tokenDetails = new HashMap<>();
        tokenDetails.put("issuedAt", jwtService.getIssuedAt(jwtToken));
        tokenDetails.put("expiration", jwtService.getExpiration(jwtToken));
        return tokenDetails;
    }

    private Store getStoreById(Long storeId) {
        return storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("Tienda no encontrada con el ID: " + storeId));
    }
}