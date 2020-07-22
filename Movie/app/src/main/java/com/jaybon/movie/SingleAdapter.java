package com.jaybon.movie;

import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class SingleAdapter extends BaseAdapter {

    private static final String TAG = "SingleAdapter";
    
    // 데이터
    private List<String> items = new ArrayList<>();

    // 모든건수 받기
    public void addItems(List<String> items){
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
    @Override
    public View getView(int position, View convertView, ViewGroup parent) { // 포지션값은 제일 밑에 값
        Log.d(TAG, "getView: " +position);

        LayoutInflater inflater = LayoutInflater.from(parent.getContext()); // 인플레이터
        View itemView = inflater.inflate(R.layout.item, parent, false); //아이템뷰
        TextView tv = itemView.findViewById(R.id.tv_title); //뷰에서 텍스트뷰 찾기
        tv.setText(getItem(position).toString());

        return itemView;
    }
}
