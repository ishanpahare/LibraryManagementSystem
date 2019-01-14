package com.main.lms.dao;

import com.main.lms.entities.IssuedBook;
import com.main.lms.utils.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class IssuedBookImpl implements IssueBookDao<IssuedBook>{

    @Override
    public Optional<IssuedBook> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<IssuedBook> getAll(DBConnect connection) {
        return null;
    }

    @Override
    public void save(IssuedBook issuedBook, DBConnect connection) {
        String sql="INSERT INTO issued(uid,cid,lid,issuedate,returndate) VALUES (?,?,?,?,?)";
        try(Connection conn = connection.getConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql);) {
            pstmt.setInt(1,issuedBook.getUid());
            pstmt.setInt(2,issuedBook.getCid());
            pstmt.setInt(3,issuedBook.getLid());
            pstmt.setDate(4,issuedBook.getIssuedDate());
            pstmt.setDate(5,issuedBook.getReturnDate());
            int rowsAffected=pstmt.executeUpdate();
            //System.out.println(rowsAffected+" added to db");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(IssuedBook issuedBook, DBConnect connection) {

    }

    @Override
    public void delete(IssuedBook issuedBook, DBConnect connection) {

    }
}
