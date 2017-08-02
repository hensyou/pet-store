package com.xb.petstore.inventory;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.xb.petstore.inventory.model.Pet;
import com.xb.petstore.inventory.repo.InventoryRepo;


@RunWith(SpringRunner.class)
@SpringBootTest
@EntityScan("com.xb.petstore.inventory.model")
public class InventoryServiceApplicationTests {
	
	private static EntityManagerFactory factory = null;
    private static EntityManager entityManager = null;
    @Autowired
	InventoryRepo inventoryRepo;
    
    @PersistenceContext
    private EntityManager em;
    
    
    @BeforeClass
    public static void init() {
        //factory = Persistence.createEntityManagerFactory("jpa-db");
        //entityManager = factory.createEntityManager();
    }
    
    @Test
    @Ignore
    public void findPetsUsingStored() {
        StoredProcedureQuery storedProcedure = 
        		em
            .createStoredProcedureQuery("getAllPets",Pet.class);
//            .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
//            .setParameter(1, 2015);
        
        storedProcedure.getResultList()
          .forEach(p -> Assert.assertEquals(new Integer(2015), ((Pet) p).getPrice()));
    }
    
    @Test
    public void findPetsUsingStoredProc(){
    	List<Pet> allPets= (List<Pet>) inventoryRepo.findAll();
    	System.out.println(allPets.size());
    	Assert.assertNotNull(allPets);
    	int total=0;
    	int allPetsByStoreProc = inventoryRepo.getAllPetsUsingStoreProcedure(total);
    	Assert.assertEquals(allPets.size(), allPetsByStoreProc);
    	
    	String result=inventoryRepo.invokingFunctionTest("World");
    	
    	System.out.println(result);
    }
}
