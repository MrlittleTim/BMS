package com.books.view;

import java.io.IOException;
import java.util.Scanner;

public class MainClass {
	
	String[] bookList = new String[100];
	int bookCount = 0;
	
	public MainClass()
	{
		Scanner scanner = new Scanner(System.in);
		
		while (true)
		{
			System.out.println("欢迎来到肖老师的图书管理系统!,请输入：");
			System.out.println("1-----------------添加图书");
			System.out.println("2-----------------删除图书");
			System.out.println("3-----------------修改图书");
			System.out.println("4-----------------查询图书");
			System.out.println("0-----------------退出系统");
			
			int choice = scanner.nextInt();
			if (choice == 0) break;
			if (choice == 1) addBook();
			else if (choice == 2) deleteBook();
				
		}
		System.out.println("系统退出了");
		return;
		
	}
	
	public void addBook()
	{
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入图书名：");
		String bookName = scanner.next();
		bookList[bookCount] = bookName;
		bookCount++;
		return;
	}
	
	public void deleteBook()
	{
		
	}

	public void printAllBook()
	{
		for (int i = 0; i < bookCount; i++)
		{
			System.out.println(bookList[i]);
		}
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new MainClass();

	}

	
	
	
}





