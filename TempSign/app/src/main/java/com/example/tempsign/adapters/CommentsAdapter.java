package com.example.tempsign.adapters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tempsign.R;
import com.example.tempsign.models.Comment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.MyViewHolder> {

    static ArrayList<Comment> comments;
    private DatabaseReference commentsRef;
    private ValueEventListener commentsListener;

    public CommentsAdapter(ArrayList<Comment> comments, DatabaseReference commentsRef) {
        this.comments = comments;
        this.commentsRef = commentsRef;

        // Start listening for changes in Firebase
        listenForComments();
    }

    // Method to listen for comments in Firebase
    private void listenForComments() {
        commentsListener = commentsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Clear existing dataset
                comments.clear();
                // Iterate through the dataSnapshot to fetch comments
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Comment comment = snapshot.getValue(Comment.class);
                    comments.add(comment);
                }
                // Notify adapter of data changes
                notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle errors
            }
        });
    }

    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        // Remove the ValueEventListener when the adapter is detached from the RecyclerView
        if (commentsRef != null && commentsListener != null) {
            commentsRef.removeEventListener(commentsListener);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Assuming parent is a ViewGroup or a View object
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_comments, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if (comments.isEmpty()) {
            // Display placeholder or message when dataset is empty
            holder.textContent.setText("No comments available");
            holder.textDate.setText("");
            holder.textNumLike.setText("");
        } else {
            Comment comment = comments.get(position);
            holder.textContent.setText(comment.getText());
            holder.textDate.setText(comment.getTimestamp());
            holder.textNumLike.setText(String.valueOf(comment.getLikeNum()));
        }
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textContent;
        TextView textDate;
        TextView textNumLike;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textContent = itemView.findViewById(R.id.commentContent);
            textDate = itemView.findViewById(R.id.commentDate);
            textNumLike = itemView.findViewById(R.id.commentNumLikes);
        }
    }
}