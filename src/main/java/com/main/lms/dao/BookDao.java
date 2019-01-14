package com.main.lms.dao;

import com.main.lms.utils.DBConnect;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public interface BookDao<Book> {
    ResultSet get(Book book,DBConnect connection);

    ResultSet getAll(DBConnect connection);

    void save(Book book, DBConnect connection);

    void update(Book book,DBConnect connection);

    void delete(Book book,DBConnect connection);
}
