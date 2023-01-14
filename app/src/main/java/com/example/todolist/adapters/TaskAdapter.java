package com.example.todolist.adapters;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.todolist.Beans.GroupModel;
import com.example.todolist.Beans.Task;
import com.example.todolist.MainActivity;
import com.example.todolist.R;
import com.example.todolist.Utils.Crendentials;
import com.example.todolist.networking.ApiClient;
import com.example.todolist.networking.ApiInterface;
import com.example.todolist.ui.ListGroupe.ListGroupeViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaskAdapter extends BaseAdapter {
    private List<Task> objects;
    private LayoutInflater inflater;
    public static   List<GroupModel>ss;

    public TaskAdapter(Activity activity, List<Task> objects) {
        this.objects = objects;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public List<Task> getObjects() {
        return objects;
    }


    public TaskAdapter(MainActivity activity) {
        this.objects = new ArrayList<Task>();
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position + 1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
            convertView = inflater.inflate(R.layout.iteam_alltask, null);


        TextView id = convertView.findViewById(R.id.id);
        TextView txt_name = convertView.findViewById(R.id.txt_name);
        TextView txt_deadline = convertView.findViewById(R.id.txt_deadline);
        TextView txt_done = convertView.findViewById(R.id.txt_sharedwith);


        id.setText(objects.get(position).getTaskId()+"");
        Log.i("sabriiiiiiiiiiiiiiii", objects.get(position).getName());
        txt_name.setText(String.valueOf(""+objects.get(position).getName()));
        txt_deadline.setText(String.valueOf(""+objects.get(position).getDeadline()));
        txt_done.setText(String.valueOf(""+objects.get(position).getDone()));
        ProgressBar progressBar = convertView.findViewById(R.id.progressBar);
        TextView thth = convertView.findViewById(R.id.thth);

        if(objects.get(position).getDone()=="true") {
            progressBar.setProgress(100);
            thth.setText("Done");
        }else{
            progressBar.setProgress((int) (Math.random() * 70));
            thth.setText("In Progess");
        }
        return convertView;
    }

    public void setObjects(List<Task> tasks) {

        this.objects=tasks;
        Log.i("Adapter", this.objects.toString());


    }
}
