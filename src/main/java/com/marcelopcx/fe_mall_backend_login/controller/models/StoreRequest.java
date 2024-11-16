package com.marcelopcx.fe_mall_backend_login.controller.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreRequest {
    private String rif;
    private String name;
    private String description;
    private String phone;
    private int floor;
    private int localNumber;
}