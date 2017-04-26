package utilities;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
		
public class ParseJSON {
	
	/**
	 * Legge i file json e salva il sentiment rilevato
	 * in ciascuna sentence nel file sentiment.txt
	 * 
	 * @param args
	 * @throws Exception
	 * @throws IOException
	 * @throws ParseException
	 */

	public static void main(String[] args) throws Exception, IOException, ParseException {
		
		ArrayList<String> sentiment = new ArrayList<String>();
		
		String type;
		double score = 0;
		
		int i = 1;
				
		File dir = new File("json");
		File[] directoryListing = dir.listFiles();
		System.out.println("Numero di file: "+directoryListing.length);
		
		JSONObject jsonObject;
		
		if (directoryListing != null) {
		    while (i<=directoryListing.length+151) {		// length + x dove x è il numero di json assenti
		     
			    JSONParser parser = new JSONParser();
			    
			    try {
			    	
				    jsonObject  = (JSONObject) parser.parse(new FileReader("json/sentiment_"+i+".json"));
				    System.out.println(i);
			    
			    } catch (Exception e) {
			    	
			    	i++;
			    	continue;
			    }
			    			
			    JSONObject doc = (JSONObject) jsonObject.get("docSentiment"); // get the nested object first
			    type = (String)doc.get("type"); // get a string from the nested object
			    
				// CODICE PER PRENDERE ANCHE LO SCORE
			    if (!(type.equals("neutral"))){
			    
					score = (double) doc.get("score");
				}
				
				else score = 0;
				
				//sentiment.add(type+";"+score);
			    sentiment.add(type);
				i++;
		    }
		}

		PrintWriter out = new PrintWriter("sentiment.txt");
		
		for(String value: sentiment)
			out.println(value);

		System.out.println("FILE SCRITTO!");
		out.close();
	}
}