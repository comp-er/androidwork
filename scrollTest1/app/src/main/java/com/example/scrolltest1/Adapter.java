package com.example.scrolltest1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "ContentAdapter";

    private List<Data> contents = new ArrayList<>();

    public void addContent(Data content) {
        contents.add(content);
    }

    @Override
    public int getItemViewType(int position) {
        return contents.get(position).getType();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if(viewType == 1)
        {
            View view = inflater.inflate(R.layout.item, parent, false);
            return new MyViewHolder(view);
        }
        else
        {
            View view = inflater.inflate(R.layout.header_item, parent, false);
            return new HeaderViewHoleder(view);
        }


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Data content = contents.get(position);
        if(holder instanceof MyViewHolder)
        {
            ((MyViewHolder) holder).setContent(content);
        }
        else
        {
            ((HeaderViewHoleder) holder).setContent(content);
        }
    }

    @Override
    public int getItemCount() {
        return contents.size();
    }

    // ViewHolder (뷰들의 책꽂이)
    public static class HeaderViewHoleder extends RecyclerView.ViewHolder { // 뷰홀더

        // 규칙1 (xml이 들고있는 뷰)
        private Button btn;

        public HeaderViewHoleder(@NonNull View itemView) {
            super(itemView);
            btn = itemView.findViewById(R.id.btn_header);
        }

        // 규칙3
        public void setContent(Data content) {
            btn.setText(content.getContent());
        }

    }

    // ViewHolder (뷰들의 책꽂이)
    public static class MyViewHolder extends RecyclerView.ViewHolder { // 뷰홀더

        // 규칙1 (xml이 들고있는 뷰)
        private Button btn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            btn = itemView.findViewById(R.id.btn_item);
        }

        // 규칙3
        public void setContent(Data content) {
            btn.setText(content.getContent());
        }

    }
}