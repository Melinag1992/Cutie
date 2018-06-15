package com.example.melg.trackit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText trackingNumber_input;
    private Button trackItButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        init();




    }

    private void init() {

      trackingNumber_input =  findViewById(R.id.tracking_number);
      trackItButton = findViewById(R.id.trackit_btn);

    }

    public void onClick(View view){

        Intent intent  = new Intent(MainActivity.this, BluetoothConnectionService.class);
        intent.putExtra("tracking_number", trackingNumber_input.getText().toString());
        startActivity(intent);
    }
}