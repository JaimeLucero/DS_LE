package com.example.ds_le.objects;

import java.util.HashMap;
import android.content.Context;
import android.content.res.AssetManager;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class EntriesHash {
    HashMap<String, Object> entries;
    String file;
    String list;
    private final Context context;
    EntriesHash hash;
    EntriesHash(Context context){
        this.context = context;
        file = "";
        entries = null;
    }

    public void setFile(String file, String list) {
        this.file = file;
        this.list = list;
    }

    public void addEntry(String key, Object entry){
        entries.put(key, entry);
    }

    public void updateEntry(String key, Object entry){
        entries.replace(key, entry);
    }

    public void deleteEntry(String key){
        entries.remove(key);
    }

    private void readFile(){
        try {
            // Read JSON data from the assets folder
            String json = loadJSONFromAsset(file);

            // Parse the JSON data
            JSONObject jsonObject = new JSONObject(json);
            JSONArray entries = jsonObject.getJSONArray(list);

            if(list == "Notes"){
                for (int i = 0; i < entries.length(); i++) {
                    JSONObject note = entries.getJSONObject(i);
                    String id = note.getString("id");
                    String title = note.getString("title");
                    String description = note.getString("description");

                    Note noteItem = new Note(id, title, description);
                    addEntry(id, noteItem);
                }
            } else {
                for (int i = 0; i < entries.length(); i++) {
                    JSONObject task = entries.getJSONObject(i);
                    String id = task.getString("id");
                    String title = task.getString("title");
                    String description = task.getString("description");
                    String deadline = task.getString("deadline");
                    String category = task.getString("category");
                    boolean isPriority = task.getBoolean("isPriority");
                    boolean isDone = task.getBoolean("isDone");

                    Task taskItem = new Task(id, title, description, deadline, category, isPriority, isDone);
                    addEntry(id, taskItem);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String loadJSONFromAsset(String filename) {
        String json;
        try {
            AssetManager manager = context.getAssets();
            InputStream is = manager.open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
