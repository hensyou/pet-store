package com.xb.petstore.inventory.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xb.petstore.inventory.model.PurchaseOrder;

public interface PurchaseOrderRepo extends JpaRepository<PurchaseOrder, Long> {

}
