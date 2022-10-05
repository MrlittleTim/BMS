package com.books.controler;

import com.books.bean.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Manu {
    Scanner scanner = new Scanner(System.in);
    List<Book> bookList = new ArrayList<>();

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
        int choice = scanner.nextInt();
        return choice;
    }

    public void addBook() {
        System.out.print("请输入书籍编号：");
        int id = scanner.nextInt();
        if (queryBookById(id) != null) {
            System.out.println("该书籍编号已经存在！");
            return;
        }
        System.out.print("请输入书名：");
        scanner.nextLine();
        String name = scanner.nextLine();
        System.out.print("请输入价格：");
        double price = scanner.nextDouble();
        System.out.print("请输入作者：");
        scanner.nextLine();
        String author = scanner.nextLine();
        Book book = new Book(id, name, price, author);
        boolean add = bookList.add(book);
        if (add) {
            System.out.println("添加成功！");
        } else {
            System.out.println("添加失败！");
        }
    }

    public void deleteBook() {
        System.out.println("1. 按编号删除");
        System.out.println("2. 按书名删除");
        System.out.print("请选择删除方式：");
        int choice = scanner.nextInt();
        if (choice == 1) {
            deleteBookById();
        } else if (choice == 2) {
            deleteBookByName();
        } else {
            System.out.println("您的选择有误！");
        }
    }


    private void deleteBookById() {
        boolean flag = true;
        System.out.print("请输入需要删除的书籍编号：");
        int id = scanner.nextInt();
        for (int i = 0; i < bookList.size(); i++) {
            if (bookList.get(i).getId() == id) {
                bookList.remove(i);
                System.out.println("删除成功！");
                flag = false;
                break;
            }
        }
        if (flag) {
            System.out.println("没有找到该编号的书籍！");
        }
    }

    private void deleteBookByName() {
        boolean flag = true;
        System.out.print("请输入需要删除的书名：");
        scanner.nextLine();
        String name = scanner.nextLine();
        for (int i = 0; i < bookList.size(); i++) {
            if (bookList.get(i).getName().equals(name)) {
                bookList.remove(i);
                System.out.println("删除成功！");
                flag = false;
                break;
            }
        }
        if (flag) {
            System.out.println("没有找到改书名！");
        }
    }

    public void updateBook() {
        System.out.print("请输入需要更新的书籍编号：");
        int id = scanner.nextInt();
        int index;
        if ((index = bookList.indexOf(queryBookById(id))) != -1) {
            System.out.print("请输入需要更新的书籍名称：");
            scanner.nextLine();
            String name = scanner.nextLine();
            System.out.print("请输入需要更新的书籍价格：");
            double price = scanner.nextDouble();
            System.out.print("请输入需要更新的书籍作者：");
            scanner.nextLine();
            String author = scanner.nextLine();
            bookList.set(index, new Book(id, name, price, author));
            System.out.println("修改成功！");
        } else {
            System.out.println("输入的书籍编号有误！");
        }
    }

    public void queryBook() {
        System.out.println("1. 按编号查询");
        System.out.println("2. 按书名查询");
        System.out.print("请选择查询方式：");
        int choice = scanner.nextInt();
        if (choice == 1) {
            System.out.print("请输入需要查询的书籍编号：");
            int id = scanner.nextInt();
            Book book = queryBookById(id);
            if (book != null) {
                printBook(book);
            } else {
                System.out.println("查询失败！");
            }
        } else if (choice == 2) {
            System.out.print("请输入需要查询的书籍名称：");
            scanner.nextLine();
            String name = scanner.nextLine();
            Book book = queryBookByName(name);
            if (book != null) {
                printBook(book);
            } else {
                System.out.println("查询失败！");
            }
        } else {
            System.out.println("您的选择有误！");
        }
    }




    private Book queryBookById(int id) {
        for (int i = 0; i < bookList.size(); i++) {
            if (bookList.get(i).getId() == id) {
                return bookList.get(i);
            }
        }
        return null;
    }

    private Book queryBookByName(String name) {
        for (int i = 0; i < bookList.size(); i++) {
            if (bookList.get(i).getName().equals(name)) {
                return bookList.get(i);
            }
        }
        return null;
    }

    private void printBook(Book book) {
        System.out.println("*******************************");
        System.out.println("编号\t\t" + "书名\t\t" + "价格\t\t\t" + "作者");
        System.out.println("*******************************");
        System.out.println(book);
        System.out.println("*******************************");
    }
    public void printAllBook() {
        System.out.println("*******************************");
        System.out.println("编号\t\t" + "书名\t\t" + "价格\t\t\t" + "作者");
        System.out.println("*******************************");
        for (Book book : bookList) {
            System.out.println(book);
        }
        System.out.println("*******************************");
    }
}
