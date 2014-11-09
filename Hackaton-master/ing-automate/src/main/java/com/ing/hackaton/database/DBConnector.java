package com.ing.hackaton.database;

/* 
 * Remember to startup MYSQL:
 * from a command line, enter:
 *
 * "C:\Program Files\MySQL\MySQL Server 5.6\bin\mysqld"
*/

import java.sql.*; 
import com.mysql.jdbc.Driver;

//this class makes a connection with MYSQL
public class DBConnector { 
	public static void main(String[] args) { 
		String url = "jdbc:mysql://localhost:3306/"; 
		String dbName = "demo"; 
		String driver = "com.mysql.jdbc.Driver"; 
		String userName = "root"; 
		String password = "JEDIX"; 
		try 
		{ 
			Class.forName(driver).newInstance(); 
			Connection conn = DriverManager.getConnection(url+dbName,userName,password); 
			Statement st = conn.createStatement(); 
			ResultSet res = st.executeQuery("SELECT * FROM event"); 
			while (res.next()) 
			{ 
				int id = res.getInt("id"); 
				String msg = res.getString("msg");
				System.out.println(id + "\t" + msg);
			} 
			int val = st.executeUpdate("INSERT into event VALUES("+1+","+"'Easy'"+")"); 
			if(val==1) System.out.print("Successfully inserted value"); 
			conn.close(); 
		} catch (Exception e) { e.printStackTrace(); } 
	}
}

//Source: http://mrbool.com/how-to-connect-with-mysql-database-using-java/25440
//Read more: http://mrbool.com/how-to-connect-with-mysql-database-using-java/25440#ixzz3IFM7RqkN