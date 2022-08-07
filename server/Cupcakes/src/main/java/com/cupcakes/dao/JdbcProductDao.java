package com.cupcakes.dao;

import com.cupcakes.model.Product;
import com.cupcakes.model.ProductNotFoundException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcProductDao implements ProductDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcProductDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Product> list() {
        List<Product> products = new ArrayList<>();
        String sql = "select * from product;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            Product product = mapRowToProduct(results);
            products.add(product);
        }
        return products;
    }

    @Override
    public Product get(long id) {
        Product product = null;
        String sql = "select * from product where product_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
        if (result.next()) {
            product = mapRowToProduct(result);
        } else {
            throw new ProductNotFoundException();
        }
        return product;
    }

    @Override
    public boolean save(Product product) {
        String sql = "insert into product (product_id, name, inventory, price) values (default, ?, ?, ?);";
        return jdbcTemplate.update(sql, product.getProductName(), product.getProductCount(), product.getProductPrice()) == 1;
    }

    @Override
    public boolean update(long id, Product product) {
        String sql = "update product set name = ?, inventory = ?, price = ? where product_id = ?;";
        return jdbcTemplate.update(sql, product.getProductName(), product.getProductCount(), product.getProductPrice(), id) == 1;
    }

    @Override
    public boolean delete(long id) {
        //because the table is many to many, we will need to delete all instances of the product in the product_customer table first.
        //GOOD TO KNOW: you can stack queries in the sql String.
        String sql = "delete from product_customer where product_id = ?;delete from product where product_id = ?;";
        return jdbcTemplate.update(sql, id, id) == 1;
    }


    private Product mapRowToProduct(SqlRowSet rs) {
        Product product = new Product();
        product.setProductId(rs.getLong("product_id"));
        product.setProductName(rs.getString("name"));
        product.setProductCount(rs.getInt("inventory"));
        product.setProductPrice(rs.getBigDecimal("price"));
        return product;
    }

}
