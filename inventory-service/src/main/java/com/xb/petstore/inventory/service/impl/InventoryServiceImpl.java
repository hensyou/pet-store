package com.xb.petstore.inventory.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.xb.petstore.inventory.model.Pet;
import com.xb.petstore.inventory.repo.InventoryRepo;
import com.xb.petstore.inventory.service.InventoryService;
@Service
public class InventoryServiceImpl implements InventoryService {
	private static final Logger LOG = LoggerFactory.getLogger(InventoryServiceImpl.class);
	@Autowired
	InventoryRepo inventoryRepo;
	
	
	public InventoryServiceImpl(InventoryRepo inventoryRepo) {
    	this.inventoryRepo=inventoryRepo;
    }
	@Override
	public List<Pet> getAllPets() {
		LOG.info("retrieving all pets");
		return Lists.newArrayList(inventoryRepo.findAll());
	}

	@Override
	@Transactional(isolation=Isolation.SERIALIZABLE,propagation=Propagation.REQUIRES_NEW)
	public Pet createUpdatePet(Pet pet) {
		//Pet existingPet = inventoryRepo.findOne(pet.getId());
		pet = inventoryRepo.save(pet);
		return pet;
	}

	@Override
	public boolean deletePet(Long petId) {
		try {
			inventoryRepo.delete(petId);
			return true;
		} catch (EmptyResultDataAccessException e) {
			LOG.error("pet ID does't exists", e);
			return false;
		}
	}
	@Override
	public Pet getGetById(Long petId) {
		return inventoryRepo.findOne(petId);
	}
	

}
