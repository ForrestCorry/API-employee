package com.api;

	import java.io.BufferedReader;
	import java.io.IOException;
	import java.io.InputStreamReader;
	import java.net.HttpURLConnection;
	import java.net.MalformedURLException;
	import java.net.URL;
	import java.net.URLEncoder;


	/*Simple class to make requests to google maps
	 * @author Forrest Corry
	 * @since 2016-07-09
	 */


	public class Post {
		/*
		 * the URL of the API we want to connect to
		 */
		protected static String endpoint = "http://localhost:1337/employee";
		
		/*The character set to use when encoding IRL parameters
		 * 
		 */
		protected static String charset = "UTF-8";
		
		/*
		 * API key used for making requests to API
		 */
		
		public static void main(String[] args) {
			
			try{
				
				//input fields
				String firstName = "Megan";
								
				String lastName = "Stevens";
								
				String email = "mudjewelery@broadstripe.com";
				
				String cellPhone = "410.852.8885";
				
				String homePhone = "410.519.2814";
				
				String password = "ForLawMom1";
				
				String active = "1";
				
				//creates the url parameters as a string encoding them with the defined charset
				String queryString = String.format("firstName=%s&lastName=%s&email=%s&cellPhone=%s&homePhone=%s&password=%s&active=%s",
						URLEncoder.encode(firstName, charset),
						URLEncoder.encode(lastName, charset),
						URLEncoder.encode(email, charset),
						URLEncoder.encode(cellPhone, charset),
						URLEncoder.encode(homePhone, charset),
						URLEncoder.encode(password, charset),
						URLEncoder.encode(active, charset));
				
						
				//creates a new URL out of the endpoint, returnType and queryString
				URL employeeAPI = new URL(endpoint + "?" + queryString);
				HttpURLConnection connection = (HttpURLConnection) employeeAPI.openConnection();
				connection.setRequestMethod("POST");
				
				//if we did not get a 201 (success) throw an exception
				if(connection.getResponseCode() !=201) {
					throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseCode());
				}
				
				//read response into buffer
				BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				
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
