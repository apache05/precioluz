package com.ruben.precioluz2.BroadcastReceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.ruben.precioluz2.Activities.R;


public class AlarmaBroadcastReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		try {
			Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
			assert vibrator != null;
			vibrator.vibrate(2000);

			MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.alarm);


			mediaPlayer.start();


			String userInputtedString = intent.getStringExtra(context.getResources().getString(R.string.MENSAJE_ALARMA));
			Toast.makeText(context, userInputtedString, Toast.LENGTH_LONG).show();
		}catch (Exception ignored){

		}finally {
			 SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
			SharedPreferences.Editor editor = prefs.edit();
			editor.putBoolean(context.getResources().getString(R.string.ALARMA), false);
			editor.apply();
		}
	}


}
