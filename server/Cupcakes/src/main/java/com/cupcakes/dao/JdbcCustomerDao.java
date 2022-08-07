package com.cupcakes.dao;

import com.cupcakes.model.Customer;
import com.cupcakes.model.CustomerNotFoundException;
//import com.cupcakes.model.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class JdbcCustomerDao implements CustomerDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcCustomerDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<Customer> list() {
        List<Customer> customers = new ArrayList<>();
        String sql = "select * from customer;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            customers.add(mapRowToCustomer(results));
        }
        return customers;
    }

    @Override
    public Customer get(long id) throws CustomerNotFoundException{
        Customer customer = null;
        String sql = "select * from customer where customer_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
        if (result.next()) customer = mapRowToCustomer(result);
        return customer;
    }

    @Override
    public boolean save(Customer customer) {
        String sql = "insert into customer (customer_id, fname, lname, birthday, username, password) " +
                     "values (default, ?, ?, ?, ?, ?);";
        return jdbcTemplate.update(sql, customer.getfName(), customer.getlName(), customer.getBirthday(),
                                        customer.getUsername(), customer.getPassword()) == 1;
    }

    @Override
    public boolean update(long id, Customer customer) {
        String sql = "update customer set fname = ?, lname = ?, birthday = ?, username = ?, password = ? " +
                     "where customer_id = ?;";
        return jdbcTemplate.update(sql, customer.getfName(), customer.getlName(), customer.getBirthday(),
                                        customer.getUsername(), customer.getPassword(), id) == 1;
    }


    private Customer mapRowToCustomer(SqlRowSet rs) {
        Customer customer = new Customer();
        customer.setCustomerId(rs.getLong("customer_id"));
        customer.setfName(rs.getString("fname"));
        customer.setlName(rs.getString("lname"));
        customer.setBirthday(rs.getDate("birthday"));
        customer.setUsername(rs.getString("username"));
        customer.setPassword(rs.getString("password"));
        return customer;
    }
}
