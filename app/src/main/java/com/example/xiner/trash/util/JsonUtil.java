package com.example.xiner.trash.util;

import android.util.Log;

import com.example.xiner.trash.model.Commodity;
import com.example.xiner.trash.model.Waste;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by peng on 15-3-12.
 */
public class JsonUtil {

    private static final String TAG = "JsonUtil" ;

    public List<?> stringFormJson(String jsonData) {
        Type listType = new TypeToken<List<?>>() {
        }.getType();
        Gson gson = new Gson();
        ArrayList<?> list = gson.fromJson(jsonData, listType);
        return list;
    }

    private class JsonIO {
        public String getJsonData(List<?> list) {
            Gson gson = new Gson();
            String jsonString = gson.toJson(list);
            return jsonString;
        }
    }

    public Commodity getCommodity(String key, String jsonString) {
        Commodity item = new Commodity();
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONObject commodity = new JSONObject(key);

            item.setId(commodity.getString(""));

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return item;
    }

    public ArrayList<Commodity> getCommodities(String json) {
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonArray array = null;
        Log.v(TAG,json+"jsonjson");
        if (!json.equals("No Thing")) {
            if (parser.parse(json).isJsonArray()) {
                array = parser.parse(json).getAsJsonArray();

                ArrayList<Commodity> list = new ArrayList<Commodity>();
                for (JsonElement e : array) {
                    Commodity item = gson.fromJson(e, Commodity.class);
                    list.add(item);
                }

                return list;
            } else {
                return new ArrayList<>();
            }
        }else{
            Log.v(TAG,"nothingnothing");

            return new ArrayList<>();

        }
    }
    public ArrayList<Waste> getWaste(String json) {
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonArray array = null;
        Log.v(TAG,json+"jsonjson");
        if (!json.equals("No Thing")) {
            if (parser.parse(json).isJsonArray()) {
                array = parser.parse(json).getAsJsonArray();

                ArrayList<Waste> list = new ArrayList<Waste>();
                for (JsonElement e : array) {
                    Waste item = gson.fromJson(e, Waste.class);
                    list.add(item);
                }

                return list;
            } else {
                return new ArrayList<>();
            }
        }else{
            Log.v(TAG,"nothingnothing");

            return new ArrayList<>();

        }
    }



}
