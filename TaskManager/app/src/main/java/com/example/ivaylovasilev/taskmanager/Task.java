package com.example.ivaylovasilev.taskmanager;

/**
 * Created by Ivaylo Vasilev on 25/5/2018.
 */

public class Task {
    private int id;
    private String text;

    public Task(int id, String text) {
        this.id = id;
        this.text = text;

    }


    public Task(String text) {

        this.text = text;
    }

    public Task() {
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getText() {

        return text;
    }

    public void setText(String text) {

        this.text = text;
    }

}
