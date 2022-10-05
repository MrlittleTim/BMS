package com.books.dao.intface;

import com.books.bean.Book;

import java.sql.Connection;
import java.util.List;

public interface BookDAO {
    boolean addBook(Connection conn, Book book);
    boolean delBookByName(Connection conn, String name);
    boolean delBookById(Connection conn, int id);
    List<Book> getBookList(Connection conn);
    Book getBookByName(Connection conn, String name);
    Book getBookById(Connection conn, int id);
    boolean updateBook(Connection conn, Book book);
}
