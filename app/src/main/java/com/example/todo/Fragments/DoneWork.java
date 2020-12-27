package com.example.todo.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.todo.R;
import com.example.todo.activity.AddTaskActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DoneWork extends Fragment {
   FloatingActionButton floatButton;

    public DoneWork() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      View view =  inflater.inflate(R.layout.donelayout,container,false);
      floatButton = view.findViewById(R.id.fabs);
      floatButton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent = new Intent(getActivity(), AddTaskActivity.class);
              startActivityForResult(intent,1);
          }
      });
      return view;
    }
}
