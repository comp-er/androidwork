package com.jaybon.movieapp;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class YtsAdapter extends RecyclerView.Adapter<YtsAdapter.MyViewHolder>{

    private static final String TAG = "YtsAdapter";

    // 어댑터는 항상 데이터(컬렉션)을 들고 있어야한다.
    private List<YtsData.MyData.Movie> movies = new ArrayList<>();

    // 아이템을 개별로 넣기
    public void addItem(YtsData.MyData.Movie movie){
        movies.add(movie);
    }

    // 리스트를 바로 넣기
    public void addItems(List<YtsData.MyData.Movie> movies){
        this.movies = movies;
    }

    @NonNull
    @Override // 껍데기 생성
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: ");
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View cardItemView = inflater.inflate(R.layout.card_item, parent, false); // false : 동적
        return new MyViewHolder(cardItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.setItem(movies.get(position));

        Log.d(TAG, "onBindViewHolder: "+position);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    // 책꽂이 (View들을 채워 두면 됨)
    // 뷰가 만들어지려면 card_item이 들고있는 모든 뷰들을 전역변수로 설정해야함
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivPoster;
        private TextView tvTitle;
        private TextView tvRating;
        private RatingBar ratingBar;

        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            ivPoster = itemView.findViewById(R.id.iv_poster);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvRating = itemView.findViewById(R.id.tv_rating);
            ratingBar = itemView.findViewById(R.id.rating_bar);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(v.getContext(), "클릭됨"+getAdapterPosition(), Toast.LENGTH_SHORT).show();
//                }
//            });

        }

        public void setItem(YtsData.MyData.Movie movie){
            tvTitle.setText(movie.getTitle());
            tvRating.setText(movie.getRating()+"");
            Picasso.get().load(movie.getMedium_cover_image()).into(ivPoster);
            ratingBar.setRating(movie.getRating()/2);
        }

    }

}
