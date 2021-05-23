package jp.ac.titech.itpro.sdl.startservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = MainActivity.class.getSimpleName();
    private BroadcastReceiver scanReceiver;
    private IntentFilter scanFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate in " + Thread.currentThread());
        setContentView(R.layout.activity_main);

        scanReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                Log.d(TAG, "onReceive: " + action);
                if (action == null) {
                    return;
                }
                switch (action) {
                    case Service3.Action_ANSWER:
                        Log.d(TAG, "myarg = " + intent.getStringExtra(Service3.EXTRA_ANSWER));//受け取ったブロードキャストをログに出力
                        break;
                }
            }
        };
        scanFilter = new IntentFilter();
        scanFilter.addAction(Service3.Action_ANSWER);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        registerReceiver(scanReceiver, scanFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
        unregisterReceiver(scanReceiver);
    }


    public void onClickTest1(View v) {
        Log.d(TAG, "onClickTest1 in " + Thread.currentThread());
        Intent intent = new Intent(this, Service1.class);
        intent.putExtra(Service1.EXTRA_MYARG, "Hello, Service1");
        startService(intent);
    }

    public void onClickTest2(View v) {
        Log.d(TAG, "onClickTest2 in " + Thread.currentThread());
        Intent intent = new Intent(this, Service2.class);
        intent.putExtra(Service2.EXTRA_MYARG, "Hello, Service2");
        startService(intent);
    }

    public void onClickTest3(View v) {
        Log.d(TAG, "onClickTest3 in " + Thread.currentThread());
        Intent intent = new Intent(this, Service3.class);
        intent.putExtra(Service3.EXTRA_MYARG, "Hello, Service3");
        startService(intent);
    }
}
