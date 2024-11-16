package com.marcelopcx.fe_mall_backend_login.repository;

import com.marcelopcx.fe_mall_backend_login.entity.Stores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Stores, Long> {
}
