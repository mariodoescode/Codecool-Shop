package com.codecool.shop.dao.database;

import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.model.Product;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class ShoppingCartDaoJdbc implements ShoppingCartDao {

    DataSource dataSource;


    public ShoppingCartDaoJdbc(DataSource datasource) {
        this.dataSource = datasource;
    }


    @Override
    public void add(Product product) {
        int productId = product.getId();


    }

    @Override
    public Product find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public int countShoppingCarts() throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT count(user_id) FROM shopping_cart";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            if (!rs.next()) {
                return 0;
            }
            System.out.println("shopping carts count" + rs.getInt(1));
            return rs.getInt(1);

        }
    }

    @Override
    public void addUser(int lastUserID) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO shopping_cart (user_id) VALUES (?)";
            PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1,lastUserID);
            st.executeUpdate();
        } catch (SQLException throwable) {
            throw new RuntimeException("Error while adding user to shopping cart", throwable);
        }
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public String getTotalPrice() {
        return null;
    }
}
