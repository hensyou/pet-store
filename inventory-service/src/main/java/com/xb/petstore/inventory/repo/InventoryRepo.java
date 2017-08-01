package com.xb.petstore.inventory.repo;

import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.xb.petstore.inventory.model.Pet;

@Repository
@ComponentScan("com.xb.petstore.inventory.model")
public interface InventoryRepo extends PagingAndSortingRepository<Pet, Long> {
    Pet findByName(String Name);
    
    @Procedure(procedureName="getAllPets")
    Object[] getAllPetsUsingStoreProcedure();
}
