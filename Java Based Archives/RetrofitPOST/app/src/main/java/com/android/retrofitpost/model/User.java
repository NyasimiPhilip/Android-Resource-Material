package com.android.retrofitpost.model;

public class User {
    private long id;
    private String title;
    private String body;
    private long userId;

    public long getId() { return id; }
    public void setId(long value) { this.id = value; }

    public String getTitle() { return title; }
    public void setTitle(String value) { this.title = value; }

    public String getBody() { return body; }
    public void setBody(String value) { this.body = value; }

    public long getUserId() { return userId; }
    public void setUserId(long value) { this.userId = value; }
}
