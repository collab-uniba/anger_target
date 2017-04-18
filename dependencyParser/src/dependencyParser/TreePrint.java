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
 * in questo caso scrive nei file solo i subj
 * 
 * 
 * @author Antonio
 *
 */

public class TreePrint {

	public static void demoDP(LexicalizedParser lp, String filename, int i) throws Exception {

		TreebankLanguagePack tlp = new PennTreebankLanguagePack();
		
		GrammaticalStructureFactory gsf = tlp.grammaticalStructureFactory();

		for (List<HasWord> sentence : new DocumentPreprocessor(filename)) {
			Tree parse = lp.apply(sentence);
			//parse.pennPrint();	 	// Stampa l'albero di parsing

			GrammaticalStructure gs = gsf.newGrammaticalStructure(parse);
			Collection tdl = gs.typedDependenciesCCprocessed();

			//System.out.println(tdl); // stampa la dependencies tutte su una riga

			//System.out.println();
			
			PrintWriter out = new PrintWriter("csv/periodo"+"_"+i+".csv");
			
			String separator ="";

			for(Object o: tdl) {

				if(o.toString().startsWith("nsubj", 0)) {

					out.print(separator +o);
					separator = ",";
				}
			}

			out.close();
			//i++;

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

				String path = "periodi/periodo_"+i+".txt";

				demoDP(lp, path, i);
				i++;
			}



			//demoDP(lp,"C:/Users/Antonio/Desktop/sentence.txt");
		}
		System.out.println("DONE");
	}
}