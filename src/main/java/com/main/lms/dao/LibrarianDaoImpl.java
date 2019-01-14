package com.main.lms.dao;

import com.main.lms.entities.Librarian;
import com.main.lms.utils.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class LibrarianDaoImpl implements LibrarianDao<Librarian> {

    @Override
    public Optional<Librarian> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Librarian> getAll(DBConnect connection) {
        return null;
    }

    @Override
    public void save(Librarian librarian, DBConnect connection) {
        String sql="INSERT into librarian(lid,username,password) VALUES(?,?,?)";
        try(Connection conn = connection.getConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql)) {
            pstmt.setInt(1,librarian.getLid());
            pstmt.setString(2,librarian.getUsername());
            pstmt.setString(3,librarian.getPassword());
            int rowsAffected=pstmt.executeUpdate();
            System.out.println(rowsAffected+" added to db");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Librarian book, DBConnect connection) {

    }

    @Override
    public void delete(Librarian book, DBConnect connection) {

    }
}
