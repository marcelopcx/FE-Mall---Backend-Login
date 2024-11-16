package com.marcelopcx.fe_mall_backend_login.controller;

import com.marcelopcx.fe_mall_backend_login.entity.Stores;
import com.marcelopcx.fe_mall_backend_login.service.StoreService;
import com.marcelopcx.fe_mall_backend_login.controller.models.StoreRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/store")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @PostMapping("/create")
    public ResponseEntity<Stores> createStore(@RequestBody StoreRequest request) {
        Stores store = storeService.createStore(request);
        return ResponseEntity.ok(store);
    }
}