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
import java.util.List;
import java.util.Scanner;

import org.tartarus.snowball.ext.englishStemmer;

public class NDM {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final PositionalPosting aPosting = new PositionalPosting();
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
			/* Opens folder index */
			/*
			 * File directory = new File(pwd1); File file; // File the_file =
			 * new File; System.out.println(directory.listFiles()); for (final
			 * File file_entry:directory.listFiles()) {
			 * indexFile(file_entry,aPosting,aDocID,count); }
			 */

		}
		System.out.println("Enter a word you would like to search for");
		stemmer.setCurrent(input.nextLine());
		//choice = stemmer.getCurrent();
		//stemmer.setCurrent(choice);
		 if(stemmer.stem()){
	    	  choice = stemmer.getCurrent();
	      }
		//choice = stemmer.getCurrent();
		System.out.println(choice);
		while (!choice.equals(":q")) {
			printIndex(choice, aPosting, fileNames);
			System.out.println("Enter a word you would like to search for");
			stemmer.setCurrent(input.nextLine());
			if(stemmer.stem()){
		    	  choice = stemmer.getCurrent();
		      }
		}
		//System.out.println("This is the docID: " + Nearing(aPosting,"puzzled","long",6));
		//wildCardSplit splitter = new wildCardSplit("colo*r");
		//splitter.word_split();

		/*
		 * System.out.println("Enter a word you would like to search for");
		 * choice = input.nextLine(); while(!choice.equals(":q")){
		 * 
		 * }
		 */

	}

	public static List<Integer> Nearing(PositionalPosting pos_position, String first, String last, int k) {
		List<Integer> near = new ArrayList<Integer>();
		// check docid to see if they are the same
		for (Postings first_term : pos_position.getPostings(first)) {
			for (Postings second_term : pos_position.getPostings(last)) {
				if (first_term.getDocID() == second_term.getDocID()) {
					for (int pos_first : first_term.getPosition()) {
						for (int pos_second : second_term.getPosition()) {
							if(pos_second-pos_first <= k){
								near.add(first_term.getDocID());
							}
						}
					}
				}
			}
		}
		// while(pos_position.getPostings(first).get(i).getDocID() ==
		// pos_position.getPostings(last).get(i).getDocID()){
		// if(pos_position.getPostings(last).get(i).getPosition().get(i) -
		// pos_position.getPostings(first).get(i).getDocID() <= k){
		// near.add(pos_position.getPostings(first).get(i).getDocID());
		// i++;
		// }
		// else{
		// i++;
		// }
		// }
		// get the positions of first
		// get the positions of last
		// check if the position is within k position of each other
		// if they are add the docid to the list

		return near;
	}

	private static void indexFile(File file, PositionalPosting pos_position, int id) {
		// String term = " ";
		try {
			StreamToken token = new StreamToken(file);
			HashMap<String, ArrayList<Integer>> pos = null;
			List<String> remove_token = new ArrayList<String>();
			while (token.hasNextToken()) {
				remove_token.add(token.nextToken());
			}
			// pos = token.position_term(remove_token);
			for (int i = 0; i < remove_token.size(); i++) {

				pos_position.addTerm(remove_token.get(i), id, i);
			}
			remove_token.clear();
			/*
			 * for(String key:pos.keySet()){ term = key;
			 * //System.out.println(term + " " + pos.get(term));
			 * 
			 * }
			 */
			// System.out.println(pos.size());
			/*
			 * for(int i = 0; i < pos.size(); i++){ for(String
			 * key:pos.keySet()){ pos_position.addTerm(key, id, pos.get(key));
			 * //System.out.println(key + " " + pos.get(key));
			 * 
			 * } }
			 */
			System.out.println(pos_position.getTermCount());

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
	}

	private static void printIndex(String choice, PositionalPosting index, List<String> fileNames) {
		// System.out.println(index.getPostings(choice).size());
		String[] dict = index.getDictionary();
		if ((index.getMap().containsKey(choice))) {
			System.out.print(choice + ": ");
			for (int t = 0; t < index.getPostings(choice).size(); t++) {
				System.out.print(fileNames.get(index.getPostings(choice).get(t).getDocID()) + " ");
			}
			// nSystem.out.print(fileNames.get(index.getMap().get(choice)) + "
			// ");
			// System.out.println(index.getMap().get(choice).toString());
		}
		System.out.println();
		/*
		 * for(int i = 0; i < dict.length; i++){
		 * if(dict[i].equalsIgnoreCase(choice)){ System.out.print(dict[i] +
		 * ": "); for(int t = 0; t < index.getPostings(dict[i]).size(); t++){
		 * System.out.println(index.getPostings(dict[i]).get(t).getPosition());
		 * System.out.print(fileNames.get(t) + " ");
		 * 
		 */
		// System.out.println(index.getPostings(choice).get(i));

		/*
		 * for(int t = 0; t < index.getPostings(dict[i]).size(); t++){
		 * System.out.print(index.getPostings(choice)); }
		 */
		// System.out.println();

	}

}