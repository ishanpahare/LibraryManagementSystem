package com.main.lms.dao;

import com.main.lms.utils.DBConnect;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public interface VendorDao<Vendor> {
    ResultSet get(Vendor vendor,DBConnect connection);
    ResultSet getBook(Vendor vendor,int isbn,DBConnect connection);

    ResultSet getAll(DBConnect connection);

    void save(Vendor vendor, DBConnect connection);

    void update(Vendor vendor,DBConnect connection);

    void delete(Vendor vendor,DBConnect connection);
}
