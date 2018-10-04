package com.example.yevstaf.checkthesun.SunriseSunsetServices;

import com.example.yevstaf.checkthesun.http_services.HTTPGetRequest;
import com.example.yevstaf.checkthesun.http_services.Item;

import java.util.concurrent.ExecutionException;

/**
 * Created by Vladyslav on 29.07.2018.
 */

public class SunriseSunsetGETRequest {

    public String executeGetReauest(String url) {
        HTTPGetRequest request = new HTTPGetRequest();
        String response = "No response";
        try {
            response = request.execute(url).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return response;
    }

}
