package com.example.clipserver;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class ClipService extends Service {
    private static final String CHANNEL_ID = "ClipServerServiceChannel";
    private MediaPlayer mediaPlayer;
    private final IBinder binder = new ClipServiceBinder();
    private int currentClipIndex = -1;
    private int[] clipResources = {
            R.raw.audio_clip_1,
            R.raw.audio_clip_2,
            R.raw.audio_clip_3,
            R.raw.audio_clip_4
    };

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
        mediaPlayer = new MediaPlayer();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startForeground(1, getNotification("Service running"));
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public class ClipServiceBinder extends IClipService.Stub {
        @Override
        public void playClip(int clipIndex) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            mediaPlayer.reset();
            currentClipIndex = clipIndex;
            mediaPlayer = MediaPlayer.create(ClipService.this, clipResources[clipIndex]);
            mediaPlayer.start();
            startForeground(1, getNotification("Playing audio clip " + (clipIndex + 1)));
        }

        @Override
        public void pauseClip() {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                stopForeground(false);
            }
        }

        @Override
        public void resumeClip() {
            if (!mediaPlayer.isPlaying() && currentClipIndex != -1) {
                mediaPlayer.start();
                startForeground(1, getNotification("Resuming audio clip " + (currentClipIndex + 1)));
            }
        }

        @Override
        public void stopClip() {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
                mediaPlayer.reset();
                currentClipIndex = -1;
                stopForeground(true);
                stopSelf();
            }
        }

        @Override
        public int getClipCount() {
            return clipResources.length;
        }
    }

    private Notification getNotification(String contentText) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return new Notification.Builder(this, CHANNEL_ID)
                    .setContentTitle("Clip Server")
                    .setContentText(contentText)
                    .setSmallIcon(R.drawable.ic_notification)
                    .build();
        } else {
            return new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setContentTitle("Clip Server")
                    .setContentText(contentText)
                    .setSmallIcon(R.drawable.ic_notification)
                    .build();
        }
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    getString(R.string.clip_server_notification_channel),
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
    }
}
