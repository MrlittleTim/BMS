package com.books.dao.impl;

import com.alibaba.druid.sql.ast.statement.SQLAlterTableRenameIndex;
import com.books.bean.Book;
import com.books.dao.BaseDAO;
import com.books.dao.intface.BookDAO;

import java.sql.Connection;
import java.util.List;

public class BookDAOImpl extends BaseDAO<Book> implements BookDAO {
    @Override
    public boolean addBook(Connection conn, Book book) {
        String sql = "insert into books values(0, ?, ?, ?)";
        return super.executeUpdate(conn, sql, book.getName(), book.getPrice(), book.getAuthor()) > 0;
    }

    @Override
    public boolean delBookByName(Connection conn, String name) {
        String sql = "delete from books where name like ?";
        return super.executeUpdate(conn, sql, name) > 0;
    }

    @Override
    public boolean delBookById(Connection conn, int id) {
        String sql = "delete from books where id like ?";
        return super.executeUpdate(conn, sql, id) > 0;
    }

    @Override
    public List<Book> getBookList(Connection conn) {
        String sql = "select * from books";
        return super.getBeanList(conn, sql);
    }

    @Override
    public Book getBookByName(Connection conn, String name) {
        String sql = "select * from books where name like ?";
        return super.getBean(conn, sql, name);
    }

    @Override
    public Book getBookById(Connection conn, int id) {
        String sql = "select * from books where id like ?";
        return super.getBean(conn, sql, id);
    }

    @Override
    public boolean updateBook(Connection conn, Book book) {
        String sql = "update books set name =?, price = ?, author = ? where id = ?";
        return super.executeUpdate(conn, sql, book.getName(), book.getPrice(), book.getAuthor(), book.getId()) > 0;
    }
}
