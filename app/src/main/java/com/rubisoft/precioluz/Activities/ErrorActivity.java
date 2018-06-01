package com.rubisoft.precioluz.Activities;

import android.content.Context;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.rubisoft.precioluz.utils.utils;

public class ErrorActivity extends AppCompatActivity {
	private Toolbar toolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_error);
		TextView TextView_error = findViewById(R.id.texto_error);
		TextView_error.setText("ErrorActivity \n no hay conexión a Internet");
		setup_toolbar();

	}

	private void setup_toolbar() {
		try {
			// Setup toolbar and statusBar (really FrameLayout)
			toolbar = findViewById(R.id.mToolbar);
			setSupportActionBar(toolbar);
			getSupportActionBar().setTitle(getResources().getString(R.string.app_name));
			getSupportActionBar().setHomeButtonEnabled(true);
		} catch (Exception e) {
			new utils.AsyncTask_Guardar_Error().execute(new Pair<Context, String>(this,getClass().getName()+" "+e.toString()));
		}
	}
}
