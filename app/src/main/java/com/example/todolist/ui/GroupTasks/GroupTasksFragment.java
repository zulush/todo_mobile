package com.example.todolist.ui.GroupTasks;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.todolist.Beans.TaskModel;
import com.example.todolist.MainActivity;
import com.example.todolist.R;
import com.example.todolist.adapters.GroupTasksAdapter;
import com.example.todolist.adapters.SharedTasksAdapter;
import com.example.todolist.databinding.FragmentGroupTasksBinding;
import com.example.todolist.databinding.FragmentListGroupeBinding;
import com.example.todolist.databinding.FragmentSharedTasksBinding;
import com.example.todolist.ui.sharedTasks.SharedTasksViewModel;

import java.util.List;

public class GroupTasksFragment extends Fragment {

    private static final String TAG = "ListGroupe";

    private FragmentGroupTasksBinding binding;
    private ListView list;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentGroupTasksBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        // instantiate ViewModel
        GroupTasksViewModel mgroupTasksViewModel = new ViewModelProvider(this).get(GroupTasksViewModel.class);

        // reception initial les données
        mgroupTasksViewModel.init();
        // instantiate the adapter
        GroupTasksAdapter adapter = new GroupTasksAdapter((MainActivity) getActivity());
        list = root.findViewById(R.id.ListGroupsTasks);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

            }
        });
        list.setAdapter(adapter);


        // set Observer of the DataLive (which is products)
        mgroupTasksViewModel.getGroups().observe(getActivity(), new Observer<List<TaskModel>>(){

            @Override
            public void onChanged(List<TaskModel> pers) {
                Log.i(TAG, "onchanged in observer is called!");
                Log.i("data", pers.toString());
                adapter.setObjects(pers);
                adapter.notifyDataSetChanged();
            }
        });

        return root;
    }


}
