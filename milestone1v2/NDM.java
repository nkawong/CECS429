package milestone1v2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.tartarus.snowball.ext.englishStemmer;

public class NDM {
	static HashMap<String, HashSet<String>> vocab_values;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// CorpusSelectionGUI start = new CorpusSelectionGUI();
		// start.setVisible(true);
		final PositionalPosting aPosting = new PositionalPosting();
		kGramIndex kgram = new kGramIndex();
		// the list of file names that were processed
		final List<String> fileNames = new ArrayList<String>();
		Scanner input = new Scanner(System.in);
		int count = 0;
		ArrayList<Integer> mDocumentID = new ArrayList<Integer>();
		String pwd1 = "/Users/n.wong/documents/workspace/cecs429/src/Homework3/MobyDick";

		englishStemmer stemmer = new englishStemmer();

		final Path currentWorkingPath = Paths.get(pwd1).toAbsolutePath();
		String choice;
		int pos = 1;
		// String pwd2 = ""

		/*
		 * Ask the user to enter path of index Enter
		 * /Users/n.wong/documents/workspace/cecs429/
		 */
		System.out.println("Please press n for National Park Files");
		choice = input.nextLine();
		while (!choice.equals("n") || choice.equals("N")) {
			System.out.println("Please press n for National Park Files");
			choice = input.nextLine();
		}
		if (choice.equals("n") || choice.equals("N")) {
			// This is our standard "walk through all .txt files" code.
			try {
				Files.walkFileTree(currentWorkingPath, new SimpleFileVisitor<Path>() {
					int id = 0;
					// mDocumentID.add(aDocID);

					public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
						// make sure we only process the current working
						// directory
						if (currentWorkingPath.equals(dir)) {
							return FileVisitResult.CONTINUE;
						}
						return FileVisitResult.SKIP_SUBTREE;
					}

					public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
						// only process .txt files
						if (file.toString().endsWith(".txt")) {
							// we have found a .txt file; add its name to the
							// fileName list,
							// then index the file and increase the document ID
							// counter.
							System.out.println("Indexing file " + file.getFileName());

							// mDocumentID.add(id);
							fileNames.add(file.getFileName().toString());
							indexFile(file.toFile(), aPosting, id);
							// mDocumentID++;
							id++;
							// aPosting.setPos(pos);
						}
						return FileVisitResult.CONTINUE;
					}

					// don't throw exceptions if files are locked/other errors
					// occur
					public FileVisitResult visitFileFailed(Path file, IOException e) {

						return FileVisitResult.CONTINUE;
					}

				});
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Enter a word you would like to search for");
		choice = input.nextLine();
		while (!choice.equals(":q")) {
			if (choice.contains('*' + "")) {
				kgram.getVocab().keySet();
				for (String check : kgram.getVocab().keySet()) {
					System.out.println("fadsfaf" + check);
				}
				wildCardSplit splitter = new wildCardSplit(choice);
				List<String> list_words = new ArrayList<String>();
				list_words = splitter.word_split(vocab_values);
				//System.out.println(list_words);
				for(String words:list_words){
					stemmer.setCurrent(words);
					if(stemmer.stem()){
						words=stemmer.getCurrent();
					}
					printIndex(words,aPosting,fileNames);
				}
				System.out.println("Enter a word you would like to search for please");
				choice = input.nextLine();
				System.out.println(choice);
			
					
				
			}
			else if(choice.contains("near/")){
				StreamToken token = new StreamToken(choice);
				String [] words = choice.split(" ");
				String[] query = words[1].split("/");
				int k = Integer.parseInt(query[1]);
				token = new StreamToken(words[0]);
				words[0] = token.nextToken();
				stemmer.setCurrent(words[0]);
				if(stemmer.stem()){
					words[0] = stemmer.getCurrent();
				}
				System.out.println(words[2]);
				token = new StreamToken(words[2]);
				words[2] = token.nextToken();
				System.out.println(words[2]);
				stemmer.setCurrent(words[2]);
				if(stemmer.stem()){
					words[2] = stemmer.getCurrent();
				}
				
				System.out.println(Nearing(aPosting, words[0], words[2], k, fileNames));
				choice = input.nextLine();
				
			}
			else {
				StreamToken token = new StreamToken(choice);
				choice = token.nextToken();
				stemmer.setCurrent(choice);
				if(stemmer.stem()){
					choice = stemmer.getCurrent();
				}
				printIndex(choice, aPosting, fileNames);

				System.out.println("Enter a word you would like to search for please");
				choice = input.nextLine();
				System.out.println(choice);
			}

		}

