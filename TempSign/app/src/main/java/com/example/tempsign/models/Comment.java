package com.example.tempsign.models;

import java.util.ArrayList;

public class Comment {
    public String text;
    public String timestamp;
    public int numLike;
    public String commentId;
    public String userId;
    public String userName;





    public String getCommentId() {
        return commentId;
    }


    public String getText() { return text; }

    public String getTimestamp() { return timestamp; }


    public int getLikeNum() { return numLike; }

    // Default constructor
    public Comment() {
        // Default constructor required for Firebase
    }

    public Comment(String commentId, String text, String timestamp, int numLike, String userId, String userName) {
        this.text = text;
        this.timestamp = timestamp;
        this.numLike = numLike;
        this.commentId = commentId;
        this.userId = userId;
        this.userName = userName;
    }
}



