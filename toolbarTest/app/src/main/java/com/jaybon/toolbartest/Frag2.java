package com.jaybon.toolbartest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

// 어딘가에서 new Frag1(); 을 하면 메모리에 뜨고 자동으로 onCreateView을 콜백해준다
public class Frag2 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // inflater를 통해서 inflate를 하면된다 (띄워주기)
        // container는 Frag를 띄울 레이아웃인데 정해지지않고 호출될 때 정해진다

        View v = inflater.inflate(R.layout.frag2, container, false);

        return v;
    }
}