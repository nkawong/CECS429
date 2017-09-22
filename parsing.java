package Homework2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import javax.json.Json;
import javax.json.JsonWriter;
import javax.json.stream.JsonParser;

public class parsing {
	public static void main(String[] args) {

		String file_name = "/Users/n.wong/Desktop/fall 2017/CECS429/all-nps-sites.json";
		String root = "/Users/n.wong/documents/workspace/cecs429/src/homework2/National Park Json files/";
		int i = 0;
		boolean flag = false;
		String folder_name = "National Park Json Files";
		File new_file = null;
		PrintWriter out = null;

		try {
			InputStream file = new FileInputStream(file_name);
			JsonParser parse = Json.createParser(file);
			String my_file_name = "JSON" + i + ".json";
			new_file = new File(root + my_file_name);
			out = new PrintWriter(new FileOutputStream(new_file));

			while (parse.hasNext()) {
				switch (parse.next()) {
				case KEY_NAME:
					String key = parse.getString();
					parse.next();
					if (new_file.exists()) {
						out.write(key + ": ");
						switch (key) {
						case "title":
							out.write(parse.getString());
							out.close();
							i++;
							my_file_name = "JSON" + i + ".json";
							new_file = new File(root + my_file_name);
							out = new PrintWriter(new FileOutputStream(new_file));
							// flag = true;
							break;
						case "body":
							out.write(parse.getString());
							break;
						case "url":
							out.write(parse.getString());
							break;
						default:
							break;
						}
					}
					break;
				default:
					break;

				}

			}

		} catch (Exception e) {
			System.out.println("it cums here");
			e.printStackTrace();
		}

	}

}
