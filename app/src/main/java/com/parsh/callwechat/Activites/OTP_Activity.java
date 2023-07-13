package com.parsh.callwechat.Activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.mukesh.OnOtpCompletionListener;
import com.parsh.callwechat.databinding.ActivityOtpBinding;

import java.util.concurrent.TimeUnit;

public class OTP_Activity extends AppCompatActivity {
    ActivityOtpBinding binding;
    String verificationId;
    FirebaseAuth auth;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);

        // Sending OTP message
        dialog = new ProgressDialog(this);
        dialog.setMessage("sending OTP...");
        dialog.setCancelable(false);
        dialog.show();
        binding = ActivityOtpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();
        String phoneNumber = getIntent().getStringExtra("phoneNumber");
        binding.phoneLbl.setText("verify +91 " + phoneNumber);


        // OTP receiving
        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(auth).
                setPhoneNumber(phoneNumber)  // Phone number to verify
                .setTimeout(60L, TimeUnit.SECONDS)  // Timeout and unit
                .setActivity(OTP_Activity.this)   // Activity (for callback binding)
                // OnVerificationStateChangedCallbacks
                .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {

                    }

                    @Override
                    public void onCodeSent(@NonNull String verifyId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(verifyId, forceResendingToken);
                        dialog.dismiss();
                        verificationId = verifyId;
                    }
                }).build();
        PhoneAuthProvider.verifyPhoneNumber(options);
        binding.otpView.setOtpCompletionListener(new OnOtpCompletionListener() {
            @Override
            public void onOtpCompleted(String otp) {
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, otp);
                auth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(OTP_Activity.this, "Logged In", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(OTP_Activity.this,setupProfile.class);
                        startActivity(intent);
                        // finish();  Only finish this activity
                        finishAffinity(); // finishes all the activity
                        } else {
                            Toast.makeText(OTP_Activity.this, "Try again", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


    }
}