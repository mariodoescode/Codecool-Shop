package com.codecool.shop.dao.database;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoJdbc implements SupplierDao {
    private final DataSource dataSource;

    public SupplierDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(Supplier supplier) {
        if(!find(supplier.getName())){
            try(Connection connection = dataSource.getConnection()){
                String sql = "INSERT INTO supplier (name, description) VALUES (?, ?)";
                PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                st.setString(1, supplier.getName());
                st.setString(2, supplier.getDescription());
                st.executeUpdate();
            } catch (SQLException throwable) {
                throw new RuntimeException("Error while adding supplier", throwable);
            }
        }
        else{
            System.out.printf("Supplier %s already exists", supplier.getName());
            System.out.println();
        }
    }

    @Override
    public Supplier find(int id) {
        try(Connection conn = dataSource.getConnection()){
            String sql = "SELECT name, description FROM supplier WHERE id=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                return null;
            }
            Supplier supplier = new Supplier(rs.getString(1), rs.getString(2));
            supplier.setId(id);
            return supplier;
        }
        catch(SQLException e){
            throw new RuntimeException("Error while reading supplier with id " + id, e);
        }
    }

    public boolean find(String supplierName){
        try(Connection conn = dataSource.getConnection()){
            String sql = "SELECT id FROM supplier WHERE name = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, supplierName);
            ResultSet rs = st.executeQuery();
            return rs.next();
        }
        catch(SQLException throwable){
            throw new RuntimeException("Error while getting supplier: " + supplierName, throwable);
        }
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Supplier> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT name, description FROM supplier";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            List<Supplier> suppliers = new ArrayList<>();
            int i = 1;
            while (rs.next()) { // while result set pointer is positioned before or on last row read authors
                Supplier supplier = new Supplier(rs.getString(1), rs.getString(2));
                supplier.setId(i);
                suppliers.add(supplier);
                i++;
            }
            return suppliers;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading all suppliers", e);
        }
    }
}
