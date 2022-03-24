package jsonproject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class WriteJson {
	public static void main(String[] args) {
		JSONObject obj = new JSONObject();

//		for (int i = 0; i < 5; i++) {
//			obj.put("Value" + i, i);
//
//		}

		JSONObject emps = new JSONObject();
		emps.put("firstname", "Jason");
		emps.put("lastname", "Array");
//
		JSONObject empObj = new JSONObject();
		empObj.put("street", "102");
		empObj.put("state", "UP");

		JSONArray emplist = new JSONArray();
		emplist.add(empObj);
//
		emps.put("array", emplist);

		try (FileWriter file = new FileWriter("Writek.json")) {
			file.write(emps.toJSONString());
//			file.write(emplist.toJSONString());
			file.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
