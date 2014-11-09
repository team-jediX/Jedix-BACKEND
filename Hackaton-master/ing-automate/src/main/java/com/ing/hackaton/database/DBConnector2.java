package com.ing.hackaton.database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.commons.io.IOUtils;

/* 
 * Remember to startup MYSQL & it is very nice to have the MySQL workbench and Command Line Client...
 *
 *SQL COMMANDS:
 *
 *CREATE SCHEMA `accounts` ;
 *
 *CREATE TABLE `accounts`.`accounts` (
  `id` VARCHAR(45) NOT NULL,
  `label` VARCHAR(45) NULL,
  `views_available` BLOB NULL,   --> this one is not working, because this fields contains many flags
  `bank_id` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));
 *
*/

public class DBConnector2 {
	private static String tableName = "accounts";
	public static void main(String[] args) {
		 try {
			   ClassLoader cl = DBConnector2.class.getClassLoader();
			   InputStream is = cl.getResourceAsStream("account_test2.json");
			 
			   String str = IOUtils.toString(is);

			   JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON(str);
			   JSONArray jsonArr = jsonObject.getJSONArray("accounts");
			   JSONObject obj = null;
			   JSONArray nameArr = null;
			   JSONArray valArr = null;
			   
			   for (int i = 0; i < jsonArr.size(); i++) {
			    obj = jsonArr.getJSONObject(i);
			    nameArr = obj.names();
			    valArr = obj.toJSONArray(nameArr);
			    saveRecord(nameArr, valArr);
			   }
		} catch (Exception e) {
			   e.printStackTrace();
		}
		 
	}
	
	private static void saveRecord(JSONArray nameArray, JSONArray valArray) {
		  Connection conn = getConnection();
		  StringBuffer sb = new StringBuffer("insert into " + tableName + "(");
		  int size = nameArray.size();
		  int count = 0;
		  Iterator<Object> iterator = nameArray.iterator();
		  
		  while (iterator.hasNext()) {
		   if (count < (size - 1))
		    sb.append(iterator.next() + ",");
		   else
		    sb.append(iterator.next() + ")");
		   count++;
		  }
		  sb.append(" values(");
		 
		  for (int i = 0; i < size; i++) {  
		   if (i < (size - 1))
		    sb.append("?,");
		   else
		    sb.append("?)");  
		  }
		  System.out.println(sb.toString());
		  try {
		   PreparedStatement pstmt = conn.prepareStatement(sb.toString());
		   bindVariables(valArray, pstmt);
		   pstmt.executeUpdate();
		  } catch (SQLException e) {
		   e.printStackTrace();
		  }
		 }
		 private static void bindVariables(JSONArray valArray,
		   PreparedStatement pstmt) throws SQLException {
		  Iterator<Object> iterator = valArray.iterator();
		  int cnt = 0;
		  while (iterator.hasNext()) {
		   Object obj = iterator.next();
		   if (obj instanceof String) {
		    pstmt.setString(++cnt, (String) obj);
		   } else if (obj instanceof Integer) {
		    pstmt.setLong(++cnt, (Integer) obj);
		   } else if (obj instanceof Long) {
		    pstmt.setLong(++cnt, (Long) obj);
		   } else if (obj instanceof Double) {
		    pstmt.setDouble(++cnt, (Double) obj);
		   }
		  }
		 }
		 private static Connection getConnection() {
		  Connection con = null;
		  String url = "jdbc:mysql://localhost:3306/";
		  String db =  "accounts";
		  String driver = "com.mysql.jdbc.Driver";
		  String user = "admin";
		  String pass = "JEDIX";
		  try {
		   Class.forName(driver);
		   con = DriverManager.getConnection(url + db, user, pass);
		  } catch (ClassNotFoundException e) {
		   e.printStackTrace();
		  } catch (SQLException e) {
		   e.printStackTrace();
		  }
		  return con;
		 }

}
