package com.example.ds_le.objects;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class EntriesHash {
    private static HashMap<String, Task> entries;
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

    public void addEntry(String key, Task entry){
        entries.put(key, entry);
        for (Map.Entry<String, Task> i : entries.entrySet()) {
            System.out.println("Key: " + i.getKey() + ", Value: " + i.getValue());
        }
    }

    public void updateEntry(String key, Task entry){
        entries.replace(key, entry);
        for (Map.Entry<String, Task> i : entries.entrySet()) {
            System.out.println("Key: " + i.getKey() + ", Value: " + i.getValue());
        }
    }

    public HashMap<String, Task> getDone(){
        HashMap<String, Task> done = new HashMap<>();
        for(Map.Entry<String, Task> entry: entries.entrySet()){
            String key = entry.getKey();
            Task task = entry.getValue();

            if(task.isDone()){
                done.put(key, task);
            }
        }
        return done;
    }
    public Task getEntry(String key){
        return entries.get(key);
    }

    public void deleteEntry(String key){
        entries.remove(key);
    }

    public static HashMap<String, Task> getEntries() {
        return entries;
    }
    public String getKeyByValue(Task task) {
        for (Map.Entry<String, Task> entry : entries.entrySet()) {
            if (task.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null; // Value not found in the map
    }
    public static void loadHashMap(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        // Retrieve the JSON string from SharedPreferences
        String jsonHashMap = sharedPreferences.getString(KEY_HASHMAP, null);

        if (jsonHashMap != null) {
            try {
                // Convert JSON string back to HashMap using Gson library
                Gson gson = new Gson();
                Type type = new com.google.gson.reflect.TypeToken<HashMap<String, Task>>() {}.getType();
                HashMap<String, Task> myHashMap = gson.fromJson(jsonHashMap, type);
                entries = myHashMap;

                Log.d("LoadHashMap", "Loaded JSON: " + jsonHashMap);
            } catch (JsonSyntaxException e) {
                // Handle JSON parsing error
                Log.e("LoadHashMap", "Error parsing JSON: " + e.getMessage());
            }
        } else {
            // Create a new HashMap and save it to SharedPreferences
            entries = new HashMap<>();
            saveHashMap(context, entries);

            Log.d("LoadHashMap", "Created a new HashMap");
        }
    }

    public static void saveHashMap(Context context, HashMap<String, Task> myHashMap) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        try {
            // Convert HashMap to JSON string using Gson library
            Gson gson = new Gson();
            String jsonHashMap = gson.toJson(myHashMap);

            // Save the JSON string in SharedPreferences
            editor.putString(KEY_HASHMAP, jsonHashMap);
            editor.apply();

            Log.d("SaveHashMap", "Saved JSON: " + jsonHashMap);
        } catch (JsonSyntaxException e) {
            // Handle JSON conversion error
            Log.e("SaveHashMap", "Error converting to JSON: " + e.getMessage());
        }

    }

    public static String generateHashKey(Task value) {
        // Example: Using hashCode as a key
        return String.valueOf(value.hashCode());
    }

    public static String generateUniqueUUID() {
        return UUID.randomUUID().toString() + System.currentTimeMillis();
    }


}
