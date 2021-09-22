package com.example.push2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getIntent().getExtras() != null) {
            for (String key : getIntent().getExtras().keySet()) {
                Object value = getIntent().getExtras().get(key);
                Log.e("", "Key: " + key + " Value: " + value);
            }
        }
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.e("========== PUSH ID ========","" + token);
        Toast.makeText(MainActivity.this, "PUSH ID :::" + token,         Toast.LENGTH_SHORT).show();


    }
}