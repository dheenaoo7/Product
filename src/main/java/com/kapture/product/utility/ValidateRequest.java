package com.kapture.product.utility;

import com.kapture.product.dto.ProductDto;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ValidateRequest {
    public Boolean validate(ProductDto productDto) {
        String name = productDto.getName();
        Pattern pattern = Pattern.compile("\\d");
        Matcher matcher = pattern.matcher(name);
        return productDto.getId()>=0 && productDto.getClientId()>0 && productDto
                .getEmpId()>0 && !productDto.getName()
                .isEmpty() && !matcher.find() ;
    }
}
