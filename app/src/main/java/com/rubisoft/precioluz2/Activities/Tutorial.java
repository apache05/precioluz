package com.rubisoft.precioluz2.Activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.rubisoft.precioluz2.utils.utils;

public class Tutorial extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tutorial);

		TextView TextView_titulo= findViewById(R.id.app_name);
		TextView TextView_tutorial= findViewById(R.id.texto_tutorial);
		Typeface typeFace_roboto_light = Typeface.createFromAsset(this.getAssets(), "fonts/Roboto-Light.ttf");
		Typeface typeFace_roboto_bold = Typeface.createFromAsset(this.getAssets(), "fonts/Roboto-Bold.ttf");

		TextView_titulo.setTypeface(typeFace_roboto_bold);
		TextView_tutorial.setTypeface(typeFace_roboto_light);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
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
					Intent mIntent_tutorial = new Intent(this, Tutorial.class);
					mIntent_tutorial.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(mIntent_tutorial);
					finish();
					break;
			}
		} catch (Exception e) {
			new utils.AsyncTask_Guardar_Error().execute(new Pair<Context, String>(this,e.toString()));
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

}
