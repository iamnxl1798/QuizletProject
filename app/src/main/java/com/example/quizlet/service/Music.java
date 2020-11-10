package com.example.quizlet.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.example.quizlet.R;

public class Music extends Service {

    MediaPlayer mediaPlayer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String s = intent.getExtras().getString("extra");
        if (s.equals("on")) {
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.baothuc);
            mediaPlayer.start();
        } else {
            mediaPlayer.stop();
            mediaPlayer.reset();
        }
        return START_NOT_STICKY;
    }
}
