package com.example;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class jsonBase {
    protected final String TAG = "DCSS-" + this.getClass().getSimpleName();

    protected void parseJson(HashMap<String, String> store,
            JSONObject jsonObject, String[] strings) throws JSONException {
        String result;
        for (String str : strings) {
            result = (String)jsonObject.get(str);
            store.put(str, result);
        }
    }

    protected void parseJson(ArrayList<String> store, JSONObject jsonObject,
                                          String target) throws JSONException {
        JSONArray jsonArray = (JSONArray) jsonObject.get(target);
        for (int i = 0; i < jsonArray.length(); i++) {
            store.add((String)jsonArray.get(i));
        }
    }

    protected String parseJson(JSONObject jsonObject,
                               String target) {
         return  jsonObject.getString(target);
    }


    protected void printHashMap(HashMap<String, String> map) {
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry pair = (Map.Entry)it.next();
            mLog.i(TAG,pair.getKey() + " : " + pair.getValue());
        }
    }

    protected void printArrayList(ArrayList<String> list) {
        for (String aEntry : list) {
            mLog.i(TAG, aEntry);
        }
    }
}
