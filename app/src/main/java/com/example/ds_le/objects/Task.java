package com.example.ds_le.objects;

public class Task {
    private String id;
    private String title;
    private String description;
    private String deadline;
    private String category;
    private boolean isPriority;
    private boolean isDone;

    Task(){
        id = "";
        title = "";
        description = "";
        deadline = "";
        isPriority = false;
        isDone = false;
    };

    public Task(String id, String title, String description, String deadline, String category, boolean isPriority, boolean isDone){
        this.id = id;
        this.title = title;
        this.description = description;
        this.deadline = description;
        this.deadline = deadline;
        this.category = category;
        this.isPriority = isPriority;
        this.isDone = isDone;
    };

    public String getId(){
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getDeadline() {
        return deadline;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public boolean isPriority() {
        return isPriority;
    }

    public void setPriority(boolean isPriority) {
        this.isPriority = isPriority;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
}
