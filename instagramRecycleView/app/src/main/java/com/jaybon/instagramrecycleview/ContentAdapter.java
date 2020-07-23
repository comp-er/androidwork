package com.jaybon.instagramrecycleview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.MyViewHolder> {

    private static final String TAG = "ContentAdapter";

    private List<Content> contents = new ArrayList<>();

    public void addContent(Content content) {
        contents.add(content);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_content, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Content content = contents.get(position);
        holder.setContent(content);

    }

    @Override
    public int getItemCount() {
        return contents.size();
    }

    // ViewHolder (뷰들의 책꽂이)
    public static class MyViewHolder extends RecyclerView.ViewHolder { // 뷰홀더

        // 규칙1 (xml이 들고있는 뷰)
        private ImageView imageView3;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView3 = itemView.findViewById(R.id.imageView3);
        }

        // 규칙3
        public void setContent(Content content) {
            imageView3.setImageResource(content.getImgResource());
        }

    }
}
