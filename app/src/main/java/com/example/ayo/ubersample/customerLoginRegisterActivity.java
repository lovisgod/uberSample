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

public class customerLoginRegisterActivity extends AppCompatActivity {

    private Button customerLogin;
    private Button customerRegister;
    private TextView notreg;
    private EditText customerEmail;
    private EditText customerPass;
    private FirebaseAuth mAuth;
    private ProgressDialog loadingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login_register);

        customerLogin = (Button) findViewById(R.id.customerlogin);
        customerRegister = (Button) findViewById(R.id.custumerreg);
        notreg = (TextView) findViewById(R.id.customernotreg);
        customerEmail = (EditText) findViewById(R.id.customeremail);
        customerPass = (EditText) findViewById(R.id.customerpass);
        mAuth = FirebaseAuth.getInstance();
        loadingBar = new ProgressDialog(this);

        customerRegister.setVisibility(View.INVISIBLE);
        customerRegister.setEnabled(false);

        notreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                customerLogin.setVisibility(View.INVISIBLE);
                notreg.setVisibility(View.INVISIBLE);
                customerRegister.setVisibility(View.VISIBLE);
                customerRegister.setEnabled(true);
            }
        });

        customerLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = customerEmail.getText().toString();
                String passw = customerPass.getText().toString();
                loginCustomer(mail,passw);
            }
        });

        customerRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = customerEmail.getText().toString();
                String pass = customerPass.getText().toString();

                registerDriver(email, pass);
            }
        });
    }

    private void loginCustomer(String mail, String passw) {
        if(TextUtils.isEmpty(mail)){
            Toast.makeText(customerLoginRegisterActivity.this,"email is empty ", Toast.LENGTH_LONG).show();
        }

        if (TextUtils.isEmpty(passw)) {
            Toast.makeText(customerLoginRegisterActivity.this, " the password is empty please input the pass", Toast.LENGTH_LONG).show();
        }else{
            loadingBar.setTitle("Login in");
            loadingBar.setMessage("Login Ongoing");
            loadingBar.show();

            mAuth.createUserWithEmailAndPassword(mail, passw)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){

                                Toast.makeText(customerLoginRegisterActivity.this, "login complete and successful ", Toast.LENGTH_LONG).show();
                                loadingBar.dismiss();
                                Intent loginIntent = new Intent(customerLoginRegisterActivity.this, customerLandingActivity.class);
                                startActivity(loginIntent);
                            }else {

                                Toast.makeText(customerLoginRegisterActivity.this, "login   not complete and successful ", Toast.LENGTH_LONG).show();
                                loadingBar.dismiss();
                            }

                        }
                    });
        }
    }

    private void registerDriver(String email, String pass) {

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(customerLoginRegisterActivity.this, " the email is empty please input the email", Toast.LENGTH_LONG).show();

        }

        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(customerLoginRegisterActivity.this, " the password is empty please input the pass", Toast.LENGTH_LONG).show();
        }else{
            loadingBar.setTitle("customer registration");
            loadingBar.setMessage("Registeration Ongoing");
            loadingBar.show();

           mAuth.createUserWithEmailAndPassword(email, pass)
                   .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){

                                Toast.makeText(customerLoginRegisterActivity.this, "customer registration complete and successful ", Toast.LENGTH_LONG).show();
                                loadingBar.dismiss();
                                Intent loginIntent = new Intent(customerLoginRegisterActivity.this, customerLandingActivity.class);
                                startActivity(loginIntent);
                            }else {

                                Toast.makeText(customerLoginRegisterActivity.this, "customer registration  not complete and successful ", Toast.LENGTH_LONG).show();
                                loadingBar.dismiss();
                            }

                       }
                   });
        }
    }
}
