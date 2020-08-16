package com.example.mvvmbindingfirestore1;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class NameViewModel extends AndroidViewModel {

   private LiveData<Name> liveName;
   private NameRepository nameRepository = new NameRepository();

    public NameViewModel(@NonNull Application application) {
        super(application);
        this.liveName = nameRepository.findAll();
    }

    public LiveData<Name> 구독(){
        return liveName;
    }

    public void 이름바꾸기(String changeName){
        nameRepository.changeName(changeName);
    }
}


