package com.koterwong.androidhero.chapter_12.viewholder;


import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.NotificationCompat;
import android.widget.RemoteViews;

import com.koterwong.androidhero.R;

import butterknife.OnClick;

/**
 * Description:
 * <p/>
 * Created By：Koterwong; Time: 2016/07/08 10:42
 */
public class NotificationHolder extends BaseHolder {

    public NotificationHolder(Context context) {
        super(context);
    }

    @Override protected int getLayoutId() {
        return R.layout.view_notification;
    }

    @Override public void initData() {

    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP) @OnClick(R.id.basic_notify)
    public void onClick() {

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.baidu.com"));
        PendingIntent pendingIntent = PendingIntent.getActivity(
                mContext, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext);
        Notification notification = builder.setAutoCancel(true)
                .setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.ic_android_orange_600_36dp))
                .setSmallIcon(R.drawable.ic_android_orange_600_36dp)
                .setContentTitle("contentTitle")
                .setContentText("contentText")
                .setSubText("subText")
                .setColor(mContext.getResources().getColor(R.color.brown))
                .setContentIntent(pendingIntent)
                .build();
        NotificationManager manager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, notification);
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN) @OnClick(R.id.collapsedNotify) public void collapsedNotify() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.baidu.com"));
        PendingIntent pendingIntent = PendingIntent.getActivity(
                mContext, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext);
        Notification notification = builder.setAutoCancel(true)
                .setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.ic_android_orange_600_36dp))
                .setSmallIcon(R.drawable.ic_android_orange_600_36dp)
                .setContentIntent(pendingIntent)
                .build();

        RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(), R.layout.notification);
        remoteViews.setTextViewText(R.id.textView, "show me when collapsed");
        notification.contentView = remoteViews;
        RemoteViews remoteViews1 = new RemoteViews(mContext.getPackageName(), R.layout.notification_expanded);
        notification.bigContentView = remoteViews1;

        NotificationManager manager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(1, notification);
    }


    @OnClick(R.id.headsupNotify) public void headsupNotify() {

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.baidu.com"));
        PendingIntent pendingIntent = PendingIntent.getActivity(
                mContext, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext);
        Notification notification = builder.setSmallIcon(R.drawable.ic_android_orange_600_36dp)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setContentTitle("Headsup ContentTitle")
                .setContentText("Headsup ContentTitle Text")
                .setFullScreenIntent(pendingIntent,true)
                .build();

        NotificationManager manager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(2, notification);
    }
}
