package com.example.mvvmbindingfirestore1;

import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.SetOptions;

public class NameRepository {

    private static final String TAG = "NameRepository";

    FirebaseFirestore db;

    private MutableLiveData<Name> liveName = new MutableLiveData<>();

    public NameRepository() {
        db = FirebaseFirestore.getInstance();
        initPush();
//        getFireName();

    }

    public LiveData<Name> findAll(){
        return liveName;
    }


    public void changeName(String changeName){
        Name name = new Name(liveName.getValue().getId(), changeName);
        db.collection("test").document("1")
                .set(name, SetOptions.merge());
//        getFireName();
    }


    private void initPush(){
        //        파이어스토어 빨대
        final DocumentReference docRef = db.collection("test").document("1");
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {

            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w(TAG, "Listen failed.", e);
                    return;
                }

                if (snapshot != null && snapshot.exists()) {
                    Log.d(TAG, "Current data: " + snapshot.getData());

                    liveName.setValue(snapshot.toObject(Name.class));

                } else {
                    Log.d(TAG, "Current data: null");
                }
            }
        });
    }


//    public void getFireName(){
//        //파이어스토어에서 데이터 가져오기
//        db.collection("test")
//                .document("1")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                        if (task.isSuccessful()) {
//
//                            Log.d(TAG, "onComplete: " + task.getResult().getData());
//
//                            Name name = task.getResult().toObject(Name.class);
//
//                            liveName.setValue(name);
//
//                        } else {
//                            Log.w(TAG, "Error getting documents.", task.getException());
//                            liveName.setValue(null);
//                        }
//                    }
//                });
//    }

}
