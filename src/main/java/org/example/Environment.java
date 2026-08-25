package org.example;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class Environment {

    private static Environment instance = new Environment();
    private DocumentContext jsonContext;

    /**
     * Private constructor for the Environment singleton.
     *
     * <p>Initializes the singleton by loading and parsing a JSON configuration file named
     * "config.json" from the working directory. The parsed JSON is converted into a
     * JsonPath DocumentContext (jsonContext) which can be used to query configuration
     * values throughout the application.</p>
     *
     * <p>The constructor uses a try-with-resources FileInputStream to read the file and
     * org.json.simple.parser.JSONParser to parse it. If the file is not found, cannot be
     * read, or contains invalid JSON, the exception is caught and the stack trace is
     * printed; in those cases jsonContext will remain null.</p>
     *
     * <p>This constructor is private to enforce the singleton pattern. Obtain the shared
     * instance via Environment.getInstance().</p>
     */
    private Environment() {
        JSONParser parser = new JSONParser();
        // load file
        try (InputStream input = new FileInputStream("config.json")) {
            // read file
            Reader fileReader = new InputStreamReader(input);
            // parse the file in a JSON object
            JSONObject jsonObject = (JSONObject) parser.parse(fileReader);
            // parse the jsonObject and assign to jsonContext
            jsonContext = JsonPath.parse(jsonObject);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static Environment getInstance() { return instance; }

    public String getValue(final String keyJsonPath) { return jsonContext.read(keyJsonPath); }

    public static void main(String[] args) {
        System.out.println(Environment.getInstance().getValue("test"));
        System.out.println(Environment.getInstance().getValue("credentials.owner.key"));
    }
}
