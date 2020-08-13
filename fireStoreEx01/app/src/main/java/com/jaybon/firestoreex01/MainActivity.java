package com.jaybon.firestoreex01;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main_Activity";

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = FirebaseFirestore.getInstance(); // 양방향

        User user = User.builder() // 테스트 유저객체 생성(fireStore 문서 생성)
                .id(3)
                .username("test3")
                .password("123")
                .email("test3@test.com")
                .createDate(Timestamp.now())
                .build();

//        save(user);

//        findAll();

//        findById();

        mStream();
    }

    // 실시간 수신대기
    private void mStream(){
        // user컬렉션의 1번문서의 레퍼런스
        final DocumentReference docRef = db.collection("user").document("1");
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                Log.d(TAG, "onEvent: 데이터 변경됨");
                User user = documentSnapshot.toObject(User.class);
                Log.d(TAG, "onEvent: "+user.getPassword());
            }
        });
    }

    // 일부데이터만 가져오기
    private void findById(){
//        문서 안의 컬렉션을 가져올 때

//        db.collection("user").document("3").collection("follow").get();

//        db.collection("user")
//                .document("3")
//                .get()// 3번 id 의데이터를 가져온다
//                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                        if(task.isSuccessful()){
//                           DocumentSnapshot document = task.getResult();
//                           User user = document.toObject(User.class);
//                            Log.d(TAG, "onComplete: "+user.getEmail());
//                        } else {
//                            Log.d(TAG, "onComplete: 실패"+task.getException());
//                        }
//                    }
//                });

        db.document("user/3") // Rest처럼 사용
                .get()// 3번 id 의데이터를 가져온다
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot document = task.getResult();
                            User user = document.toObject(User.class);
                            Log.d(TAG, "onComplete: "+user.getEmail());
                        } else {
                            Log.d(TAG, "onComplete: 실패"+task.getException());
                        }
                    }
                });

    }


    private void findAll() {

        db.collection("user").orderBy("id", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        // 데이터베이스에서 QuerySnapshot을 task로 가져온다

                        if (task.isSuccessful()) { // 제대로 task가 만들어졌다면
                            Log.d(TAG, "onComplete: task 성공 " + task.getResult().getDocuments());

//                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                Log.d(TAG, "onComplete: document : " + document.toObject(User.class));
//                            }

                            // 뷰모델 레퍼런스 접근.data.setValue(컬렉션);

                        } else {
                            Log.d(TAG, "onComplete: task 실패" + task.getException());
                        }
                    }
                });

    }

    private void save(User user) {
        db.collection("user") // 자동으로 user 컬렉션을 만들어준다
                .document(Integer.toString(user.getId())) // 문서생성, id를 유저가 만든 id로 입력
                .set(user) // user컬렉션에 user객체를 삽입
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "onSuccess: 성공");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "onFailure: " + e.getMessage());
                    }
                });
    }
}