		// System.out.println("This is the docID: " +
		// Nearing(aPosting,"puzzl","long",6));

	}

	public static List<String> Nearing(PositionalPosting pos_position, String first, String last, int k, List<String> fileNames) {
		List<String> near = new ArrayList<String>();
		// check docid to see if they are the same
		for (Postings first_term : pos_position.getPostings(first)) {
			for (Postings second_term : pos_position.getPostings(last)) {
				if (first_term.getDocID() == second_term.getDocID()) {
					for (int pos_first : first_term.getPosition()) {
						for (int pos_second : second_term.getPosition()) {
							if (pos_second - pos_first <= k) {
								if(!(near.contains(fileNames.get(first_term.getDocID())))){
									near.add(fileNames.get(first_term.getDocID()));
								}
							}
						}
					}
				}
			}
		}

		return near;
	}

	private static void indexFile(File file, PositionalPosting pos_position, int id) {
		// String term = " ";
		try {
			StreamToken token = new StreamToken(file);
			// HashMap<String, ArrayList<Integer>> pos = null;
			englishStemmer stemmer = new englishStemmer();
			List<String> remove_token = new ArrayList<String>();
			while (token.hasNextToken()) {
				remove_token.add(token.nextToken());
			}
			// pos = token.position_term(remove_token);
			
			//////////////////////////////////////////////////
			//////////////////////////////////////////////////
			//This adds to the Grand Index
			/////////////////////////////////////////////////
			////////////////////////////////////////////////
			String one_term = "";
			for (int i = 0; i < remove_token.size(); i++) {
				//stems the word
				stemmer.setCurrent(remove_token.get(i));
				//this check if the word has been checked
				if (stemmer.stem()) {
					// System.out.println(stemmer.getCurrent());
					one_term = stemmer.getCurrent();
				}
				pos_position.addTerm(one_term, id, i);
			}
			/////////////////////////////////////////////////////
			////////////////////////////////////////////////////
			//This add to the vocab hashmap
			///////////////////////////////////////////////////
			//////////////////////////////////////////////////
			one_term="";
			kGramIndex token_gram = new kGramIndex();
			for(int i = 0; i<remove_token.size();i++){
				//Separates next into one,two,three grams
			    //then add
				one_term = remove_token.get(i);
				token_gram.word_builder(one_term);
				List<String> grams = null;
				for (int j = 1; j <= 3; j++) {
					grams = token_gram.k_gram_token(j);
					token_gram.addKey(grams, one_term);
				}
			}
			vocab_values = token_gram.getVocab();
			System.out.println("The keys are: "+vocab_values.keySet()+"\n");
			System.out.println("This is the size of the vocab hashset: "+vocab_values.size());
			remove_token.clear();
			
			System.out.println(pos_position.getTermCount());

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
	}

	private static void printIndex(String choice, PositionalPosting index, List<String> fileNames) {
		String[] dict = index.getDictionary();
		if ((index.getMap().containsKey(choice))) {
			System.out.print(choice + ": ");
			for (int t = 0; t < index.getPostings(choice).size(); t++) {
				System.out.print(fileNames.get(index.getPostings(choice).get(t).getDocID()) + " ");
			}
		}
		System.out.println();
	}

	public static String stringTokenizer(String token) {
		StringTokenizer next_token = new StringTokenizer(token);
		String words = "";
		if (next_token.hasMoreTokens()) {
			words = next_token.nextToken();
			words.toLowerCase();
		}
		return words;
	}

}