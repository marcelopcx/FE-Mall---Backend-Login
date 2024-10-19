package com.marcelopcx.fe_mall_backend_login.controller;

import com.marcelopcx.fe_mall_backend_login.controller.models.AuthResponse;
import com.marcelopcx.fe_mall_backend_login.controller.models.AuthenticateRequest;
import com.marcelopcx.fe_mall_backend_login.controller.models.RegisterRequest;
import com.marcelopcx.fe_mall_backend_login.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/admin/register")
    public ResponseEntity<AuthResponse> registerAdmin(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.registerEmployee(request));
    }

    @PostMapping("/employee/register")
    public ResponseEntity<AuthResponse> registerEmployee(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.registerAdmin(request));
    }

    @PostMapping("/admin/authenticate")
    public ResponseEntity<AuthResponse> authenticateAdmin(@RequestBody AuthenticateRequest request) {
        return ResponseEntity.ok(authService.authenticateAdmin(request));
    }

    @PostMapping("/employee/authenticate")
    public ResponseEntity<AuthResponse> authenticateEmployee(@RequestBody AuthenticateRequest request) {
        return ResponseEntity.ok(authService.authenticateEmployee(request));
    }
}