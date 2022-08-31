package utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class JSONFileParser {
    public static final  String PROPERTY_FILE_PATH = "src/test/resources/properties.json";

    public static JSONObject getJsonObjectFromFile () {
        Object object = null;
        try {
            object = new JSONParser().parse(new FileReader(PROPERTY_FILE_PATH));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return (JSONObject) object;
    }
}
