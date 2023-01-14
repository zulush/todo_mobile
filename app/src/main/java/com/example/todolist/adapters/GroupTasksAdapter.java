package com.example.todolist.adapters;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.todolist.Beans.TaskModel;
import com.example.todolist.MainActivity;
import com.example.todolist.R;

import java.util.ArrayList;
import java.util.List;

public class GroupTasksAdapter extends BaseAdapter {
    private List<TaskModel> objects;
    private LayoutInflater inflater;

    public GroupTasksAdapter(Activity activity, List<TaskModel> objects) {
        this.objects = objects;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public List<TaskModel> getObjects() {
        return objects;
    }


    public GroupTasksAdapter(MainActivity activity) {
        this.objects = new ArrayList<TaskModel>();
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
            convertView = inflater.inflate(R.layout.iteam_shared_tasks3, null);


        TextView id = convertView.findViewById(R.id.id);
        TextView txt_name = convertView.findViewById(R.id.txt_name);
        TextView txt_deadline = convertView.findViewById(R.id.txt_deadline);
        TextView txt_sharedwith = convertView.findViewById(R.id.txt_sharedwith);


        // Log.i("sabriiiiiiiiiiiiiiii", objects.get(position).getName());
        txt_name.setText(String.valueOf(objects.get(position).getName()));
        txt_deadline.setText(String.valueOf(objects.get(position).getDeadline()));
        txt_sharedwith.setText(String.valueOf(objects.get(position).getSharedWith()));
        ProgressBar progressBar = convertView.findViewById(R.id.progressBar);
        TextView thth = convertView.findViewById(R.id.thth);
        if(objects.get(position).isDone()==true) {
            progressBar.setProgress(100);
            thth.setText("Done");
        }else{
            progressBar.setProgress((int) (Math.random() * 70));
            thth.setText("In Progess");
        }
        return convertView;
    }

    public void setObjects(List<TaskModel> groupModels) {

        this.objects=groupModels;
        Log.i("Adapter", this.objects.toString());


    }
}