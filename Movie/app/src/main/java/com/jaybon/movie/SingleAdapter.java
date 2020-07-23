package com.jaybon.movie;

import android.media.Image;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class SingleAdapter extends BaseAdapter {

    private static final String TAG = "SingleAdapter";

    // 데이터
    private List<Movie> items = new ArrayList<>();

    public SingleAdapter() {
        items.add(new Movie("써니",R.drawable.mov01));
        items.add(new Movie("완득이",R.drawable.mov02));
        items.add(new Movie("괴물",R.drawable.mov03));
        items.add(new Movie("라디오스타",R.drawable.mov04));
        items.add(new Movie("비열한거리",R.drawable.mov05));
        items.add(new Movie("왕의남자",R.drawable.mov06));
        items.add(new Movie("아일랜드",R.drawable.mov07));
        items.add(new Movie("웰컴투동막골",R.drawable.mov08));
        items.add(new Movie("헬보이",R.drawable.mov09));
        items.add(new Movie("백투더퓨처",R.drawable.mov10));
        items.add(new Movie("여인의향기",R.drawable.mov11));
        items.add(new Movie("쥬라기공원",R.drawable.mov12));
    }

    // 모든건수 받기
    public void addItems(List<Movie> items){
        this.items = items;
    }

    // 한건씩 받기 생략
    
    // getCount과 getItem는 필수!

    @Override
    public int getCount() { // 데이터 사이즈, 최초에 화면을 몇건 만들 것인지
        Log.d(TAG, "getCount: ");
        return items.size();
    }

    @Override
    public Object getItem(int position) { // 아이템 가져오기
        Log.d(TAG, "getItem: ");
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        Log.d(TAG, "getItemId: ");
        return 0;
    }


    // 집중!
    // 메모리에 인플레이트해서 화면에 뿌려주는 것
    @Override
    public View getView(int position, View convertView, ViewGroup parent) { // 포지션값은 제일 밑에 값
        Log.d(TAG, "getView: " +position);

        // 레이아웃 인플레이터로 인플레이터 객체 접근하기
        LayoutInflater inflater = LayoutInflater.from(parent.getContext()); // 인플레이터

        // 메모리에 아이템 하나를 인플레이터하기
        View itemView = inflater.inflate(R.layout.item, parent, false); //아이템뷰

        // View 찾기
        TextView tv = itemView.findViewById(R.id.tv_title); //뷰에서 텍스트뷰 찾기
        ImageView iv = itemView.findViewById(R.id.iv_img_resource); //뷰에서 이미지뷰 찾기

        String title = ((Movie) getItem(position)).getTitle();
        int imageResource = ((Movie) getItem(position)).getImgResource();

        tv.setText(title);
        iv.setImageResource(imageResource);

        return itemView;
    }
}
