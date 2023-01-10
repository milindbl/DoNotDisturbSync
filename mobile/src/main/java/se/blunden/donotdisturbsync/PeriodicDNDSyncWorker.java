package se.blunden.donotdisturbsync;

import android.app.NotificationManager;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class PeriodicDNDSyncWorker extends Worker {

    public PeriodicDNDSyncWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @Override
    public Result doWork() {

        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        int interruptionFilter = notificationManager.getCurrentInterruptionFilter();

        DNDSyncNotificationService dndService = new DNDSyncNotificationService();

        dndService.sendDNDSyncMessage(interruptionFilter);
        // Do your work here
        return Result.success();
    }
}
