package nuovi_test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Test_doppia_emozione {

	public static void main(String[] args) throws Exception, IOException, ParseException {
		// TODO Auto-generated method stub

		PrintWriter out = new PrintWriter("emozioni.txt");
		
		int i= 1;
		
		int conto_totale = 0; // numero di righe in cui ci sono più emozioni maggiori di 0.5
		
		File dir = new File("json");
		File[] directoryListing = dir.listFiles();
		System.out.println("Numero di file json presenti: " + directoryListing.length);
		
		if (directoryListing != null) {
		    for (File child : directoryListing) {
		     
			    JSONParser parser = new JSONParser();
			    JSONObject jsonObject  =(JSONObject) parser.parse(new FileReader("json/emotion_"+i+".json"));
			    JSONObject doc = (JSONObject) jsonObject.get("docEmotions"); // get the nested object first
			    
			    //type = (String)doc.get("type"); // get a string from the nested object
	
			    //System.out.println(doc);
			    
				double joy = (double) doc.get("joy");
				double sadness = (double) doc.get("sadness");
				double disgust = (double) doc.get("disgust");
				double anger = (double) doc.get("anger");
				double fear = (double) doc.get("fear");
				
				int trovato = 0;
				
				String result = null;
				
				if (joy > 0.5) {
					
					trovato++;
					result = " joy = "+joy;
				}

				
				if (sadness > 0.5) {
					
					trovato++;
					result += " sadness = "+sadness;
				}
	
				
				if (disgust > 0.5) {
					
					trovato++;
					result += " disgust = "+disgust;
				}
				
				if (anger > 0.5) {
					
					trovato++;
					result += " anger = "+anger;
				}
				
				if (fear > 0.5) {
					
					trovato++;
					result += " fear = "+fear;
				}
				
				if (trovato>1) {
					
					conto_totale++;
					//System.out.print(trovato+" valori maggiori di 0.5 per il commento "+i+":\"\"");
					//System.out.print(" ---- ");
					System.out.println(result);
				}

					
				
				//out.println(joy+";"+sadness+";"+disgust+";"+anger+";"+fear);			
				i++;
		    }
		}
		System.out.println(conto_totale);
		out.close();
	}
}