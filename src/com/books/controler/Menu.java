package com.books.controler;

import com.books.bean.Book;
import com.books.dao.impl.BookDAOImpl;
import com.books.dao.intface.BookDAO;
import com.books.utils.JDBCUtils;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    BookDAO bookDAO = new BookDAOImpl();

    public int showManu() {
        System.out.println("======欢迎使用图书管理系统!=====");
        System.out.println("1----------添加图书----------");
        System.out.println("2----------删除图书----------");
        System.out.println("3----------修改图书----------");
        System.out.println("4----------查询图书----------");
        System.out.println("5----------显示藏书----------");
        System.out.println("0----------退出系统----------");
        System.out.println("===========================");
        System.out.print("请输入您的选择：");
        return scanner.nextInt();
    }

    public void addBook() {
        Connection conn = null;
        try {
            conn = JDBCUtils.connection();
            conn.setAutoCommit(false);
            System.out.print("请输入书名：");
            scanner.nextLine();
            String name = scanner.nextLine();
            if (bookDAO.getBookByName(conn, name) == null) {
                System.out.print("请输入价格：");
                BigDecimal price = scanner.nextBigDecimal();
                System.out.print("请输入作者：");
                scanner.nextLine();
                String author = scanner.nextLine();
                Book book = new Book(0, name, price, author);
                boolean add = bookDAO.addBook(conn, book);
                if (add) {
                    System.out.println("添加成功！");
                } else {
                    System.out.println("添加失败！");
                }
            } else {
                System.out.println("该书籍已存在！");
            }
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            JDBCUtils.closeResources(conn);
        }
    }

    public void deleteBook() {
        Connection conn = null;
        try {
            conn = JDBCUtils.connection();
            conn.setAutoCommit(false);
            System.out.println("1. 按编号删除");
            System.out.println("2. 按书名删除");
            System.out.print("请选择删除方式：");
            boolean del = false;
            int choice = scanner.nextInt();
            if (choice == 1) {
                System.out.print("请输入需要删除的书籍编号：");
                int id = scanner.nextInt();
                del = bookDAO.delBookById(conn, id);
            } else if (choice == 2) {
                System.out.print("请输入需要删除的书名：");
                scanner.nextLine();
                String name = scanner.nextLine();
                del = bookDAO.delBookByName(conn, name);
            } else {
                System.out.println("您的选择有误！");
            }
            if (del) {
                System.out.println("删除成功！");
            } else {
                System.out.println("删除失败！");
            }
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            JDBCUtils.closeResources(conn);
        }
    }


    public void updateBook() {
        Connection conn = null;
        try {
            conn = JDBCUtils.connection();
            conn.setAutoCommit(false);
            System.out.print("请输入需要更新的书籍编号：");
            int id = scanner.nextInt();
            if (bookDAO.getBookById(conn, id) != null) {
                System.out.print("请输入需要更新的书籍名称：");
                scanner.nextLine();
                String name = scanner.nextLine();
                System.out.print("请输入需要更新的书籍价格：");
                BigDecimal price = scanner.nextBigDecimal();
                System.out.print("请输入需要更新的书籍作者：");
                scanner.nextLine();
                String author = scanner.nextLine();
                boolean updateBook = bookDAO.updateBook(conn, new Book(id, name, price, author));
                if (updateBook) {
                    System.out.println("修改成功！");
                } else {
                    System.out.println("修改失败！");
                }
            } else {
                System.out.println("输入的书籍编号有误！");
            }
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            JDBCUtils.closeResources(conn);
        }
    }

    public void queryBook() {
        Connection conn = null;
        try {
            conn = JDBCUtils.connection();
            conn.setAutoCommit(false);
            System.out.println("1. 按编号查询");
            System.out.println("2. 按书名查询");
            System.out.print("请选择查询方式：");
            int choice = scanner.nextInt();
            if (choice == 1) {
                System.out.print("请输入需要查询的书籍编号：");
                int id = scanner.nextInt();
                Book book = bookDAO.getBookById(conn, id);
                if (book != null) {
                    printBook(book);
                } else {
                    System.out.println("查询失败！");
                }
            } else if (choice == 2) {
                System.out.print("请输入需要查询的书籍名称：");
                scanner.nextLine();
                String name = scanner.nextLine();
                //            Book book = queryBookByName(name);
                Book book = bookDAO.getBookByName(conn, name);
                if (book != null) {
                    printBook(book);
                } else {
                    System.out.println("查询失败！");
                }
            } else {
                System.out.println("您的选择有误！");
            }
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            JDBCUtils.closeResources(conn);
        }
    }

    private void printBook(Book book) {
        System.out.println("*******************************");
        System.out.println("编号\t\t" + "书名\t\t" + "价格\t\t\t" + "作者");
        System.out.println("*******************************");
        System.out.println(book);
        System.out.println("*******************************");
    }

    public void printAllBook() {
        Connection conn = null;
        try {
            conn = JDBCUtils.connection();
            conn.setAutoCommit(false);
            List<Book> bookList = bookDAO.getBookList(conn);
            if (bookList == null || bookList.size() == 0) {
                System.out.println("对不起，数据为空！");
            } else {
                System.out.println("*******************************");
                System.out.println("编号\t\t" + "书名\t\t" + "价格\t\t\t" + "作者");
                System.out.println("*******************************");
                for (Book book : bookList) {
                    System.out.println(book);
                }
                System.out.println("*******************************");

            }
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            JDBCUtils.closeResources(conn);
        }
    }
}
