package utilities;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class PickMainEmotion {
	
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

		PrintWriter out = new PrintWriter("single_emotion.txt");

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
		System.out.println("Emozioni: "+i);
		out.close();
	}
}
