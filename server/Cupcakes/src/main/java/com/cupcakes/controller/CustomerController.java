package com.cupcakes.controller;

import com.cupcakes.dao.CustomerDao;
import com.cupcakes.model.Customer;
import com.cupcakes.model.CustomerNotFoundException;
import com.cupcakes.model.Product;
import com.cupcakes.model.ProductNotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerDao customerDao;

    public CustomerController(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @CrossOrigin
    @RequestMapping(value="")
    public List<Customer> list() {
        return customerDao.list();
    }

    @RequestMapping("/{id}")
    public Customer getCustomerById(@PathVariable long id) throws CustomerNotFoundException{
        return customerDao.get(id);
    }

//    @ResponseStatus(HttpStatus.ACCEPTED)
//    @RequestMapping(path="")
//    public boolean authenticateCustomer(@RequestParam String username, @RequestParam String password) {
//        return false;
//    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path="", method=RequestMethod.POST)
    public boolean addCustomer(@RequestBody Customer customer) {
        return customerDao.save(customer);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(path="/{id}", method=RequestMethod.PUT)
    public boolean updateCustomer(@PathVariable long id, @RequestBody Customer customer) throws CustomerNotFoundException {
        return customerDao.update(id, customer);
    }


}
