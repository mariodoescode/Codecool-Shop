package com.codecool.shop.dao.database;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoJdbc implements ProductDao {
    DataSource dataSource;

    public ProductDaoJdbc(DataSource datasource) {
        this.dataSource = datasource;
    }

    @Override
    public void add(Product product) {
        String productName = product.getName();
        int categoryId = getCategoryId(product.getProductCategory());
        int supplierId = getSupplierId(product.getSupplier());
        if(!find(productName)){
            try(Connection connection = dataSource.getConnection()){
                String sql = "INSERT INTO product (name, description, price, currency, quantity, category_id, supplier_id) VALUES (?, ? ,?, ?, ?, ? ,?)";
                PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                st.setString(1, productName);
                st.setString(2, product.getDescription());
                st.setBigDecimal(3, product.getDefaultPrice());
                st.setString(4, String.valueOf(product.getDefaultCurrency()));
                st.setInt(5, product.getQuantity());
                st.setInt(6, categoryId);
                st.setInt(7, supplierId);
                st.executeUpdate();
            } catch (SQLException throwable) {
                throw new RuntimeException("Error while adding product", throwable);
            }
        }
        else{
            System.out.printf("Product %s already exists", productName);
            System.out.println();
        }

    }

    public int getCategoryId (ProductCategory category){
        String categoryName = category.getName();
        try(Connection conn = dataSource.getConnection()){
            String sql = "SELECT id FROM category WHERE name = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, categoryName);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                return 0;
            }
            return rs.getInt(1);

        }
        catch(SQLException throwable){
            throw new RuntimeException("Error while getting category from database", throwable);
        }
    }

    public int getSupplierId (Supplier supplier){
        String supplierName = supplier.getName();
        try(Connection conn = dataSource.getConnection()){
            String sql = "SELECT id FROM supplier WHERE name = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, supplierName);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                return 0;
            }
            return rs.getInt(1);

        }
        catch(SQLException throwable){
            throw new RuntimeException("Error while getting category from database", throwable);
        }
    }

    @Override
    public Product find(int id) throws SQLException {
        Product product = null;
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM product WHERE id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            product = new Product(rs.getString(1), new BigDecimal(rs.getString(2)),
                    rs.getString(3), rs.getString(4),
                    rs.getString(5), rs.getString(6),
                    rs.getString(7), rs.getString(8), rs.getString(9));
            product.setId(id);
            return product;

        } catch (SQLException throwable) {
            throw new RuntimeException("Error while getting product with Id: " + id, throwable);
        }
    }

    public boolean find(String productName){
        try(Connection conn = dataSource.getConnection()){
            String sql = "SELECT id FROM product WHERE name = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, productName);
            ResultSet rs = st.executeQuery();
            return rs.next();
        }
        catch(SQLException throwable){
            throw new RuntimeException("Error while getting product:" + productName, throwable);
        }
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Product> getAll() {
        return getProducts(dataSource);
    }

    static List<Product> getProducts(DataSource dataSource) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT product.name, price, currency, product.description, category.name, supplier.name, " +
                    "category.department , category.description, supplier.description " +
                    "FROM product " +
                    "JOIN category ON category_id = category.id " +
                    "JOIN supplier ON supplier_id = supplier.id ";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            List<Product> products = new ArrayList<>();
            int i = 1;
            while (rs.next()) { // while result set pointer is positioned before or on last row read authors
                Product product = new Product(rs.getString(1), new BigDecimal(rs.getString(2)),
                        rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6),
                        rs.getString(7),rs.getString(8),rs.getString(9) );
                product.setId(i);
                products.add(product);
                i++;

            }
            return products;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading all products", e);
        }
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        int supplierId = supplier.getId();
        List<Product> products = new ArrayList<>();

        try(Connection connection = dataSource.getConnection()){
            String sql = "SELECT product.name, product.description, product.price, product.currency, " +
                    "category.name, category.department, category.description, " +
                    "supplier.name, supplier.description, product.id " +
                    "FROM product " +
                    "JOIN category ON product.category_id = category.id " +
                    "JOIN supplier ON product.category_id = supplier.id " +
                    "WHERE supplier_id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, supplierId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) { // while result set pointer is positioned before or on last row read authors
                ProductCategory category = new ProductCategory(rs.getString(5), rs.getString(6), rs.getString(7));
                supplier = new Supplier(rs.getString(8), rs.getString(9));
                Product product = new Product(rs.getString(1), rs.getBigDecimal(3), rs.getString(4), rs.getString(2), category, supplier);
                product.setId(rs.getInt(10));
                products.add(product);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("Error while reading products with supplier: " + supplier, e);
        }
        return products;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        int categoryId = productCategory.getId();
        List<Product> products = new ArrayList<>();

        try(Connection connection = dataSource.getConnection()){
            String sql = " SELECT product.name, product.description, product.price, product.currency, " +
                    "category.name, category.department, category.description, " +
                    "supplier.name, supplier.description, product.id " +
                    "FROM product " +
                    "JOIN category ON product.category_id = category.id " +
                    "JOIN supplier ON product.supplier_id = supplier.id " +
                    "WHERE category_id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, categoryId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                productCategory = new ProductCategory(rs.getString(5), rs.getString(6), rs.getString(7));
                Supplier supplier = new Supplier(rs.getString(8), rs.getString(9));
                Product product = new Product(rs.getString(1), rs.getBigDecimal(3), rs.getString(4), rs.getString(2), productCategory, supplier);
                product.setId(rs.getInt(10));
                products.add(product);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("Error while reading products with category: " + productCategory, e);
        }
        return products;

    }
}
