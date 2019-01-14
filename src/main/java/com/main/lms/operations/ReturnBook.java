package com.main.lms.operations;

import com.main.lms.utils.DBConnect;

import java.sql.*;
import java.time.LocalDate;
import java.time.Period;

public class ReturnBook {

    public static void returnBook(int isbn, int cid, DBConnect connect){
        String sql="DELETE FROM issued WHERE isbn=? AND cid=?";

        try(Connection conn=connect.getConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql)){
            pstmt.setInt(1,isbn);
            pstmt.setInt(2,cid);
            int affectedRows=pstmt.executeUpdate();
            if(affectedRows>0) System.out.println("Book returned Successfully!");
            else
                System.out.println("Could not return book, please try again!");

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static int calculateFine(int cid,DBConnect connect){
        final int finePerDay=5;
        final int maxIssueDays=IssueBook.numberOfIssueDays;

        String sql="SELECT issuedate FROM issued where cid=?";
        LocalDate currentDate=LocalDate.now();
        int totalFine=0;
        try(Connection conn = connect.getConnection();
        PreparedStatement pstmt=conn.prepareStatement(sql)){
            pstmt.setInt(1,cid);
            ResultSet rs=pstmt.executeQuery();
            while(rs.next()){
                LocalDate issueDate=(rs.getDate("issuedate")).toLocalDate();
                Period period=Period.between(issueDate,currentDate);
                int diff=period.getDays();
                int daysExceeded=diff-maxIssueDays;

                if(daysExceeded>0){
                    totalFine=totalFine+daysExceeded*finePerDay;
                }
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return totalFine;
    }
}
