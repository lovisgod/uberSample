package com.example.ayo.ubersample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class landingActivity extends AppCompatActivity {

    private Button customerButton;
    private Button driverButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        customerButton = (Button)findViewById(R.id.landing_customerbtn);
        driverButton = (Button)findViewById(R.id.landing_driverbtn);

        customerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent customerIntent = new Intent(landingActivity.this, customerLoginRegisterActivity.class);
                startActivity(customerIntent);
            }
        });

        driverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent driverIntent = new Intent(landingActivity.this, driverLoginRegisterActivity.class);
                startActivity(driverIntent);
            }
        });


    }
}
