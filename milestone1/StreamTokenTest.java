package milestone1;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

public class StreamTokenTest {

	@Test
	public void test() {
		//fail("Not yet implemented");
		String pwd = "/Users/n.wong/documents/workspace/CECS429/src/homework/chapter1.txt";
		File file = new File(pwd);
		
		try {
			StreamToken test = new StreamToken(file);
			HashMap<String,ArrayList<Integer>> pos = null;
			List<String> token = new ArrayList<String>();
			while(test.hasNextToken()){
				token.add(test.nextToken());
			}
			pos = test.position_term(token);
			for(String key : pos.keySet()){
				System.out.println(key + " " + pos.get(key));
			}
			
//			while(test.hasNextToken()){
//				term_word = test.list_word();
//				pos = test.position_term(term_word);
//			}
			
			
			
//			for(String key:test.position_term().keySet()){
//				System.out.println(key + " " + test.position_term().get(key));
//			}
//			for(int i = 0; i<pos.get(i).size()-1;i++){
//				List<String> key = (List<String>) pos.keySet();
//				List<Integer> positions = pos.get(key.get(i));
//				
//				System.out.println(key.get(i) + " " + positions.get(i));
//			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
