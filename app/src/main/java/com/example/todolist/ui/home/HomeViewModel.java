package com.example.todolist.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("A truly cross platform task management app. Whether you're at home using the desktop app or are using the mobile app on the go you can access your task list and stay organized.");
    }

    public LiveData<String> getText() {
        return mText;
    }
}