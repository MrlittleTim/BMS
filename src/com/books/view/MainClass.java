package com.books.view;

import java.util.Scanner;

public class MainClass {

    String[] bookList = new String[100];
    int bookCount = 0;
    Scanner scanner = new Scanner(System.in);

    public MainClass() {
        boolean flag = true;
        while (flag) {
            System.out.println("1---------添加图书---------");
            System.out.println("2---------删除图书---------");
            System.out.println("3---------修改图书---------");
            System.out.println("4---------查询图书---------");
            System.out.println("5---------显示藏书---------");
            System.out.println("0---------退出系统---------");
            System.out.print("欢迎来到肖老师的图书管理系统!请输入您的选择：");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    deleteBook();
                    break;
                case 3:
                    updateBook();
                    break;
                case 4:
                    queryBook();
                    break;
                case 5:
                    printAllBook();
                    break;
                case 0:
                    flag = false;
                    scanner.close();
                    break;
            }


        }
        System.out.println("系统退出了");
        return;

    }


    public void addBook() {
        System.out.println("请输入图书名：");
        String bookName = scanner.next();
        bookList[bookCount++] = bookName;
        System.out.println("添加成功！");
    }

    public void deleteBook() {
        System.out.print("请输入需要删除的图书名：");
        String bookName = scanner.nextLine();

    }

    private void updateBook() {
    }

    private void queryBook() {
    }

    public void printAllBook() {
        for (int i = 0; i < bookCount; i++) {
            System.out.println(bookList[i]);
        }
    }


    public static void main(String[] args) {
        new MainClass();

    }


}





