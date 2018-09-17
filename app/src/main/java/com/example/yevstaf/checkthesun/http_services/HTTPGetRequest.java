package com.example.yevstaf.checkthesun.http_services;

import android.os.AsyncTask;

import com.example.yevstaf.checkthesun.SunriseSunsetServices.SunriseSunsetItem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;



public class HTTPGetRequest extends AsyncTask<String, Void, String> {



    public static final String REQUEST_METHOD = "GET";
    public static final int READ_TIMEOUT = 15000;
    public static final int CONNECTION_TIMEOUT = 15000;
        @Override
        public String doInBackground(String... params){
            String stringUrl = params[0];
            String result;
            String inputLine;
            try {
                //Create a URL object holding our url
                URL myUrl = new URL(stringUrl);
                HttpURLConnection connection = createConnection(myUrl);

                    setMethods(connection);
                    setTimeouts(connection);
               //Connect to our url
                connection.connect();

                InputStreamReader streamReader = createInputStreamReader(connection);

                //Create a new buffered reader and String Builder
                BufferedReader reader = new BufferedReader(streamReader);
                StringBuilder stringBuilder = new StringBuilder();

                //Check if the line we are reading is not null
                while((inputLine = reader.readLine()) != null){
                    stringBuilder.append(inputLine);
                }
                //Close InputStream and Buffered reader
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
            super.onPostExecute(result);
        }

        public HttpURLConnection createConnection(URL url) throws IOException {
            HttpURLConnection  connection =(HttpURLConnection)
                    url.openConnection();
            return  connection;
        }

        public void setMethods(HttpURLConnection connection) throws ProtocolException {
            connection.setRequestMethod(REQUEST_METHOD);
        }
        public void setTimeouts(HttpURLConnection connection){
            connection.setReadTimeout(READ_TIMEOUT);
            connection.setConnectTimeout(CONNECTION_TIMEOUT);
        }

        public InputStreamReader createInputStreamReader(HttpURLConnection connection) throws IOException {
            InputStreamReader streamReader = new
                    InputStreamReader(connection.getInputStream());
            return streamReader;
        }


}