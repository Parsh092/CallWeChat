package com.parsh.callwechat.Activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.google.firebase.auth.FirebaseAuth;
import com.parsh.callwechat.R;
import com.parsh.callwechat.databinding.ActivityPhoneNumberBinding;

public class phoneNumber extends AppCompatActivity {
    ActivityPhoneNumberBinding binding;
FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_number);
        binding = ActivityPhoneNumberBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();

        // If user is already exist
        if(auth.getCurrentUser()!=null){
            Intent intent = new Intent(phoneNumber.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        getSupportActionBar().hide(); // To hide the top bar

        binding.phonebox.requestFocus();  // open textpad automatically


        // To  entered number in the OTP page
        binding.continuebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(phoneNumber.this, OTP_Activity.class);
                intent.putExtra("phoneNumber", binding.phonebox.getText().toString());
                startActivity(intent);
            }
        });
    }
}