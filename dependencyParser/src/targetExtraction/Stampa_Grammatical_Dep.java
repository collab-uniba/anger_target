package targetExtraction;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringReader;
import java.util.Collection;
import java.util.List;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.DocumentPreprocessor;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.process.Tokenizer;
import edu.stanford.nlp.process.TokenizerFactory;
import edu.stanford.nlp.trees.GrammaticalStructure;
import edu.stanford.nlp.trees.GrammaticalStructureFactory;
import edu.stanford.nlp.trees.PennTreebankLanguagePack;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreebankLanguagePack;

public class Stampa_Grammatical_Dep {

	private final static String PCG_MODEL = "edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz";        

	private final TokenizerFactory<CoreLabel> tokenizerFactory = PTBTokenizer.factory(new CoreLabelTokenFactory(), "invertible=true");

	private static final LexicalizedParser parser = LexicalizedParser.loadModel(PCG_MODEL);

	public Tree parse(String str) {                
		List<CoreLabel> tokens = tokenize(str);
		Tree tree = parser.apply(tokens);
		return tree;
	}

	private List<CoreLabel> tokenize(String str) {
		Tokenizer<CoreLabel> tokenizer =
				tokenizerFactory.getTokenizer(
						new StringReader(str));    
		return tokenizer.tokenize();
	}

	public static void main(String[] args) throws Exception { 
		
		int i= 1;

		File dir = new File("periodi_preprocessed");
		File[] directoryListing = dir.listFiles();
		System.out.println("Numero file: "+directoryListing.length);

		if (directoryListing != null) {
			for (File child : directoryListing) {

				String path = "periodi_preprocessed/periodo_"+i+".txt";
				
				BufferedReader br = new BufferedReader(new FileReader(path));
				String line = br.readLine();				
				br.close();

				Tree tree = parser.parse(line);  

				List<Tree> leaves = tree.getLeaves();
				// Print words and Pos Tags
				for (Tree leaf : leaves) { 
					Tree parent = leaf.parent(tree);
					System.out.print(leaf.label().value() + "-" + parent.label().value() + ";");
				}
				System.out.println(); 
				i++;
			}		
		}
	}
}