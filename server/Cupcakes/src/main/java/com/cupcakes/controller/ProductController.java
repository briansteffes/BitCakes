package com.cupcakes.controller;

import com.cupcakes.dao.ProductDao;
import com.cupcakes.model.Product;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductDao productDao;


    public ProductController(ProductDao productDao) {
        this.productDao = productDao;
    }

    @RequestMapping(value="")
    public List<Product> list() {
        return productDao.list();
    }
}
