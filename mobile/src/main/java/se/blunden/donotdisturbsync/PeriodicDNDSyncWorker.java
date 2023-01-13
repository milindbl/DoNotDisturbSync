package se.blunden.donotdisturbsync;

import android.app.NotificationManager;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.lang.ref.SoftReference;

public class PeriodicDNDSyncWorker extends Worker {
    private static final String TAG = "PeriodicDNDSyncWorker";

    SoftReference<Context> contextSoftReference;

    public PeriodicDNDSyncWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);

        contextSoftReference = new SoftReference<>(context);
        Log.d(TAG, "PeriodicDNDSyncWorker");
    }

    @Override
    public Result doWork() {

        Log.d(TAG, "doWork");

        NotificationManager notificationManager = (NotificationManager) contextSoftReference.get().getSystemService(Context.NOTIFICATION_SERVICE);
        int interruptionFilter = notificationManager.getCurrentInterruptionFilter();

        DNDSyncNotificationService dndService = new DNDSyncNotificationService();

        dndService.sendDNDSyncMessage(interruptionFilter, contextSoftReference.get());
        // Do your work here
        return Result.success();
    }
}
