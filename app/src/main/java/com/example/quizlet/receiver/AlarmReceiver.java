package com.example.quizlet.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.quizlet.service.Music;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("Toi trong rece","haha");
        String chuoi = intent.getExtras().getString("extra");

        Intent intent1 = new Intent(context, Music.class);
        intent1.putExtra("extra",chuoi);
        context.startService(intent1);
    }
}
