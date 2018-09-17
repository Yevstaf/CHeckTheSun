package com.example.yevstaf.checkthesun.http_services;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Vladyslav on 29.07.2018.
 */

public abstract class Item {
    JSONObject jsonObject;
        public Item(String object) {
            try {
                jsonObject = new JSONObject(object);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
           public String getString(String key) throws JSONException {
                String result = "no such parameter";
                    try {
                        result = jsonObject.getString(key);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                return result;
            }

            public JSONObject getJsonObject(){
            return jsonObject;
            }
}
