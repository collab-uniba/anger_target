package com.ibm.watson.developer_cloud.alchemy;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import com.ibm.watson.developer_cloud.alchemy.v1.AlchemyLanguage;
import com.ibm.watson.developer_cloud.alchemy.v1.model.DocumentEmotion;
import com.ibm.watson.developer_cloud.alchemy.v1.model.DocumentSentiment;

public class Main_test_singole_frasi {

	public static void main(String[] args) throws Exception {
	
		AlchemyLanguage service = new AlchemyLanguage();
		
		service.setApiKey(""); // key goes here
		
		Map<String,Object> params = new HashMap<String, Object>();
		
		String frase = new String();
		
		//frase = "Excellent happy to help! If you dont mind can you accept my answer?";
		
		frase = "SVG transform on text attribute works excellent! This snippet for example will increase your text by 2x at Y-axis.";
				
		params.put(AlchemyLanguage.TEXT, frase);
		
		// ---- Stampa della polarità rilevata

		//DocumentSentiment sentiment = service.getSentiment(params).execute();
		
		//System.out.println(sentiment);
		
		// ---- Stampa delle emozioni rilevate
		
		DocumentEmotion emotion = service.getEmotion(params).execute();
		
		System.out.println(emotion);
		
		PrintWriter out = new PrintWriter("C:/Users/Antonio/Desktop/test.json");
		out.print(emotion);
		System.out.println("HO SCRITTO IL FILE");
		out.close();
	}
}
