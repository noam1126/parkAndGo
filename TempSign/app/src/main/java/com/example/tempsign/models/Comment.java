package com.example.myapplication.models;

public class Comment {
    public String commentText;
    public String date;
    public String time;
    public String userId;

    public Comment() {
    }

    public Comment(String commentText, String date,  String time, String userId) {
        this.commentText = commentText;
        this.date = date;
        this.time = time;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentText='" + commentText + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }

}



