package com.ibm.watson.developer_cloud.tone_analyzer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.ToneAnalyzer;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneAnalysis;

public class Main {

	public static void main(String[] args) throws Exception {
		
		ToneAnalyzer service = new ToneAnalyzer(ToneAnalyzer.VERSION_DATE_2016_05_19);
		
		service.setUsernameAndPassword("", ""); // key goes here
		
		List<String> lista = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader("goldstandard.txt"));
		String line = br.readLine();
		while (line != null)
		{
			lista.add(line); // add the line to your list
		    line = br.readLine(); // try to read another line
		}
		
		br.close();

		// lista è pronta con le frasi al suo interno
				
		int i = 1;
		
		for(String frase: lista) {
			
			// Call the service and get the tone
			
			ToneAnalysis tone = service.getTone(frase, null).execute();
						
			PrintWriter out = new PrintWriter("json/emotion"+"_"+i+".json");
			out.print(tone);
			System.out.println("HO SCRITTO IL FILE"+" "+i);
			i++;
			out.close();
		}
	}
}
