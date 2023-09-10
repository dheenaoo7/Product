package com.kapture.product.controller;

import com.kapture.product.dto.Paging;
import com.kapture.product.dto.ProductDto;
import com.kapture.product.service.productService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/products")
public class productController {
    @Autowired
    private productService productService;

    @PostMapping("/save-product")
    public ResponseEntity<?> saveProduct(@RequestBody @Validated ProductDto product){
        return productService.saveProduct(product);
    }
    @GetMapping("/get-products")
    public ResponseEntity<?> getProducts(@RequestBody @Validated Paging paging){
        return productService.getProducts(paging.getPageno(),paging.getPagesize());
    }
    @GetMapping("/get-product-by-id/{id}")
    public ResponseEntity<?> getProductById(@PathVariable int id){
        return productService.getProductById(id);
    }

    @GetMapping("/get-product-by-skucode/{skuCode}")
    public ResponseEntity<?> getProductsBySkuCode(@PathVariable String skuCode){
        return productService.getProductBySkuCode(skuCode);
    }

    @DeleteMapping("/disable-by-id/{id}")
    public ResponseEntity<?> disable(@PathVariable int id){
        return productService.disableById(id);
    }
}
