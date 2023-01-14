package com.example.todolist.ui.personalTasks;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.todolist.Beans.GroupModel;
import com.example.todolist.Beans.TaskModel;
import com.example.todolist.Beans.TasksListModel;
import com.example.todolist.Utils.Crendentials;
import com.example.todolist.networking.ApiClient;
import com.example.todolist.networking.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonalTasksViewModel extends AndroidViewModel {

    private  MutableLiveData<List<TaskModel>> mtaskmodel = new MutableLiveData<>();

    private static final String TAG = "Personneltaskks";

    public PersonalTasksViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<TaskModel>> getGroups() {
        return mtaskmodel;
    }


    void init() {

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<TasksListModel> usercall = ApiClient.getUserService().getTaskSpared(Crendentials.getaccess_token());
        usercall.enqueue(new Callback<TasksListModel>() {
            @Override
            public void onResponse(Call<TasksListModel> call, Response<TasksListModel> response) {
                if(response.body().getPersonalTasks()!=null) {
                    Log.i("getTaskSpared", response.body().toString());
                    List<TaskModel> t = response.body().getPersonalTasks();
                    mtaskmodel.setValue(t);
                }
                Log.d(TAG, "gettttttttttttttttttt: " + mtaskmodel.getValue());
            }

            @Override
            public void onFailure(Call<TasksListModel> call, Throwable t) {

            }


        });


    }

}