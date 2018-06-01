package com.rubisoft.precioluz.BroadcastReceivers;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.util.Pair;

import com.rubisoft.precioluz.Activities.MainActivity;
import com.rubisoft.precioluz.Activities.R;
import com.rubisoft.precioluz.utils.utils;


//AlarmaBroadcastReceiver is continously listening for notifications
public class AlarmaBroadcastReceiver extends BroadcastReceiver {

	//Notification received. Create and show a Notification
	@Override
	public void onReceive(Context context, Intent intent) {
		try {
			Intent new_intent = new Intent(context, MainActivity.class);

			PendingIntent pendingIntent = PendingIntent.getActivity(context, 0 /* Request code */, intent, PendingIntent.FLAG_ONE_SHOT);

			Uri uri_sound= Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.alarm);
			NotificationCompat.Builder builder=new NotificationCompat.Builder(context, "PRECIOLUZ");
			builder.setAutoCancel(true)
					.setDefaults(Notification.DEFAULT_ALL)
					.setWhen(System.currentTimeMillis())
					.setSound(uri_sound)
					.setContentIntent(pendingIntent)
					.setSmallIcon(R.mipmap.ic_launcher)
					.setContentTitle(context.getResources().getString(R.string.Alarma))
					.setContentInfo(context.getString(R.string.app_name))
					.setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 })
					.setLights(Color.RED, 3000, 3000)
					.setContentText(intent.getStringExtra(context.getResources().getString(R.string.MENSAJE_ALARMA))
					);

			NotificationManager mNotificationManager= (NotificationManager)context.getSystemService(context.NOTIFICATION_SERVICE);
			mNotificationManager.notify(1,builder.build());
		}catch (Exception e){
			new utils.AsyncTask_Guardar_Error().execute(new Pair<>(context, e.toString()));
		}finally {
			 SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
			SharedPreferences.Editor editor = prefs.edit();
			editor.putBoolean(context.getResources().getString(R.string.ALARMA), false);
			editor.apply();
		}
	}


}
