package com.example.spendify;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

import java.security.ProtectionDomain;

public class registrationPage extends AppCompatActivity {

    private EditText mEmail, mPass;
    private Button btnReg;
    private TextView mSignin;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);
        registration();
    }

    private void registration() {
        mEmail=findViewById(R.id.email_reg);
        mPass=findViewById(R.id.password_reg);
        btnReg=findViewById(R.id.btn_reg);
        mSignin=findViewById(R.id.signIn);
        mAuth=FirebaseAuth.getInstance();
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=mEmail.getText().toString();
                String password=mPass.getText().toString();
                if(email.isEmpty()){
                    mEmail.setError("Invalid Email");
                    mEmail.requestFocus();
                    return;
                }
                if(password.isEmpty()){
                    mPass.setError("Invalid Password");
                    mPass.requestFocus();
                    return;
                }
                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Log.d(TAG, "signIn Success");
                            startActivity(new Intent(registrationPage.this,Home.class));

                        }
                        else{
                            Log.d(TAG,"Failed");
                        }
                    }
                });
            }
        });
    }
}