package com.main.lms;

import com.main.lms.dao.*;
import com.main.lms.entities.Customer;
import com.main.lms.entities.IssuedBook;
import com.main.lms.entities.Librarian;
import com.main.lms.entities.Vendor;
import com.main.lms.utils.DBConnect;
import com.main.lms.utils.ReadJSON;
import com.main.lms.utils.VerifyUtil;
import com.main.lms.views.MainView;

import java.sql.Connection;
import java.util.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {

        DBConnect connect = new DBConnect();

        try (Connection conn = connect.getConnection()) {
            System.out.println("Connected to DB");
        } catch (SQLException e) {
            System.out.println("Connection to server failed");
        }

        MainView mv = new MainView();
        mv.getMainView(mv, connect);
    }
}
