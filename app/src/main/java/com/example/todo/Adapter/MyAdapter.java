package com.example.todo.Adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.todo.Fragments.DoWork;
import com.example.todo.Fragments.DoneWork;

public class MyAdapter extends FragmentStatePagerAdapter {
    Context context;
    int TotalTabs;
    public MyAdapter(@NonNull FragmentManager fm ,Context context,int tabs) {
        super(fm);
        this.context = context;
        TotalTabs = tabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                DoWork DoworkFragment = new DoWork();
                return DoworkFragment;
            case 1:
                DoneWork DoneWorkFragment = new DoneWork();
                return DoneWorkFragment;
            default:
               return null;
        }
    }

    @Override
    public int getCount() {
        return TotalTabs;
    }
}
