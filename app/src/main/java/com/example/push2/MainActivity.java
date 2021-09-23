package com.example.push2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private final String BASE_URL = "https://taewoooh88.cafe24.com/";
    Retrofit retrofit;
    public static Context mContext;

    int anInt=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        String token = FirebaseInstanceId.getInstance().getToken();
        Log.e("========== PUSH ID ========", "" + token);
       // Toast.makeText(MainActivity.this, "PUSH ID :::" + token, Toast.LENGTH_SHORT).show();







        if(token!=null){

            Tongsin(token);
        }

       // Log.e("token",""+token);



    }


    public void init() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        // GSON 컨버터를 사용하는 REST 어댑터 생성
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public void Tongsin(String token) { // 서버 데이터를 가지고 온다 파라미터는 불러올 테이블 이름


        init();
        GitHub gitHub = retrofit.create(GitHub.class);
        Call<List<ListViewItem>> call = gitHub.contributors(token);
        call.enqueue(new Callback<List<ListViewItem>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            // 성공시
            public void onResponse(Call<List<ListViewItem>> call, Response<List<ListViewItem>> result) {
                List<ListViewItem> contributors = result.body();
                // 받아온 리스트를 순회하면서

                Log.e("Test888", result.body().toString());

                for (ListViewItem contributor : contributors) {


                    String value = contributor.value;


                }


            }

            @Override
            public void onFailure(Call<List<ListViewItem>> call, Throwable t) {
//                Toast.makeText(MainActivity.this, "정보받아오기 실패 " + t, Toast.LENGTH_LONG)
//                        .show();

                Log.e("onFailure", "- > " + t);

            }
        });


    }
}