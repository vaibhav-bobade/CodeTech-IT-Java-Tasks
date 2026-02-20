package task2_api_client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherClient {
    private static final String BASE_URL = "https://api.coindesk.com/v1/bpi/currentprice.json";

    public static void main(String[] args) {
        String city = "Pune"; // You can use Scanner to get this from the user
        fetchWeather(city);
    }

    public static void fetchWeather(String city) {
        try {
            // 1. Construct the URL (No key or city needed for this API)
            URL url = new URL(BASE_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // 2. Check Connection Status
            int status = conn.getResponseCode();
            if (status == HttpURLConnection.HTTP_OK) { // Status 200
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();

                // 3. Display Data
                System.out.println("--- Current Bitcoin Price Data ---");
                System.out.println(content); // Displays raw JSON
            } else {
                System.out.println("❌ Error: API request failed with status " + status);
            }
            conn.disconnect();
        } catch (Exception e) {
            System.out.println("❌ Network Error: " + e.getMessage());
        }
    }
}