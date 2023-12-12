package com.example.ds_le.objects;

public class Note {
    private String id;
    private String title;
    private String description;

    Note(){
        id = "";
        title = "";
        description = "";
    }

    Note(String id, String title, String description){
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
