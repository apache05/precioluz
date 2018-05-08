package com.rubisoft.precioluz2.utils;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rubisoft.precioluz2.Activities.R;
import com.rubisoft.precioluz2.Clases.Error;

import java.util.Calendar;



public class utils {

	public static class AsyncTask_Guardar_Error extends AsyncTask<Pair<Context,String>, Void, Void> {

		@Override
		protected void onPreExecute() {
		}

		@SafeVarargs
		@Nullable
		@Override
		protected final Void doInBackground(Pair<Context, String>... params) {
			try {
				String detalle_error=params[0].second;
				Context context= params[0].first;
				Calendar hoy = Calendar.getInstance();
				Long id= hoy.getTimeInMillis();
				FirebaseDatabase database = FirebaseDatabase.getInstance();
				DatabaseReference myRef = database.getReference("Errores");
				Error un_error= new Error(detalle_error, context != null ? context.getResources().getString(R.string.version) : null);
				myRef.child(id.toString()).setValue(un_error);

			} catch (Exception ignored) {
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void resultado) {
		}
	}

}
