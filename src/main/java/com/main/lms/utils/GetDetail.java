package com.main.lms.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetDetail {

    public void getCustomerDetails(int id, DBConnect connect) {
        String sql = "SELECT cid,username FROM customer WHERE cid=?";
        try (Connection conn = connect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                System.out.println("ID: " + rs.getInt("cid"));
                System.out.println("Name: " + rs.getString("username"));
            } else {
                System.out.println("Customer does not exist!");
                return;
            }
            getIssueDetails(id,connect);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getIssueDetails(int cid, DBConnect connect) {
        String sql = "select i.uid uid_i,b.name name_b,i.lid lid_i,i.issuedate issuedate_i,i.returndate returndate_i from issued i full join books b on i.uid=b.uid where i.cid=?";
        System.out.println("List of books issued: ");
        System.out.println("--------------------------------");
        try(Connection conn=connect.getConnection();
        PreparedStatement pstmt=conn.prepareStatement(sql)){
            pstmt.setInt(1,cid);
            ResultSet rs=pstmt.executeQuery();
            while(rs.next()){
                System.out.println("Name: "+rs.getString("name_b"));
                System.out.println("UID: "+rs.getInt("uid_i"));
                System.out.println("Issued By (Librarian Id): "+rs.getInt("lid_i"));
                System.out.println("Issue Date: "+rs.getDate("issuedate_i"));
                System.out.println("Return Date: "+rs.getDate("returndate_i"));
                System.out.println();
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void getBookDetails(int isbn, DBConnect connect) {
        String sql = "SELECT uid,isbn,name,price,author,publisher FROM books WHERE isbn=?";
        try (Connection conn = connect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, isbn);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println("UId: " + rs.getInt("uid"));
                System.out.println("Isbn: " + rs.getInt("isbn"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Price: " + rs.getInt("price"));
                System.out.println("Author: " + rs.getString("author"));
                System.out.println("Publisher: " + rs.getString("publisher"));
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getBookDetails(DBConnect connect) {
        String sql = "SELECT uid,isbn,name,price,author,publisher FROM books";
        try (Connection conn = connect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println("UId: " + rs.getInt("uid"));
                System.out.println("Isbn: " + rs.getInt("isbn"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Price: " + rs.getInt("price"));
                System.out.println("Author: " + rs.getString("author"));
                System.out.println("Publisher: " + rs.getString("publisher"));
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
