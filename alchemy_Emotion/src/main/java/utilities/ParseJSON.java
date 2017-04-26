package utilities;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
		
public class ParseJSON {
	
	/**
	 * Legge i file json e salva la emotion prevalente rilevata
	 * in ciascuna sentence nel file emotion.txt
	 * 
	 * @param args
	 * @throws Exception
	 * @throws IOException
	 * @throws ParseException
	 */

	public static String getMainEmotion(Map<String, Double> map) {
		
		String mainEmotion = new String();
		
        double maxValueInMap=(Collections.max(map.values()));  // This will return max value in the Hashmap
        
        if (maxValueInMap <= 0.5)
        	mainEmotion = "neutral";
        
        else {
        
		for (Entry<String, Double> entry : map.entrySet()) {  // Iterate through hashmap
            
			if (entry.getValue()==maxValueInMap) {
                //System.out.println(entry.getKey());     // Print the key with max value
                mainEmotion = entry.getKey().toString();
            }
		}
	}
		return mainEmotion;
	}
	
	public static void main(String[] args) throws Exception, IOException, ParseException {
				
		PrintWriter out = new PrintWriter("emotion.txt");
		
		int i= 1;
		
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
				
				Map<String, Double> map = new HashMap<String, Double>();
				
				map.put("joy", joy);
				map.put("sadness", sadness);
				map.put("disgust", disgust);
				map.put("anger", anger);
				map.put("fear", fear);
				
				String emotion = getMainEmotion(map);
				
				//System.out.println(emotion);
				
				out.println(emotion);

				i++;
		    }
		}
		System.out.println("FILE SCRITTO: "+i+" emozioni!");
		out.close();	
	}
}