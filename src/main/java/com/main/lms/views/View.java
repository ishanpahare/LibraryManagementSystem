package com.main.lms.views;

import com.main.lms.dao.BookDaoImpl;
import com.main.lms.dao.CustomerDaoImpl;
import com.main.lms.dao.VendorDaoImpl;
import com.main.lms.entities.Book;
import com.main.lms.entities.Customer;
import com.main.lms.entities.Vendor;
import com.main.lms.operations.IssueBook;
import com.main.lms.operations.ReturnBook;
import com.main.lms.utils.DBConnect;
import com.main.lms.utils.GetDetail;
import com.main.lms.utils.VerifyUtil;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class View {

    public static void librarianLoginView(DBConnect connect, MainView mv) {
        Scanner sc = new Scanner(System.in);
        System.out.println("-------------LIBRARIAN LOGIN-------------");

        sc.nextLine();
        System.out.println("USERNAME:");
        String username = sc.nextLine();

        System.out.println("PASSWORD: ");
        String password = sc.nextLine();

        int lid = VerifyUtil.isLibrarian(username, password, connect);

        if (lid != 0) {
            //go to librarian main menu
            librarianView(lid, connect, mv);
        } else {
            System.out.println("INVALID CREDENTIALS!");
            System.out.println("1.Try Again\n2.Exit");
            int choice = sc.nextInt();

            if (choice == 1)
                librarianLoginView(connect, mv);
            else
                System.exit(0);
        }
    }

    public static void customerLoginView(DBConnect connect, MainView mv) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Customer Id(0 to Exit): ");
        int id = sc.nextInt();

        if (id == 0) {
            System.exit(0);
        }
        int cid = VerifyUtil.isCustomer(id, connect);
        if (cid != 0) {
            userView(connect, cid, mv);
        } else {
            System.out.println("Invalid Id, Try Again!");
            customerLoginView(connect, mv);
        }
    }

    public static void userView(DBConnect connect, int cid, MainView mv) {
        Scanner sc = new Scanner(System.in);
        GetDetail details = new GetDetail();
        System.out.println("--------WELCOME USER " + cid + "-------------");
        System.out.println("1.View Information\n2.Available Books in Library\n3.Check Fine\n4.Logout");
        int choice = sc.nextInt();
        switch (choice) {
            case 1: {
                details.getCustomerDetails(cid, connect);
            }
            break;
            case 2: {
                details.getBookDetails(connect);
                System.out.println();
            }
            break;
            case 3: {
                System.out.println("----------Fine---------");
                int totalFine = ReturnBook.calculateFine(cid, connect);
                System.out.println("Total Fine: " + totalFine);
            }
            break;
            case 4: {
                mv.getMainView(mv, connect);
            }
            break;
            default: {
                System.out.println("Enter a valid choice!");
            }
            break;
        }
        userView(connect, cid, mv);
    }

    public static void librarianView(int lid, DBConnect db, MainView mv) {
        Scanner sc = new Scanner(System.in);
        GetDetail details = new GetDetail();
        BookDaoImpl bookDao = new BookDaoImpl();
        System.out.println("----------------Welcome Librarian---------------");
        System.out.println();
        System.out.println("Operation to perform: ");
        System.out.println("1.Issue Book\n" + "2.Return Book\n" + "3.Add Book\n" + "4.Search Book\n"
                + "5.Add Customer\n" + "6.View Customers\n" + "7.Check Stock\n" + "8.Request Vendor\n" + "9.LogOut");
        int choice = sc.nextInt();

        switch (choice) {
            case 1: {
                //IssueView issueView = new IssueView();
                getIssueView(lid, db, mv);
            }
            break;
            case 2: {
                //ReturnView returnView = new ReturnView();
                returnView(lid, db, mv);
            }
            break;

            case 3: {
                //AddBookView addBook = new AddBookView();
                addBookView(lid, db, mv);
            }
            break;
            case 4: {
                System.out.println("Enter isbn of book: ");
                int isbn = sc.nextInt();
                Book book = new Book(isbn);
                try {

                    ResultSet rs = bookDao.get(book, db);
                    while (rs.next()) {
                        System.out.println("ISBN: " + rs.getInt("isbn"));
                        System.out.println("Name: " + rs.getString("name"));
                        System.out.println("Author: " + rs.getString("author"));
                        System.out.println("Publisher: " + rs.getString("publisher"));
                        System.out.println("Price: " + rs.getInt("price"));
                        System.out.println();
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
            break;
            case 5: {
                //AddCustomerView addCustom = new AddCustomerView();
                addCustomerView(lid, db, mv);
            }
            break;

            case 6: {
                //db.getCustomer(lid, db, mv);
                System.out.println("Enter Customer Id to get details: ");
                int id = sc.nextInt();
                details.getCustomerDetails(id, db);
            }
            break;
            case 7: {
                // Check Stock
                System.out.println("----------Library Stock------------");
                System.out.println();
                //db.getDetails(lid, db, mv);
                //System.out.println("Press 1 to go back.");
                //int back = sc.nextInt();
                try {

                    ResultSet rs = bookDao.getAll(db);

                    while (rs.next()) {
                        System.out.println("ISBN: " + rs.getInt("isbn"));
                        System.out.println("Name: " + rs.getString("name"));
                        System.out.println("Author: " + rs.getString("author"));
                        System.out.println("Publisher: " + rs.getString("publisher"));
                        System.out.println("Price: " + rs.getInt("price"));
                        System.out.println();
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
            break;

            case 8: {
                //VendorView vendorView = new VendorView();
                getVendorView(lid, db, mv);
            }
            break;
            case 9: {

                mv.getMainView(mv, db);
            }
            break;
            default: {
                System.out.println("Enter A valid Option!");

            }
            //libView(lid, db, mv);
            break;
        }
        librarianView(lid, db, mv);
    }

    public static void getIssueView(int lid, DBConnect connect, MainView mv) {
        Scanner sc = new Scanner(System.in);
        System.out.println("----------Issue Book----------");
        System.out.println("Enter Id of Customer: ");
        int cid = sc.nextInt();
        System.out.println("Enter ISBN of book to be issued: ");
        int isbn = sc.nextInt();

        // checking the number of issued books is less than 3
        // checking availability of book
        int isCustomer = VerifyUtil.isCustomer(cid, connect);
        int issued = VerifyUtil.getIssued(cid, connect);
        int availableUid = VerifyUtil.isAvailable(isbn, connect);

        if (isCustomer == 0) {
            System.out.println("Customer does not exist! Try with a different customer id");
            return;
        }

        if (availableUid == 0) {
            System.out.println("Book not in stock. Try a different isbn!");
            return;
        }

        if (issued >= 3) {
            System.out.println("Already 3 books issued! Cannot issue more books!");
        }

        if (issued < 3 && availableUid != 0) {
            IssueBook.issueBook(availableUid, isbn, cid, lid, connect);
            System.out.println("Issued Successfully!");
        }

        /*System.out.println("Press 1 to go back.");
        int back = sc.nextInt();

        if (back == 1) {
            librarianView(lid, connect, mv);
        } else {
            getIssueView(lid, connect, mv);
        }*/

    }

    public static void returnView(int lid, DBConnect connect, MainView mv) {
        Scanner sc = new Scanner(System.in);
        System.out.println("---------Return Book-----------");
        System.out.println("Enter the ISBN of the book: ");
        int isbn = sc.nextInt();
        System.out.println("Enter the Id of the customer: ");
        int id = sc.nextInt();
        int numberOfIssued = VerifyUtil.getIssued(id, connect);
        int cid = VerifyUtil.isCustomer(id, connect);
        if (numberOfIssued != 0) {
            ReturnBook.returnBook(isbn, id, connect);
        } else if (numberOfIssued == 0) {
            System.out.println("Book Not Issued so cannot be returned!");
        }
        if (cid == 0) {
            System.out.println("User does not exist!");
        }

        /*System.out.println("Press 1 to go back.");
        int back = sc.nextInt();

        if (back == 1) {
            librarianView(lid, connect, mv);
        } else {
            returnView(lid, connect, mv);
        }*/
    }

    public static void addBookView(int lid, DBConnect connect, MainView mv) {
        Scanner sc = new Scanner(System.in);
        System.out.println("-------ADD BOOK--------");
        //AddBook addBook;
        System.out.println("Enter ISBN of Book: ");
        int isbn = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Name of book: ");
        String name = sc.nextLine();
        //System.out.println("Enter Unique Id of Book: ");
        //int uid = sc.nextInt();
        // System.out.println("Enter Quantity of Book: ");
        // int quantity = sc.nextInt();
        System.out.println("Enter Price of Book: ");
        int price = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Author: ");
        String author = sc.nextLine();
        System.out.println("Enter Publisher: ");
        String publisher = sc.nextLine();

        BookDaoImpl newBook = new BookDaoImpl();
        Book book = new Book(isbn, name, price, author, publisher);
        newBook.save(book, connect);
        System.out.println("Book Added to the library!");


        // }

        /*System.out.println("Press 1 to go back.");
        int back = sc.nextInt();

        if (back == 1) {
            librarianView(lid, connect, mv);
        } else {
            addBookView(lid, connect, mv);
        }*/
    }

    public static void addCustomerView(int lid, DBConnect connect, MainView mv) {
        Scanner sc = new Scanner(System.in);
        System.out.println("-----------ADD NEW USER-----------");
        System.out.println("Enter customer Name: ");
        String name = sc.nextLine();
        System.out.println("Enter customer id: ");
        int id = sc.nextInt();
        int cid = VerifyUtil.isCustomer(id, connect);
        if (cid == 0) {
            Customer customer = new Customer(name, id);
            CustomerDaoImpl newCustomer = new CustomerDaoImpl();
            newCustomer.save(customer, connect);
            System.out.println("Customer added successfully!");
        } else {
            System.out.println("User already exists!");
        }

        /*System.out.println("Press 1 to go back.");
        int back = sc.nextInt();

        if (back == 1) {
            librarianView(lid, connect, mv);
        } else {
            addCustomerView(lid, connect, mv);
        }*/
    }

    public static void getVendorView(int lid, DBConnect db, MainView mv) {
        Scanner sc = new Scanner(System.in);
        VendorDaoImpl vendorDao = new VendorDaoImpl();
        System.out.println("--------List of Vendors--------");
        System.out.println("1.List of vendors and books\n2.Check Vendor Inventory\n3.Order Books from vendor\n4.Back");
        int choice = sc.nextInt();
        switch (choice) {
            case 1: {
                System.out.println("LIST OF VENDORS AND BOOKS:");
                try {
                    ResultSet rs = vendorDao.getAll(db);
                    while (rs.next()) {
                        System.out.println("Vendor Id: " + rs.getInt("vid"));
                        System.out.println("Vendor Name: " + rs.getString("name"));
                        System.out.println("Book Name: " + rs.getString("bookname"));
                        System.out.println("Book ISBN: " + rs.getInt("isbn"));
                        System.out.println("Author: " + rs.getString("author"));
                        System.out.println("Publisher: " + rs.getString("publisher"));
                        System.out.println("Price: " + rs.getInt("price"));
                        System.out.println();
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
            break;
            case 2: {
                System.out.println("Enter vendor Id to get a list of books: ");
                int vid = sc.nextInt();
                Vendor vendor = new Vendor(vid);
                try {

                    ResultSet rs = vendorDao.get(vendor, db);
                    if (rs == null) {
                        System.out.println("Vendor does not exist!");
                        break;
                    }
                    while (rs.next()) {
                        System.out.println("Name of Book: " + rs.getString("bookname"));
                        System.out.println("ISBN: " + rs.getInt("isbn"));
                        System.out.println("Author: " + rs.getString("author"));
                        System.out.println("Publisher: " + rs.getString("publisher"));
                        System.out.println("Price: " + rs.getInt("price"));
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
            break;
            case 3: {
                System.out.println("Enter id of Vendor: ");
                int vid = sc.nextInt();
                System.out.println("Enter the isbn of book you wish to purchase: ");
                int isbn = sc.nextInt();
                System.out.println("Enter the quantity you wish to purchase: ");
                int quantity = sc.nextInt();
                Vendor vendor = new Vendor(vid);
                try {
                    ResultSet rs = vendorDao.getBook(vendor, isbn, db);
                    if (rs == null) {
                        System.out.println("Vendor Does not exist!");
                        break;
                    }
                    if (VerifyUtil.isBookWithVendor(vid, isbn, db)) {
                        rs.next();
                        int vendorIsbn = rs.getInt("isbn");
                        String bookname = rs.getString("bookname");
                        int price = rs.getInt("price");
                        String author = rs.getString("author");
                        String publisher = rs.getString("publisher");

                        Book book = new Book(vendorIsbn, bookname, price, author, publisher);
                        BookDaoImpl addBook = new BookDaoImpl();
                        for (int i = 1; i <= quantity; i++)
                            addBook.save(book, db);
                        System.out.println("Books added to library successfully!");

                    } else {
                        System.out.println("Book not available with vendor!");
                        break;
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
            break;
            case 4: {
                librarianView(lid, db, mv);
            }
            default: {
                System.out.println("Enter a Valid choice!");
                getVendorView(lid, db, mv);
            }
            break;
        }
        getVendorView(lid, db, mv);

    }

}



