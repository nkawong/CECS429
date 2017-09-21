package milestone1;

import java.io.File;
import java.util.Scanner;

public class NDM {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final PositionalPosting aPosting = new PositionalPosting();
		Scanner input = new Scanner(System.in);
		int aDocID = 0;
		int count = 0;
		String pwd = "";
		
		/*
		   Ask the user to enter path of index 
		   Enter /Users/n.wong/documents/workspace/cecs429/src/milestone1/...
		*/		
		System.out.println("Enter the folder index: ");
		pwd = input.nextLine();
		
		/* Opens folder index */
		File directory = new File(pwd);
		
		
	}
	private static void indexFile(File file,PositionalPosting pos_position, int doc_id, int position){
		
	}
	private static void printIndex(){
		
	}

}
