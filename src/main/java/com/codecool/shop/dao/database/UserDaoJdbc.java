package com.codecool.shop.dao.database;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.User;

import javax.sql.DataSource;
import java.sql.*;

public class UserDaoJdbc implements UserDao {

    private DataSource dataSource;

    public UserDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(User user) throws SQLException {
        try (Connection connection = dataSource.getConnection()){
            String query = "INSERT INTO \"users\" (email, password, name, phone_number, billing_country, billing_city, " +
                    "billing_zip, billing_address, shipping_country, shipping_city, shipping_zip, shipping_address) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement st = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, user.getEmail());
            st.setString(2, user.getPassword());
            st.setString(3, user.getName());
            st.setString(4, user.getPhone_number());
            st.setString(5, user.getBilling_country());
            st.setString(6, user.getBilling_city());
            st.setString(7, user.getBilling_zipcode());
            st.setString(8, user.getBilling_address());
            st.setString(9, user.getShipping_country());
            st.setString(10, user.getShipping_city());
            st.setString(11, user.getShipping_zipcode());
            st.setString(12, user.getShipping_address());

            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            rs.next();
        } catch(SQLException e){
            throw new RuntimeException("Error while adding user.", e);
        }
    }

    @Override
    public User find(int id){
        return null;
    }

    @Override
    public User findByEmail(String email) throws SQLException {
        try (Connection connection = dataSource.getConnection()){
            String query = "SELECT id, email, password, name, billing_country, billing_city, billing_zip, billing_address, phone_number, shipping_address, shipping_city, shipping_country, shipping_zip FROM \"users\" WHERE email = ? ";
            PreparedStatement st = connection.prepareStatement(query);
//            st.setString(1, " ' " + email + " ' ");
            st.setString(1, email);
            ResultSet resultSet = st.executeQuery();
//            st.executeUpdate();
            if (!resultSet.next()) {
                return null;
            }
            int id = resultSet.getInt(1);
            String emailAddress = resultSet.getString(2);
            String password = resultSet.getString(3);
            String name = resultSet.getString(4);
            String phone_number = resultSet.getString(9);
            String billing_country = resultSet.getString(5);
            String billing_city = resultSet.getString(6);
            String billing_zipCode = resultSet.getString(7);
            String billing_address = resultSet.getString(8);

            String shipping_address = resultSet.getString(10);
            String shipping_country = resultSet.getString(11);
            String shipping_city = resultSet.getString(12);
            String shipping_zip = resultSet.getString(13);


            User user = new User(emailAddress, password, name);
            user.setId(id);
            user.setBilling_address(billing_address);
            user.setBilling_city(billing_city);
            user.setBilling_country(billing_country);
            user.setBilling_zipcode(billing_zipCode);
            user.setPhone_number(phone_number);
            user.setShipping_address(shipping_address);
            user.setShipping_country(shipping_country);
            user.setShipping_city(shipping_city);
            user.setShipping_zipcode(shipping_zip);
            return user;
        } catch (SQLException throwables){
            throw new RuntimeException("Error while finding email in database", throwables);
        }
    }



}
