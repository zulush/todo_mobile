package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todolist.Beans.UserModel;
import com.example.todolist.Utils.Crendentials;
import com.example.todolist.networking.ApiClient;
import com.example.todolist.networking.ApiInterface;

import java.text.ParseException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class activity_register extends AppCompatActivity {
    EditText inputUsername, inputEmail, inputPassword, inputConformPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        inputUsername = findViewById(R.id.inputUsername);
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        inputConformPassword = findViewById(R.id.inputConformPassword);
        EditText id_alreadyHaveAccount = findViewById(R.id.inputConformPassword);
        id_alreadyHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity_register.this, Activity_Login.class));
            }
        });
        Button btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Create();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void Create() throws ParseException {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Object> usercall = ApiClient.getUserService().CreateUser(inputUsername.getText().toString(), inputPassword.getText().toString());
        usercall.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response <Object>response) {
                Toast.makeText(activity_register.this, "Created ", Toast.LENGTH_LONG).show();
                startActivity(new Intent(activity_register.this, Activity_Login.class));
            }

            @Override
            public void onFailure(Call <Object>call, Throwable t) {
                Log.e("qqqqqqqqqqqqqq", "onFailure: " + t.getLocalizedMessage());
            }


        });
    }
}