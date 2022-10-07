package com.books.view;

import com.books.controler.Manu;

public class Client {
    public static void main(String[] args) {
        Manu manu = new Manu();
        manu.readDataFromFile();
        boolean flag = true;
        while (flag) {
            int choice = manu.showManu();
            switch (choice) {
                case 1:
                    manu.addBook();
                    manu.writeDataToFile();
                    break;
                case 2:
                    manu.deleteBook();
                    manu.writeDataToFile();
                    break;
                case 3:
                    manu.updateBook();
                    manu.writeDataToFile();
                    break;
                case 4:
                    manu.queryBook();
                    break;
                case 5:
                    manu.printAllBook();
                    break;
                case 0:
                    flag = false;
                    break;
                default:
                    System.out.print("您选择有误！请重新选择：");
            }
        }
        manu.writeDataToFile();
        System.out.println("系统已退出！");
    }
}
