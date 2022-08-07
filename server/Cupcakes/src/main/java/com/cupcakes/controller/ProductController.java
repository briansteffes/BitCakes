package com.cupcakes.controller;

import com.cupcakes.dao.ProductDao;
import com.cupcakes.model.Product;
import com.cupcakes.model.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductDao productDao;


    public ProductController(ProductDao productDao) {
        this.productDao = productDao;
    }

    @CrossOrigin
    @RequestMapping(value="")
    public List<Product> list() {
        return productDao.list();
    }

    @RequestMapping("/{id}")
    public Product getProductById(@PathVariable long id) throws ProductNotFoundException{
        return productDao.get(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path="", method=RequestMethod.POST)
    public boolean addProduct(@RequestBody Product product) {
        return productDao.save(product);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(path="/{id}", method=RequestMethod.PUT)
    public boolean updateProduct(@PathVariable long id, @RequestBody Product product) throws ProductNotFoundException {
        return productDao.update(id, product);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path="/{id}", method=RequestMethod.DELETE)
    public boolean deleteProduct(@PathVariable long id) throws ProductNotFoundException {
        return productDao.delete(id);
    }


}
