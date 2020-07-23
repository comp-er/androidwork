package com.jaybon.instagramrecycleview;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.MyViewHolder> {

    private static final String TAG = "ProfileAdapter";

    private List<Profile> profiles = new ArrayList<>();

    public void addProfile(Profile profile) {
        profiles.add(profile);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_profile, parent, false);
        return new ProfileAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Profile profile = profiles.get(position);
        holder.setProfile(profile);

    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }

    // ViewHolder (뷰들의 책꽂이)
    public static class MyViewHolder extends RecyclerView.ViewHolder { // 뷰홀더

        // 규칙1 (xml이 들고있는 뷰)
        private CircleImageView imgPro1;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPro1 = itemView.findViewById(R.id.img_pro1);
        }

        // 규칙3
        public void setProfile(Profile profile) {
            imgPro1.setImageResource(profile.getImgResource());
        }
    }
}
