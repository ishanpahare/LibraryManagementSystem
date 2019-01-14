package com.main.lms.utils;

import com.main.lms.entities.Vendor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VerifyUtil {

    public static int isCustomer(int id, DBConnect connect) {
        String sql = "SELECT cid,username FROM customer WHERE cid=?";
        try (Connection conn = connect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) return rs.getInt("cid");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public static int isLibrarian(String username, String password, DBConnect connect) {
        String sql = "SELECT lid,username,password FROM librarian WHERE username=? AND password=?";
        try (Connection conn = connect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next())
                return rs.getInt("lid");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return 0;
    }

    public static int getIssued(int cid, DBConnect connect) {
        String sql = "SELECT uid FROM issued WHERE cid=?";
        int rowCount = 0;

        try (Connection conn = connect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, cid);
            ResultSet rs = pstmt.executeQuery();
            //calculating the entries in resultSet
            while (rs.next()) {
                rowCount++;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return rowCount;
    }

    public static int isAvailable(int isbn, DBConnect connect) {
        String sql = "SELECT DISTINCT uid FROM books WHERE isbn=?";

        try (Connection conn = connect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, isbn);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) return rs.getInt("uid");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public static boolean isBook(int uid,DBConnect connect){
        String sql="SELECT uid FROM books where uid=?";
        try(Connection conn = connect.getConnection();
        PreparedStatement pstmt=conn.prepareStatement(sql)) {
            pstmt.setInt(1,uid);
            ResultSet rs=pstmt.executeQuery();
            if(rs.next()) return true;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static boolean isBookWithVendor(int vid,int isbn,DBConnect db){
        String sql="SELECT vid,isbn FROM vendor where vid=? AND isbn=?";
        try(Connection conn = db.getConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql)) {
            pstmt.setInt(1,vid);
            pstmt.setInt(2,isbn);
            ResultSet rs=pstmt.executeQuery();
            if(rs.next()) return true;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
}
