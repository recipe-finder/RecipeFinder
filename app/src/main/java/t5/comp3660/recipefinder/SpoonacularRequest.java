package t5.comp3660.recipefinder;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SpoonacularRequest extends AsyncTask<String, Void, String> {
    public static final String REQUEST_METHOD = "GET";
    public static final int READ_TIMEOUT = 15000;
    public static final int CONNECTION_TIMEOUT = 15000;
    private static final String MASHAPE_KEY = "aWQT9fBrEYmshAiaDubbuFA7wvjtp1mipnXjsngv8PzPjbi9q1";
    private OnTaskComplete listener;

    public SpoonacularRequest(OnTaskComplete listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(String... params){
        String baseUrl = "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com";
        String path = params[0];
        String reqParams = params[1];
        String result;
        String inputLine;
        try {
            //Create a URL object holding our url
            URL myUrl = new URL(baseUrl + path + reqParams);
            //Create a connection
            HttpURLConnection connection =(HttpURLConnection)
                    myUrl.openConnection();
            //Set methods and timeouts
            connection.setRequestMethod(REQUEST_METHOD);
            connection.setReadTimeout(READ_TIMEOUT);
            connection.setConnectTimeout(CONNECTION_TIMEOUT);
            connection.setRequestProperty("X-Mashape-Key", MASHAPE_KEY);
            connection.setRequestProperty("Accept", "application/json");

            //Connect to our url
            connection.connect();
            //Create a new InputStreamReader
            InputStreamReader streamReader = new
                    InputStreamReader(connection.getInputStream());
            //Create a new buffered reader and String Builder
            BufferedReader reader = new BufferedReader(streamReader);
            StringBuilder stringBuilder = new StringBuilder();
            //Check if the line we are reading is not null
            while((inputLine = reader.readLine()) != null){
                stringBuilder.append(inputLine);
            }
            //Close our InputStream and Buffered reader
            reader.close();
            streamReader.close();
            //Set our result equal to our stringBuilder
            result = stringBuilder.toString();
        }
        catch(IOException e){
            e.printStackTrace();
            result = null;
        }
        return result;
    }
    protected void onPostExecute(String result){
        listener.onTaskCompleteRequest(result);
    }
}
