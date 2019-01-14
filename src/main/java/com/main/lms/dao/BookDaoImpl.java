package com.main.lms.dao;

import com.main.lms.entities.Book;
import com.main.lms.utils.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class BookDaoImpl implements BookDao<Book> {


    @Override
    public ResultSet get(Book book,DBConnect connection) {
        String sql = "SELECT isbn,name,price,author,publisher FROM books WHERE isbn=?";
        try  {
            Connection conn = connection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, book.getIsbn());
            ResultSet rs = pstmt.executeQuery();
            return rs;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public ResultSet getAll(DBConnect connection) {
        String sql="SELECT isbn,name,publisher,author,price FROM books";
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
    public void save(Book book,DBConnect connection) {
        String sql = "INSERT into books(isbn,name,price,author,publisher) VALUES(?,?,?,?,?)";
        try (Connection conn = connection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, book.getIsbn());
            pstmt.setString(2, book.getName());
            pstmt.setInt(3, book.getPrice());
            pstmt.setString(4,book.getAuthor());
            pstmt.setString(5, book.getPublisher());
            int rowsAffected = pstmt.executeUpdate();
            System.out.println(rowsAffected+" Added to the library");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void update(Book book,DBConnect connection) {

    }

    @Override
    public void delete(Book book,DBConnect connection) {

    }
}
