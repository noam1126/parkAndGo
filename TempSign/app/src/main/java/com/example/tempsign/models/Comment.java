package com.example.tempsign.models;

public class Comment {
    public String commentText;
    public String date;
    public String userName;
    public int likeNum;

    public String getCommentText() { return commentText; }

    public String getDate() { return date; }

    public String getUserName() { return userName; }

    public int getLikeNum() { return likeNum; }


    public Comment(String commentText, String date, String userName, int likeNum) {
        this.commentText = commentText;
        this.date = date;
        this.userName = userName;
        this.likeNum = likeNum;
    }
}



