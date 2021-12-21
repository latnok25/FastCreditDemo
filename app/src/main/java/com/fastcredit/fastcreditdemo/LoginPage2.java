package com.fastcredit.fastcreditdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class LoginPage2 extends AppCompatActivity {
    Button button;
    ImageView imageView;
    EditText editText,editText2;
    CheckBox checkBox;
    StringRequest mStringRequest;
    ArrayList<String> ar = new ArrayList<String>();
    JsonArrayRequest objectRequest;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page2);
        hideSystemUI();
        requestQueue = Volley.newRequestQueue(this.getApplicationContext());
        sendAndRequestResponse();

        button = findViewById(R.id.btn);
        editText = findViewById(R.id.email);
        editText2 = findViewById(R.id.pass);
        imageView = findViewById(R.id.back);
        checkBox = findViewById(R.id.check);




        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginPage2.this, LoginPagee.class);
                startActivity(intent);
                //overridePendingTransition(R.anim.fadeinfast, R.anim.fadeoutfast);
                finish();
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(editText.getText().toString())){
                    View view = (View) findViewById(R.id.cord);
                    final Snackbar snackbar = Snackbar.make(view, "Kindly input your email", Snackbar.LENGTH_INDEFINITE);
                    snackbar.getView().setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                    snackbar.setTextColor(getResources().getColor(android.R.color.white));
                    snackbar.setActionTextColor(getResources().getColor(android.R.color.white));
                    snackbar.show();
                    snackbar.setAction("Dismiss", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            snackbar.dismiss();
                        }
                    });

                }

                else if(TextUtils.isEmpty(editText2.getText().toString())){
                    View view = (View) findViewById(R.id.cord);
                    final Snackbar snackbar = Snackbar.make(view, "Kindly input your password", Snackbar.LENGTH_INDEFINITE);
                    snackbar.getView().setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                    snackbar.setTextColor(getResources().getColor(android.R.color.white));
                    snackbar.setActionTextColor(getResources().getColor(android.R.color.white));
                    snackbar.show();
                    snackbar.setAction("Dismiss", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            snackbar.dismiss();
                        }
                    });

                }
                else if(ar.contains(editText.getText().toString())){
                    if(editText2.getText().toString().equals("password")){
                        Intent intent = new Intent(LoginPage2.this, MainActivity.class);
                        startActivity(intent);
                        //overridePendingTransition(R.anim.fadeinfast, R.anim.fadeoutfast);
                        finish();

                    }else{
                        View view = (View) findViewById(R.id.cord);
                        final Snackbar snackbar = Snackbar.make(view, "Wrong Password", Snackbar.LENGTH_INDEFINITE);
                        snackbar.getView().setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                        snackbar.setActionTextColor(getResources().getColor(android.R.color.white));
                        snackbar.setTextColor(getResources().getColor(android.R.color.white));
                        snackbar.show();
                        snackbar.setAction("Dismiss", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                snackbar.dismiss();
                            }
                        });
                    }
                }
                else{
                    View view = (View) findViewById(R.id.cord);
                    final Snackbar snackbar = Snackbar.make(view, "Wrong Email", Snackbar.LENGTH_INDEFINITE);
                    snackbar.getView().setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                    snackbar.setActionTextColor(getResources().getColor(android.R.color.white));
                    snackbar.setTextColor(getResources().getColor(android.R.color.white));
                    snackbar.show();
                    snackbar.setAction("Dismiss", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            snackbar.dismiss();
                        }
                    });
                }

            }
        });
    }

    private void sendAndRequestResponse() {

        findViewById(R.id.progress).setVisibility(View.VISIBLE);
//        //findViewById(R.id.progress).setVisibility(View.GONE);
//        findViewById(R.id.fab).setEnabled(false);
        findViewById(R.id.back).setEnabled(false);

        //SharedPreferences prefs = getSharedPreferences("venom", MODE_PRIVATE);
        //String restoredText = prefs.getString("text", null);
        //String bvvn = prefs.getString("bvvn", "222222222222");

        requestQueue = Volley.newRequestQueue(this);

        String GET_URL = "https://jsonplaceholder.typicode.com/users";


        objectRequest = new JsonArrayRequest(Request.Method.GET, GET_URL,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                findViewById(R.id.progress).setVisibility(View.GONE);

                findViewById(R.id.back).setEnabled(true);
                try {
                    //ten();
                   ///JSONArray array = new JSONArray(response);

                    for (int i = 0; i < response.length(); i++) {


                        JSONObject obj = response.getJSONObject(i);
                        String s = obj.getString("email");

                        ar.add(s);
                        // Model model = new Model();

                    }




                } catch (JSONException f) {
                    f.printStackTrace();
                    View view = (View) findViewById(R.id.cord);
                    final Snackbar snackbar = Snackbar.make(view, "Something went wrong please try later", Snackbar.LENGTH_INDEFINITE);
                    snackbar.getView().setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                    snackbar.setActionTextColor(getResources().getColor(android.R.color.white));
                    snackbar.setTextColor(getResources().getColor(android.R.color.white));
                    snackbar.show();
                    snackbar.setAction("Dismiss", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            snackbar.dismiss();
                        }
                    });
                }
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof TimeoutError){
                    mStringRequest.setRetryPolicy(new DefaultRetryPolicy(
                            1500,
                            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                    // requestQueue.add(mStringRequest);
                }

                else if (error instanceof NoConnectionError){
                    findViewById(R.id.progress).setVisibility(View.GONE);
                    findViewById(R.id.progress).setVisibility(View.GONE);
                    //findViewById(R.id.fab).setEnabled(true);
                    findViewById(R.id.back).setEnabled(true);
                    View view = (View) findViewById(R.id.cord);
                    final Snackbar snackbar = Snackbar.make(view, "No Connection Available", Snackbar.LENGTH_INDEFINITE);
                    snackbar.getView().setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                    snackbar.setActionTextColor(getResources().getColor(android.R.color.white));
                    snackbar.setTextColor(getResources().getColor(android.R.color.white));
                    snackbar.show();
                    snackbar.setAction("Dismiss", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            snackbar.dismiss();
                        }
                    });
                }
                else {
                    findViewById(R.id.progress).setVisibility(View.GONE);
                    findViewById(R.id.progress).setVisibility(View.GONE);
                    //findViewById(R.id.fab).setEnabled(true);
                    findViewById(R.id.back).setEnabled(true);
                    View view = (View) findViewById(R.id.cord);
                    final Snackbar snackbar = Snackbar.make(view, "Something went wrong, Try Again", Snackbar.LENGTH_INDEFINITE);
                    snackbar.getView().setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                    snackbar.setActionTextColor(getResources().getColor(android.R.color.white));
                    snackbar.setTextColor(getResources().getColor(android.R.color.white));
                    snackbar.show();
                    snackbar.setAction("Dismiss", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            snackbar.dismiss();
                        }
                    });
                    // Log.d("Error", String.valueOf(error));
                }
            }
        });
        objectRequest.setTag("Rewards");
        requestQueue.add(objectRequest);
    }

    @Override
    protected void onResume() {
        super.onResume();
        hideSystemUI();
    }

    @Override
    protected void onStart() {
        super.onResume();
        hideSystemUI();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        hideSystemUI();
    }

    private void hideSystemUI() {
        // Set the IMMERSIVE flag.
        // Set the content to appear under the system bars so that the content
        // doesn't resize when the system bars hide and show.
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);

    }

//    public void GoTo(View view) {
//        try {
//            Intent intent = new Intent(LoginPage2.this, MainActivity.class);
//            startActivity(intent);
//            //overridePendingTransition(R.anim.fadeinfast, R.anim.fadeoutfast);
//            finish();
//        }catch (Exception e){
//
//        }
//
//    }
}