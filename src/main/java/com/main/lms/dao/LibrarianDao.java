package com.main.lms.dao;

import com.main.lms.utils.DBConnect;

import java.util.List;
import java.util.Optional;

public interface LibrarianDao<Librarian> {
    Optional<Librarian> get(long id);

    List<Librarian> getAll(DBConnect connection);

    void save(Librarian book, DBConnect connection);

    void update(Librarian book,DBConnect connection);

    void delete(Librarian book,DBConnect connection);
}
