package utilities;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class ConfrontaRisultati {

	public static void main(String[] args) throws FileNotFoundException {

		Scanner scanner = new Scanner(new File("C:/Users/Antonio/Desktop/confronto.csv"));
		//scanner.useDelimiter(";");

		int total = 0;
		int rowNum = 1;
		while (scanner.hasNext()) {
			
		    String[] parts = scanner.nextLine().split("\\s*;\\s*");
		    
		    //if (parts[0].equals("negative") && parts[1].equals("positive")) {
		    	
		    	if(!parts[0].equals(parts[1])) {
		    	
		        System.out.println(rowNum + " " + parts[0] + " " + parts[1]);
		        ++total;
		    }
		    ++rowNum;
		}

		System.out.println("\n" + "There were " + total + " rows where the two items were not equal.");
		scanner.close();
	}
}
