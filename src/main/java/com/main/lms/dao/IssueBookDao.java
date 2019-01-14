package com.main.lms.dao;

import com.main.lms.utils.DBConnect;

import java.util.List;
import java.util.Optional;

public interface IssueBookDao<IssuedBook> {
    Optional<IssuedBook> get(long id);

    List<IssuedBook> getAll(DBConnect connection);

    void save(IssuedBook issuedBook, DBConnect connection);

    void update(IssuedBook issuedBook,DBConnect connection);

    void delete(IssuedBook issuedBook,DBConnect connection);
}
