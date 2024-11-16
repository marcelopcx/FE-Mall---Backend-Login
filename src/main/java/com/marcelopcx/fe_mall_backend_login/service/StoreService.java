package com.marcelopcx.fe_mall_backend_login.service;

import com.marcelopcx.fe_mall_backend_login.controller.models.StoreRequest;
import com.marcelopcx.fe_mall_backend_login.entity.Stores;

public interface StoreService {
    Stores createStore(StoreRequest request);
}
