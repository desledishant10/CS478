package com.example.clipserver;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button btnStartService, btnPlayAudio, btnPauseAudio, btnResumeAudio, btnStopService;
    private IClipService clipService;
    private boolean isServiceBound = false;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            clipService = IClipService.Stub.asInterface(service);
            isServiceBound = true;
            btnPlayAudio.setEnabled(true);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            clipService = null;
            isServiceBound = false;
            btnPlayAudio.setEnabled(false);
            btnPauseAudio.setEnabled(false);
            btnResumeAudio.setEnabled(false);
            btnStopService.setEnabled(false);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStartService = findViewById(R.id.btnStartService);
        btnPlayAudio = findViewById(R.id.btnPlayAudio);
        btnPauseAudio = findViewById(R.id.btnPauseAudio);
        btnResumeAudio = findViewById(R.id.btnResumeAudio);
        btnStopService = findViewById(R.id.btnStopService);

        btnStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent serviceIntent = new Intent(MainActivity.this, ClipService.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    startForegroundService(serviceIntent);
                } else {
                    startService(serviceIntent);
                }
                bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE);
            }
        });

        btnPlayAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (clipService != null) {
                        clipService.playClip(1); // Example to play the first clip
                        btnPauseAudio.setEnabled(true);
                        btnStopService.setEnabled(true);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btnPauseAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (clipService != null) {
                        clipService.pauseClip();
                        btnResumeAudio.setEnabled(true);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btnResumeAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (clipService != null) {
                        clipService.resumeClip();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btnStopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (clipService != null) {
                        clipService.stopClip();
                        unbindService(serviceConnection);
                        isServiceBound = false;
                        btnPlayAudio.setEnabled(false);
                        btnPauseAudio.setEnabled(false);
                        btnResumeAudio.setEnabled(false);
                        btnStopService.setEnabled(false);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isServiceBound) {
            unbindService(serviceConnection);
            isServiceBound = false;
        }
    }
}
