package com.example.todolist.ui.ListGroupe;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.todolist.Beans.GroupModel;
import com.example.todolist.Beans.Task;
import com.example.todolist.Utils.Crendentials;
import com.example.todolist.networking.ApiClient;
import com.example.todolist.networking.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListGroupeViewModel extends AndroidViewModel {

    static MutableLiveData<List<GroupModel>> mgroupemodel = new MutableLiveData<>();

    private static final String TAG = "AllGroupeViewModel";
    public static SharedPreferences sharref;
    public ListGroupeViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<GroupModel>> getGroups() {
        return mgroupemodel;
    }


    void init() {

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<GroupModel>> call = apiInterface.GetGroupes(Crendentials.getaccess_token());
        call.enqueue(new Callback<List<GroupModel>>() {
            @Override
            public void onResponse(Call call, Response response) {
                //Log.d(TAG, "onResponse: " + response);
                mgroupemodel.setValue((List<GroupModel>) response.body());

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });

    }

}