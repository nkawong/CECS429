package milestone1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class NDM {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final PositionalPosting aPosting = new PositionalPosting();
		Scanner input = new Scanner(System.in);
		int aDocID = 0;
		int count = 0;
		String pwd1 = "/Users/n.wong/documents/workspace/cecs429/src/Homework2/NationalParkFiles";
		String choice;
		// String pwd2 = ""

		/*
		 * Ask the user to enter path of index Enter
		 * /Users/n.wong/documents/workspace/cecs429/
		 */
		System.out.println("Please press n for National Park Files");
		choice = input.nextLine();
		while(!choice.equals("n") || choice.equals("N")){
			System.out.println("Please press n for National Park Files");
			choice = input.nextLine();
		}
		while (choice.equals("n") || choice.equals("N")) {
			/* Opens folder index */
			File directory = new File(pwd1);
			File file;
			// File the_file = new File;
			for (final File file_entry:directory.listFiles()) {
				indexFile(file_entry,aPosting,aDocID,count);
			}
		}

	}

	private static void indexFile(File file, PositionalPosting pos_position, int doc_id, int position) {
		try {
			StreamToken token = new StreamToken(file);
			HashMap<String,ArrayList<Integer>> pos = null;
			List<String> remove_token = new ArrayList<String>();
			while(token.hasNextToken()){
				remove_token.add(token.nextToken());
			}
			pos = token.position_term(remove_token);
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
	}

	private static void printIndex() {

	}

}
