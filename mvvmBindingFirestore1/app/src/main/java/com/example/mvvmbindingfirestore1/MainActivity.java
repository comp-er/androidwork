package com.example.mvvmbindingfirestore1;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.mvvmbindingfirestore1.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main_Activity";

    private ActivityMainBinding activityMainBinding;

    private NameViewModel nameViewModel;

//    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameViewModel = ViewModelProviders.of(this).get(NameViewModel.class);

        nameViewModel.구독().observe(this, new Observer<Name>() {
            @Override
            public void onChanged(Name name) {
                activityMainBinding.setName(name);
            }
        });

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        activityMainBinding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameViewModel.이름바꾸기(activityMainBinding.et.getText().toString());
            }
        });

//        db = FirebaseFirestore.getInstance();

//        activityMainBinding.setName(new Name(1, "홍길동"));

//        //파이어스토어에서 데이터 가져오기
//        db.collection("test")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                Log.d(TAG, document.getId() + " => " + document.getData());
//                            }
//                        } else {
//                            Log.w(TAG, "Error getting documents.", task.getException());
//                        }
//                    }
//                });


//        파이어스토어 빨대
//        final DocumentReference docRef = db.collection("test").document("1");
//        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
//
//            @Override
//            public void onEvent(@Nullable DocumentSnapshot snapshot,
//                                @Nullable FirebaseFirestoreException e) {
//                if (e != null) {
//                    Log.w(TAG, "Listen failed.", e);
//                    return;
//                }
//
//                if (snapshot != null && snapshot.exists()) {
//                    Log.d(TAG, "Current data: " + snapshot.getData());
//
//                    Name name = snapshot.toObject(Name.class);
//
//                    activityMainBinding.setName(name);
//
//                } else {
//                    Log.d(TAG, "Current data: null");
//                }
//            }
//        });

    }
}
