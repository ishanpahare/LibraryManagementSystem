package com.main.lms.dao;

import com.main.lms.utils.DBConnect;

import java.util.List;
import java.util.Optional;

public interface CustomerDao<Customer> {
    Optional<Customer> get(long id);

    List<Customer> getAll(DBConnect connection);

    void save(Customer customer, DBConnect connection);

    void update(Customer customer, DBConnect connection);

    void delete(Customer customer, DBConnect connection);
}
