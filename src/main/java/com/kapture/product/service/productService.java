package com.kapture.product.service;

import com.kapture.product.dto.ProductDto;
import com.kapture.product.entity.Product;
import com.kapture.product.repository.productRepository;
import com.kapture.product.utility.GenerateSkuCode;
import com.kapture.product.utility.ValidateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class productService {
    @Autowired
    private productRepository productRespository;
    @Autowired
    private GenerateSkuCode generateSkuCode;
    @Autowired
    private ValidateRequest validateRequest;

    public ResponseEntity<?> saveProduct(ProductDto productDto) {
        if (!validateRequest.validate(productDto)) {
            return new ResponseEntity<>("check the paramaters", HttpStatus.BAD_REQUEST);
        }

        if (productDto.getId() > 0) {
            Product forSavingProduct = new Product(productDto.getId(), productDto.getClientId(),
                    productDto.getEmpId(), productDto.getName(), 1);
            forSavingProduct.setSkuCode(generateSkuCode.generateCode(productDto.getId(),productDto.getName()));
            return new ResponseEntity<>(productRespository.saveAndFlush(forSavingProduct), HttpStatus.OK);

        } else {

            Product forSavingProduct = new Product(productDto.getClientId(), productDto.getEmpId(),
                    productDto.getName(), 1);
            Product savedProduct = productRespository.saveAndFlush(forSavingProduct);
            savedProduct.setSkuCode(generateSkuCode.generateCode(savedProduct.getId(), savedProduct.getName()));
            return new ResponseEntity<>(productRespository.save(savedProduct), HttpStatus.OK);

        }
    }

    public ResponseEntity<?> getProducts(int pageNo, int pageSize) {
        Page<Product> page = productRespository.findAll(PageRequest.of(pageNo, pageSize));
        List<Product> products = page.getContent();
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    public ResponseEntity<?> getProductById(int id) {
        Product product = (Product) productRespository.findById(id).orElse(null);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    public ResponseEntity<?> getProductBySkuCode(String skucode) {
        if (skucode.matches("^[A-Za-z]{0,4}[0-9]{0,4}$")) {
            Product product = (Product) productRespository.findBySkuCode(skucode);
            if (product == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> disableById(int id) {
        Product product = (Product) productRespository.findById(id).orElse(null);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        product.setEnabled(0);
        return new ResponseEntity<>(productRespository.save(product), HttpStatus.OK);
    }


}
