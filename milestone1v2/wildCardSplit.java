package milestone1v2;

public class wildCardSplit {
	private String word;
	private String[] substring; 
	
	public wildCardSplit(){
		word="";
		substring = new String[word.length()]; 
	}
	public wildCardSplit(String wild_card){
		word = "$" + wild_card + "$";
		substring = new String[word.length()];
	}
	public String[] word_split(){
		substring = word.split("\\*");
		System.out.println(substring[0] + " " +substring[1]);
		return substring;
	}
	
	
}
