package com.jaybon.recyclerviewmovieex01;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {

    private static final String TAG = "MovieAdapter";
    
    // 어댑터는 컬렉션을 들고 있어야한다
    private List<Movie> items = new ArrayList<>();

    public void addItem(Movie movie){
        items.add(movie);
    }


    // 리스트뷰의 getView와 비슷하다
    // 껍데기만! 만들어줌! 1번으로 실행됨
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item,parent,false);

        // n개의 뷰를 리턴해준다
        return new MyViewHolder(view);
    }

    // 껍데기에 데이터 바인딩 해줌. 2번으로 실행됨
    // holder는 뷰홀더의 주소를 가지고 있다
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // 아이템의 포지션값을 이용해서 데이터를 가져옴
        Movie movie = items.get(position);
        // 홀더에 아이템넣기
        holder.setItem(movie);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    // ViewHolder (뷰들의 책꽂이)
    public static class MyViewHolder extends RecyclerView.ViewHolder { // 뷰홀더

        // 규칙1 (xml이 들고있는 뷰)
        private TextView tvTitle;
        private ImageView ivImgResource;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            ivImgResource = itemView.findViewById(R.id.iv_img_resource);
        }

        // 규칙3
        public void setItem(Movie movie){
            tvTitle.setText(movie.getTitle());
            ivImgResource.setImageResource(movie.getImgResource());
        }

    }

}
