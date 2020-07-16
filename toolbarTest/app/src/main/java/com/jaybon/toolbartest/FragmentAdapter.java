package com.jaybon.toolbartest;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

// 프래그먼트 어댑터는 두가지
// FragmentPagerAdapter - 모든 프래그먼트를 메모리에 미리 로딩 시켜둠, 소멸시키지 않는다(탭이 적을 경우)
// FragmentStatePagerAdapter - 디폴트3, 자기를 포함한 양옆까지 메모리에 로딩(탭이 많을 경우)
// 어댑터를 사용하면 fragment를 관리해줘서 퍼포먼스가 제일 잘 나오도록 해준다
public class FragmentAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList = new ArrayList<>();

    public FragmentAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    // FragmentList에 아이템 추가하는 함수 필요
    public void addFragment(Fragment fragment){
        fragmentList.add(fragment);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }


    // 어댑터를 new할 때 콜백된다.
    @Override
    public int getCount() {

        // fragment를 다 띄울지 들어올때 띄울지 결정
        return fragmentList.size();
    }
}