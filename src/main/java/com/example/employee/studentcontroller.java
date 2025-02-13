package com.example.employee;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class studentcontroller {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int ch;
		do {
			displaymenu();
			ch = Integer.parseInt(sc.nextLine());
			switch (ch) {
			case 1:
				insertion();
				break;

			case 2:
				delete();
				break;

			case 3:
				update();
				break;

			case 4:
				getbyid();
				break;

			case 5:
				getall();
				break;

			case 6:
				System.exit(0);
				break;

			default:
				System.out.println("invalid option");
				break;
			}

		} while (ch > 0);

	}

	private static void getall() {
	 StandardServiceRegistry ssr=new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
	 Metadata md=new MetadataSources(ssr).getMetadataBuilder().build();
	 SessionFactory sf=md.buildSessionFactory();
	 Session s=sf.openSession();
	 Transaction t=s.beginTransaction();
	 
	 List<student> li=s.createQuery("from student",student.class).list();
	 t.commit();
	 for(student std:li) {
		 System.out.println("id :"+std.getId());
		 System.out.println("name:"+std.getName());
		 System.out.println("email:"+std.getEmail());
		 System.out.println("password:"+std.getPassword());
		 System.out.println("phonenumber:"+std.getPhonenumber());
		 
	 }

	}

	private static void getbyid() {
		Scanner sc=new Scanner(System.in);
		StandardServiceRegistry ssr=new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata md=new MetadataSources(ssr).getMetadataBuilder().build();
		SessionFactory sf=md.buildSessionFactory();
		Session s=sf.openSession();
		Transaction t=s.beginTransaction();
		
		System.out.println("enter id :");
		int id=sc.nextInt();
		student std=s.get(student.class, id);
		if(std !=null) {
			System.out.println("id :"+std.getId());
			System.out.println("name:"+std.getName());
			System.out.println("email:"+std.getEmail());
			System.out.println("password:"+std.getPassword());
			System.out.println("phonenumber:"+std.getPhonenumber());
			
		}
		else {
			System.out.println("no data found");
		}
		t.commit();

	}

	private static void update() {
		Scanner sc=new Scanner(System.in);
		StandardServiceRegistry ssr=new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata md=new MetadataSources(ssr).getMetadataBuilder().build();
		SessionFactory sf=md.buildSessionFactory();
		Session s=sf.openSession();
		Transaction t=s.beginTransaction();
		System.out.println("enter id :");
		int id=sc.nextInt();
		student std=s.get(student.class, id);
		if(std !=null) {
			System.out.println("enter name");
			String name=sc.next();
			System.out.println("enter email");
			String email=sc.next();
			System.out.println("enter password");
			String password=sc.next();
			System.out.println("enter phonenumber");
			long phonenumber=sc.nextLong();
			std.setName(name);
			std.setEmail(email);
			std.setPassword(password);
			std.setPhonenumber(phonenumber);
			s.update(std);
			System.out.println("updated successfully");
		}
		else {
			System.out.println("error");
		}
		t.commit();

	}

	private static void delete() {
	  Scanner sc=new Scanner(System.in);
	  StandardServiceRegistry ssr=new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
	  Metadata md=new MetadataSources(ssr).getMetadataBuilder().build();
	  SessionFactory sf=md.buildSessionFactory();
	  Session s=sf.openSession();
	  Transaction t=s.beginTransaction();
	  
	  System.out.println("enter id");
	  int id=sc.nextInt();
	  student std=s.get(student.class, id);
	  s.delete(std);
	  t.commit();
	  System.out.println("successfully deleted");
	}

	private static void insertion() {
		Scanner sc = new Scanner(System.in);
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata md=new MetadataSources(ssr).getMetadataBuilder().build();
		SessionFactory sf=md.buildSessionFactory();
		Session s=sf.openSession();
		Transaction t=s.beginTransaction();
		
		student std=new student();
		
		System.out.println("enter name");
		String name=sc.next();
		std.setName(name);
		
		System.out.println("enter email");
		String email=sc.next();
		std.setEmail(email);
		
		System.out.println("enter password");
		String password=sc.next();
		std.setPassword(password);
		
		System.out.println("enter phonenumber");
		long phonenumber=sc.nextLong();
		std.setPhonenumber(phonenumber);
		
		s.save(std);
		
		t.commit();
		System.out.println("successfully inserted");
	}

	private static void displaymenu() {
		System.out.println("_______");
		System.out.println("enter your choice");
		System.out.println("\t 1.insertion");
		System.out.println("\t 2.delete");
		System.out.println("\t 3.update");
		System.out.println("\t 4.getbyid");
		System.out.println("\t 5.getall");
		System.out.println("\t6.exit");
		System.out.println("_______");
	}

}

