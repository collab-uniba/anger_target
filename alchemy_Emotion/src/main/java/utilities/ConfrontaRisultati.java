package utilities;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class ConfrontaRisultati {

	public static void main(String[] args) throws FileNotFoundException {

		Scanner scanner = new Scanner(new File("C:/Users/Antonio/Desktop/xxx.csv"));
		//scanner.useDelimiter(";");

		int total = 0;
		int rowNum = 1;
		double total_rows = 0; // se int la divisione per accordo è intera
		
		while (scanner.hasNext()) {
			
			total_rows++;
			
		    String[] parts = scanner.nextLine().split("\\s*;\\s*");
		    
		    //if (parts[0].equals("negative") && parts[1].equals("positive")) {
		    	
		    if (parts[0].equals(parts[1])) {
		    	
		        System.out.println(rowNum + " " + parts[0] + " " + parts[1]);
		        ++total;
		    }
		    ++rowNum;
		}

		System.out.println("\n" + "There were " + total + " rows where the two items were the same. [Total rows: "+total_rows+"]");
		double accordo = (total/total_rows)*100;
		System.out.println("Accordo: "+accordo+"%");
		scanner.close();
	}
}
