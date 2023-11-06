package czc;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 *Class containing methods that obtain the user's ZIP code, 
 *query the ZIP code search API, 
 *obtain the result of the request with the address information, 
 *process the obtained information and display it on the screen to the user.
 *
 *@author Ramos, Isaac
 *
 */

public class QueryMethod{

	Scanner scan = new Scanner (System.in);
	private String address, neighborhood, location, state, zipCode;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	/**
	 *Method that obtains the zip code entered by the user.
	 */
	public void DefiningZipCode () {
		System.out.println("Insert your Zip Code(CEP)");
		String CEP = scan.next();
		setZipCode(CEP);
	}
	
	/**
	 * Method that prints information corresponding to the entered zip code on the screen.
	 */
	public String ToString() {
		return "Address: " + getAddress() + "\nNeighborhood: " + getNeighborhood() + "\nCity: " + getLocation() + "\nState: " + getState();
	}

	/**
	 * Method that queries the CEP using the ViaCEP API. 
	 * This class also handles the JSON (structure returned from the request) and fills the variables defined 
	 * at the beginning of the class with the information contained in the treated JSON.
	 */
	public void QueryZipCode () throws IOException {
	String QueryAddress = "https://viacep.com.br/ws/" + getZipCode() + "/json/";
	
	try {
		URL url = new URL (QueryAddress);
		HttpURLConnection conection = (HttpURLConnection) url.openConnection();
		
		if (conection.getResponseCode() != 200)
            throw new RuntimeException("HTTPS error code: " + conection.getResponseCode());
		
		BufferedReader response = new BufferedReader(new InputStreamReader((conection.getInputStream())));
        BufferedReader jsonResponse = response;
		 Gson gson = new Gson();
         JsonObject jsonObject = gson.fromJson(jsonResponse, JsonObject.class);
         
         setAddress(jsonObject.get("logradouro").getAsString());
         setNeighborhood(jsonObject.get("bairro").getAsString());
         setState(jsonObject.get("uf").getAsString());
         setLocation(jsonObject.get("localidade").getAsString());
         
	} catch (MalformedURLException e) {
		throw new RuntimeException("ERROR: " + e);
	}
	
	}
}
