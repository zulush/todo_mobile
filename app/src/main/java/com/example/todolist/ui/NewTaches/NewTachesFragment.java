package com.example.todolist.ui.NewTaches;

import androidx.appcompat.app.AlertDialog;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.todolist.Beans.Task;
import com.example.todolist.DetailTask;
import com.example.todolist.MainActivity;
import com.example.todolist.R;
import com.example.todolist.Utils.Crendentials;
import com.example.todolist.networking.ApiClient;
import com.example.todolist.networking.ApiInterface;
import com.example.todolist.ui.TodoList.TachesFragment;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewTachesFragment extends Fragment {
    AlertDialog.Builder builder;
    EditText txt_name,txt_date;
    Button btn_add;
    DatePickerDialog.OnDateSetListener setListener;

    public static NewTachesFragment newInstance()  {
        return new NewTachesFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_newtaches, container, false);


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
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                ;
                String date = year + "/" + month + "/" + day;
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
                        Create();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    viderLeschamps();
                    Toast.makeText(getActivity(), "Bien enregistrer", Toast.LENGTH_LONG).show();


                }
            }
        });

        return view;
    }
    public void Create() throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("mm/dd/yyyy");
        String dateInString = txt_date.getText().toString();
        Date d = (Date)formatter.parse(dateInString);

        System.out.println(d);


        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Task> livraisonCall = ApiClient.getUserService().createTask(txt_name.getText().toString(),dateInString, Crendentials.getaccess_token());
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

}