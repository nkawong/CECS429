package milestone1;

public class Postings {
	private int docID;
	private int position;
	
	public Postings(){
		docID = 0;
		position = 0;
	}
	public Postings(int doc_id, int pos){
		docID = doc_id;
		position= pos;
	}
	public Postings(int pos){
		position = pos;
	}
	public int getDocID() {
		return docID;
	}
	public int getPosition() {
		return position;
	}
	
}
