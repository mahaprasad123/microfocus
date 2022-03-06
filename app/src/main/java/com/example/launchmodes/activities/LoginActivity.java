package com.example.launchmodes.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import androidx.fragment.app.FragmentActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.example.launchmodes.R;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getName();
    BiometricPrompt myBiometricPrompt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).setTitle("BioMetric Login");
        Button fingerprintlauncher = (Button) findViewById(R.id.launchAuthentication);
        fingerprintlauncher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBiometricPrompt();
            }
        });
//Create a thread pool with a single thread//

        Executor newExecutor = Executors.newSingleThreadExecutor();

        FragmentActivity activity = this;

//Start listening for authentication events//

        myBiometricPrompt = new BiometricPrompt(activity, newExecutor, new BiometricPrompt.AuthenticationCallback() {
            @SuppressLint("RestrictedApi")
            @Override

//onAuthenticationError is called when a fatal error occurrs//

            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                if (errorCode == BiometricPrompt.ERROR_NEGATIVE_BUTTON) {
                    finish();
                } else {
                    finish();
//Print a message to Logcat//

                    Log.d(TAG, "An unrecoverable error occurred");
                }
            }

//onAuthenticationSucceeded is called when a fingerprint is matched successfully//

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                startActivity(new Intent(LoginActivity.this, UserDataActivity.class));
                Log.d(TAG, "Fingerprint recognised successfully");
            }

//onAuthenticationFailed is called when the fingerprint doesn’t match//

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Log.d(TAG, "Fingerprint not recognised");
            }
        });

//Create the BiometricPrompt instance//
        showBiometricPrompt();
    }

    private void showBiometricPrompt() {
        final BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Microfocus Login")
                .setDescription("Please Authorize by your fingerprint data")
                .setNegativeButtonText("Cancel")
                .build();
        myBiometricPrompt.authenticate(promptInfo);
    }
}