package com.main.lms.operations;

import com.main.lms.dao.IssuedBookImpl;
import com.main.lms.entities.IssuedBook;
import com.main.lms.utils.DBConnect;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class IssueBook {

    static final int numberOfIssueDays = 14;

    public static void issueBook(int uid, int isbn, int cid, int lid, DBConnect connect) {
        Date date = new Date();
        String pattern = "dd-MMM-yyyy";


        DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        String LocalIssueDate = simpleDateFormat.format(date);

        LocalDate issueDate = LocalDate.parse(LocalIssueDate, df);
        LocalDate returnDate = issueDate.plusDays(numberOfIssueDays);

        IssuedBook issuedBook = new IssuedBook(uid, isbn, cid, lid, issueDate, returnDate);
        IssuedBookImpl addBook = new IssuedBookImpl();
        addBook.save(issuedBook, connect);
    }
}
