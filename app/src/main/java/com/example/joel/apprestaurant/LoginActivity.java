package com.example.joel.apprestaurant;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.joel.apprestaurant.utils.Data;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpRequest;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_log;
    TextView tv_reg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_log = findViewById(R.id.btn_login);
        btn_log.setOnClickListener(this);

        tv_reg = findViewById(R.id.tvlog_registrarme);
        tv_reg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:{
                //Intent intentRest = new Intent(this,.class);
                //Toast.makeText(this, "Click login", Toast.LENGTH_LONG).show();
                sendDataLogin();
                break;
            }
            case R.id.tvlog_registrarme:{
                Intent intentReg = new Intent(this, NuevaCuentaActivity.class);
                startActivity(intentReg);
                break;
            }
        }

    }
    public void sendDataLogin () {

        TextView email = findViewById(R.id.email_log);
        TextView password = findViewById(R.id.password_log);

        AsyncHttpClient client = new AsyncHttpClient();

        //client.addHeader("authorization", Data.TOKEN);

        RequestParams params = new RequestParams();


        params.add("email", email.getText().toString());
        params.add("password", password.getText().toString());
        //Toast.makeText(this, "Click login", Toast.LENGTH_LONG).show();
        client.post(Data.LOGIN, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response){
                AlertDialog alertDialog = new AlertDialog.Builder(LoginActivity.this).create();
                try {
                    //String msn = response.getString("msn");
                    String tok = response.getString("token");
                    /*alertDialog.setTitle("RESPONSE SERVER");
                    alertDialog.setMessage(msn);
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    alertDialog.show();*/

                    Intent intentRestaurants = new Intent(LoginActivity.this, RestaurantsActivity.class);

                    intentRestaurants.putExtra("token", tok);

                    LoginActivity.this.startActivity(intentRestaurants);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                /*Intent intentRestaurants = new Intent(LoginActivity.this, RestaurantsActivity.class);
                LoginActivity.this.startActivity(intentRestaurants);*/
            }
        });
    }
}

