package com.main.lms.views;

import com.main.lms.utils.DBConnect;

import java.util.Scanner;

public class MainView {
    Scanner sc=new Scanner(System.in);
    public void getMainView(MainView mv, DBConnect connect){
        System.out.println("------------Welcome to Library Management System-------");
        System.out.println("1.Librarian\n2.User\n3.Exit");
        int choice = sc.nextInt();
        switch (choice) {
            case 1: {
                // Librarian View
                View.librarianLoginView(connect,mv);
            }
            break;

            case 2: {
                // CustomerView
                View.customerLoginView(connect,mv);
            }
            break;
            case 3:
                System.exit(0);
            default: {
                System.out.println("Enter a valid choice!");
            }
            getMainView(mv,connect);
            break;
        }
    }
    }
