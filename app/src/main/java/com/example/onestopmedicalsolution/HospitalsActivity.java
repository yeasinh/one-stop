package com.example.onestopmedicalsolution;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HospitalsActivity extends AppCompatActivity implements HospitalAdapter.ItemClicked {


    RecyclerView recyclerView;
    RecyclerView.Adapter myAdapter;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference myRef;

    ArrayList<Hospital> hospitals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospitals);

        recyclerView = findViewById(R.id.listHospitals);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        ProgressDialog dialog = ProgressDialog.show(this, "Loading", "Please wait ...", true);

        hospitals = new ArrayList<Hospital>();

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Hospitals");

        myAdapter = new HospitalAdapter(this,hospitals);
        recyclerView.setAdapter(myAdapter);

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                hospitals.clear();

                for(DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    Hospital value = snapshot.getValue(Hospital.class);
                    hospitals.add(value);

                }


                myAdapter.notifyDataSetChanged();


                //Log.d(TAG, "Value is: " + value);
                dialog.dismiss();

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
               // Log.w(TAG, "Failed to read value.", error.toException());
                dialog.dismiss();
                Toast.makeText(HospitalsActivity.this, error.toException().toString(), Toast.LENGTH_SHORT).show();
            }
        });



        // for updating the listView myAdapter.notifyDataSetChanged();



    }


    @Override
    public void onItemClicked(int index) {

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q="+hospitals.get(index).getName()));
        startActivity(intent);
    }
}