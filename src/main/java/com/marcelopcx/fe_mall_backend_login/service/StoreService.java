package com.marcelopcx.fe_mall_backend_login.service;
import com.marcelopcx.fe_mall_backend_login.entity.Store;
import com.marcelopcx.fe_mall_backend_login.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    public Store createStore(Store store) {
        return storeRepository.save(store);
    }

    public Store getStoreById(Long id) {
        return storeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Store not found"));
    }

    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    public Store updateStore(Long id, Store updatedStore) {
        // Obtener la tienda existente por ID
        Store existingStore = getStoreById(id);

        // Actualizar los campos de la tienda existente con los valores del objeto actualizado
        existingStore.setName(updatedStore.getName());
        existingStore.setDescription(updatedStore.getDescription());
        existingStore.setPhone(updatedStore.getPhone());
        existingStore.setEmail(updatedStore.getEmail());
        existingStore.setTaxId(updatedStore.getTaxId());

        // Actualizar las horas de apertura y cierre
        existingStore.setOpening(updatedStore.getOpening());
        existingStore.setClosing(updatedStore.getClosing());

        // Actualizar los detalles de ubicación
        existingStore.setStoreNumber(updatedStore.getStoreNumber());
        existingStore.setFloorNumber(updatedStore.getFloorNumber());

        // Actualizar el rol
        existingStore.setRole(updatedStore.getRole());

        // Guardar los cambios en el repositorio y devolver la tienda actualizada
        return storeRepository.save(existingStore);
    }

    public void deleteStore(Long id) {
        storeRepository.deleteById(id);
    }
}

