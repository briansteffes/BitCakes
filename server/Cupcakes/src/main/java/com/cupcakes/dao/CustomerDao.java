package com.cupcakes.dao;

import com.cupcakes.model.Customer;

import java.util.List;
import java.util.WeakHashMap;

public interface CustomerDao {
    List<Customer> list();
    Customer get(long id);
    boolean save(Customer customer);
    boolean update(long id, Customer customer);
//    boolean delete(long id);
}
