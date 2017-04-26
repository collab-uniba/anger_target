package com.ibm.watson.developer_cloud.alchemy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import com.ibm.watson.developer_cloud.alchemy.v1.AlchemyLanguage;
import com.ibm.watson.developer_cloud.alchemy.v1.model.DocumentSentiment;

public class Main {
	
	/**
	 * Prima di avviare l'analisi, copiare da goldstandard.txt
	 * il chunk di 1000 frasi in sentence.txt
	 * 
	 * @param args
	 * @throws Exception
	 */

	public static void main(String[] args) throws Exception {
	
		AlchemyLanguage service = new AlchemyLanguage();
		
		service.setApiKey(""); // key goes here
		
		// Leggo le frasi da analizzare dal file sentence.txt e le metto in una list
		
		List<String> lista = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader("sentence.txt"));
		String line = br.readLine();
		while (line != null)
		{
			lista.add(line); // add the line to your list
		    line = br.readLine(); // try to read another line
		}
		
		br.close();

		// lista è pronta con le frasi al suo interno
		
		Map<String,Object> params = new HashMap<String, Object>();
		
		int i = 1;
		
		for(String frase: lista) {
			
			//params.put(AlchemyLanguage.TEXT, "IBM Watson won the Jeopardy television show hosted by Alex Trebek");
			
			params.put(AlchemyLanguage.TEXT, frase);
			
			DocumentSentiment sentiment;
			
			try {
				
				sentiment = service.getSentiment(params).execute();
			
			} catch(Exception | Error e) {
				
				System.out.println("----------------ERRORE RIGA "+i+": "+frase);
				System.out.println(e);
				i++;
				continue;
			}
			
			PrintWriter out = new PrintWriter("json/sentiment"+"_"+i+".json");
			out.print(sentiment);
			System.out.println("HO SCRITTO IL FILE"+" "+i);
			i++;
			out.close();
		}
	}
}
