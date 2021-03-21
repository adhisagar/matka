package com.example.matkamasthi.manager;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import com.example.matkamasthi.activity.HomeActivity;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

public class MyFireBaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFMService";
    Bitmap logo;

    // Clears notification tray messages
    public static void clearNotifications(Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        String token = FirebaseInstanceId.getInstance().getToken();

        String message = remoteMessage.getData().get("body");
        String imageUri = remoteMessage.getData().get("image");
        String title = remoteMessage.getData().get("title");
        String booking_id = remoteMessage.getData().get("booking_id");
        String TrueOrFlase = remoteMessage.getData().get("AnotherActivity");
        String notifyType = remoteMessage.getData().get("type");
        String topicId = remoteMessage.getData().get("topicId");
        String doubtType = remoteMessage.getData().get("doubtType");
        String blogUrl = remoteMessage.getData().get("blogUrl");
        String notifyId = remoteMessage.getData().get("notifyId");
      //  Bitmap bitmap = getBitmapfromUrl(imageUri);

        Log.i("mes", message);
        //logo = getBitmapfromUrl(AppController.getInstance().getAppData().getNotificationLogoUrl());

        sendNotification(doubtType, message, TrueOrFlase, topicId, notifyType, imageUri, title, booking_id, blogUrl);

    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);

    }

    private void sendNotification(String doubtType, String messageBody, String TrueOrFalse, String topicId, String notifyType, String imageUri, String title, String booking_id, String blogUrl) {

        int m = (int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE);
        Bundle bundle = new Bundle();
        Intent intent = new Intent(this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setAction(Long.toString(System.currentTimeMillis()));
        intent.putExtra("AnotherActivity", TrueOrFalse);
        intent.putExtra("notifyType", notifyType);
        intent.putExtra("topicId", topicId);
        intent.putExtra("doubtType", doubtType);
        intent.putExtra("title", title);
        intent.putExtra("body",messageBody);
        intent.putExtra("booking_id", booking_id);
        intent.putExtra("imageUrl", imageUri);
        intent.putExtra("blogUrl", blogUrl);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        bundle.putString("AnotherActivity", TrueOrFalse);
        bundle.putString("notifyType", notifyType);
        bundle.putString("topicId", topicId);
        bundle.putString("doubtType", doubtType);
        bundle.putString("imageUrl", imageUri);
        bundle.putString("body",messageBody);
        bundle.putSerializable("title", title);
        bundle.putString("blogUrl", blogUrl);
        bundle.putString("notifyId", String.valueOf(m));
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        //final Uri NOTIFICATION_SOUND_URI = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + BuildConfig.APPLICATION_ID + "/" + R.raw.flap);
        //Uri sound = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + getApplicationContext().getPackageName() + "/" + R.raw.flap);
        //Uri sound = Uri.parse("C:\\Users\\Sagar\\Downloads\\flap.mp3");

       // Uri sound = Uri.parse("android.resource://" + getApplicationContext().getPackageName() + "/" + R.raw.happy);
        long[] pattern = {0, 500};
        NotificationCompat.Builder nb = new NotificationCompat.Builder(this, "notify_001");
       // nb .setDefaults(NotificationCompat.DEFAULT_ALL);
                nb.setContentTitle(title);
                nb.setContentText(messageBody);
        nb.setAutoCancel(true);
                //.setLargeIcon(logo)
        //nb.setSmallIcon(R.drawable.contact_icon);
       // nb.setSound(Settings.System.DEFAULT_NOTIFICATION_URI);
       // nb.setDefaults(Notification.DEFAULT_SOUND);
       // nb.setSound(sound, AudioManager.STREAM_NOTIFICATION);

        nb.setPriority(NotificationCompat.PRIORITY_MAX);
        nb.setStyle(new NotificationCompat.BigTextStyle().bigText(title));

        nb .setContentIntent(pendingIntent);
        nb.setStyle(new NotificationCompat.InboxStyle());
        nb.setLights(Color.BLUE, 500, 500);
        nb.setVibrate(pattern);
        nb.setTimeoutAfter(30000);


//        if (image != null && !imageUri.equals("") && !notifyType.equals("DOUBTS")) {
//            nb.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(image).setSummaryText(messageBody));
//        }

        /* NotificationManagerCompat nm = NotificationManagerCompat.from(MyFireBaseMessagingService.this);
         */
        //Uri sound = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + getApplicationContext().getPackageName() + "/" + R.raw.flap);
        NotificationManager nm = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

//            if(sound != null) {
//                // Changing Default mode of notification
//                nb.setDefaults(Notification.DEFAULT_VIBRATE);
//
//                NotificationChannel channel = new NotificationChannel("notify_001",
//                        "Channel human readable title",
//                        NotificationManager.IMPORTANCE_HIGH);
//
//                AudioAttributes audioAttributes = new AudioAttributes.Builder()
//                        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
//                        .setUsage(AudioAttributes.USAGE_ALARM)
//                        .build();
//
//                channel.setSound(sound, audioAttributes);
//                nm.createNotificationChannel(channel);
//
//                nm.cancelAll();
//
//
//            }

        }
        nm.notify(m, nb.build());

    }

    public Bitmap getBitmapfromUrl(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            return BitmapFactory.decodeStream(input);

        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }
}

