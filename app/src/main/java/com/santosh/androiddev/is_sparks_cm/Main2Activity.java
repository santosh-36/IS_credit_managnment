package com.santosh.androiddev.is_sparks_cm;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Main2Activity extends AppCompatActivity {

    String id;
    String URL_DATA ;
    ImageView iv;
    TextView tv1,tv2,tv3,tv4;
    EditText et1,et2;
    Button b;
    private View alertView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        id = getIntent().getStringExtra("id");
        URL_DATA = "http://santosh36.ga/internship/androidact.php?id="+id;
        ini();
        loadRecyclerViewData();
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sendmoney()==1){
                    return ;
                }
                URL_DATA = "http://santosh36.ga/internship/androiddotrans.php?fromid="+id+"&toid="+et1.getText().toString()+"&credit="+et2.getText().toString();
                final ProgressDialog progressDialog = new ProgressDialog(getApplicationContext());
                progressDialog.setMessage("Transaction under process ...");
                Toast.makeText(getApplicationContext(),"Transaction successful.",Toast.LENGTH_LONG).show();
                StringRequest stringRequest = new StringRequest(Request.Method.GET,
                        URL_DATA,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                progressDialog.dismiss();
                                try {
                                    JSONArray jsonArray  = new JSONArray(response);
                                    for(int i=0;i<jsonArray.length();i++){
                                        JSONObject o = jsonArray.getJSONObject(i);
                                        //todo : add alert of some sort
                                        Toast.makeText(getApplicationContext(),o.getString("status"),Toast.LENGTH_LONG).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);
                startActivity(new Intent(Main2Activity.this,MainActivity.class));

            }
        });
    }

    private int sendmoney() {
        if (TextUtils.isEmpty(et1.getText().toString())) {
            et1.setError("User id is needed to transfer credit !");
            return 1;
        }
        if (TextUtils.isEmpty(et2.getText().toString())) {
            et2.setError("Credit amount is needed to transfer!");
            return 1;
        }
        return 0;
    }

    private void ini() {
        iv = findViewById(R.id.iv);
        tv1= findViewById(R.id.tv_id);
        tv2= findViewById(R.id.tv_name);
        tv3= findViewById(R.id.tv_phone);
        tv4= findViewById(R.id.tv_credit);
        et1= findViewById(R.id.et1);
        et2= findViewById(R.id.et2);
        b=findViewById(R.id.button);
    }

    private void loadRecyclerViewData() {
        final ProgressDialog progressDialog = new ProgressDialog(getApplicationContext());
        progressDialog.setMessage("Loading data . . .");
        //progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONArray jsonArray  = new JSONArray(response);
                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject o = jsonArray.getJSONObject(i);
                                tv1.setText("Name : "+o.getString("name"));
                                tv2.setText("Email  : "+o.getString("email"));
                                tv3.setText("Phone : "+o.getString("phone"));
                                tv4.setText("Credit : "+o.getString("credit"));
                                Picasso.with(getApplicationContext()).load(o.getString("image")).into(iv);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

}
