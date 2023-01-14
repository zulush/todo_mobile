package com.example.todolist.ui.NewTacheForuser;

import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todolist.Activity_Login;
import com.example.todolist.Beans.Task;
import com.example.todolist.Beans.UserModel;
import com.example.todolist.DetailTask;
import com.example.todolist.MainActivity;
import com.example.todolist.R;
import com.example.todolist.Utils.Crendentials;
import com.example.todolist.networking.ApiClient;
import com.example.todolist.networking.ApiInterface;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewTacheForUserFragment extends Fragment {
    private static final String TAG = "NewTacheForUserFragment";
    TextView txt_iduser ;
    EditText txt_username;
    Button btn_rechercher,btn_affecter;
    public static int iduser;
    EditText txt_name,txt_date;
    Button btn_add;
    DatePickerDialog.OnDateSetListener setListener;

    public static NewTacheForUserFragment newInstance() {
        return new NewTacheForUserFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_tache_for_user_, container, false);
        txt_iduser = view.findViewById(R.id.txt_iduser);
        txt_username = view.findViewById(R.id.txt_username);
        btn_rechercher=view.findViewById(R.id.btn_rechercher);
        btn_rechercher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(txt_iduser.getText().toString()) ) {

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

        txt_name = view.findViewById(R.id.txt_name);
        txt_date = view.findViewById(R.id.txt_date);
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);


        txt_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), android.R.style.Theme_Holo_Light_Dialog_MinWidth, setListener, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });
        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                ;
                String date = i + "/" + i1 + "/" + i2;
                txt_date.setText(date);
            }
        };
        btn_add = view.findViewById(R.id.btn_add);
        ///////////////////////////////////////////


        /////////////////////////////////////////////////
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(txt_name.getText().toString()) || TextUtils.isEmpty(txt_date.getText().toString()) ) {
                    Toast.makeText(getActivity(), "Veillez remplir toutes les champs", Toast.LENGTH_LONG).show();
                } else {
                    try {
                        Create3();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    viderLeschamps();
                    Toast.makeText(getActivity(), "Bien enregistrer", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getActivity(), MainActivity.class));


                }
            }
        });
        return view;
    }

    public void Create3() throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("mm/dd/yyyy");
        String dateInString = txt_date.getText().toString();
        Date d = (Date)formatter.parse(dateInString);

        System.out.println(d);


        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Task> livraisonCall = ApiClient.getUserService().createTaskforUser(txt_name.getText().toString(),dateInString,iduser, Crendentials.getaccess_token());
        livraisonCall.enqueue(new Callback<Task>() {
            @Override
            public void onResponse(Call<Task> call, Response<Task> response) {
                if (response.body() != null) {
                    Log.d("TAG", response.body().toString());
                    startActivity(new Intent(getActivity(), MainActivity.class));
                } else {
                    Toast.makeText(getActivity(), "Login Failed ", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Task> call, Throwable t) {
                Log.e("qqqqqqqqqqqqqq", "onFailure: " + t.getLocalizedMessage());
            }


        });

    }
    public void viderLeschamps() {
        txt_name.setText("");
        txt_date.setText("");


    }

    public void Create() throws ParseException {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<UserModel> usercall = ApiClient.getUserService().getUserbyusername(txt_username.getText().toString(),Crendentials.getaccess_token());
        usercall.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (response.body() != null) {
                    Log.d("TAG", response.body().toString());
                    String u = response.body().getUsername();
                    iduser = response.body().getId();
                    txt_iduser.setText(txt_username.getText().toString() + "  est trouver");
                    //txt_username.setText(String.valueOf(iduser));
                    Log.d("TAG", String.valueOf(iduser));
                    Toast.makeText(getActivity(), "bien trouver id user == " + iduser, Toast.LENGTH_LONG).show();
                    txt_username.setVisibility(View.INVISIBLE);
                    btn_rechercher.setVisibility(View.INVISIBLE);


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
}