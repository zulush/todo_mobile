package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todolist.Beans.LoginResponse;
import com.example.todolist.networking.ApiClient;
import com.example.todolist.networking.ApiInterface;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_Login extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private EditText username;
    private EditText password;
    private TextView Signupbtn;
    public static SharedPreferences sharref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharref = getSharedPreferences("access_token", Context.MODE_PRIVATE);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        Signupbtn = findViewById(R.id.Signupbtn);
        Signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Activity_Login.this, activity_register.class));
            }
        });
        Button loginBtn = findViewById(R.id.btnlogin);

    loginBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //Intent intent = new Intent(LoginActivity.this, MainActivity.class );
            //startActivity(intent);

            if (TextUtils.isEmpty(username.getText().toString()) || TextUtils.isEmpty(password.getText().toString())) {
                Toast.makeText(Activity_Login.this, "the username or password is incorrect", Toast.LENGTH_LONG).show();
            } else {
               login();
            }
        }
    });

    }

        public void login() {
            Map<String, String> userdata = new HashMap<>();

            userdata.put("username", username.getText().toString());
            userdata.put("password", password.getText().toString());
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<LoginResponse> loginResponseCall = ApiClient.getUserService().Authentifcate(userdata);
            loginResponseCall.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    if (response.body() != null) {
                        Log.d(TAG, response.body().toString());
                        // AppUser u = response.body().ge
                        String accessToken = "Bearer " + response.body().getAccess_token();
                        SharedPreferences.Editor myEditor = sharref.edit();
                        myEditor.putString("access_token", accessToken);
                        myEditor.commit();
                        Log.i("access_token", accessToken);

                    }
                    if (response.isSuccessful()) {
                        LoginResponse loginResponse = response.body();
                        Toast.makeText(Activity_Login.this, "Bienvenu", Toast.LENGTH_LONG).show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                startActivity(new Intent(Activity_Login.this, MainActivity.class).putExtra("access_token", loginResponse.getAccess_token()));
                                //Toast.makeText(LoginActivity.this, response.body().getToken(), Toast.LENGTH_SHORT).show();
                                //token = response.body().getToken();
                            }
                        }, 700);

                    } else {
                        Toast.makeText(Activity_Login.this, "the username or password is incorrect ", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Toast.makeText(Activity_Login.this, "Throwable " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }
            });

        }

        }

