package com.main.lms.dao;

import com.main.lms.entities.Vendor;
import com.main.lms.utils.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class VendorDaoImpl implements VendorDao<Vendor> {

    @Override
    public ResultSet get(Vendor vendor, DBConnect connect) {
        String sql = "SELECT isbn,bookname,price,author,publisher FROM vendor WHERE vid=?";
        try  {
            Connection conn = connect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, vendor.getVid());
            ResultSet rs = pstmt.executeQuery();
            return rs;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public ResultSet getBook(Vendor vendor,int isbn, DBConnect connect) {
        String sql = "SELECT isbn,bookname,price,author,publisher FROM vendor WHERE vid=? AND isbn=?";
        try  {
            Connection conn = connect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, vendor.getVid());
            pstmt.setInt(2,isbn);
            ResultSet rs = pstmt.executeQuery();
            return rs;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public ResultSet getAll(DBConnect connection) {
        String sql = "SELECT vid,name,isbn,bookname,price,author,publisher FROM vendor";
        try {
            Connection conn = connection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            return rs;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void save(Vendor vendor, DBConnect connection) {
        String sql = "INSERT INTO vendor(vid,name,isbn,bookname,price,author,publisher) VALUES (?,?,?,?,?,?,?)";
        try (Connection conn = connection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, vendor.getVid());
            pstmt.setString(2, vendor.getName());
            pstmt.setInt(3, vendor.getIsbn());
            pstmt.setString(4, vendor.getBookName());
            pstmt.setInt(5, vendor.getPrice());
            pstmt.setString(6, vendor.getAuthor());
            pstmt.setString(7, vendor.getPublisher());
            int rowsUpdated = pstmt.executeUpdate();
            System.out.println(rowsUpdated + " inserted in db");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Vendor vendor, DBConnect connection) {

    }

    @Override
    public void delete(Vendor vendor, DBConnect connection) {

    }
}
