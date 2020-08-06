package com.jaybon.contactsapp;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.jaybon.contactsapp.adapter.ContactAdapter;
import com.jaybon.contactsapp.db.ContactAppDatabase;
import com.jaybon.contactsapp.db.model.Contact;
import com.jaybon.contactsapp.service.ContactService;
import com.jaybon.contactsapp.util.ImageUpload;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main_Activity";
    private Context mContext = MainActivity.this;

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ContactAdapter adapter;
    private FloatingActionButton fab;

    private ContactAppDatabase contactAppDatabase;
    private ContactService contactService;
    private List<Contact> contacts;

    // 사진 업로드
    private CircleImageView ivProfile;
    private File tempFile;
    private String imageRealPath;
    private static final int PICK_FROM_ALBUM = 1;
    private static final int PICK_FROM_CAMERA = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // DB관련
        contactAppDatabase = Room.databaseBuilder(getApplicationContext(), ContactAppDatabase.class, "ContactDB")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
        contactService = new ContactService(contactAppDatabase.contactRepository());

        initData();
        initObject();
        initDesign();
        initListener();

        tedPermission();
    }

    private void initData(){
        contacts = contactService.연락처전체보기();
    }

    private void initObject(){
        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recycler_view_contacts);
        fab = findViewById(R.id.fab);
        ivProfile = findViewById(R.id.iv_profile);
        adapter = new ContactAdapter((MainActivity)mContext , contacts);
    }

    private void initDesign(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(" Contact App");

        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void initListener(){
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addContactDialog();
            }
        });
    }

    public void addContactDialog(){
        View dialogView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.layout_add_contact, null);
        final EditText etName = dialogView.findViewById(R.id.name);
        final EditText etEmail = dialogView.findViewById(R.id.email);

        // 갤러리 사진 가져오기
        ivProfile = dialogView.findViewById(R.id.iv_profile);
        ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageUpload.goToAlbum((MainActivity) mContext);
            }
        });

        AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
        dlg.setTitle("연락처 등록");
        dlg.setView(dialogView);
        dlg.setPositiveButton("등록", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                createContact(etName.getText().toString(), etEmail.getText().toString());
            }
        });
        dlg.setNegativeButton("닫기", null);
        dlg.show();
    }

    public void editContactDialog(final Contact contact){
        View dialogView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.layout_add_contact, null);
        final EditText etName = dialogView.findViewById(R.id.name);
        final EditText etEmail = dialogView.findViewById(R.id.email);

        // 갤러리 사진 수정하기
        ivProfile = dialogView.findViewById(R.id.iv_profile);
        ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //goToAlbum();
            }
        });

        etName.setText(contact.getName());
        etEmail.setText(contact.getEmail());

        AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
        dlg.setTitle("연락처 수정");
        dlg.setView(dialogView);
        dlg.setPositiveButton("수정", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                createContact(etName.getText().toString(), etEmail.getText().toString());
                Contact updateContact = new Contact(contact.getId(), etName.getText().toString(), etEmail.getText().toString());

                contactService.연락처수정(updateContact);
                notifyListener();

            }
        });
        dlg.setNegativeButton("삭제", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                contactService.연락처삭제(contact.getId());
                notifyListener();
            }
        });
        dlg.show();
    }

    private void createContact(String name, String email) {
        long contactId = contactService.연락처등록(new Contact(name, email));
        Contact contact = contactService.연락처상세보기(contactId);
        adapter.addItem(contact);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

//        long menuId = item.getItemId(); // 개별 연락처의 번호

        contactService.연락처전체삭제();
        Log.d(TAG, "onOptionsItemSelected: 삭제됨");

        notifyListener();

        return true;
    }

    public void notifyListener(){
        // DB내용변경 -> 어댑터 데이터 변경 -> UI 갱신
        // MVVM의 경우 DB내용변경 -> 어댑터 데이터 변경시 UI 자동갱신
        // 리액티브 프로그래밍의 경우 DB내용변경시 어댑터 데이터 자동갱신 +UI 자동갱신
        adapter.addItems(contactService.연락처전체보기()); // adapter 내용변경
        adapter.notifyDataSetChanged(); // ui 갱신
    }

//    // 앨범으로 이동
//    private void goToAlbum() {
//        Intent intent = new Intent(Intent.ACTION_PICK);
//        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
//        startActivityForResult(intent, PICK_FROM_ALBUM);
//    }
//
//    // 이미지 채우기
//    public void setImage() {
//        BitmapFactory.Options options = new BitmapFactory.Options();
//        Bitmap originalBm = BitmapFactory.decodeFile(tempFile.getAbsolutePath(), options);
//        ivProfile.setImageBitmap(originalBm);
//    }
//
//    // URI로 이미지 실제 경로 가져오기
//    private String getRealPathFromURI(Uri contentURI) {
//        String result;
//        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
//        if (cursor == null) { // Source is Dropbox or other similar local file path
//            result = contentURI.getPath();
//        } else {
//            cursor.moveToFirst();
//            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
//            result = cursor.getString(idx);
//            cursor.close();
//        }
//        return result;
//    }

    // 이미지 선택 후 이미지 채우기
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d(TAG, "onActivityResult: 사진 선택 후 여기");
        
        if (requestCode == PICK_FROM_ALBUM) { // 앨범에서 가져온 데이터
            Uri photoUri = data.getData();

            imageRealPath = ImageUpload.getRealPathFromURI(photoUri, (MainActivity) mContext);

            Log.d(TAG, "onActivityResult: imageRealPath" + imageRealPath);

            tempFile = new File(imageRealPath);
            ImageUpload.setImage(tempFile.getAbsolutePath(), ivProfile);

        } else if(requestCode == PICK_FROM_CAMERA) {
            // 카메라로 찍은 데이터
        }
    }

    // ------------- 아래는 사진 업로드를 위한 추가 코드 ---------------------
    // https://dd00oo.tistory.com/entry/%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C-%EA%B0%A4%EB%9F%AC%EB%A6%AC%EC%9D%98-%EC%8B%A4%EC%A0%9C%EA%B2%BD%EB%A1%9C-%EA%B0%80%EC%A0%B8%EC%98%A4%EA%B8%B0

    // 권한 관련
    private void tedPermission() {
        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                // 권한 요청 성공
            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                // 권한 요청 실패
            }
        };

        TedPermission.with(this)
                .setPermissionListener(permissionListener)
                .setRationaleMessage(getResources().getString(R.string.permission_2))
                .setDeniedMessage(getResources().getString(R.string.permission_1))
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .check();

    }
}