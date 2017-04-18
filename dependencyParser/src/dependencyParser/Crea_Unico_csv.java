package dependencyParser;

import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.Reader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

/**
 * Crea un unico csv in cui:
 * - la prima colonna contiene il commento iniziale
 * - la seconda il numero di soggetti identificati dallo Stanford NLP parser (0, 1 o più di uno)
 * - la terza contiene il soggetto (se unico) oppure "multiple subjects" se ne sono stati identificati più di uno
 * 
 * @author Antonio
 *
 */

public class Crea_Unico_csv {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		int i= 1;
		
		PrintWriter out = new PrintWriter("C:/Users/Antonio/Desktop/subjects.csv");
		
		File dir = new File("csv");
		File[] directoryListing = dir.listFiles();
		System.out.println("Numero file: "+directoryListing.length);

		if (directoryListing != null) {
		    for (File child : directoryListing) {
		    	
				Reader in = new FileReader("csv/periodo_"+i+".csv");
				Iterable<CSVRecord> records = CSVFormat.RFC4180.parse(in);
				
				String columnOne = "";
			    String columnTwo = "";
			    String columnThree = "";
			    
			    int num_subj = 0;
			    
				for (CSVRecord record : records) {
					
					try {
						columnOne = record.get(0);
						num_subj++;
					} catch(ArrayIndexOutOfBoundsException e) {
						
						break;
					}	    

										
					try {
						columnTwo = record.get(1);
						num_subj++;
					} catch(ArrayIndexOutOfBoundsException e) {
						
						break;
					}	 
					
					try {
						columnThree = record.get(2);
						num_subj++;
					} catch(ArrayIndexOutOfBoundsException e) {
						
						break;
					}
				}
				
				if(num_subj==0) {
					
					out.println(num_subj+";"+columnOne);
				}
					
				
				
				if(num_subj==1) {
					
					out.println(num_subj+";"+columnOne);
				}
						
				
				if(num_subj==2) {
					
					num_subj = 1;
					out.println(num_subj+";"+columnOne+" "+columnTwo);
				}
				
				if(num_subj==3) {
					
					 
					out.println("più di uno"+";"+"Multiple Subjects");
				}
						
				i++;
		    }		    	
		}
		out.close();
	}
}
