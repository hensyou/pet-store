package com.xb.petstore.inventory.service;

import java.util.List;

import com.xb.petstore.inventory.model.Pet;

public interface InventoryService {
	List<Pet> getAllPets();

	Pet createUpdatePet(Pet pet);

    boolean deletePet(Long petId);
    
    Pet getGetById(Long petId);
}
