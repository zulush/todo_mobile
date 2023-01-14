package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todolist.Beans.GroupModel;
import com.example.todolist.Beans.Task;
import com.example.todolist.Beans.TasksListModel;
import com.example.todolist.Beans.UserModel;
import com.example.todolist.Utils.Crendentials;
import com.example.todolist.networking.ApiClient;
import com.example.todolist.networking.ApiInterface;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailTask extends AppCompatActivity {
    SharedPreferences sp;
    private static final String TAG = "DetaileTask";
    private int task_id;
    TextView txt_iduser ;
    EditText txt_username;
    Button btn_rechercher,btn_affecter,btn_affecter3,btn_done;
    public static int iduser;
    public static List<GroupModel> ss;
    public static  int iditeamselected;
    private String task_name, task_deadline, task_done, task_lastupdatedesc, task_lastupdatedate;
    TextView txt_name, txt_deadline, txt_done, txt_lastupdatedesc, txt_lastupdatedate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_task);
        ///recrupration data

        Intent i = getIntent();
        task_id = i.getIntExtra("task_id", -1);
        Log.i("sabiriiiiiiiiii", String.valueOf(task_id));
        task_name = getIntent().getStringExtra("task_name");
        task_deadline = getIntent().getStringExtra("task_deadline");
        task_done = getIntent().getStringExtra("task_done");
        task_lastupdatedesc = getIntent().getStringExtra("task_lastupdatedesc");
        task_lastupdatedate = getIntent().getStringExtra("task_lastupdatedate");
        txt_iduser = findViewById(R.id.txt_iduser);
        txt_username = findViewById(R.id.txt_username);
        btn_affecter=findViewById(R.id.btn_affecter);
        btn_rechercher=findViewById(R.id.btn_rechercher);
        btn_affecter.setVisibility(View.INVISIBLE);
        btn_affecter3=findViewById(R.id.btn_affecter3);
        btn_done=findViewById(R.id.btn_done);
        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
                Call<Object> usercall = ApiClient.getUserService().makethisTaskDone(task_id,true, Crendentials.getaccess_token());
                usercall.enqueue(new Callback<Object>() {
                    @Override
                    public void onResponse(Call<Object> call, Response<Object> response) {
                        if (response.body() != null) {
                            Toast.makeText(DetailTask.this, "Task Done ", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(DetailTask.this, "Task already Done  ", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Object> call, Throwable t) {
                        Log.e("qqqqqqqqqqqqqq", "onFailure: " + t.getLocalizedMessage());
                        Toast.makeText(DetailTask.this, "Task Done " +txt_username.getText().toString()  , Toast.LENGTH_LONG).show();
                        viderLeschamps();
                        startActivity(new Intent(DetailTask.this, MainActivity.class));
                    }


                });
            }
        });


// Find the Spinner in the layout
        Spinner spinner = findViewById(R.id.spinner);
