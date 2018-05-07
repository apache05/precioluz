package com.rubisoft.precioluz2.BroadcastReceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.Settings;

/**
 * Created by ruben on 2/5/18.
 */

public class Alarma extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		MediaPlayer mi_MediaPlayer= MediaPlayer.create( context, Settings.System.DEFAULT_ALARM_ALERT_URI);
		mi_MediaPlayer.start();
	}


}
