package com.rubisoft.precioluz.Activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rubisoft.precioluz.Clases.SugerenciaClass;
import com.rubisoft.precioluz.utils.utils;

import java.util.Calendar;

public class ValorarActivity extends AppCompatActivity {
	private TextView TextView_titulo;
	private RadioGroup RadioGroup_me_gusta;
	private Button Button_enviar_feedback;
	private TextView Button_rate_app;
	private EditText EditText_Feedback;
	private RadioButton RadioButton_si_me_gusta;
	private RadioButton RadioButton_no_me_gusta;
	private TextView TextView_rate_app;
	private Toolbar toolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try {
			super.onCreate(savedInstanceState);
			if (isNetworkAvailable()) {

				setContentView(R.layout.layout_valorar);
				setup_Views();
				setTypefaces();

				FirebaseApp.initializeApp(this);
				RadioGroup_me_gusta.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(RadioGroup radioGroup, int i) {
						switch (i) {
							case R.id.Layout_feedback_RadioButton_si_me_gusta:
								EditText_Feedback.setVisibility(View.INVISIBLE);
								Button_enviar_feedback.setVisibility(View.INVISIBLE);

								Button_rate_app.setVisibility(View.VISIBLE);
								TextView_rate_app.setVisibility(View.VISIBLE);

								break;
							case R.id.Layout_feedback_RadioButton_no_me_gusta:
								EditText_Feedback.setVisibility(View.VISIBLE);
								Button_enviar_feedback.setVisibility(View.VISIBLE);

								Button_rate_app.setVisibility(View.INVISIBLE);
								TextView_rate_app.setVisibility(View.INVISIBLE);
								break;
						}
					}
				});

				//Creamos un listener para el botón de rate app
				Button_rate_app.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						try {
							Intent intent = new Intent(Intent.ACTION_VIEW);
							intent.setData(Uri.parse("market://details?id=" + getApplication().getPackageName()));
							startActivity(intent);
						} catch (Exception e) { //google play app is not installed
							Intent intent = new Intent(Intent.ACTION_VIEW);
							intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=" + getApplication().getPackageName()));
							startActivity(intent);
						}
					}
				});

				Button_enviar_feedback.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {

						if (EditText_Feedback.getText().toString().isEmpty()) {
							Toast.makeText(getApplicationContext(), "Por favor, escribe un comentario", Toast.LENGTH_LONG).show();
						} else {
							new AsyncTask_Guardar_Feedback().execute((EditText_Feedback.getText().toString()));//Actualizamos el numero de estrellas para que aparezca en el navigation drawer

							Toast.makeText(getApplicationContext(), "Texto enviado", Toast.LENGTH_LONG).show();
							Intent mIntent = new Intent(getApplicationContext(), MainActivity.class);
							mIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
							startActivity(mIntent);
							finish();
						}
					}
				});
				setup_toolbar();

			} else {
				Intent mIntent = new Intent(this, ErrorActivity.class);
				mIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(mIntent);
				finish();
			}
		}catch (Exception e){
			new utils.AsyncTask_Guardar_Error().execute(new Pair(this,e.toString()));
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
			new utils.AsyncTask_Guardar_Error().execute(new Pair(this,e.toString()));

		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Intent mIntent = new Intent(this, MainActivity.class);
		mIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(mIntent);
		finish();
	}
	private class AsyncTask_Guardar_Feedback extends AsyncTask<String, Void, Void> {

		@Override
		protected void onPreExecute() {
		}

		@Nullable
		@Override
		protected Void doInBackground(String... params) {
			try {
				Calendar hoy = Calendar.getInstance();
				Long id= hoy.getTimeInMillis();
				FirebaseDatabase database = FirebaseDatabase.getInstance();
				DatabaseReference myRef = database.getReference("Sugerencias");
				SugerenciaClass una_sugerencia= new SugerenciaClass(params[0],getResources().getString(R.string.version));
				myRef.child(id.toString()).setValue(una_sugerencia);

			} catch (Exception e) {
				new utils.AsyncTask_Guardar_Error().execute(new Pair<>(getApplicationContext(), e.toString()));
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void resultado) {
		}
	}

	private void setup_Views() {
		EditText_Feedback = findViewById(R.id.Layout_feedback_EditText_feedback);

		TextView_titulo = findViewById(R.id.Layout_feedback_TextView_titulo);
		Button_enviar_feedback = findViewById(R.id.Layout_feedback_Button_enviar_feedback);
		Button_rate_app = findViewById(R.id.Layout_feedback_Button_rate_app);
		RadioGroup_me_gusta = findViewById(R.id.Layout_feedback_RadioGroup_me_gusta);
		RadioButton_si_me_gusta = findViewById(R.id.Layout_feedback_RadioButton_si_me_gusta);
		RadioButton_no_me_gusta = findViewById(R.id.Layout_feedback_RadioButton_no_me_gusta);

		TextView_rate_app= findViewById(R.id.Layout_feedback_TextView_rate_app);

	}

	private void setTypefaces() {
		Typeface typeFace_roboto_light = Typeface.createFromAsset(this.getAssets(), "fonts/Roboto-Light.ttf");
		Typeface typeFace_roboto_bold = Typeface.createFromAsset(this.getAssets(), "fonts/Roboto-Bold.ttf");

		TextView_titulo.setTypeface(typeFace_roboto_bold);
		Button_rate_app.setTypeface(typeFace_roboto_light);
		Button_rate_app.setTypeface(typeFace_roboto_light);
		RadioButton_no_me_gusta.setTypeface(typeFace_roboto_light);
		RadioButton_si_me_gusta.setTypeface(typeFace_roboto_light);

		EditText_Feedback.setTypeface(typeFace_roboto_light);

		Button_enviar_feedback.setTypeface(typeFace_roboto_light);
		Button_rate_app.setTypeface(typeFace_roboto_light);
	}

	private boolean isNetworkAvailable() {
		ConnectivityManager connectivityManager
				= (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		assert connectivityManager != null;
		NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
		return activeNetworkInfo != null && activeNetworkInfo.isConnected();
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
