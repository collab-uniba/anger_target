package utilities;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ParseJSON {

public static void main(String[] args) throws Exception, IOException, ParseException {
		
		String type ="";
		ArrayList<String> sentiment = new ArrayList<String>();
		
		PrintWriter out = new PrintWriter("emotion.txt");
		
		int i= 1;
		
		File dir = new File("json");
		File[] directoryListing = dir.listFiles();
		System.out.println("Numero file: "+directoryListing.length);
		
		if (directoryListing != null) {
		    for (File child : directoryListing) {
		     
			    JSONParser parser = new JSONParser();
			    JSONObject jsonObject  =(JSONObject) parser.parse(new FileReader("json/emotion_"+i+".json"));
			    JSONObject document_tone = (JSONObject) jsonObject.get("document_tone"); 
			    
			    JSONArray tone_categories = (JSONArray) document_tone.get("tone_categories");
			    
			    JSONObject tones = (JSONObject) tone_categories.get(0);

			    JSONArray array_con_emozioni = (JSONArray) tones.get("tones");

			    double anger;	//0
			    double disgust; //1
			    double fear;	//2
			    double joy;		//3
			    double sadness; //4
			   
			    JSONObject pezzo_joy = (JSONObject) array_con_emozioni.get(3);
			    
			    joy = (Double) pezzo_joy.get("score");
			    
			    JSONObject pezzo_sadness = (JSONObject) array_con_emozioni.get(4);
			    
			    sadness = (Double) pezzo_sadness.get("score");
			    
			    JSONObject pezzo_disgust = (JSONObject) array_con_emozioni.get(1);
			    
			    disgust = (Double) pezzo_disgust.get("score");
			    
			    JSONObject pezzo_anger = (JSONObject) array_con_emozioni.get(0);
			    
			    anger = (Double) pezzo_anger.get("score");
			    
			    JSONObject pezzo_fear = (JSONObject) array_con_emozioni.get(2);
			    
			    fear = (Double) pezzo_fear.get("score");
			    
			    //System.out.println(joy+";"+sadness+";"+disgust+";"+anger+";"+fear);
			    
			    out.println(joy+";"+sadness+";"+disgust+";"+anger+";"+fear);
			    
			    conta_doppia_emozioni(joy, sadness, disgust, anger, fear);
			    
			    i++;
			    
		    }
		}
		System.out.println("Emozioni: "+i);
		out.close();
	}

private static void conta_doppia_emozioni(double joy, double sadness, double disgust, double anger, double fear) {

	int conto_totale = 0; // numero di righe in cui ci sono più emozioni maggiori di 0.5
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
		System.out.println(conto_totale);
	}
}
}
