package com.Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner;

public class AppMain {
static Scanner sc= new Scanner(System.in);
static AppMain m =new AppMain();

String book;
String Name;
String Remark;
String date ;

static Connection con;



	public static void main(String[] args) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/local","root","5839");
		}catch(Exception e) {
		e.printStackTrace();
		}
		
		m.start();

	}
	
	public void start() {
		int i;	
		System.out.println("--------------Welcome to Library Management System-------");
		
	while(true) {
		
		System.out.println("Edit : Press 1");
		System.out.println("View : Press 2");
		System.out.println("Update : Press 3");
		System.out.println("Exit : Press ");
		i=sc.nextInt();
		if(i==1) {
			m.Edit();
			break;
		}
		else if(i==2) {
			m.View();
			break;
		}
		else if(i==3) {
			update();
			break;
		}
		else {
			exit();
			break;
		}
		
	}
	}

	private void update() {
		
		String s = "update student set book=?, remark=?, date=? where name =?";
	
	
		System.out.println("Enter new Book name : ");
		String b = sc.next();
		
		System.out.println("Enter new Remark : ");
		String r = sc.next();
		
		System.out.println("Enter Date : ");
		String d = sc.next();
	
		System.out.println("Enter Name : ");
		String dn = sc.next();
	
		try {
			PreparedStatement p = con.prepareStatement(s);
			
			
			p.setString(1, b);
			p.setString(2, r);
			p.setString(3, d);
			p.setString(4, dn);
			
			
			
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	start();
	
	}

	private void exit() {
		
		System.out.println("Bye,Thanks To come!!!");
		System.exit(0);
		
	}

	private void View() {
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from student");
			System.out.println("Name   Book    Remark     date");
			while(rs.next()) {
				String nam = rs.getString(1);
				String boo = rs.getString(2);
				String re = rs.getString(3);
				String dat = rs.getString(4);
				
				
				System.out.println(nam+"   "+boo+"    "+re+"    "+dat);
			}
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		
		
		start();
		
	}

	private void Edit() {
		
		System.out.println("-----Welcome TO Edit Section------");
		
		System.out.println("Name : ");
		Name = sc.next();		
		
		System.out.println("Book Name : ");
		book = sc.next();
		
		System.out.println("Remark (Return/Issue) : ");
		Remark = sc.next();
		
		System.out.println("Date : ");
		date =sc.next() ;
		
		try {
			PreparedStatement ps = con.prepareStatement("insert into student(name,book,remark,date) value(?,?,?,?)");
			ps.setString(1, Name);
			ps.setString(2, book);
			ps.setString(3, Remark);
			ps.setString(4, date);
			
			int n =ps.executeUpdate();
			if(n==1) {
				System.out.println("Data Update\n");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
			
		
		
		
		
		
		start();
	}
}
