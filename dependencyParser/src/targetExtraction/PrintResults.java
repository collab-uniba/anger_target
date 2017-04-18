package targetExtraction;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.Reader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class PrintResults {
	
	/**
	 * L'output della console � ridirezionato al file C:/Users/Antonio/Desktop/results.csv
	 * 
	 * 
	 * @param args
	 * @throws Exception
	 */

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		Reader in = new FileReader("C:/Users/Antonio/Desktop/output.csv");
		Iterable<CSVRecord> records = CSVFormat.RFC4180.parse(in);

		String id = new String();
		String target = new String();
		
		int prevId = 0;
		for (CSVRecord record : records) {
		    id = record.get(0);
		    target = record.get(1);
		    if(Integer.parseInt(id) == prevId+1){
		        System.out.println(id+";"+target);
		        prevId = Integer.parseInt(id);
		    }else if(Integer.parseInt(id) > prevId+1){
		        prevId++;
		        for(; prevId < Integer.parseInt(id); prevId++){
		            System.out.println(prevId+";no target");
		        }
		        System.out.println(id+";"+target);
		        prevId = Integer.parseInt(id);
		    }
		}
		
		System.out.println("721;no target");
		System.out.println("722;no target");
		System.out.println("723;no target");
	}
}

