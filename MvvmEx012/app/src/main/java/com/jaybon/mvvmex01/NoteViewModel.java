package com.jaybon.mvvmex01;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    // 1 뷰모델이 가진 데이터 정리
    // 구독시킬 데이터를 LiveData 타입으로 묶음.
    // 묶어야 옵저버를 짤 수 있다
    private LiveData<List<Note>> allNotes;
    private NoteRepository noteRepository = new NoteRepository();

    public NoteViewModel(@NonNull Application application) {
        super(application);
        allNotes = noteRepository.findAll();
    }

    // 빨대 꼽기 위해서 라이브데이터에 접근하는
    public LiveData<List<Note>> 구독() {
        return allNotes;
    }

    // 액티비티는 뷰모델에 요청 - 뷰모델은 레파지토리에 요청
    // 뷰에서 insert를 할때에도 뷰모델에다가 해야한다(서비스를 대신한다)

    public void 추가하기(Note note){
        noteRepository.save(note);
    }

    public void 삭제하기(Note note){
        noteRepository.delete(note);
    }

    //필요없음
//    public LiveData<List<Note>> 전체보기(){
//        return noteRepository.findAll();
//    }

}
