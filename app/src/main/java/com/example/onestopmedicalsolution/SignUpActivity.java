package com.example.onestopmedicalsolution;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    private TextView btnSignUp;
    private EditText edtUserName,edtEmailSignUp,edtPassSignUp,edtConPassSignUp,edtDobSignUp;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btnSignUp = findViewById(R.id.tvSignUp);

        edtUserName = findViewById(R.id.edtUserName);
        edtEmailSignUp = findViewById(R.id.edtEmailSignUp);
        edtPassSignUp = findViewById(R.id.edtPassSignUp);
        edtConPassSignUp = findViewById(R.id.edtConPassSignUp);
        edtDobSignUp = findViewById(R.id.edtDobSignUp);

        auth = FirebaseAuth.getInstance();

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name,email,pass,dob,conPass;

                name = edtUserName.getText().toString();
                email = edtEmailSignUp.getText().toString().trim();
                pass = edtPassSignUp.getText().toString();
                conPass = edtConPassSignUp.getText().toString();
                dob = edtDobSignUp.getText().toString();

                if(name.isEmpty() || email.isEmpty() || pass.isEmpty() || conPass.isEmpty() || dob.isEmpty())
                {
                    Toast.makeText(SignUpActivity.this, "Fill Up all the Fields", Toast.LENGTH_SHORT).show();
                }
                else if(!pass.equals(conPass))
                {
                    Toast.makeText(SignUpActivity.this, "Password is Not Confirmed", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(SignUpActivity.this, "Signed Up Successfully", Toast.LENGTH_SHORT).show();
                                openLoginActivity();
                            }
                            else
                            {
                                Toast.makeText(SignUpActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }



            }
        });

    }

    private void openLoginActivity() {
        super.finish();
    }

}