package milestone1;

public class Postings {
	private Object docID;
	private Object position;
	
	public Postings(){
		docID = null;
		position = null;
	}
	public Postings(int doc_id, int pos){
		docID = doc_id;
		position= pos;
	}
	public Object getDocID() {
		return docID;
	}
	public void setDocID(Object docID) {
		this.docID = docID;
	}
	public Object getPosition() {
		return position;
	}
	public void setPosition(Object position) {
		this.position = position;
	}
	
}
