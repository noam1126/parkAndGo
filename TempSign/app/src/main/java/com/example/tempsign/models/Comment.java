package com.example.tempsign.models;

import java.util.ArrayList;

public class Comment {
    public String text;
    public String timestamp;
    public int likeNum;
    public String commentId;
    public String userId;
    public String userName;





    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public String getText() { return text; }

    public String getTimestamp() { return timestamp; }


    public int getLikeNum() { return likeNum; }

    // Default constructor
    public Comment() {
        // Default constructor required for Firebase
    }

    public Comment(String commentId, String text, String timestamp, int likeNum, String userId, String userName) {
        this.text = text;
        this.timestamp = timestamp;
        this.likeNum = likeNum;
        this.commentId = commentId;
        this.userId = userId;
        this.userName = userName;
    }
}



