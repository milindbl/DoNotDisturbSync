package se.blunden.donotdisturbsync;

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
                PeriodicWorkRequest workRequest = new PeriodicWorkRequest
                        .Builder(PeriodicDNDSyncWorker.class, 1, TimeUnit.HOURS)
                        .setInitialDelay(3, TimeUnit.MINUTES)
                        .build();
                WorkManager.getInstance(context).enqueueUniquePeriodicWork("dnd_custom_worker", ExistingPeriodicWorkPolicy.KEEP, workRequest);
            }
        }
}
