package com.xb.petstore.inventory.controller;

import java.util.List;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import com.xb.petstore.inventory.model.Pet;
import com.xb.petstore.inventory.service.InventoryService;

@RestController
@RequestMapping("/v1")
public class InventoryController {
	private static final Logger LOG = LoggerFactory.getLogger(InventoryController.class);

	@Autowired
	InventoryService inventoryService;

	@GetMapping(value = "/pets", produces = "application/json")
	ResponseEntity<List<Pet>> getPets() {
		List<Pet> returnValue = this.inventoryService.getAllPets();
		if (returnValue != null) {
			LOG.info("All Pets returned");
			return ResponseEntity.ok(returnValue);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping(value = "/pets/{petId}", produces = "application/json")
	ResponseEntity<Pet> getPetById(@PathVariable Long petId) {
		Pet foundPet = this.inventoryService.getGetById(petId);
		if (foundPet != null) {
			LOG.info("found one pet");
			return ResponseEntity.ok(foundPet);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping(value = "/pets", produces = "application/json")
	ResponseEntity<Pet> createPet(@RequestBody @Valid Pet pet) {
		try {
			return ResponseEntity.ok(this.inventoryService.createUpdatePet(pet));
		} catch (RestClientException | DataIntegrityViolationException | ConstraintViolationException e) {
			LOG.error("error thrown during create/update of Pet", e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping(value = "/pets/{petId}",consumes = "application/json", produces = "application/json")
	ResponseEntity<Pet> updatePet(@PathVariable Long petId,@RequestBody @Valid Pet pet) {
		try {
			if(null==pet.getId()) pet.setId(petId);
			return ResponseEntity.ok(this.inventoryService.createUpdatePet(pet));
		} catch (RestClientException | DataIntegrityViolationException | ConstraintViolationException e) {
			LOG.error("error thrown during create/update of Pet", e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(value = "/pets/{petId}")
	ResponseEntity<String> deletePet(@PathVariable Long petId) {
		if (this.inventoryService.deletePet(petId))
			return ResponseEntity.ok("Pet deleted successfully");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Pet does not exist");
	}
}
