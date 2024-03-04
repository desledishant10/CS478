/**
 * Project: PhoneAppDemo
 * Course: CS478 - Software Development for Mobile Platforms
 * Author: Dishant Desle
 * Date: 02/11/2024
 *
 * Description:
 * This Android application is designed to demonstrate the functionality of invoking the phone's dialer and messaging apps
 * through implicit intents with a focus on user interaction and intent handling. The app allows users to enter a phone number
 * in two different formats and choose between dialing the number or composing a message addressed to it.
 *
 * The layout adjusts dynamically to orientation changes, ensuring a consistent user experience across portrait and landscape modes.
 * The project emphasizes Android's intent system, UI design principles, and handling runtime configuration changes.
 *
 * Features:
 * - Input validation for phone numbers in two distinct formats.
 * - Use of implicit intents to interact with the system's dialer and messaging applications.
 * - Dynamic layout adjustments for different device orientations.
 * - State preservation across configuration changes to retain the user's input.
 *
 * Permissions:
 * - QUERY_ALL_PACKAGES: Required for querying the package manager to identify available activities for handling intents.
 *   Note: As per Android 12's requirements, specific intent queries are used instead of this broad permission for enhanced privacy.
 *
 * Target Device: Pixel 5 AVD running Android 14 (API Level 34).
 *
 */





package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText phoneNumberInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Ensure you have the correct layout file for portrait

        phoneNumberInput = findViewById(R.id.phone_number_input);
        Button buttonDial = findViewById(R.id.button_dial);
        Button buttonMessage = findViewById(R.id.button_message);

        buttonDial.setOnClickListener(v -> {
            if (isValidPhoneNumber(phoneNumberInput.getText().toString())) {
                dialPhoneNumber(phoneNumberInput.getText().toString());
            } else {
                showToast(phoneNumberInput.getText().toString());
            }
        });

        buttonMessage.setOnClickListener(v -> {
            if (isValidPhoneNumber(phoneNumberInput.getText().toString())) {
                composeSmsMessage(phoneNumberInput.getText().toString());
            } else {
                showToast(phoneNumberInput.getText().toString());
            }
        });

        if (savedInstanceState != null) {
            phoneNumberInput.setText(savedInstanceState.getString("phoneNumberKey"));
        }
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("^\\d{10}$") || phoneNumber.matches("^\\(\\d{3}\\) \\d{3}-\\d{4}$");
    }

    private void dialPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private void composeSmsMessage(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:" + phoneNumber));  // Only SMS apps should handle this
        intent.putExtra("sms_body", "Hello [Dishant Desle]");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private void showToast(String phoneNumber) {
        Toast.makeText(getApplicationContext(), "Incorrect number: " + phoneNumber, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("phoneNumberKey", phoneNumberInput.getText().toString());
    }
}
