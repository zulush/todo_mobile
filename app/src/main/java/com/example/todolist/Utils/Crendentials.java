package com.example.todolist.Utils;


import com.example.todolist.Activity_Login;


public class Crendentials {
    final public static String access_token = "";



    public static String getaccess_token() {
        return Activity_Login.sharref.getString("access_token", "");

    }

}