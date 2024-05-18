package com.example.onestopmedicalsolution;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ManuActivity extends AppCompatActivity {

    private TextView btnEmrCall;
    private TextView btnMakeApp;
    private TextView btnChkHos;
    private TextView btnOrdrMed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manu);

        btnEmrCall = findViewById(R.id.tvEmrCall);
        btnMakeApp = findViewById(R.id.tvMakeApp);
        btnChkHos = findViewById(R.id.tvChkHos);
        btnOrdrMed = findViewById(R.id.tvOrdrMed);

        btnEmrCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCallActivity();
            }
        });

        btnMakeApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAppointmentActivity();
            }
        });

        btnChkHos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHospitalActivity();
            }
        });

        btnOrdrMed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPharmacyFindActivity();
            }
        });
    }

    private void openPharmacyFindActivity() {
        Intent intent = new Intent(this,PharmacyFindActivity.class);
        startActivity(intent);
    }

    private void openHospitalActivity() {
        Intent intent = new Intent(this,HospitalsActivity.class);
        startActivity(intent);
    }

    private void openAppointmentActivity() {
        Intent intent = new Intent(this,ApointmentActivity.class);
        startActivity(intent);
    }

    private void openCallActivity() {
        Intent intent = new Intent(this,CallActivity.class);
        startActivity(intent);
    }
}