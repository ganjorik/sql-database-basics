package org.example;

import org.example.util.DBConnection;

import java.sql.Connection;

public class TestConnection {

	public static void main(String[] args) {

		try {

			Connection connection = DBConnection.getConnection();

			if (connection != null) {
				System.out.println("Connection successful!");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}