//Log.i("sabbbbbbbbbbbbbbbbbbbbb",ss.toString());
// Create an ArrayAdapter for the ArrayList
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<GroupModel>> call = apiInterface.GetGroupes(Crendentials.getaccess_token());
        call.enqueue(new Callback<List<GroupModel>>() {
            @Override
            public void onResponse(Call call, Response response) {
                //Log.d(TAG, "onResponse: " + response);
                if(response.body()!=null) {
                    ss = ((List<GroupModel>) response.body());
                    Log.i("jnvfjvndfvngerg", ss.toString());
                    ArrayAdapter<GroupModel> adapter = new ArrayAdapter<GroupModel>(getApplicationContext(), android.R.layout.simple_spinner_item, ss);
// Set the adapter for the Spinner
                spinner.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item
                GroupModel item = (GroupModel) parent.getItemAtPosition(position);

              iditeamselected= item.getId();
Log.i("fhufherhfer", String.valueOf(iditeamselected));
                // Do something with the selected item
                // For example, you can display the selected item in a TextView


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
        btn_affecter3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
                Call<Object> usercall = ApiClient.getUserService().affecteTasktoGroupe(task_id,iditeamselected, Crendentials.getaccess_token());
                usercall.enqueue(new Callback<Object>() {
                    @Override
                    public void onResponse(Call<Object> call, Response<Object> response) {
                        if (response.body() != null) {
                            Toast.makeText(DetailTask.this, "GOOD ", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(DetailTask.this, "Cette est deja affecter  ", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Object> call, Throwable t) {
                        Log.e("qqqqqqqqqqqqqq", "onFailure: " + t.getLocalizedMessage());
                        Toast.makeText(DetailTask.this, "Cette tache  a ete affecter a " +txt_username.getText().toString()  , Toast.LENGTH_LONG).show();
                        viderLeschamps();
                        startActivity(new Intent(DetailTask.this, MainActivity.class));
                    }


                });
            }
        });
        try {
            getgroups();
        } catch (ParseException e) {
            e.printStackTrace();
        }
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

        btn_affecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(txt_iduser.getText().toString()) ) {
                    Toast.makeText(DetailTask.this, "Veillez saisir les 2 Champs", Toast.LENGTH_LONG).show();
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
        Log.i(TAG, task_name);




        txt_name = findViewById(R.id.txt_name);
        txt_deadline = findViewById(R.id.txt_deadline);
        txt_done = findViewById(R.id.txt_sharedwith);
        txt_lastupdatedesc = findViewById(R.id.txt_lastupdatedesc);
        txt_lastupdatedate = findViewById(R.id.txt_lastupdatedate);


        txt_name.setText("name : " + task_name);
        txt_deadline.setText("deadline : " + task_deadline);
        txt_done.setText("done : " + task_done);
        txt_lastupdatedesc.setText("lastupdatedesc : " + task_lastupdatedesc);
        txt_lastupdatedate.setText("lastupdatedate  : " + task_lastupdatedate);



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
                    txt_iduser.setText(txt_username.getText().toString()+"  est trouver");
                    //txt_username.setText(String.valueOf(iduser));
                    Log.d("TAG", String.valueOf(iduser));
                    Toast.makeText(DetailTask.this, "bien trouver id user == " + iduser , Toast.LENGTH_LONG).show();
                    txt_username.setVisibility(View.INVISIBLE);
                    btn_rechercher.setVisibility(View.INVISIBLE);
                    btn_affecter.setVisibility(View.VISIBLE);

                } else {
                    Toast.makeText(DetailTask.this, "impossible ", Toast.LENGTH_LONG).show();
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
        Call<Task> usercall = ApiClient.getUserService().AffecterTaskaunuser(task_id,iduser, Crendentials.getaccess_token());
        usercall.enqueue(new Callback<Task>() {
            @Override
            public void onResponse(Call<Task> call, Response<Task> response) {
                if (response.body() != null) {
                    Toast.makeText(DetailTask.this, "GOOD ", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(DetailTask.this, "Cette est deja affecter  ", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Task> call, Throwable t) {
                Log.e("qqqqqqqqqqqqqq", "onFailure: " + t.getLocalizedMessage());
                Toast.makeText(DetailTask.this, "Cette tache  a ete affecter a " +txt_username.getText().toString()  , Toast.LENGTH_LONG).show();
                viderLeschamps();
                startActivity(new Intent(DetailTask.this, MainActivity.class));
            }


        });

    }
    public void viderLeschamps() {

        txt_iduser.setText("Recherher user by username");
        txt_username.setText("");

    }

    public void getgroups() throws ParseException {


        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<TasksListModel> usercall = ApiClient.getUserService().getTaskSpared(Crendentials.getaccess_token());
        usercall.enqueue(new Callback<TasksListModel>() {
            @Override
            public void onResponse(Call<TasksListModel> call, Response<TasksListModel> response) {
                Log.i("getTaskSpared",response.body().toString());
            }

            @Override
            public void onFailure(Call<TasksListModel> call, Throwable t) {

            }


        });

    }

}