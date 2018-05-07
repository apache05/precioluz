package com.rubisoft.precioluz2.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import com.rubisoft.precioluz2.Dialogs.tutorialDialog;

public class Configuracion extends AppCompatActivity {

	SharedPreferences prefs;
	RadioButton RadioButton_tarifa_20A=null;
	RadioButton RadioButton_tarifa_20DHA=null;
	RadioButton RadioButton_tarifa_20DHS=null;
	CheckBox CheckBox_hace_una_semana=null;
	CheckBox CheckBox_hace_un_año=null;
	TextView TextView_Configuracion=null;
	private final int INDICADOR_TARIFA_20A = 1013;
	private final int INDICADOR_TARIFA_20DHA = 1014;
	private final int INDICADOR_TARIFA_20DHS = 1015;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_configuracion);
		setTypefaces();
		RadioButton_tarifa_20A= (RadioButton)findViewById(R.id.radioButton_tarifa20A);
		 RadioButton_tarifa_20DHA= (RadioButton)findViewById(R.id.radioButton_tarifa20DHA);
		 RadioButton_tarifa_20DHS= (RadioButton)findViewById(R.id.radioButton_tarifa20DHS);
		TextView_Configuracion= (RadioButton)findViewById(R.id.TextView_Configuracion);
		Button boton_aceptar= (Button)findViewById(R.id.boton_aceptar);
		CheckBox_hace_una_semana=(CheckBox)findViewById(R.id.checkBox_hace_una_semana);
		CheckBox_hace_un_año=(CheckBox)findViewById(R.id.checkBox_hace_un_año);
		prefs = PreferenceManager.getDefaultSharedPreferences(this);
		inicializa_radiobuttons();
		inicializa_checkboxes();
		boton_aceptar.setOnClickListener(new View.OnClickListener() {
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

		CheckBox_hace_una_semana.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				CheckBox checkBox = (CheckBox)v;
				SharedPreferences.Editor editor = prefs.edit();
				editor.putBoolean(getResources().getString(R.string.TAG_HACE_UNA_SEMANA), checkBox.isChecked());
				editor.apply();
			}
		});

		CheckBox_hace_un_año.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				CheckBox checkBox = (CheckBox)v;
				SharedPreferences.Editor editor = prefs.edit();
				editor.putBoolean(getResources().getString(R.string.TAG_HACE_UN_AÑO), checkBox.isChecked());
				editor.apply();
			}
		});
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

	public  void set_Tarifa_SharedPreferences(int tarifa) {
		/*********** guarda la ultima tarifa que ha seleccioado el usuario***********/
		try {

			SharedPreferences.Editor editor = prefs.edit();
			editor.putInt(getResources().getString(R.string.TAG_TARIFA_ACTUAL), tarifa);
			editor.apply();
		} catch (Exception e) {
			e.toString();
		}

	}
	public  String inicializa_radiobuttons() {
		String tarifa_actual="";

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
		} catch (Exception e) {
			e.toString();
		}
		return tarifa_actual;

	}
	public void inicializa_checkboxes(){
		CheckBox_hace_una_semana.setChecked(prefs.getBoolean(getString(R.string.TAG_HACE_UNA_SEMANA),false));
		CheckBox_hace_un_año.setChecked(prefs.getBoolean(getString(R.string.TAG_HACE_UN_AÑO),false));

	}
	private void setTypefaces() {
		Typeface typeFace_roboto_light = Typeface.createFromAsset(this.getAssets(), "fonts/Roboto-Light.ttf");
		Typeface typeFace_roboto_bold = Typeface.createFromAsset(this.getAssets(), "fonts/Roboto-Bold.ttf");

		TextView_Configuracion.setTypeface(typeFace_roboto_bold);
		RadioButton_tarifa_20A.setTypeface(typeFace_roboto_light);
		RadioButton_tarifa_20DHA.setTypeface(typeFace_roboto_light);
		RadioButton_tarifa_20DHS.setTypeface(typeFace_roboto_light);
		CheckBox_hace_un_año.setTypeface(typeFace_roboto_light);
		CheckBox_hace_una_semana.setTypeface(typeFace_roboto_light);
	}

}
