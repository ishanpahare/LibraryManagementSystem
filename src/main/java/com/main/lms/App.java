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

      /*  VendorDaoImpl addBook=new VendorDaoImpl();

        Vendor v1=new Vendor(1,"vendor1",6,"To Kill a Mockingbird",199,"Harper Lee","Harper Perennial");
        Vendor v2=new Vendor(1,"vendor1",7,"1984",299,"George Orwell","New American Library");
        Vendor v3=new Vendor(1,"vendor1",8,"The Great Gatsby",399,"F. Scott Fitzgerald","Scribner");
        Vendor v4=new Vendor(1,"vendor1",9,"The Hobbit",499,"J.R.R Tolkien","Houghton Mifflin");
        Vendor v5=new Vendor(1,"vendor1",10,"The Diary of a Young Girl",300,"Anne Frank","Frank House");
        addBook.save(v1,connect);
        addBook.save(v2,connect);
        addBook.save(v3,connect);
        addBook.save(v4,connect);
        addBook.save(v5,connect);

        Vendor v6=new Vendor(2,"vendor2",11,"The Catcher in the Rye",199,"J.D. Salinger","Salinger Publishers");
        Vendor v7=new Vendor(2,"vendor2",12,"Fahrenheit 451",299,"Ray Bradbury","New American Library");
        Vendor v8=new Vendor(2,"vendor2",13,"Gone with the Wind",399,"Margaret Mitchell","Mitchell Publications");
        Vendor v9=new Vendor(2,"vendor2",14,"The Giver",499,"Lois Lowry","Lowry and Company");
        Vendor v10=new Vendor(2,"vendor2",15,"Memoirs of a Geisha",300,"Arthur Golden","Golden Books");
        addBook.save(v6,connect);
        addBook.save(v7,connect);
        addBook.save(v8,connect);
        addBook.save(v9,connect);
        addBook.save(v10,connect);
        */

/*        Date date = new Date();
        String pattern = "dd-MMM-yyyy";

        final int numberOfIssueDays = 14;

        DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        String LocalIssueDate = simpleDateFormat.format(date);

        LocalDate issueDate = LocalDate.parse(LocalIssueDate, df);
        LocalDate returnDate = issueDate.plusDays(numberOfIssueDays);

        IssuedBook issuedBook=new IssuedBook(3,3,1,1,issueDate,returnDate);
        IssuedBookImpl addBook=new IssuedBookImpl();
        addBook.save(issuedBook,connect); */

        /*for(int i=2;i<=5;i++) {
            Librarian librarian = new Librarian(i, "lib"+i, "password");
            LibrarianDaoImpl addLibrarian = new LibrarianDaoImpl();
            addLibrarian.save(librarian, dbConnect);
        }*/
        //VerifyUtil.isLibrarian("lib1","password",connect);
        //System.out.println(result);
        // Customer customer=new Customer("cust1",1);
        // CustomerDaoImpl newCustomer=new CustomerDaoImpl();
        // newCustomer.save(customer,dbConnect);
        //ReadJSON.getJson();
        MainView mv = new MainView();
        mv.getMainView(mv, connect);
    }
}
