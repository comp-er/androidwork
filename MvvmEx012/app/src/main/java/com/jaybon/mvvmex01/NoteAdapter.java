package com.jaybon.mvvmex01;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.jaybon.mvvmex01.databinding.NoteItemBinding;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {

    private List<Note> notes = new ArrayList<>();

    class NoteHolder extends RecyclerView.ViewHolder {
//        private TextView textViewTitle;
//        private TextView textViewDescription;
//        private TextView textViewPriority;

        // 데이터 바인딩
        private NoteItemBinding noteItemBinding;

        // 데이터 바인딩 이전의 매개변수 View itemView
        public NoteHolder(@NonNull NoteItemBinding noteItemBinding) {
//            기존
//            super(itemView);
//            textViewTitle = itemView.findViewById(R.id.text_view_title);
//            textViewDescription = itemView.findViewById(R.id.text_view_description);
//            textViewPriority = itemView.findViewById(R.id.text_view_priority);

            // 데이터 바인딩
            super(noteItemBinding.getRoot());
            this.noteItemBinding = noteItemBinding;

        }
    }


    // noteItemBinding을 메모리에 띄운다
    // parent = 리사이클러뷰
    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        기존
//        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
//        return new NoteHolder(itemView);

        // 데이터 바인딩
        NoteItemBinding noteItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),R.layout.note_item,parent,false
        );
        return new NoteHolder(noteItemBinding); // 리턴 하는 순간 데이터바인딩이 메모리에 뜬다
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        Note currentNote = notes.get(position);
//        기존
//        holder.textViewTitle.setText(currentNote.getTitle());
//        holder.textViewDescription.setText(currentNote.getDescription());
//        holder.textViewPriority.setText(currentNote.getPriority()+"");

        // 데이터 바인딩
        // 변수이름을 찾아서 알아서 입력한다(getter setter가 있어야한다)
        holder.noteItemBinding.setNote(currentNote);

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setNotes(List<Note> notes){
        this.notes = notes;
        notifyDataSetChanged();
    }

    public Note getNoteAt(int position){
        return notes.get(position);
    }
}
