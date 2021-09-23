package com.example.push2;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.HashMap;
import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {







        // Handle FCM Message Log.e(TAG, remoteMessage.getFrom()); // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.e(TAG, "Message data payload: " + remoteMessage.getData());
            handleNow();
        }
        if (remoteMessage.getNotification() != null) {
            Log.e(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
            String getMessage = remoteMessage.getNotification().getBody();
            if (TextUtils.isEmpty(getMessage)) {
                Log.e(TAG, "ERR: Message data is empty...");
            } else {
                Map<String, String> mapMessage = new HashMap<>();
                assert getMessage != null;
                mapMessage.put("key", getMessage); // Broadcast Data Sending Test
                Intent intent = new Intent("alert_data");
                intent.putExtra("msg", getMessage);
                LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
            }
        }


    }




    private void handleNow() {
        Log.d(TAG, "Short lived task is done.");
    }

    /**
     * 새로운 토큰이 생성되는 경우
     **/
    @Override
    public void onNewToken(String refreshedToken) {
        super.onNewToken(refreshedToken);
        Log.e(TAG, "Refreshed token: " + refreshedToken);


        ((MainActivity)MainActivity.mContext).Tongsin(refreshedToken);



    }



}