package se.blunden.donotdisturbsync;

import static se.blunden.donotdisturbsync.MainActivity.UNIQUE_WORKER_NAME;
import static se.blunden.donotdisturbsync.MainActivity.UNIQUE_WORKER_REPEAT_INTERVAL;
import static se.blunden.donotdisturbsync.MainActivity.UNIQUE_WORKER_REPEAT_INTERVAL_UNIT;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import java.util.concurrent.TimeUnit;


public class PeriodicDNDSyncBootReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
                MainActivity.initPeriodicWork(context);
            }
        }
}
