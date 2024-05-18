package com.example.onestopmedicalsolution;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PharmacyFindActivity extends AppCompatActivity {

    private TextView btnTstBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacy_find);

        btnTstBtn = findViewById(R.id.tvTstBtn);

        btnTstBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOrderActivity();
            }
        });
    }

    private void openOrderActivity() {
        Intent intent = new Intent(this,OrderActivity.class);
        startActivity(intent);
    }
}