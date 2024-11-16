package com.marcelopcx.fe_mall_backend_login.service;

import com.marcelopcx.fe_mall_backend_login.entity.Stores;
import com.marcelopcx.fe_mall_backend_login.repository.StoreRepository;
import com.marcelopcx.fe_mall_backend_login.controller.models.StoreRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;

    @Override
    public Stores createStore(StoreRequest request) {
        Stores store = Stores.builder()
                .rif(request.getRif())
                .name(request.getName())
                .description(request.getDescription())
                .phone(request.getPhone())
                .floor(request.getFloor())
                .local_number(request.getLocalNumber())
                .build();
        return storeRepository.save(store);
    }
}