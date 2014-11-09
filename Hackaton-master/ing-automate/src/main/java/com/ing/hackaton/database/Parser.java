package com.ing.hackaton.database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Parser {
	public static void main(String[] args) {
		
		/*
        //read json file data to String
        byte[] jsonData;
		try {
			jsonData = Files.readAllBytes(Paths.get("account_test.json"));
			
	         
	        //create ObjectMapper instance
	        ObjectMapper objectMapper = new ObjectMapper();
	         
	        //convert json string to object
	        OBAccount emp = objectMapper.readValue(jsonData, OBAccount.class);
	         
	        System.out.println("Employee Object\n"+emp);
	         
	        //convert Object to json string
	        OBAccount emp1 = createAccount();
	        //configure Object mapper for pretty print
	        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
	         
	        //writing to console, can write to any output stream such as file
	        StringWriter stringEmp = new StringWriter();
	        objectMapper.writeValue(stringEmp, emp1);
	        System.out.println("Employee JSON is\n"+stringEmp);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

		
		

		ClassLoader cl = DBConnector2.class.getClassLoader();
		InputStream is = cl.getResourceAsStream("account_test.json");
		try {
			String str = IOUtils.toString(is);
			
			JsonNode json;
			json = new ObjectMapper().readTree(str);
			printAll(json);
			JsonNode views_available;
			views_available = json.get("accounts");
			printAll(views_available);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static OBAccount createAccount() {
		OBAccount account = new OBAccount();
		
		
		
		return account;
	}
	
	public static void printAll(JsonNode node) {
	     Iterator<String> fieldNames = node.fieldNames();
	     while(fieldNames.hasNext()){
	         String fieldName = fieldNames.next();
	         JsonNode fieldValue = node.get(fieldName);
	         if (fieldValue.isObject()) {
	            System.out.println(fieldName + " :");
	            printAll(fieldValue);
	         } else {
	            String value = fieldValue.asText();
	            System.out.println(fieldName + " : " + value);
	         }
	     }
	}
}
