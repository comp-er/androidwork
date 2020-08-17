package com.example.imgdatabinding;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

// 이미지 바인딩을 위한함수

public class ImageBindAdapter {

    @BindingAdapter({"getImg"})
    public static void getImg(ImageView imageView, String imgURI) {
        Glide.with(imageView.getContext()).load(imgURI).into(imageView);
    }
}


