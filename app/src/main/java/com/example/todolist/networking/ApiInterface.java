package com.example.todolist.networking;


import com.example.todolist.Beans.GroupModel;
import com.example.todolist.Beans.LoginResponse;
import com.example.todolist.Beans.Task;
import com.example.todolist.Beans.TaskModel;
import com.example.todolist.Beans.TasksListModel;
import com.example.todolist.Beans.UserModel;

import java.util.Date;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

import retrofit2.http.GET;

import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;


public interface ApiInterface {
    //POST HTTP
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("/task/personal")
    Call<Task> createTask(@Query("name")String name, @Query("deadline") String deadline,@Header("Authorization") String auth);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("/task/other_user")
    Call<Task> createTaskforUser(@Query("name")String name, @Query("deadline") String deadline,@Query("user_id")int userid,@Header("Authorization") String auth);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("/user/add_task")
    Call<Task> AffecterTaskaunuser(@Query("task_id")int task_id, @Query("user_id") int user_id,@Header("Authorization") String auth);


    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("/group")
    Call <Object> CreateGroupe(@Query("name")String name, @Query("user_id") List<Integer>names,@Header("Authorization") String auth);

    //GET HTTP
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @GET("/task/personal")
    Call<List<Task>> getTasks(@Header("Authorization") String auth);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @GET("/group")
    Call <List<GroupModel>> GetGroupes(@Header("Authorization") String auth);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @GET("/user/search")
    Call<UserModel> getUserbyusername(@Query("username")String username,@Header("Authorization") String auth);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @GET("/task/all")
    Call<TasksListModel> getTaskSpared(@Header("Authorization") String auth);

    @POST("/user/login")
    Call<LoginResponse> Authentifcate(@QueryMap Map<String, String> options);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("/user/save")
    Call <Object>CreateUser(@Query("username")String username,@Query("password")String password);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("/group/task")
    Call <Object>affecteTasktoGroupe(@Query("task_id")int task_id,@Query("group_id")int group_id,@Header("Authorization") String auth);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("/task/done")
    Call <Object>makethisTaskDone(@Query("task_id")int task_id,@Query("done")boolean done,@Header("Authorization") String auth);


}
