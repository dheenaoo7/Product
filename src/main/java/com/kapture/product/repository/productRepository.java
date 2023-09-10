package com.kapture.product.repository;

import com.kapture.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface productRepository extends JpaRepository<Product, Integer> {

    Product findBySkuCode(String skucode);
}
