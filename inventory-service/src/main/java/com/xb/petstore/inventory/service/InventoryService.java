package com.xb.petstore.inventory.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.xb.petstore.inventory.model.Pet;

public interface InventoryService {
	List<Pet> getAllPets();

	Pet createUpdatePet(Pet pet);
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    boolean deletePet(Long petId);
    
    Pet getGetById(Long petId);
}
