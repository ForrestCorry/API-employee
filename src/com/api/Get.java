package com.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Get {
	/*
	 * the URL of the API we want to connect to
	 */
	protected static String endpoint = "http://localhost:1337/employee/";
	
	/*The character set to use when encoding IRL parameters
	 * 
	 */
	protected static String charset = "UTF-8";
	
	/*
	 * API key used for making requests to API
	 */
	
	public static void main(String[] args) {
		
		try{
			
			//creats a new URL out of the endpoint, returnType and queryString
			URL employeeAPI = new URL(endpoint + "?");
			HttpURLConnection connection = (HttpURLConnection) employeeAPI.openConnection();
			connection.setRequestMethod("GET");
			
			//if we did not get a 200 (success) throw an exception
			if(connection.getResponseCode() !=200) {
				throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseCode());
			}
			
			//read response into buffer
			BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));
			
			//loop of buffer line by line until it return null meaning there are no more lines
			while(br.readLine() !=null){
				System.out.println(br.readLine());
			}
			
			//close connection to API
			connection.disconnect();
			
			
		}catch (MalformedURLException e){
			e.printStackTrace();
			
		}catch (IOException e){
			e.printStackTrace();
		}
		
		
	}//main
	


}//class




