package com.example.onestopmedicalsolution;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private TextView btnSignUp;
    private TextView btnLogin;
    private TextView btnForgotPass;
    private EditText edtEmail,edtPass;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSignUp = findViewById(R.id.tvSignIN);
        btnLogin = findViewById(R.id.tvLogin);
        btnForgotPass = findViewById(R.id.tvForgotPass);
        edtEmail = findViewById(R.id.edtEmail);
        edtPass = findViewById(R.id.edtPass);

        auth = FirebaseAuth.getInstance();

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignUpActivity();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog dialog = ProgressDialog.show(MainActivity.this, "Loading", "Please wait ...", true);
                String email,pass;

                email = edtEmail.getText().toString().trim();
                pass = edtPass.getText().toString();

                if(email.isEmpty() || pass.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Fill Up all the Fields", Toast.LENGTH_SHORT).show();
                }
                else {

                    auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(MainActivity.this, "Logged IN", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                                openManuActivity();
                            }
                            else
                            {
                                dialog.dismiss();
                                Toast.makeText(MainActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }


            }
        });

        btnForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email;
                email = edtEmail.getText().toString().trim();

                if(email.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Please provide an Email", Toast.LENGTH_SHORT).show();
                }
                else {

                    auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "A Reset Password Email has been sent", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(MainActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }

            }
        });


    }



    private void openManuActivity() {
        Intent intent = new Intent(this,ManuActivity.class);
        startActivity(intent);
    }

    private void openSignUpActivity() {
        Intent intent = new Intent(this,SignUpActivity.class);
        startActivity(intent);
    }

}