package com.marcelopcx.fe_mall_backend_login.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "stores")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    @Column(nullable = false, unique = true)
    private String phone;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String taxId; // RIF (Registro de Información Fiscal)

    @Column(name = "opening_time", nullable = true)
    private LocalTime opening;

    @Column(name = "closing_time", nullable = true)
    private LocalTime closing;

    @Column(name = "store_number", nullable = true)
    private String storeNumber;

    @Column(name = "floor_number", nullable = true)
    private String floorNumber;

    @Enumerated(EnumType.STRING)
    private StoreRole role;
}