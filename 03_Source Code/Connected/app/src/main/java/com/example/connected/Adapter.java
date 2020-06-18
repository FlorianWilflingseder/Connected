package com.example.connected;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.connected.Feed.FeedMessage;

import java.util.ArrayList;


public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private ArrayList<FeedMessage> mfeedMessages;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView author, title, description, publishedAt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            author = itemView.findViewById(R.id.author);
            title = itemView.findViewById(R.id.showTitle);
            description = itemView.findViewById(R.id.desc);
            publishedAt = itemView.findViewById(R.id.publishedAt);
        }
    }

    public Adapter(ArrayList<FeedMessage> feedMessages){
        mfeedMessages = feedMessages;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        MyViewHolder mvh = new MyViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        FeedMessage currentItem = mfeedMessages.get(position);

        holder.author.setText(currentItem.getAuthor());
        holder.title.setText(currentItem.getTitle());
        holder.description.setText(currentItem.getDescription());
        holder.publishedAt.setText(currentItem.getDate());
    }

    @Override
    public int getItemCount() {
        return mfeedMessages.size();
    }
}
