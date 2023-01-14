package com.example.todolist.ui.personalTasks;

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

import com.example.todolist.Beans.GroupModel;
import com.example.todolist.Beans.TaskModel;
import com.example.todolist.MainActivity;
import com.example.todolist.R;
import com.example.todolist.adapters.ListGroupeAdapter;
import com.example.todolist.adapters.PersonalTasksAdapter;
import com.example.todolist.databinding.FragmentListGroupeBinding;
import com.example.todolist.databinding.FragmentPersonalTasksBinding;
import com.example.todolist.ui.ListGroupe.ListGroupeViewModel;

import java.util.List;

public class personalTasksFragment extends Fragment {

    private static final String TAG = "ListGroupe";

    private FragmentPersonalTasksBinding binding;
    private ListView list;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentPersonalTasksBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        // instantiate ViewModel
        PersonalTasksViewModel mpersonalTasksViewModel = new ViewModelProvider(this).get(PersonalTasksViewModel.class);

        // reception initial les données
        mpersonalTasksViewModel.init();
        // instantiate the adapter
        PersonalTasksAdapter adapter = new PersonalTasksAdapter((MainActivity) getActivity());
        list = root.findViewById(R.id.ListpersonalTasks);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

            }
        });
        list.setAdapter(adapter);


        // set Observer of the DataLive (which is products)
        mpersonalTasksViewModel.getGroups().observe(getActivity(), new Observer<List<TaskModel>>(){

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
