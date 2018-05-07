package com.rubisoft.precioluz2.Activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.rubisoft.precioluz2.BroadcastReceivers.Alarma;
import com.rubisoft.precioluz2.Dialogs.tutorialDialog;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;

public class Avisos extends AppCompatActivity {
	private final Float[] precios_mañana = new Float[24];
	private SharedPreferences prefs = null;
	private final int INDICADOR_TARIFA_20A = 1013;
	private RadioButton RadioButton_mejor_precio;
	private RadioButton RadioButton_peor_precio;

	private  Spinner Spinner_antelacion;
	private static AlarmManager mAlarmManager;
	private TextView TextView_Aviso;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_avisos);
			setTypefaces();
			prefs = PreferenceManager.getDefaultSharedPreferences(this);
			mAlarmManager= (AlarmManager)getSystemService(Context.ALARM_SERVICE);
			Switch Switch_aviso_activado = (Switch) findViewById(R.id.switch_Activa_Aviso);
			RadioButton_mejor_precio = (RadioButton) findViewById(R.id.radioButton_mejor_precio);
			RadioButton_peor_precio = (RadioButton) findViewById(R.id.radioButton_peor_precio);

			Spinner_antelacion = (Spinner) findViewById(R.id.spinner_antelacion);
			TextView_Aviso=(TextView)findViewById(R.id.TextView_Aviso);
			String[] arraySpinner = new String[]{"0", "1", "2", "3"};
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arraySpinner);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			Spinner_antelacion.setAdapter(adapter);
			adapter.notifyDataSetChanged();
			Switch_aviso_activado.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					if (isChecked) {
						new AsyncTask_getPrecios_mañana().execute(prefs.getInt(getResources().getString(R.string.TAG_TARIFA_ACTUAL), INDICADOR_TARIFA_20A));
					}else{
						unsetAlarm();
					}
				}
			});
		}catch (Exception e){
			Toast.makeText(getApplicationContext(), "Exception en onCreate " + e, Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.

		try {
			switch (item.getItemId()) {
				case R.id.configuracion:
					Intent mIntent_configuracion = new Intent(this, Configuracion.class);
					mIntent_configuracion.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(mIntent_configuracion);
					finish();
					break;
				case R.id.avisos:
					Intent mIntent_avisos = new Intent(this, Avisos.class);
					mIntent_avisos.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(mIntent_avisos);
					finish();
					break;
				case R.id.valorar:
					Intent mIntent_valorar = new Intent(this, Valorar.class);
					mIntent_valorar.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(mIntent_valorar);
					finish();
					break;
				case R.id.tutorial:
					//lanzamos el tutorial
					android.app.DialogFragment un_dialogo = new tutorialDialog();
					un_dialogo.show(this.getFragmentManager(), "");
					break;
			}
		} catch (Exception e) {

		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	private class AsyncTask_getPrecios_mañana extends AsyncTask<Integer, Void,Pair<Integer,JSONArray[]>> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();

		}

		@Override
		protected Pair<Integer,JSONArray[]> doInBackground(Integer... params) {
			JSONArray mJSONObject=new JSONArray();
			Integer indicador= params[0];

			try {
				if (isNetworkAvailable()) {
					Calendar mCalendar=Calendar.getInstance();
					mCalendar.add(Calendar.DAY_OF_YEAR, 1);  //PARA COJER EL DE MAÑANA AÑADIMOS UN DIA MAS

					/*************************** Mañana ***********************************/
					int año_0=mCalendar.get(Calendar.YEAR);
					String mes_0=String.format("%02d", mCalendar.get(Calendar.MONTH)+1);
					int dia_0=mCalendar.get(Calendar.DAY_OF_MONTH);

					URL mURL_para_hoy = new URL("https://api.esios.ree.es/indicators/"+indicador+"?start_date="+año_0+"-"+mes_0+"-"+dia_0+"T00:00:00&end_date="+año_0+"-"+mes_0+"-"+dia_0+"T23:59:59");

					HttpURLConnection mHttpURLConnection_0= (HttpURLConnection) mURL_para_hoy.openConnection();
					mHttpURLConnection_0.setRequestMethod("GET");
					mHttpURLConnection_0.setRequestProperty("Accept","application/json; application/vnd.esios-api-v1+json");
					mHttpURLConnection_0.setRequestProperty("Content-type","application/json");
					mHttpURLConnection_0.setRequestProperty("Host","api.esios.ree.es");
					mHttpURLConnection_0.setRequestProperty("Authorization","Token token=\""+getResources().getString(R.string.TOKEN_ESIOS)+"\"");
					mHttpURLConnection_0.setRequestProperty("Cookie"," ");

					InputStream mInputStream_0= mHttpURLConnection_0.getInputStream();

					JSONParser jsonParser_0 = new JSONParser();
					JSONObject aux_01= (JSONObject)jsonParser_0.parse(new InputStreamReader(mInputStream_0, "UTF-8"));
					JSONArray aux_02=((JSONArray)((JSONObject)aux_01.get("indicator")).get("values"));
					mJSONObject = aux_02;

				}
			} catch (Exception e) {
				e.toString();
			}

			return new Pair(indicador,mJSONObject);
		}

		@Override
		protected void onPostExecute(Pair mPair) {
			try {
				Integer hora= (Integer)mPair.first;
				JSONArray mJSONArray= (JSONArray)mPair.second;
				Float[] precios_hoy=new Float[24];

				for (int i = 0; i < 24; i++) {

					//Puede que aún no estén disponibles los precios de mañana
					if (mJSONArray.size()==24){
						JSONObject mJSONObject_0 = (JSONObject)mJSONArray.get(i);
						precios_hoy[i]= ((Double)mJSONObject_0.get("value")).floatValue()/1000;
					}

				}
				boolean resultado;

				resultado=guarda_precios(precios_hoy);

				if (resultado){
					 int offset=0;
					 if (RadioButton_mejor_precio.isChecked()){
						 offset= getIndexOfLargest(precios_hoy);
					 }else{
					 	offset= getIndexOfShortest(precios_hoy);
					 }

					 Calendar mCalendar= Calendar.getInstance();
					 mCalendar.set(
							 Calendar.DAY_OF_YEAR,
							mCalendar.get(Calendar.DAY_OF_YEAR)+1
							);
					mCalendar.set(
							Calendar.HOUR_OF_DAY,
							0
					);
					mCalendar.set(
							Calendar.MINUTE,
							0
					);
					 setAlarm(mCalendar.getTimeInMillis(),offset);
				}
			}catch (Exception e){
				Toast.makeText(getApplicationContext(), "Exception en AsyncTask_getPrecios_mañana " + e, Toast.LENGTH_LONG).show();
			}finally {

			}
		}

		boolean guarda_precios( Float[] precios) {
			if (precios.length > 0) {
				for (Integer i = 0; i < 24; i++) {
					precios_mañana[i] = precios[i];
				}
				return true;
			} else {
				return false;
			}
		}
	}

	private boolean isNetworkAvailable() {
		ConnectivityManager connectivityManager
				= (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
		return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}

	public int getIndexOfLargest( Float[] array )
	{
		if ( array == null || array.length == 0 ) return -1; // null or empty

		int largest = 0;
		for ( int i = 1; i < array.length; i++ )
		{
			if ( array[i] > array[largest] ) largest = i;
		}
		return largest; // position of the first largest found
	}
	public int getIndexOfShortest( Float[] array )
	{
		if ( array == null || array.length == 0 ) return -1; // null or empty

		int shortest = 0;
		for ( int i = 1; i < array.length; i++ )
		{
			if ( array[i] < array[shortest] ) shortest = i;
		}
		return shortest; // position of the first largest found
	}

	public void setAlarm(Long TimeInMilis, int hora){
		int antelacion=Spinner_antelacion.getSelectedItemPosition();

		Intent mIntent = new Intent(this, Alarma.class);
		PendingIntent mPendingIntent= PendingIntent.getBroadcast(this,0,mIntent,0);
		mAlarmManager.setRepeating(AlarmManager.RTC,TimeInMilis+(hora*1000*60*60)-antelacion*1000*60*60,0,mPendingIntent);
		Toast.makeText(getApplicationContext(), "Alarma establecida ", Toast.LENGTH_LONG).show();

	}

	public void unsetAlarm(){
		int antelacion=Spinner_antelacion.getSelectedItemPosition();
		Intent mIntent = new Intent(this, Alarma.class);
		PendingIntent mPendingIntent= PendingIntent.getBroadcast(this,0,mIntent,0);
		mAlarmManager.cancel(mPendingIntent);
		Toast.makeText(getApplicationContext(), "Alarma desestablecida ", Toast.LENGTH_LONG).show();

	}

	private void setTypefaces() {
		Typeface typeFace_roboto_light = Typeface.createFromAsset(this.getAssets(), "fonts/Roboto-Light.ttf");
		Typeface typeFace_roboto_bold = Typeface.createFromAsset(this.getAssets(), "fonts/Roboto-Bold.ttf");

		TextView_Aviso.setTypeface(typeFace_roboto_bold);
		RadioButton_mejor_precio.setTypeface(typeFace_roboto_light);
		RadioButton_peor_precio.setTypeface(typeFace_roboto_light);
	}

}
