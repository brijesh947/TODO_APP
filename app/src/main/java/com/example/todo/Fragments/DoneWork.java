package com.example.todo.Fragments;

import android.app.Activity;
import android.app.Dialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo.Adapter.DisplayDataAdapter;
import com.example.todo.R;
import com.example.todo.activity.AddTaskActivity;
import com.example.todo.activity.EditActivity;
import com.example.todo.database.TaskDetail;
import com.example.todo.database.TaskViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class DoneWork extends Fragment {
    FloatingActionButton floatButton;
    private TaskViewModel taskViewModel;
    private Activity activity;
    private Context context;
    private RecyclerView recyclerView;
    DisplayDataAdapter displayDataAdapter;
    int pos;
    public DoneWork() {
        super.onAttach(context);
        this.context = context;
        if (context instanceof Activity) {
            activity = (Activity) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      View view =  inflater.inflate(R.layout.donelayout,container,false);
        recyclerView = view.findViewById(R.id.done_recyler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        displayDataAdapter = new DisplayDataAdapter(activity, new DoneWork());
        recyclerView.setAdapter(displayDataAdapter);
        setHasOptionsMenu(true);
        taskViewModel = ViewModelProviders.of((FragmentActivity) getActivity()).get(TaskViewModel.class);
        taskViewModel.getmDonework().observe((LifecycleOwner) getActivity(), new Observer<List<TaskDetail>>() {
            @Override
            public void onChanged(List<TaskDetail> taskDetails) {
                displayDataAdapter.setTaskDetails(taskDetails);

            }
        });
        recyclerView.addOnItemTouchListener(new RecycleListener(getContext(), recyclerView, new DoneWork.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                LinearLayout linearLayout = view.findViewById(R.id.show_action_onclick);
                Button doneButton = view.findViewById(R.id.done_button);
                doneButton.setText("UNDO");
                linearLayout.setVisibility(View.VISIBLE);
                Button cancelButton = view.findViewById(R.id.cancel_button);
                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        linearLayout.setVisibility(View.GONE);
                    }
                });
                Button editButton = view.findViewById(R.id.edit_button);
                editButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), EditActivity.class);
                        intent.putExtra("EXTRA_TITLE",displayDataAdapter.getTaskDetailAt(position).getTask_name());
                        intent.putExtra("EXTRA_DATE",displayDataAdapter.getTaskDetailAt(position).getTask_endDate());
                       // intent.putExtra("FRAGMENT","DoneWork");
                        startActivityForResult(intent, 1);
                        pos = position;
                    }
                });
                Button deleteButton = view.findViewById(R.id.delete_button);
                deleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Dialog dialog = new Dialog(getActivity());
                        dialog.setContentView(R.layout.dialog_box);

                        dialog.setCancelable(true);
                        dialog.show();
                        Button ok_button = (Button)dialog.findViewById(R.id.ok_button);
                        Button cancel_button = (Button)dialog.findViewById(R.id.cancel_button);
                        ok_button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                taskViewModel.delete(displayDataAdapter.getTaskDetailAt(position));
                            }
                        });
                        cancel_button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });

                    }
                });

                doneButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Dialog dialog = new Dialog(getActivity());
                        dialog.setContentView(R.layout.dialog_box);

                        dialog.setCancelable(true);
                        dialog.show();
                        Button ok_button = (Button)dialog.findViewById(R.id.ok_button);
                        Button cancel_button = (Button)dialog.findViewById(R.id.cancel_button);
                        ok_button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                displayDataAdapter.getTaskDetailAt(position).setTask_status(0);
                                taskViewModel.update(displayDataAdapter.getTaskDetailAt(position));
                                Toast.makeText(getContext(), "Task Marked as Not Done", Toast.LENGTH_LONG).show();
                            }
                        });
                        cancel_button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });

                    }
                });
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

      floatButton = view.findViewById(R.id.fabs);
      floatButton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent = new Intent(getActivity(), AddTaskActivity.class);
              startActivityForResult(intent,0);
          }
      });
      return view;
    }


//    @Override
//    public void onCreateOptionsMenu(@NonNull  Menu menu, @NonNull  MenuInflater inflater) {
//        inflater.inflate(R.menu.sample_menu,menu);
//        super.onCreateOptionsMenu(menu, inflater);
//    }
@Override
public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull  MenuInflater inflater) {

    inflater.inflate(R.menu.sample_menu,menu);
    MenuItem searchItem = menu.findItem(R.id.search_button);
    SearchManager searchManager =  (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
    SearchView searchView = (SearchView) searchItem.getActionView();
    if(searchView!=null){
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                displayDataAdapter.filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                displayDataAdapter.filter(newText);
                return true;
            }
        });
    }
    super.onCreateOptionsMenu(menu, inflater);
}

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       switch (requestCode){
           case 0:
               String task_name = data.getStringExtra(AddTaskActivity.EXTRA_TITLE);
               String task_date = data.getStringExtra(AddTaskActivity.EXTRA_DATE);
               if(!task_name.isEmpty() && !task_date.isEmpty()){
                   TaskDetail taskDetail = new TaskDetail(task_name, task_date, 1);
                   taskViewModel.insert(taskDetail);
                   Toast.makeText(getContext(), "Task Saved", Toast.LENGTH_LONG).show();
               }
               else
                   Toast.makeText(getContext(), "Filled the Detail Correctly", Toast.LENGTH_LONG).show();
               break;
           case 1:
               String task_name1= data.getStringExtra(EditActivity.EXTRA_TITLE);
               String task_date2 = data.getStringExtra(EditActivity.EXTRA_DATE);
               if(task_name1!=null && task_date2!=null){
                   TaskDetail taskDetail = displayDataAdapter.getTaskDetailAt(pos);
                   taskDetail.setTask(task_name1,task_date2);
                   taskViewModel.update(taskDetail);
                   Toast.makeText(getContext(), "Task Updated", Toast.LENGTH_SHORT).show();
               }else
                   Toast.makeText(getContext(), "Please Filled Detail Correctly", Toast.LENGTH_SHORT).show();
               break;
           default:
               break;
       }

    }


    public static interface ClickListener {
        public void onClick(View view, int position);

        public void onLongClick(View view, int position);
    }

    class RecycleListener implements RecyclerView.OnItemTouchListener {
        private DoneWork.ClickListener clicklistener;
        private GestureDetector gestureDetector;


        public RecycleListener(Context context, final RecyclerView recyclerView, final DoneWork.ClickListener clicklistener) {


          this.clicklistener = clicklistener;
            gestureDetector = new GestureDetector(context, new GestureDetector.OnGestureListener() {
                @Override
                public boolean onDown(MotionEvent e) {
                    return false;
                }

                @Override
                public void onShowPress(MotionEvent e) {

                }

                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                    return false;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clicklistener != null) {
                        clicklistener.onLongClick(child, recyclerView.getChildAdapterPosition(child));
                    }

                }

                @Override
                public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                    return false;
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clicklistener != null && gestureDetector != null) {
                clicklistener.onClick(child, rv.getChildAdapterPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

}
