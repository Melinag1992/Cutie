package com.example.melg.tracked.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.melg.tracked.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;

/**
 * Created by melg on 6/2/18.
 */

public class PhoneAuth extends AppCompatActivity{

    private static final String KEY_VERIFY_IN_PROGRESS = "key_verify_in_progress";


    private CountryCodePicker countryCode;

    private EditText userPhoneNumber;
    private Button sendSMSButton;
    private String TAG = "phone verifying";
    private FirebaseAuth fbAuth;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            verificationCallbacks;
    private String phoneVerificationId;
    private String codeSent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phoneverify);
        init();


    }

    public void init() {

        userPhoneNumber = findViewById(R.id.phone_number);
        countryCode = findViewById(R.id.ccp);
        sendSMSButton = findViewById(R.id.send_sms_button);
        fbAuth = FirebaseAuth.getInstance();

        sendSMSButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendVerificationCode();
            }
        });


    }

    public void sendVerificationCode() {


// adds countrycode to the number typed in edittext => 1 + 3467776242
        countryCode.registerCarrierNumberEditText(userPhoneNumber);
        String phoneNumber = countryCode.getFullNumberWithPlus(); // => 13467776242
        Toast.makeText(this, phoneNumber, Toast.LENGTH_SHORT).show();
        if (phoneNumber.length() <= 1) {
            userPhoneNumber.setError("Phone Number is required");
            userPhoneNumber.requestFocus();
            return;
        }
        if (phoneNumber.length() < 10) {
            userPhoneNumber.setError("Please enter a valid Phone Number");
            userPhoneNumber.requestFocus();
            return;

        }
        // OnVerificationStateChangedCallbacks

        Intent intent = new Intent(this, SMSCodeVerificationActivity.class);
        intent.putExtra("phonenumber",phoneNumber);
        startActivity(intent);
    }



}
