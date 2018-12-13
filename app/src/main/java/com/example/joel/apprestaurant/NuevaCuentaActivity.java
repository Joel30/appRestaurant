package com.example.joel.apprestaurant;

import android.content.DialogInterface;
import android.content.Intent;
import android.preference.DialogPreference;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.joel.apprestaurant.utils.Data;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class NuevaCuentaActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_reg;
    TextView tv_log;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_cuenta);

        btn_reg = findViewById(R.id.btnnc_registrar);
        btn_reg.setOnClickListener(this);

        tv_log = findViewById(R.id.tvnc_iniciarSes);
        tv_log.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnnc_registrar: {
                sendDataClient();
                //Toast.makeText(this, "Click registrar",Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.tvnc_iniciarSes: {
                Intent intentLog = new Intent(this, LoginActivity.class);
                startActivity(intentLog);
                finish();
                break;
            }
        }
    }

    public void sendDataClient () {


        TextView ci = findViewById(R.id.nc_ci);
        TextView name = findViewById(R.id.nc_nombre);
        TextView phone = findViewById(R.id.nc_nrocel);
        TextView email = findViewById(R.id.nc_email);
        TextView password = findViewById(R.id.nc_password);

        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("authorization", Data.TOKEN);

        RequestParams params = new RequestParams();

        params.add("name", name.getText().toString());
        params.add("email", email.getText().toString());
        params.add("phone", phone.getText().toString());
        params.add("ci", ci.getText().toString());
        params.add("password", password.getText().toString());

        client.post(Data.REGISTER_CLIENT, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response){
                AlertDialog alertDialog = new AlertDialog.Builder(NuevaCuentaActivity.this).create();
                try {
                    String msn = response.getString("msn");
                    /*alertDialog.setTitle("RESPONSE SERVER");
                    alertDialog.setMessage(msn);
                    /*alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });*/
                    //alertDialog.show();
                    Intent intentRestaurants = new Intent(NuevaCuentaActivity.this, RestaurantsActivity.class);
                    NuevaCuentaActivity.this.startActivity(intentRestaurants);
                    //Toast.makeText(NuevaCuentaActivity.this, msn,Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
