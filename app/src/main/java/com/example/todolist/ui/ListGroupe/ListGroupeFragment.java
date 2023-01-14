package com.example.todolist.ui.ListGroupe;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
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
import com.example.todolist.Beans.Task;
import com.example.todolist.DetailTask;
import com.example.todolist.MainActivity;
import com.example.todolist.R;
import com.example.todolist.adapters.ListGroupeAdapter;
import com.example.todolist.adapters.TaskAdapter;
import com.example.todolist.databinding.FragmentListGroupeBinding;
import com.example.todolist.databinding.FragmentTachesBinding;
import com.example.todolist.ui.TodoList.TachesViewModel;

import java.util.List;

public class ListGroupeFragment extends Fragment {

    private static final String TAG = "ListGroupe";

    private FragmentListGroupeBinding binding;
    private ListView list;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentListGroupeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        // instantiate ViewModel
        ListGroupeViewModel mlistGroupeViewModel = new ViewModelProvider(this).get(ListGroupeViewModel.class);

        // reception initial les données
        mlistGroupeViewModel.init();
        // instantiate the adapter
        ListGroupeAdapter adapter = new ListGroupeAdapter((MainActivity) getActivity());
        list = root.findViewById(R.id.Groupelist);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

            }
        });
        list.setAdapter(adapter);


        // set Observer of the DataLive (which is products)
        mlistGroupeViewModel.getGroups().observe(getActivity(), new Observer<List<GroupModel>>(){

            @Override
            public void onChanged(List<GroupModel> groupModels) {
                Log.i(TAG, "onchanged in observer is called!");
                Log.i("data", groupModels.toString());
                adapter.setObjects(groupModels);
                adapter.notifyDataSetChanged();
            }
        });

        return root;
    }


    }

