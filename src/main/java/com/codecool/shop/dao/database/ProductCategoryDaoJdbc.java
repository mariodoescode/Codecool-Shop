package com.codecool.shop.dao.database;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoJdbc implements ProductCategoryDao {
    private final DataSource dataSource;

    public ProductCategoryDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(ProductCategory category) {
        if(!find(category.getName())){
            try(Connection connection = dataSource.getConnection()){
                String sql = "INSERT INTO category (name, description, department) VALUES (?, ? ,?)";
                PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                st.setString(1, category.getName());
                st.setString(2, category.getDescription());
                st.setString(3, category.getDepartment());
                st.executeUpdate();
            } catch (SQLException throwables) {
                throw new RuntimeException("Error while adding product category", throwables);
            }
        }
        else{
            System.out.printf("Category %s already exists", category.getName());
            System.out.println();
        }
    }

    @Override
    public ProductCategory find(int id) {
        try(Connection conn = dataSource.getConnection()){
            String sql = "SELECT name, description, department FROM category WHERE id=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                return null;
            }
            ProductCategory category = new ProductCategory(rs.getString(1), rs.getString(2), rs.getString(3));
            category.setId(id);
            return category;
        }
        catch(SQLException e){
            throw new RuntimeException("Error while reading category with id " + id, e);
        }
    }

    public boolean find(String categoryName){
        try(Connection conn = dataSource.getConnection()){
            String sql = "SELECT id FROM category WHERE name = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, categoryName);
            ResultSet rs = st.executeQuery();
            return rs.next();
        }
        catch(SQLException throwables){
            throw new RuntimeException("Error while getting category: " + categoryName, throwables);
        }
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<ProductCategory> getAll() {
        try(Connection conn = dataSource.getConnection()) {
            String sql = "SELECT name, description, department FROM category ";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            List<ProductCategory> categories = new ArrayList<>();
            int i = 1;
            while (rs.next()) { // while result set pointer is positioned before or on last row read authors
                ProductCategory category = new ProductCategory(rs.getString(1), rs.getString(2), rs.getString(3));
                category.setId(i);
                categories.add(category);
                i++;

            }
            return categories;
        }

        catch(SQLException throwables){
            throw new RuntimeException("Error while getting categories ", throwables);
        }
    }
}
