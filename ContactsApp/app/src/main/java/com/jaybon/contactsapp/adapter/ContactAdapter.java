package com.jaybon.contactsapp.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jaybon.contactsapp.MainActivity;
import com.jaybon.contactsapp.R;
import com.jaybon.contactsapp.db.model.Contact;
import com.jaybon.contactsapp.util.ImageUpload;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private static final String TAG = "ContactsAdapter";
    private MainActivity mainActivity;
    private List<Contact> contacts;

    public void addItems(List<Contact> contacts){
        this.contacts = contacts;
    }

    public void addItem(Contact contact){
        contacts.add(contact);
    }

    public ContactAdapter(MainActivity mainActivity, List<Contact> contacts) {
        this.mainActivity = mainActivity;
        this.contacts = contacts;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvName;
        TextView tvEmail;
        CircleImageView ivProfile;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Log.d(TAG, "ViewHolder: ");
            tvName = itemView.findViewById(R.id.tv_name);
            tvEmail = itemView.findViewById(R.id.tv_email);
            ivProfile = itemView.findViewById(R.id.iv_profile);
        }

        void setItem(String name, String email, String profileURL){
            Log.d(TAG, "setItem: ");
            tvName.setText(name);
            tvEmail.setText(email);

            if(profileURL == null || profileURL.equals("") ){ // 사진 업로드를 하지 않으면 기본 값 null
                ivProfile.setImageResource(R.drawable.ic_person);
            }else{
                ImageUpload.setImage(profileURL, ivProfile);
            }
        }
    }

    @NonNull
    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.ViewHolder holder, final int position) {
        // 컬렉션 증가 변화에만 반응함.
        final Contact contact = contacts.get(position);
        holder.setItem(contact.getName(), contact.getEmail(), contact.getProfile());

        // 데이터 바인딩할 때 이벤트 달기
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // 홀더 안의 뷰에 이벤트 달기

                // 아이템을 누르면 다이얼로그 창 띄우기
                mainActivity.editContactDialog(contact); // contacts.get(position)
            }
        });
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }
}