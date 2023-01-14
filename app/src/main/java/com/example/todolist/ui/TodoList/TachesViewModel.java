package com.example.todolist.ui.TodoList;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.todolist.Beans.Task;
import com.example.todolist.Utils.Crendentials;
import com.example.todolist.networking.ApiClient;
import com.example.todolist.networking.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TachesViewModel extends AndroidViewModel {

    private  MutableLiveData<List<Task>> mTask = new MutableLiveData<>();

    private static final String TAG = "AllTasksViewModel";

    public TachesViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Task>> getTask() {
        return mTask;
    }


    void init() {

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Task>> call = apiInterface.getTasks(Crendentials.getaccess_token());
        call.enqueue(new Callback<List<Task>>() {
            @Override
            public void onResponse(Call call, Response response) {
                //Log.d(TAG, "onResponse: " + response);
                mTask.setValue((List<Task>) response.body());

                Log.d(TAG, "gettttttttttttttttttt: " + mTask.getValue());
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });

    }

}