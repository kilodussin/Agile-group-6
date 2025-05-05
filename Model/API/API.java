package Model.API;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.simple.JSONObject;

public class API {

    public static String getFunFact(int number) {
        String fact = null;
        try {

            String urlString = "http://numbersapi.com/" + number + "?json";

            // Create URL object
            URL url = new URL(urlString);

            // Open a connection to the API
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Get the response from the API
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            int StartIndex = response.indexOf("{ \"text\": \"") + 11;
            int EndIndex = response.indexOf("\"", StartIndex);
            fact = response.substring(StartIndex, EndIndex);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return fact;
    }


}
