package com.marcelopcx.fe_mall_backend_login.service;

import com.marcelopcx.fe_mall_backend_login.controller.models.AuthResponse;
import com.marcelopcx.fe_mall_backend_login.controller.models.AuthenticateRequest;
import com.marcelopcx.fe_mall_backend_login.controller.models.RegisterRequest;

public interface AuthService {
    public AuthResponse registerAdmin (RegisterRequest request);
    public AuthResponse authenticateAdmin (AuthenticateRequest request);
    public AuthResponse registerEmployee (RegisterRequest request);
    public AuthResponse authenticateEmployee (AuthenticateRequest request);
}
