import java.util.ArrayList;
import java.util.Scanner;

public class QueryHandler {

	public ArrayList<String> toSubQueries(String query){
		ArrayList<String> subQueries = new ArrayList<String>();
		// step 1. separate the search query into subqueries with the "+" delimiter
		Scanner querySeparator = new Scanner(query).useDelimiter("\\s*\\+\\s*");
		while(querySeparator.hasNext()){
			String subquery = querySeparator.next();
			if (subquery.contains("")) {
				Scanner quotationSeparator = new Scanner(subquery).useDelimiter("\"");
				subQueries.add(quotationSeparator.next());
			}
			else{
				subQueries.add(subquery);
			}
		}

		//System.out.println(subQueries);
		return subQueries;
	}
}