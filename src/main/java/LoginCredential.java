import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class LoginCredential {
    public static void main(String[] args) throws IOException, ParseException {
        //create login credential for user and admin
        String filePath = "./src/main/resources/user.json";
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader(filePath));

        JSONObject userObj = new JSONObject();
        userObj.put("username", "rifa");
        userObj.put("password", "1234");
        userObj.put("role", "student");

        jsonArray.add(userObj);
        System.out.println(userObj);

        FileWriter fw = new FileWriter(filePath);
        fw.write(String.valueOf(jsonArray));
        fw.flush();
        fw.close();
    }
}
