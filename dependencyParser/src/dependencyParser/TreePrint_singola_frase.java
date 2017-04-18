package dependencyParser;

import java.io.PrintWriter;
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
 * Stampa il parsing tree e le universal dependencies di una sola frase
 * 
 * 
 * @author Antonio
 *
 */

public class TreePrint_singola_frase {

	public static void demoDP(LexicalizedParser lp, String filename) throws Exception {

		TreebankLanguagePack tlp = new PennTreebankLanguagePack();
		
		GrammaticalStructureFactory gsf = tlp.grammaticalStructureFactory();

		for (List<HasWord> sentence : new DocumentPreprocessor(filename)) {
			Tree parse = lp.apply(sentence);
			//parse.pennPrint();	 	// Stampa l'albero di parsing

			GrammaticalStructure gs = gsf.newGrammaticalStructure(parse);
			Collection tdl = gs.typedDependenciesCCprocessed();

			System.out.println(tdl); // stampa la dependencies tutte su una riga
			
			//System.out.println();
			/*
			for(Object o: tdl) {

				if(o.toString().startsWith("nsubj", 0)) {

					System.out.println(o);
				}
				*/
			}
		}
//	}


	public static void main(String args[]) throws Exception {
		
		LexicalizedParser lp = LexicalizedParser.loadModel("edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz");
		
		demoDP(lp,"C:/Users/Antonio/Desktop/una_frase.txt");
		
		//demoDP(lp,"C:/Users/Antonio/Desktop/sentence.txt");
	}
}