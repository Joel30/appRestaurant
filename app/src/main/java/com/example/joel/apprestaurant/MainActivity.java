package com.example.joel.apprestaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_iniSes;
    TextView tv_maReg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_iniSes = findViewById(R.id.btn_iniciarSes);
        btn_iniSes.setOnClickListener(this);

        tv_maReg = findViewById(R.id.tvma_registrarme);
        tv_maReg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_iniciarSes:{
                Intent intentLogin = new Intent(this, LoginActivity.class);
                startActivity(intentLogin);
                Toast.makeText(this, "Click btn", Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.tvma_registrarme: {
                Intent intentRegUs = new Intent(this, NuevaCuentaActivity.class);
                startActivity(intentRegUs);
                break;
            }
        }

    }
}
