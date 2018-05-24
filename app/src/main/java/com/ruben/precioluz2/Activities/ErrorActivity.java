package com.ruben.precioluz2.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ErrorActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_error);
		TextView TextView_error = findViewById(R.id.texto_error);
		TextView_error.setText("ErrorActivity \n no hay conexión a Internet");

	}
}
