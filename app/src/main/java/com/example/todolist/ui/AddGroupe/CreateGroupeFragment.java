package com.example.todolist.ui.AddGroupe;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todolist.Beans.UserModel;
import com.example.todolist.R;
import com.example.todolist.Utils.Crendentials;
import com.example.todolist.networking.ApiClient;
import com.example.todolist.networking.ApiInterface;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateGroupeFragment extends Fragment {
    private static final String TAG = "CreateGroupeFragment";
    TextView txt_iduser;
    EditText txt_username, txt_groupename;
    Button btn_rechercher, btn_CréeGroupe;
    public static int iduser;
    public static List<Integer> usersids = new ArrayList<>();
    public static List<String> names = new ArrayList<>();

    public static CreateGroupeFragment newInstance() {
        return new CreateGroupeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_groupe, container, false);
        txt_iduser = view.findViewById(R.id.txt_iduser);
        txt_username = view.findViewById(R.id.txt_username);
        txt_groupename = view.findViewById(R.id.txt_groupename);
        btn_rechercher = view.findViewById(R.id.btn_rechercher);
        btn_CréeGroupe = view.findViewById(R.id.btn_CréeGroupe);
        btn_rechercher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(txt_iduser.getText().toString())) {

                } else {
                    try {
                        Create();
                        // Toast.makeText(getActivity(), "Bien trouver", Toast.LENGTH_LONG).show();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }

        });

        btn_CréeGroupe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(txt_iduser.getText().toString())) {

                } else {
                    try {
                        Create2();
                        // Toast.makeText(getActivity(), "Bien trouver", Toast.LENGTH_LONG).show();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }

        });
        return view;
    }

    public void Create() throws ParseException {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<UserModel> usercall = ApiClient.getUserService().getUserbyusername(txt_username.getText().toString(),Crendentials.getaccess_token());
        usercall.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (response.body() != null) {

                    usersids.add(response.body().getId());
                    Log.d("TAG", usersids.toString());
                    String u = response.body().getUsername();
                    iduser = response.body().getId();
                    names.add(txt_username.getText().toString());
                    txt_iduser.setText("Les membres du Groupe Sont : " + names.toString());
                    txt_username.setText("");
                    Log.e("tagg", names.toString());
                    //txt_username.setText(String.valueOf(iduser));
                    Log.d("TAG", String.valueOf(iduser));
                    Toast.makeText(getActivity(), "bien trouver id user == " + iduser, Toast.LENGTH_LONG).show();


                } else {
                    Toast.makeText(getActivity(), "impossible ", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Log.e("qqqqqqqqqqqqqq", "onFailure: " + t.getLocalizedMessage());
            }


        });

    }

    public void Create2() throws ParseException {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call <Object> usercall = ApiClient.getUserService().CreateGroupe(txt_groupename.getText().toString(),usersids, Crendentials.getaccess_token());
        usercall.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call <Object>call, Response  <Object>response) {
        Log.e("zzzzzzzz","success");
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Toast.makeText(getActivity(), "Groupe Creé ", Toast.LENGTH_LONG).show();
            }


        });
    }
}
