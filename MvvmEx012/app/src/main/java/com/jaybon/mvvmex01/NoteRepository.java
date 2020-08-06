package com.jaybon.mvvmex01;

//룸 연결 안할것

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.util.ArrayList;
import java.util.List;

public class NoteRepository {

    // DB에서 받아왔다고 가정
    // 라이브데이터는 new해서 만드는게 아니다
    // DB에 연결되어있으면 선언만 하면 되는데, 지금은 그게 안되기 때문
    // LiveData는 함수가 getter만 있음
    // MutableLiveData는 함수가 getter setter 둘 다 있음
    // DB에 연결해서 쓸 때는 무조건 LiveData
    // 레트로핏2 등을 사용할 때에는 MutableLiveData
    private MutableLiveData<List<Note>> allNotes = new MutableLiveData<>();

    public NoteRepository() {
        List<Note> notes = new ArrayList<>();
        notes.add(new Note("제목", "설명", 0));
        allNotes.setValue(notes);
    }

    public LiveData<List<Note>> findAll(){

        return allNotes;
    }

    public void delete(Note note){
        List<Note> notes = allNotes.getValue();
        notes.remove(note);
        allNotes.setValue(notes);
    }

    public void save(Note note){
        List<Note> notes = allNotes.getValue();
        notes.add(note);
        allNotes.setValue(notes);
    }
}
