package com.example.gopherhunting;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_CODE = 1;
    private Button startGameButton, stopGameButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startGameButton = findViewById(R.id.startButton);
        stopGameButton = findViewById(R.id.stopButton);

        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkAndRequestPermissions()) {
                    startGame();
                }
            }
        });

        stopGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopGame();
            }
        });
    }

    private boolean checkAndRequestPermissions() {
        if (ContextCompat.checkSelfPermission(this, "com.example.gopherhunting.PERMISSION_GAME_PLAYER") != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{"com.example.gopherhunting.PERMISSION_GAME_PLAYER"},
                    PERMISSION_REQUEST_CODE);
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startGame();
            } else {
                Toast.makeText(this, "Permission Denied. Cannot start the game.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void startGame() {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    private void stopGame() {
        // Notify any running threads or services to stop.
        // Here, you might send a broadcast or directly command the threads to stop.
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopGame();  // Ensure all resources are released and properly cleaned up
    }
}
