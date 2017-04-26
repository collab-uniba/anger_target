package utilities;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.Reader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class CSV_to_TXT {
	
	/**
	 * Legge il GoldStandard usando la libreria CommonsCSV
	 * e crea un file txt con una sentence per ogni riga
	 * 
	 * @param args
	 * @throws Exception
	 */

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		int i = 1;
		
		PrintWriter out = new PrintWriter("goldstandard.txt");
			
		Reader in = new FileReader("gold_emozioni.csv");
		Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
		for (CSVRecord record : records) {
		    String sentence = record.get("TEXT");
		    

		    //String polarity = record.get("polarity");
		    
		    //System.out.println("FRASE " +i+": " +sentence);
		    out.println(sentence);
			i++;

		}
		System.out.println(i+ " righe lette");
		out.close();
	}
}