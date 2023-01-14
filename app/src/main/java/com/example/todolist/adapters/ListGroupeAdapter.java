package com.example.todolist.adapters;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.todolist.Beans.GroupModel;
import com.example.todolist.Beans.Task;
import com.example.todolist.MainActivity;
import com.example.todolist.R;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.List;

public class ListGroupeAdapter extends BaseAdapter {
    private List<GroupModel> objects;
    private LayoutInflater inflater;

    public ListGroupeAdapter(Activity activity, List<GroupModel> objects) {
        this.objects = objects;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public List<GroupModel> getObjects() {
        return objects;
    }


    public ListGroupeAdapter(MainActivity activity) {
        this.objects = new ArrayList<GroupModel>();
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
            convertView = inflater.inflate(R.layout.iteam_listgroupe, null);


        TextView id = convertView.findViewById(R.id.id);
        TextView txt_name = convertView.findViewById(R.id.txt_name);




       // Log.i("sabriiiiiiiiiiiiiiii", objects.get(position).getName());
        txt_name.setText(String.valueOf(objects.get(position).getName()));



        return convertView;
    }

    public void setObjects(List<GroupModel> groupModels) {

        this.objects=groupModels;
        Log.i("Adapter", this.objects.toString());


    }
}