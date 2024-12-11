package com.marcelopcx.fe_mall_backend_login.controller.models;

import com.marcelopcx.fe_mall_backend_login.entity.Store;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String password;
    private Long storeId; // Agregar este campo
}