package org.aerogear.diffsync.android.demo;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class SyncService extends IntentService {

    public SyncService() {
        super(SyncService.class.getName());
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i(SyncService.class.getName(), "onHandleIntent: " + intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(SyncService.class.getName(), "onCreated");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(SyncService.class.getName(), "onDestroy");
    }
}
