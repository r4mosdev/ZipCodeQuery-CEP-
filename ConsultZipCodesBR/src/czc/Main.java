package czc;

import java.io.IOException;

/**
 * Class that runs the application.
 */
public class Main {

	public static void main(String[] args) throws IOException {
		
		QueryMethod zipCode = new QueryMethod();
		
	zipCode.DefiningZipCode();
	zipCode.QueryZipCode();
	System.out.println(zipCode.ToString());

	}

}
