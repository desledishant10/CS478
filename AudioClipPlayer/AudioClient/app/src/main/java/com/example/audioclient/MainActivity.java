package com.example.audioclient;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clipserver.IClipService;

public class MainActivity extends AppCompatActivity {

    private IClipService clipService;
    private boolean isBound = false;

    private Button startServiceButton;
    private Button stopServiceButton;
    private Button playButton;
    private Button pauseButton;
    private Button resumeButton;
    private Button stopButton;
    private TextView statusTextView;

    private int currentClipIndex = 0;
    private int clipCount = 0;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            clipService = IClipService.Stub.asInterface(service);
            isBound = true;
            updateUI();
            try {
                clipCount = clipService.getClipCount();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            Toast.makeText(MainActivity.this, "Service Connected", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            clipService = null;
            isBound = false;
            updateUI();
            Toast.makeText(MainActivity.this, "Service Disconnected", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startServiceButton = findViewById(R.id.startServiceButton);
        stopServiceButton = findViewById(R.id.stopServiceButton);
        playButton = findViewById(R.id.playButton);
        pauseButton = findViewById(R.id.pauseButton);
        resumeButton = findViewById(R.id.resumeButton);
        stopButton = findViewById(R.id.stopButton);
        statusTextView = findViewById(R.id.statusTextView);

        startServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService();
            }
        });

        stopServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService();
            }
        });

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playClip();
            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseClip();
            }
        });

        resumeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resumeClip();
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopClip();
            }
        });

        updateUI();
    }

    @Override
    protected void onStart() {
        super.onStart();
        bindService();
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService();
    }

    private void startService() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.example.clipserver", "com.example.clipserver.ClipService"));
        startForegroundService(intent);
        bindService();
    }

    private void stopService() {
        if (isBound) {
            unbindService(serviceConnection);
            isBound = false;
        }
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.example.clipserver", "com.example.clipserver.ClipService"));
        stopService(intent);
        updateUI();
    }

    private void bindService() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.example.clipserver", "com.example.clipserver.ClipService"));
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    private void unbindService() {
        if (isBound) {
            unbindService(serviceConnection);
            isBound = false;
        }
    }

    private void playClip() {
        if (isBound) {
            try {
                clipService.playClip(currentClipIndex);
                statusTextView.setText("Playing clip " + (currentClipIndex + 1));
                playButton.setEnabled(false);
                pauseButton.setEnabled(true);
                resumeButton.setEnabled(false);
                stopButton.setEnabled(true);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private void pauseClip() {
        if (isBound) {
            try {
                clipService.pauseClip();
                statusTextView.setText("Clip paused");
                playButton.setEnabled(false);
                pauseButton.setEnabled(false);
                resumeButton.setEnabled(true);
                stopButton.setEnabled(true);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private void resumeClip() {
        if (isBound) {
            try {
                clipService.resumeClip();
                statusTextView.setText("Resuming clip");
                playButton.setEnabled(false);
                pauseButton.setEnabled(true);
                resumeButton.setEnabled(false);
                stopButton.setEnabled(true);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private void stopClip() {
        if (isBound) {
            try {
                clipService.stopClip();
                statusTextView.setText("Clip stopped");
                playButton.setEnabled(true);
                pauseButton.setEnabled(false);
                resumeButton.setEnabled(false);
                stopButton.setEnabled(false);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateUI() {
        if (isBound) {
            playButton.setEnabled(true);
            pauseButton.setEnabled(false);
            resumeButton.setEnabled(false);
            stopButton.setEnabled(false);
        } else {
            playButton.setEnabled(false);
            pauseButton.setEnabled(false);
            resumeButton.setEnabled(false);
            stopButton.setEnabled(false);
        }
    }
}
