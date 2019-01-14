package com.main.lms.dao;

import com.main.lms.entities.Customer;
import com.main.lms.utils.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CustomerDaoImpl implements CustomerDao<Customer> {
    @Override
    public Optional<Customer> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Customer> getAll(DBConnect connection) {
        return null;
    }

    @Override
    public void save(Customer customer, DBConnect connection) {
        String sql="INSERT into customer(cid,username) VALUES (?,?)";
        try (Connection conn=connection.getConnection();
             PreparedStatement pstmt=conn.prepareStatement(sql)){
            pstmt.setInt(1,customer.getId());
            pstmt.setString(2,customer.getName());
            int rowsAffected = pstmt.executeUpdate();
            System.out.println(rowsAffected+ " added to db");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Customer customer, DBConnect connection) {

    }

    @Override
    public void delete(Customer customer, DBConnect connection) {

    }
}
