package com.ruben.precioluz2.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.ruben.precioluz2.utils.utils;

public class ConfiguracionActivity extends AppCompatActivity {

	private SharedPreferences prefs;
	private RadioButton RadioButton_tarifa_20A=null;
	private RadioButton RadioButton_tarifa_20DHA=null;
	private RadioButton RadioButton_tarifa_20DHS=null;
	private RadioButton RadioButton_hace_una_semana=null;
	private RadioButton RadioButton_hace_un_año=null;
	private RadioButton RadioButton_no_comparar=null;

	private TextView TextView_Configuracion=null;
	private Button Button_aceptar=null;
	private final int INDICADOR_TARIFA_20A = 1013;
	private final int INDICADOR_TARIFA_20DHA = 1014;
	private final int INDICADOR_TARIFA_20DHS = 1015;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try {
			super.onCreate(savedInstanceState);
			if (isNetworkAvailable()) {
				setContentView(R.layout.layout_configuracion);

				prefs = PreferenceManager.getDefaultSharedPreferences(this);
				setup_views();
				setTypefaces();

				inicializa_radiobuttons();
				Button_aceptar.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent mIntent = new Intent(getApplicationContext(), MainActivity.class);
						mIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(mIntent);
						finish();
					}
				});
				RadioButton_tarifa_20A.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						set_Tarifa_SharedPreferences(INDICADOR_TARIFA_20A);

					}
				});
				RadioButton_tarifa_20DHA.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						set_Tarifa_SharedPreferences(INDICADOR_TARIFA_20DHA);

					}
				});
				RadioButton_tarifa_20DHS.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						set_Tarifa_SharedPreferences(INDICADOR_TARIFA_20DHS);

					}
				});
				RadioButton_no_comparar.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						SharedPreferences.Editor editor = prefs.edit();
						editor.putBoolean(getResources().getString(R.string.TAG_HACE_UNA_SEMANA), false);
						editor.putBoolean(getResources().getString(R.string.TAG_HACE_UN_AÑO), false);

						editor.apply();

					}
				});
				RadioButton_hace_una_semana.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						SharedPreferences.Editor editor = prefs.edit();
						editor.putBoolean(getResources().getString(R.string.TAG_HACE_UNA_SEMANA), true);
						editor.putBoolean(getResources().getString(R.string.TAG_HACE_UN_AÑO), false);

						editor.apply();
					}
				});

				RadioButton_hace_un_año.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						SharedPreferences.Editor editor = prefs.edit();
						editor.putBoolean(getResources().getString(R.string.TAG_HACE_UNA_SEMANA), false);
						editor.putBoolean(getResources().getString(R.string.TAG_HACE_UN_AÑO), true);
						editor.apply();
					}
				});
			} else {
				Intent mIntent = new Intent(this, ErrorActivity.class);
				mIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(mIntent);
				finish();
			}
		} catch (Exception e){
			new utils.AsyncTask_Guardar_Error().execute(new Pair(this,e.toString()));

		}

	}
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Intent mIntent = new Intent(this, MainActivity.class);
		mIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(mIntent);
		finish();
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.

		try {
			switch (item.getItemId()) {
				case R.id.configuracion:
					Intent mIntent_configuracion = new Intent(this, ConfiguracionActivity.class);
					mIntent_configuracion.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(mIntent_configuracion);
					finish();
					break;
				case R.id.avisos:
					Intent mIntent_avisos = new Intent(this, AvisosActivity.class);
					mIntent_avisos.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(mIntent_avisos);
					finish();
					break;
				case R.id.valorar:
					Intent mIntent_valorar = new Intent(this, ValorarActivity.class);
					mIntent_valorar.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(mIntent_valorar);
					finish();
					break;
				case R.id.tutorial:
					Intent mIntent_tutorial = new Intent(this, TutorialActivity.class);
					mIntent_tutorial.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(mIntent_tutorial);
					finish();
					break;
			}
		} catch (Exception e) {
			new utils.AsyncTask_Guardar_Error().execute(new Pair<Context, String>(this,getClass().getName()+" "+e.toString()));
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	private void set_Tarifa_SharedPreferences(int tarifa) {
		try {

			SharedPreferences.Editor editor = prefs.edit();
			editor.putInt(getResources().getString(R.string.TAG_TARIFA_ACTUAL), tarifa);
			editor.apply();
		} catch (Exception e) {
			new utils.AsyncTask_Guardar_Error().execute(new Pair(this,e.toString()));
		}

	}
	private void inicializa_radiobuttons() {

		try {
			switch (prefs.getInt(getResources().getString(R.string.TAG_TARIFA_ACTUAL), INDICADOR_TARIFA_20A)) {
				case INDICADOR_TARIFA_20A:
					RadioButton_tarifa_20A.setChecked(true);
					break;
				case INDICADOR_TARIFA_20DHA:
					RadioButton_tarifa_20DHA.setChecked(true);
					break;
				case INDICADOR_TARIFA_20DHS:
					RadioButton_tarifa_20DHS.setChecked(true);
					break;
				default:
					RadioButton_tarifa_20A.setChecked(true);
					break;
			}

			if ((prefs.getBoolean(getResources().getString(R.string.TAG_HACE_UNA_SEMANA), false))){
				RadioButton_hace_una_semana.setChecked(true);
			}else if  ((prefs.getBoolean(getResources().getString(R.string.TAG_HACE_UN_AÑO), false))){
				RadioButton_hace_un_año.setChecked(true);
			}else{
				RadioButton_no_comparar.setChecked(true);
			}
			switch (prefs.getInt(getResources().getString(R.string.TAG_TARIFA_ACTUAL), INDICADOR_TARIFA_20A)) {
				case INDICADOR_TARIFA_20A:
					RadioButton_tarifa_20A.setChecked(true);
					break;
				case INDICADOR_TARIFA_20DHA:
					RadioButton_tarifa_20DHA.setChecked(true);
					break;
				case INDICADOR_TARIFA_20DHS:
					RadioButton_tarifa_20DHS.setChecked(true);
					break;
				default:
					RadioButton_tarifa_20A.setChecked(true);
					break;
			}
		} catch (Exception e) {
			new utils.AsyncTask_Guardar_Error().execute(new Pair(this,e.toString()));
		}
	}

	private void setup_views(){
		RadioButton_tarifa_20A= findViewById(R.id.radioButton_tarifa20A);
		RadioButton_tarifa_20DHA= findViewById(R.id.radioButton_tarifa20DHA);
		RadioButton_tarifa_20DHS= findViewById(R.id.radioButton_tarifa20DHS);
		TextView_Configuracion= findViewById(R.id.TextView_Configuracion);
		Button_aceptar= findViewById(R.id.boton_aceptar);
		RadioButton_hace_una_semana= findViewById(R.id.RadioButton_hace_una_semana);
		RadioButton_hace_un_año= findViewById(R.id.RadioButton_hace_un_año);
		RadioButton_no_comparar= findViewById(R.id.RadioButton_no_comparar);

	}
	private void setTypefaces() {
		Typeface typeFace_roboto_light = Typeface.createFromAsset(this.getAssets(), "fonts/Roboto-Light.ttf");
		Typeface typeFace_roboto_bold = Typeface.createFromAsset(this.getAssets(), "fonts/Roboto-Bold.ttf");

		TextView_Configuracion.setTypeface(typeFace_roboto_bold);
		RadioButton_tarifa_20A.setTypeface(typeFace_roboto_light);
		RadioButton_tarifa_20DHA.setTypeface(typeFace_roboto_light);
		RadioButton_tarifa_20DHS.setTypeface(typeFace_roboto_light);
		RadioButton_hace_un_año.setTypeface(typeFace_roboto_light);
		RadioButton_hace_una_semana.setTypeface(typeFace_roboto_light);
		RadioButton_no_comparar.setTypeface(typeFace_roboto_light);

	}
	private boolean isNetworkAvailable() {
		ConnectivityManager connectivityManager
				= (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		assert connectivityManager != null;
		NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
		return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}
}
