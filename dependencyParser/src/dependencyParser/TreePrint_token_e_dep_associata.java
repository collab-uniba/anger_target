package dependencyParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.process.DocumentPreprocessor;
import edu.stanford.nlp.trees.GrammaticalStructure;
import edu.stanford.nlp.trees.GrammaticalStructureFactory;
import edu.stanford.nlp.trees.PennTreebankLanguagePack;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreebankLanguagePack;

/**
 * Stampa il parsing tree e le universal dependencies
 * 
 * scrive nei file ogni token_dependencyAssociata
 * 
 * 
 * @author Antonio
 *
 */

public class TreePrint_token_e_dep_associata {

	public static void demoDP(LexicalizedParser lp, String filename, int i) throws Exception {

		TreebankLanguagePack tlp = new PennTreebankLanguagePack();
		
		GrammaticalStructureFactory gsf = tlp.grammaticalStructureFactory();
		
		for (List<HasWord> sentence : new DocumentPreprocessor(filename)) {
			
			Tree parse = lp.apply(sentence);

			GrammaticalStructure gs = gsf.newGrammaticalStructure(parse);
			Collection tdl = gs.typedDependenciesCCprocessed();
					
			String separator =" ";
			
			ArrayList<String> frase = new ArrayList<String>();
			
			String full_sentence[] = null;
			String first_part[] = null;

			for(Object o: tdl) {
				
				full_sentence = o.toString().split(",");
				
				full_sentence[1] = full_sentence[1].substring(1, full_sentence[1].length()-3);
				
				first_part = full_sentence[0].split("\\(");
				
				full_sentence[0] = first_part[0];
				
				String token = full_sentence[1]+"_"+full_sentence[0];
				
				frase.add(token);
			}
			
			for(String token: frase) {
				
				System.out.print(token+separator);
				separator = " ";
			}
			
			System.out.println();			
		}
		
	}


	public static void main(String args[]) throws Exception {

		LexicalizedParser lp = LexicalizedParser.loadModel("edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz");

		int i= 1;

		File dir = new File("periodi");
		File[] directoryListing = dir.listFiles();
		System.out.println("Numero file: "+directoryListing.length);

		if (directoryListing != null) {
			for (File child : directoryListing) {

				String path = "periodi_preprocessed/periodo_"+i+".txt";

				demoDP(lp, path, i);
				i++;
			}



			//demoDP(lp,"C:/Users/Antonio/Desktop/sentence.txt");
		}
		System.out.println("DONE");
	}
}