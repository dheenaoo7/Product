package com.kapture.product.utility;

import org.springframework.stereotype.Component;

@Component
public class GenerateSkuCode {
    public String  generateCode(int id,String name){
        String stringId =String.valueOf(id);
        return name.substring(0,Math.min(4,name.length()))
                +stringId.substring(0,Math.min(4,stringId.length()));
    }
}
