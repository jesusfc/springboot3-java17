package com.jesusfc.springboot3java17.controller.rest;

import com.jesusfc.springboot3java17.openApi.v1.api.IProduct;
import com.jesusfc.springboot3java17.openApi.v1.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;

@RestController
@Slf4j
@RequestMapping("/rest/")
public class ProductRestController implements IProduct {
    @Override
    public ResponseEntity<List<Product>> getProductList(Locale locale) {
        return null;
    }

}
