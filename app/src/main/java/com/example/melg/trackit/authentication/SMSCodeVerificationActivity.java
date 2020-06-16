package com.example.melg.trackit.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.melg.trackit.MainActivity;
import com.example.melg.trackit.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

/**
 * Created by melg on 6/2/18.
 */

public class SMSCodeVerificationActivity  extends AppCompatActivity{
    private EditText code;
    private Button verifyButton;
    private String givenSMSCode;
    private String TAG = "smspage ===== ";
    private FirebaseAuth mAuth;

    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks verificationCallbacks;
    private String codeSent;
    private String phoneNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smscode_verification);


        if (savedInstanceState != null) {
            onRestoreInstanceState(savedInstanceState);
        }

        phoneNumber = getIntent().getExtras().getString("phonenumber");

        Log.d(TAG, "onCreate: " + phoneNumber);
        mAuth = FirebaseAuth.getInstance();

        code = findViewById(R.id.code);
        verifyButton = findViewById(R.id.verify_button);


        sendVerificationCode();

        verifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                givenSMSCode = code.getText().toString();
                if (TextUtils.isEmpty(givenSMSCode)) {
                    code.setError("Cannot be empty.");
                    return;
                }
                verifyPhoneNumberWithCode(codeSent, code.getText().toString());
            }
        });

    }

    private void verifyPhoneNumberWithCode(String verificationId, String code) {
        // [START verify_with_code]
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeSent, code);
        // [END verify_with_code]
        startPhoneAuthProcess(credential);
    }

    public void startPhoneAuthProcess(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");

                            FirebaseUser user = task.getResult().getUser();
                            startActivity(new Intent(SMSCodeVerificationActivity.this, MainActivity.class));

                            Toast.makeText(SMSCodeVerificationActivity.this, "Signed in with SMS", Toast.LENGTH_SHORT).show();

                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                code.setError("Invalid code");
                            }
                        }
                    }
                });
    }

    public void sendVerificationCode() {

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
    }

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            Log.d(TAG, "Code: "+  phoneAuthCredential.getSmsCode());
            Log.d(TAG, "onVerificationCompleted: COMPLEEEEETEDDDDDD");
//            startActivity(new Intent(SMSCodeVerificationActivity.this, MainActivity.class));

        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Log.d(TAG, "onVerificationFailed: " + e.getLocalizedMessage());

        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);


            Log.d(TAG, "onCodeSent: " + s.toString() );
            codeSent = s;
            mResendToken = forceResendingToken;

            Log.d(TAG, "onCodeSent: "+ mResendToken.toString());

        }
    };


}
