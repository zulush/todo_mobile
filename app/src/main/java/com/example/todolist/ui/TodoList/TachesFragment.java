package com.example.todolist.ui.TodoList;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.todolist.Beans.Task;
import com.example.todolist.DetailTask;
import com.example.todolist.MainActivity;
import com.example.todolist.R;
import com.example.todolist.adapters.TaskAdapter;
import com.example.todolist.databinding.FragmentTachesBinding;

import java.util.List;

public class TachesFragment extends Fragment {

    private static final String TAG = "AllCamion";

    private FragmentTachesBinding binding;
    private ListView list;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentTachesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        // instantiate ViewModel
        TachesViewModel mTachesViewModel = new ViewModelProvider(this).get(TachesViewModel.class);

        // reception initial les données
        mTachesViewModel.init();
        // instantiate the adapter
        TaskAdapter adapter = new TaskAdapter((MainActivity) getActivity());
        list = root.findViewById(R.id.Taskeslist);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                OpenDetailTask(mTachesViewModel.getTask().getValue().get(position));
                // OpenDetailBon(mCamionViewLodel.getCamion().getValue().get(position));
            }
        });
        list.setAdapter(adapter);


        // set Observer of the DataLive (which is products)
        mTachesViewModel.getTask().observe(getActivity(), new Observer<List<Task>>(){

            @Override
            public void onChanged(List<Task> tasks) {
                Log.i(TAG, "onchanged in observer is called!");
                Log.i("data", tasks.toString());
                adapter.setObjects(tasks);
                adapter.notifyDataSetChanged();
            }
        });

        return root;
    }

    private void OpenDetailTask(Task l) {
        Log.i(TAG, "OpenDetailTask: id = " + l.getTaskId());
        Intent intent = new Intent(getActivity(), DetailTask.class);
        intent.putExtra("task_id", l.getTaskId());
        intent.putExtra("task_name", l.getName());
        intent.putExtra("task_deadline", l.getDeadline());
        intent.putExtra("task_done", l.getDone());
        intent.putExtra("task_lastupdatedesc", l.getLastUpdateDesc());
        intent.putExtra("task_lastupdatedate", l.getLastUpdateDate());

        /////////////////////////////////////////////////


        startActivity(intent);
    }

}