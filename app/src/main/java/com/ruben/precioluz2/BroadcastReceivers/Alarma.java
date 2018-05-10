package com.ruben.precioluz2.BroadcastReceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.widget.Toast;

import com.ruben.precioluz2.Activities.R;


public class Alarma extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		MediaPlayer mi_MediaPlayer= MediaPlayer.create( context, Settings.System.DEFAULT_ALARM_ALERT_URI);
		mi_MediaPlayer.start();

		String userInputtedString =intent.getStringExtra(context.getResources().getString(R.string.MENSAJE_ALARMA));
		Toast.makeText(context, userInputtedString, Toast.LENGTH_LONG).show();

	}


}
