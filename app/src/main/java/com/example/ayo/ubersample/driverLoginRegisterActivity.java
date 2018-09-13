package com.example.ayo.ubersample;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class driverLoginRegisterActivity extends AppCompatActivity {

    private Button driverLogin;
    private Button driverRegister;
    private TextView driverNotReg;
    private EditText driverEmail;
    private EditText driverPass;
    private FirebaseAuth dAuth;
    private ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_login_register);


        driverLogin = (Button)findViewById(R.id.driverlogin);
        driverRegister = (Button)findViewById(R.id.driverreg);
        driverNotReg = (TextView) findViewById(R.id.drivernotreg);
        driverEmail = (EditText)findViewById(R.id.driveremail);
        driverPass = (EditText)findViewById(R.id.driverpass);
        dAuth = FirebaseAuth.getInstance();
        loading = new ProgressDialog(this);

        driverRegister.setVisibility(View.INVISIBLE);
        driverRegister.setEnabled(false);

        driverNotReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                driverLogin.setVisibility(View.INVISIBLE);
                driverNotReg.setVisibility(View.INVISIBLE);
                driverRegister.setVisibility(View.VISIBLE);
                driverRegister.setEnabled(true);
            }
        });

        driverLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = driverEmail.getText().toString();
                String passw = driverPass.getText().toString();
                loginDriver(mail, passw);
            }
        });

        driverRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = driverEmail.getText().toString();
                String pass = driverPass.getText().toString();

                registerDriver(email, pass);
            }
        });
    }

    private void loginDriver(String mail, String passw) {
        if(TextUtils.isEmpty(mail)){
            Toast.makeText(driverLoginRegisterActivity.this, " emial is empty", Toast.LENGTH_LONG).show();

        }

        if(TextUtils.isEmpty(passw)){
            Toast.makeText(driverLoginRegisterActivity.this, " password is empty", Toast.LENGTH_LONG).show();

        }else{
            loading.setTitle("loading");
            loading.setMessage("please wait");
            loading.show();
            dAuth.signInWithEmailAndPassword(mail, passw)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(driverLoginRegisterActivity.this, " Login Complete", Toast.LENGTH_LONG).show();
                                loading.dismiss();

                                Intent driverGoIntent =  new Intent(driverLoginRegisterActivity.this, driverLandingActivity.class);
                                startActivity(driverGoIntent);

                            }else{
                                Toast.makeText(driverLoginRegisterActivity.this, " Login not complete", Toast.LENGTH_LONG).show();
                                loading.dismiss();
                            }
                        }
                    });
        }
    }

    private void registerDriver(String email, String pass) {

        if(TextUtils.isEmpty(email)){
            Toast.makeText(driverLoginRegisterActivity.this, " the email is empty please input the email", Toast.LENGTH_LONG).show();

        }

        if(TextUtils.isEmpty(pass)){
            Toast.makeText(driverLoginRegisterActivity.this, " the password is empty please input the pass", Toast.LENGTH_LONG).show();

        }else{
            loading.setTitle("loading");
            loading.setMessage("please wait");
            loading.show();
            dAuth.createUserWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(driverLoginRegisterActivity.this, " Registeration Complete", Toast.LENGTH_LONG).show();
                                loading.dismiss();

                                Intent driverGoIntent =  new Intent(driverLoginRegisterActivity.this, driverLandingActivity.class);
                                startActivity(driverGoIntent);

                            }else{
                                Toast.makeText(driverLoginRegisterActivity.this, " registeration not complete", Toast.LENGTH_LONG).show();
                                loading.dismiss();
                            }
                        }
                    });
        }
    }
}
