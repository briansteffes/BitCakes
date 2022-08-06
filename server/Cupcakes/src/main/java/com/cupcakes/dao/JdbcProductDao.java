package com.cupcakes.dao;

import com.cupcakes.model.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcProductDao implements ProductDao {
    private JdbcTemplate jdbcTemplate;

    public JdbcProductDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Product> list() {
        List<Product> products = new ArrayList<>();
        String sql = "select * from product";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while(results.next()) {
            Product product = mapRowToProduct(results);
            products.add(product);
        }

        return products;
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
