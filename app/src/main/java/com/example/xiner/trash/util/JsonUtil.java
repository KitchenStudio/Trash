package com.example.xiner.trash.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by peng on 15-3-12.
 */
public class JsonUtil {

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
}
