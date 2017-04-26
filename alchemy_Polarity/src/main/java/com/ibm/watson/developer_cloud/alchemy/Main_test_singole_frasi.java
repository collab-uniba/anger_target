package com.ibm.watson.developer_cloud.alchemy;

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
		
		String frase = "Excellent happy to help! If you dont mind can you accept my answer?";
				
		// frase = "No,i disegni fanno davvero pena ma pure tutto il resto. Trame insulse e trasformazioni ancora più ridicole, non pensavo fosse possibile ma è addirittura peggio di GT";  // --> neutral	
		// frase = "odio i frutti di mare";	// --> negative			
		// frase = "la pizza con il tonno è atroce"; 				//	--> neutral
		// frase = "Il mare è bellissimo. Amo il sole e il caldo";	// --> positive
		
		params.put(AlchemyLanguage.TEXT, frase);
		
		// ---- Stampa della polarità rilevata

		//DocumentSentiment sentiment = service.getSentiment(params).execute();
		
		//System.out.println(sentiment);
		
		// ---- Stampa delle emozioni rilevate
		
		DocumentEmotion emotion = service.getEmotion(params).execute();
		
		System.out.println(emotion);
	}
}
