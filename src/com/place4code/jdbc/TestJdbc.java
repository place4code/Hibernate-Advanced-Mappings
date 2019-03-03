package com.place4code.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {

	public static void main(String[] args) {

		final String URL = "jdbc:mysql://localhost:3306/hb-01-one-to-one-uni?useSSL=false&serverTimezone=UTC";
		final String user = "hbstudent";
		final String pass = "hbstudent";
		
		try {
			System.out.println("Connecting to database . . .");
			Connection connection = DriverManager.getConnection(URL, user, pass);
			System.out.println("SUCCESS");
		} catch(Exception e) {
			System.out.println(e);
		}

	}

}
