package com.example.ds_le.objects;

import java.lang.reflect.Type;
import java.util.HashMap;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.UUID;

public class EntriesHash {
    private static HashMap<String, Object> entries;
    private final Context context;

    private static  EntriesHash instance;

    private static final String PREF_NAME = "MyAppPreferences";
    private static final String KEY_HASHMAP = "myHashMap";
    public EntriesHash(Context context){
        this.context = context;
        this.entries = new HashMap<>();
        loadHashMap(context);
    }

    public static synchronized EntriesHash getInstance(Context context) {
        if (instance == null) {
            instance = new EntriesHash(context);
        }
        return instance;
    }

    public void addEntry(String key, Object entry){
        entries.put(key, entry);
        for (Map.Entry<String, Object> i : entries.entrySet()) {
            System.out.println("Key: " + i.getKey() + ", Value: " + i.getValue());
        }
    }

    public void updateEntry(String key, Object entry){
        entries.replace(key, entry);
    }

    public void deleteEntry(String key){
        entries.remove(key);
    }


    public static HashMap<String, Task> loadHashMap(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        // Retrieve the JSON string from SharedPreferences
        String jsonHashMap = sharedPreferences.getString(KEY_HASHMAP, null);

        // Convert JSON string back to HashMap using Gson library
        Gson gson = new Gson();
        Type type = new com.google.gson.reflect.TypeToken<HashMap<String,Task>>() {}.getType();
        HashMap<String, Task> myHashMap = gson.fromJson(jsonHashMap, type);

        // Return the loaded HashMap
        return myHashMap;
    }

    public static void saveHashMap(Context context, HashMap<String, Task> myHashMap) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Convert HashMap to JSON string using Gson library
        Gson gson = new Gson();
        String jsonHashMap = gson.toJson(myHashMap);

        // Save the JSON string in SharedPreferences
        editor.putString(KEY_HASHMAP, jsonHashMap);
        editor.apply();
    }

    public static String generateHashKey(Task value) {
        // Example: Using hashCode as a key
        return String.valueOf(value.hashCode());
    }

    public static String generateUniqueUUID() {
        return UUID.randomUUID().toString() + System.currentTimeMillis();
    }


}
