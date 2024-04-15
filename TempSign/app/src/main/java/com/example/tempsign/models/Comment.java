package com.example.tempsign.models;

public class Comment {
    public String text;
    public String timestamp;
    public int likeNum;

    public String getText() { return text; }

    public String getTimestamp() { return timestamp; }


    public int getLikeNum() { return likeNum; }

    // Default constructor
    public Comment() {
        // Default constructor required for Firebase
    }

    public Comment(String text, String timestamp, int likeNum) {
        this.text = text;
        this.timestamp = timestamp;
        this.likeNum = likeNum;
    }
}



