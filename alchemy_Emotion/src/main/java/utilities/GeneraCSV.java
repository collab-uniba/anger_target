package utilities;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

public class GeneraCSV {
	
	/**
	 * Crea il file csv riportante per ogni riga sentence e sentiment
	 * 
	 * @param args
	 * @throws Exception
	 */

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		// ---- Riempio l'array sentence con le sentence
		
		String[] sentence = new String[5000];

		BufferedReader br_1 = new BufferedReader(new FileReader("sentence.txt"));
		
		StringBuilder sb_1 = new StringBuilder();
	    String line_1 = br_1.readLine();

	    while (line_1 != null) {
	    	
	    	// Metti ogni frase in una cella di sentence[]
	    	sb_1.append(line_1);
	        sb_1.append(System.lineSeparator());
	        line_1 = br_1.readLine();

	    }
	    String tutte_le_frasi = sb_1.toString();
	    br_1.close();
	    
	    //System.out.println(tutte_le_frasi);
	    
	    sentence = tutte_le_frasi.split("\r\n");
	    
	    //for(String el: sentence)
	    	//System.out.println(el);
	   
	   System.out.println(sentence.length+" Frasi");
		
	   // ---- Riempio l'array score con le emotion

		String[] score = new String[5000];

		BufferedReader br_2 = new BufferedReader(new FileReader("emotion.txt"));
		
		StringBuilder sb_2 = new StringBuilder();
	    String line_2 = br_2.readLine();

	    while (line_2 != null) {
	    	
	    	// Metti ogni frase in una cella di sentence[]
	    	sb_2.append(line_2);
	        sb_2.append(System.lineSeparator());
	        line_2 = br_2.readLine();

	    }
	    String tutti_gli_score = sb_2.toString();
	    br_2.close();
	    
	    //System.out.println(tutte_le_frasi);
	    
	    score = tutti_gli_score.split("\r\n");
	    
	    //for(String el: score)
	    	//System.out.println(el);
	   
	   System.out.println(score.length +" Score");
	   
		// ---- SCRIVO IL FILE CSV
	    
		PrintWriter pw = new PrintWriter(new File("result.csv"));
        StringBuilder sb_3 = new StringBuilder();
        
        //sb_3.append("sentence");
        //sb_3.append(';');
        //sb_3.append("score");
        //sb_3.append('\n');
		        
        for(int i=0;i <score.length;i++) {

            sb_3.append(sentence[i]);
            sb_3.append(";");

            sb_3.append(score[i]);
            sb_3.append('\n');            
            
            //System.out.print(sb.toString()); to print values 
            //sb =new StringBuilder();
        }
        pw.write(sb_3.toString());
        pw.close();

        System.out.println("done!");
	}
}
