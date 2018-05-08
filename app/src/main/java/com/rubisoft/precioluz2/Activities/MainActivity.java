package com.rubisoft.precioluz2.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.rubisoft.precioluz2.Adapters.MyFragmentPagerAdapter;
import com.rubisoft.precioluz2.Fragments.ErrorFragment;
import com.rubisoft.precioluz2.Fragments.GraficasFragment_Landscape;
import com.rubisoft.precioluz2.Fragments.GraficasFragment_Portrait;
import com.rubisoft.precioluz2.utils.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {
    private ViewPager pager = null;
	private final Logger LOGGER = Logger.getLogger(getClass().getName());
    private ProgressBar mProgressBar;

    private SharedPreferences prefs = null;

    //private TutorialFragment fragment_tutorial;
    private GraficasFragment_Landscape fragment_hoy_Landscape;
    private GraficasFragment_Landscape fragment_mañana_Landscape;
    private GraficasFragment_Portrait fragment_hoy_Portrait;
    private GraficasFragment_Portrait fragment_mañana_Portrait;

    // private final Double version_de_esta_instancia= 1.1;

    private final int INDICADOR_TARIFA_20A = 1013;
    private final int INDICADOR_TARIFA_20DHA = 1014;
    private final int INDICADOR_TARIFA_20DHS = 1015;

    private final Float[] precios_hoy_tarifa_20A = new Float[24];
    private final Float[] precios_hoy_tarifa_20DHA = new Float[24];
    private final Float[] precios_hoy_tarifa_20DHS = new Float[24];
    private final Float[] precios_mañana_tarifa_20A = new Float[24];
    private final Float[] precios_mañana_tarifa_20DHA = new Float[24];
    private final Float[] precios_mañana_tarifa_20DHS = new Float[24];
    private final Float[] precios_hace_una_semana_de_hoy_tarifa_20A = new Float[24];
    private final Float[] precios_hace_una_semana_de_hoy_tarifa_20DHA = new Float[24];
    private final Float[] precios_hace_una_semana_de_hoy_tarifa_20DHS = new Float[24];
    private final Float[] precios_hace_un_año_de_hoy_tarifa_20A = new Float[24];
    private final Float[] precios_hace_un_año_de_hoy_tarifa_20DHA = new Float[24];
    private final Float[] precios_hace_un_año_de_hoy_tarifa_20DHS = new Float[24];
    private final Float[] precios_hace_una_semana_de_mañana_tarifa_20A = new Float[24];
    private final Float[] precios_hace_una_semana_de_mañana_tarifa_20DHA = new Float[24];
    private final Float[] precios_hace_una_semana_de_mañana_tarifa_20DHS = new Float[24];
    private final Float[] precios_hace_un_año_de_mañana_tarifa_20A = new Float[24];
    private final Float[] precios_hace_un_año_de_mañana_tarifa_20DHA = new Float[24];
    private final Float[] precios_hace_un_año_de_mañana_tarifa_20DHS = new Float[24];

    private final int HOY = 0;
    private final int MAÑANA = 1;
    private final int HACE_UNA_SEMANA_DE_HOY = 2;
    private final int HACE_UN_AÑO_DE_HOY = 3;
    private final int HACE_UNA_SEMANA_DE_MAÑANA = 4;
    private final int HACE_UN_AÑO_DE_MAÑANA = 5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);

        super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);


        try {
            //Con esto creamos el ViewPager e instanciamos los dos fragments de graficas
            pager = findViewById(R.id.pager);
            mProgressBar = findViewById(R.id.mProgressBar);

            if (isNetworkAvailable()) {

				prefs = PreferenceManager.getDefaultSharedPreferences(this);
                crea_fragments();

                new AsyncTask_getPrecios_hoy().execute(prefs.getInt(getResources().getString(R.string.TAG_TARIFA_ACTUAL), INDICADOR_TARIFA_20A));
                new AsyncTask_getPrecios_mañana().execute(prefs.getInt(getResources().getString(R.string.TAG_TARIFA_ACTUAL), INDICADOR_TARIFA_20A));

            } else {
                ErrorFragment fragment_error1;
                ErrorFragment fragment_error2;
                MyFragmentPagerAdapter adapter_error;
                adapter_error = new MyFragmentPagerAdapter(getSupportFragmentManager());
                fragment_error1 = ErrorFragment.newInstance(1);
                fragment_error2 = ErrorFragment.newInstance(2);
                adapter_error.addFragment(fragment_error1);
                adapter_error.addFragment(fragment_error2);
                pager.setAdapter(adapter_error);
            }
        } catch (Exception e) {
            new utils.AsyncTask_Guardar_Error().execute(new Pair<>(getApplicationContext(),e.toString()));
        }
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        try {
            final Intent mIntent = getIntent();
            mIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(mIntent);
            finish();
        }catch (Exception e){
            new utils.AsyncTask_Guardar_Error().execute(new Pair<Context, String>(this,e.toString()));

        }
    }

    private void crea_fragments() {

        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("dd-MM-yyyy", new Locale("es", "ES"));
        Float[] array_vacio = new Float[24];
        try {
            boolean boolean_precios_semana_pasada = prefs.getBoolean(getString(R.string.TAG_HACE_UNA_SEMANA), false);
            //boolean boolean_precios_semana_pasada_de_mañana = prefs.getBoolean(getString(R.string.TAG_HACE_UNA_SEMANA_DE_MAÑANA), false);
            boolean boolean_precios_año_pasado = prefs.getBoolean(getString(R.string.TAG_HACE_UN_AÑO), false);
            //boolean boolean_precios_año_pasado_de_mañana = prefs.getBoolean(getString(R.string.TAG_HACE_UN_AÑO_DE_MAÑANA), false);

			MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager());

                for (int i = 0; i < 24; i++) {
                    array_vacio[i] = -1.0f;
                }

                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    fragment_hoy_Landscape = GraficasFragment_Landscape.newInstance("Precios para hoy " + hoy(mSimpleDateFormat) + "\n" + get_Tarifa_SharedPreferences(), array_vacio, boolean_precios_semana_pasada, boolean_precios_año_pasado, array_vacio, array_vacio);
                    fragment_mañana_Landscape = GraficasFragment_Landscape.newInstance("Precios para mañana " + mañana(mSimpleDateFormat) + "\n" + get_Tarifa_SharedPreferences(), array_vacio, boolean_precios_semana_pasada, boolean_precios_año_pasado, array_vacio, array_vacio);

                    adapter.addFragment(fragment_hoy_Landscape);
                    adapter.addFragment(fragment_mañana_Landscape);
                } else {
                    fragment_hoy_Portrait = GraficasFragment_Portrait.newInstance("Precios para hoy " + hoy(mSimpleDateFormat) + "\n" + get_Tarifa_SharedPreferences(), array_vacio, boolean_precios_semana_pasada, boolean_precios_año_pasado, array_vacio, array_vacio);
                    fragment_mañana_Portrait = GraficasFragment_Portrait.newInstance("Precios para mañana " + mañana(mSimpleDateFormat) + "\n" + get_Tarifa_SharedPreferences(), array_vacio, boolean_precios_semana_pasada, boolean_precios_año_pasado, array_vacio, array_vacio);

                    adapter.addFragment(fragment_hoy_Portrait);
                    adapter.addFragment(fragment_mañana_Portrait);
                }
                pager.setAdapter(adapter);

            //PONEMOS EL PAGER QUE HABIA UTILIZADO POR ULTIMA VEZ EL USUARIO
            pager.setCurrentItem(get_Fragment_actual_SharedPreferences());
        } catch (Exception e) {
            new utils.AsyncTask_Guardar_Error().execute(new Pair<Context, String>(this,e.toString()));
        }
    }

    private void carga_datos_inicial_hoy(int tarifa_actual) {
        try {
            if (getResources().getConfiguration().orientation ==Configuration.ORIENTATION_LANDSCAPE) {
                fragment_hoy_Landscape.set_todos_los_precios(getPrecios_hoy_segun_tarifa(tarifa_actual), getPrecios_hace_un_año_de_hoy_segun_tarifa(tarifa_actual), getPrecios_hace_una_semana_de_hoy_segun_tarifa(tarifa_actual));
            } else {
                fragment_hoy_Portrait.set_todos_los_precios(getPrecios_hoy_segun_tarifa(tarifa_actual), getPrecios_hace_un_año_de_hoy_segun_tarifa(tarifa_actual), getPrecios_hace_una_semana_de_hoy_segun_tarifa(tarifa_actual));
            }
            PagerAdapter un_adapter= pager.getAdapter();
			if (un_adapter!= null){un_adapter.notifyDataSetChanged();}
        } catch (Exception e) {
            new utils.AsyncTask_Guardar_Error().execute(new Pair<Context, String>(this,e.toString()));
        }
    }

    private void carga_datos_inicial_mañana(int tarifa_actual) {
        try {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                fragment_mañana_Landscape.set_todos_los_precios(getPrecios_mañana_segun_tarifa(tarifa_actual), getPrecios_hace_un_año_de_mañana_segun_tarifa(tarifa_actual), getPrecios_hace_una_semana_de_mañana_segun_tarifa(tarifa_actual));
            } else {
                fragment_mañana_Portrait.set_todos_los_precios(getPrecios_mañana_segun_tarifa(tarifa_actual), getPrecios_hace_un_año_de_mañana_segun_tarifa(tarifa_actual), getPrecios_hace_una_semana_de_mañana_segun_tarifa(tarifa_actual));
            }
			PagerAdapter un_adapter= pager.getAdapter();
			if (un_adapter!= null){un_adapter.notifyDataSetChanged();}
        } catch (Exception e) {
            new utils.AsyncTask_Guardar_Error().execute(new Pair<Context, String>(this,e.toString()));
        }
    }

    private void set_Fragment_actual_SharedPreferences(int Fragment_actual) {
		try {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt(getResources().getString(R.string.FRAGMENT_ACTUAL), Fragment_actual);
            editor.apply();
        } catch (Exception e) {
            new utils.AsyncTask_Guardar_Error().execute(new Pair<Context, String>(this,e.toString()));
        }
    }

    private int get_Fragment_actual_SharedPreferences() {
		int fragment_actual = 0;
        try {
            fragment_actual = prefs.getInt(getResources().getString(R.string.FRAGMENT_ACTUAL), 0);
        } catch (Exception e) {
            new utils.AsyncTask_Guardar_Error().execute(new Pair<Context, String>(this,e.toString()));
        }
        return fragment_actual;

    }

    private String hoy(SimpleDateFormat mSimpleDateFormat) {
        Calendar mCalendar = Calendar.getInstance();
        return mSimpleDateFormat.format(mCalendar.getTime());
    }

    private String mañana(SimpleDateFormat mSimpleDateFormat) {
        Calendar mCalendar = new GregorianCalendar();
        mCalendar.add(Calendar.DAY_OF_YEAR, 1);
        return mSimpleDateFormat.format(mCalendar.getTime());
    }

    private Float[] getPrecios_hoy_segun_tarifa(int tarifa) {
        Float[] precios = null;
        switch (tarifa) {
            case  INDICADOR_TARIFA_20A:
                precios = precios_hoy_tarifa_20A;
                break;
            case INDICADOR_TARIFA_20DHA:
                precios = precios_hoy_tarifa_20DHA;
                break;
            case INDICADOR_TARIFA_20DHS:
                precios = precios_hoy_tarifa_20DHS;
                break;
        }
        return precios;
    }

    private Float[] getPrecios_mañana_segun_tarifa(int tarifa) {
        Float[] precios = null;
        switch (tarifa) {
            case  INDICADOR_TARIFA_20A:
                precios = precios_mañana_tarifa_20A;
                break;
            case INDICADOR_TARIFA_20DHA:
                precios = precios_mañana_tarifa_20DHA;
                break;
            case INDICADOR_TARIFA_20DHS:
                precios = precios_mañana_tarifa_20DHS;
                break;
        }
        return precios;
    }

    private Float[] getPrecios_hace_una_semana_de_hoy_segun_tarifa(int tarifa) {
        Float[] precios = null;
        switch (tarifa) {
            case  INDICADOR_TARIFA_20A:
                precios = precios_hace_una_semana_de_hoy_tarifa_20A;
                break;
            case INDICADOR_TARIFA_20DHA:
                precios = precios_hace_una_semana_de_hoy_tarifa_20DHA;
                break;
            case INDICADOR_TARIFA_20DHS:
                precios = precios_hace_una_semana_de_hoy_tarifa_20DHS;
                break;
        }
        return precios;
    }

    private Float[] getPrecios_hace_una_semana_de_mañana_segun_tarifa(int tarifa) {
        Float[] precios = null;
        switch (tarifa) {
            case INDICADOR_TARIFA_20A:
                precios = precios_hace_una_semana_de_mañana_tarifa_20A;
                break;
            case INDICADOR_TARIFA_20DHA:
                precios = precios_hace_una_semana_de_mañana_tarifa_20DHA;
                break;
            case INDICADOR_TARIFA_20DHS:
                precios = precios_hace_una_semana_de_mañana_tarifa_20DHS;
                break;
        }
        return precios;
    }

    private Float[] getPrecios_hace_un_año_de_hoy_segun_tarifa(int tarifa) {
        Float[] precios = null;
        switch (tarifa) {
            case  INDICADOR_TARIFA_20A:
                precios = precios_hace_un_año_de_hoy_tarifa_20A;
                break;
            case  INDICADOR_TARIFA_20DHA:
                precios = precios_hace_un_año_de_hoy_tarifa_20DHA;
                break;
            case INDICADOR_TARIFA_20DHS:
                precios = precios_hace_un_año_de_hoy_tarifa_20DHS;
                break;
        }
        return precios;
    }

    private Float[] getPrecios_hace_un_año_de_mañana_segun_tarifa(int tarifa) {
        Float[] precios = null;
        switch (tarifa) {
            case INDICADOR_TARIFA_20A:
                precios = precios_hace_un_año_de_mañana_tarifa_20A;
                break;
            case INDICADOR_TARIFA_20DHA:
                precios = precios_hace_un_año_de_mañana_tarifa_20DHA;
                break;
            case INDICADOR_TARIFA_20DHS:
                precios = precios_hace_un_año_de_mañana_tarifa_20DHS;
                break;
        }
        return precios;
    }

    private String get_Tarifa_SharedPreferences() {
        String tarifa_actual = "TARIFA 20A";
        try {
            switch (prefs.getInt(getResources().getString(R.string.TAG_TARIFA_ACTUAL), INDICADOR_TARIFA_20A)) {
                case INDICADOR_TARIFA_20A:
                    tarifa_actual = "TARIFA 20A";
                    break;
                case INDICADOR_TARIFA_20DHA:
                    tarifa_actual = "TARIFA 20DHA";
                    break;
                case INDICADOR_TARIFA_20DHS:
                    tarifa_actual = "TARIFA 20DHS";
                    break;
                default:
                    tarifa_actual = "TARIFA 20A";
                    break;
            }
        } catch (Exception e) {
            new utils.AsyncTask_Guardar_Error().execute(new Pair<Context, String>(this,e.toString()));
        }
        return tarifa_actual;

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		assert connectivityManager != null;
		NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int fragment_actual = this.pager.getCurrentItem();

        try {

            set_Fragment_actual_SharedPreferences(fragment_actual);

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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //Guardamos el fragment actual para que no salga siempre el tutorial inicial

        set_Fragment_actual_SharedPreferences(this.pager.getCurrentItem());
    }

    @Override
    protected void onStop() {
        super.onStop();
        set_Fragment_actual_SharedPreferences(this.pager.getCurrentItem());
    }

    private class AsyncTask_getPrecios_hoy extends AsyncTask<Integer, Void, Pair> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressBar.setVisibility(View.VISIBLE);
            mProgressBar.invalidate();
        }

        @Override
        protected Pair doInBackground(Integer... params) {
            JSONArray[] mJSONObject=new JSONArray[3];
            Integer indicador= params[0];

            try {
                if (isNetworkAvailable()) {
                    Calendar mCalendar=Calendar.getInstance();

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
                    mJSONObject[0] = aux_02;

					mCalendar.add(Calendar.DAY_OF_YEAR, -7);
                    int año_1=mCalendar.get(Calendar.YEAR);
                    String mes_1=String.format("%02d", mCalendar.get(Calendar.MONTH)+1);
                    int dia_1=mCalendar.get(Calendar.DAY_OF_MONTH);

                    URL mURL_para_hace_una_semana = new URL("https://api.esios.ree.es/indicators/"+indicador+"?start_date="+año_1+"-"+mes_1+"-"+dia_1+"T00:00:00&end_date="+año_1+"-"+mes_1+"-"+dia_1+"T23:59:59");

                    HttpURLConnection mHttpURLConnection_1= (HttpURLConnection) mURL_para_hace_una_semana.openConnection();
                    mHttpURLConnection_1.setRequestMethod("GET");
                    mHttpURLConnection_1.setRequestProperty("Accept","application/json; application/vnd.esios-api-v1+json");
                    mHttpURLConnection_1.setRequestProperty("Content-type","application/json");
                    mHttpURLConnection_1.setRequestProperty("Host","api.esios.ree.es");
                    mHttpURLConnection_1.setRequestProperty("Authorization","Token token=\""+getResources().getString(R.string.TOKEN_ESIOS)+"\"");
                    mHttpURLConnection_1.setRequestProperty("Cookie"," ");

                    InputStream mInputStream_1= mHttpURLConnection_1.getInputStream();

                    JSONParser jsonParser_1 = new JSONParser();
                    JSONObject aux_11= (JSONObject)jsonParser_1.parse(new InputStreamReader(mInputStream_1, "UTF-8"));
                    JSONArray aux_12=((JSONArray)((JSONObject)aux_11.get("indicator")).get("values"));
                    mJSONObject[1] = aux_12;

					mCalendar.add(Calendar.YEAR, -1);
                    int año_2=mCalendar.get(Calendar.YEAR);
                    String mes_2=String.format("%02d", mCalendar.get(Calendar.MONTH)+1);
                    int dia_2=mCalendar.get(Calendar.DAY_OF_MONTH);

                    URL mURL_para_hace_un_año = new URL("https://api.esios.ree.es/indicators/"+indicador+"?start_date="+año_2+"-"+mes_2+"-"+dia_2+"T00:00:00&end_date="+año_2+"-"+mes_2+"-"+dia_2+"T23:59:59");

                    HttpURLConnection mHttpURLConnection_2= (HttpURLConnection) mURL_para_hace_un_año.openConnection();
                    mHttpURLConnection_2.setRequestMethod("GET");
                    mHttpURLConnection_2.setRequestProperty("Accept","application/json; application/vnd.esios-api-v1+json");
                    mHttpURLConnection_2.setRequestProperty("Content-type","application/json");
                    mHttpURLConnection_2.setRequestProperty("Host","api.esios.ree.es");
                    mHttpURLConnection_2.setRequestProperty("Authorization","Token token=\""+getResources().getString(R.string.TOKEN_ESIOS)+"\"");
                    mHttpURLConnection_2.setRequestProperty("Cookie"," ");

                    InputStream mInputStream_2= mHttpURLConnection_2.getInputStream();

                    JSONParser jsonParser_2 = new JSONParser();
                    JSONObject aux_21= (JSONObject)jsonParser_2.parse(new InputStreamReader(mInputStream_2, "UTF-8"));
                    JSONArray aux_22=((JSONArray)((JSONObject)aux_21.get("indicator")).get("values"));
                    mJSONObject[2] = aux_22;

                }
            } catch (Exception e) {
                LOGGER.severe("Error en AsyncTask_getPrecios (doInBackground) de MainActivity " + e.toString());
            }

            return new Pair(indicador,mJSONObject);
        }

        @Override
        protected void onPostExecute(Pair mPair) {
            try {
                Integer indicador= (Integer)mPair.first;
                JSONArray[] mJSONArray= (JSONArray[])mPair.second;
                ArrayList<Float> precios_hoy=new ArrayList<Float>() {};
                ArrayList<Float> precios_hace_una_semana=new ArrayList<Float>() {};
                ArrayList<Float> precios_hace_un_año=new ArrayList<Float>() {};
                for (int i = 0; i < 24; i++) {
                    JSONObject mJSONObject_0 = (JSONObject)mJSONArray[0].get(i);
                    JSONObject mJSONObject_1 = (JSONObject)mJSONArray[1].get(i);
                    JSONObject mJSONObject_2 = (JSONObject)mJSONArray[2].get(i);

                    precios_hoy.add(i,((Double)mJSONObject_0.get("value")).floatValue()/1000);
                    precios_hace_una_semana.add(i,((Double)mJSONObject_1.get("value")).floatValue()/1000);
                    precios_hace_un_año.add(i,((Double)mJSONObject_2.get("value")).floatValue()/1000);
                }
                switch (indicador){
                    case 1013:
                        guarda_precios_20A(HOY,precios_hoy);
                        guarda_precios_20A(HACE_UNA_SEMANA_DE_HOY,precios_hace_una_semana);
                        guarda_precios_20A(HACE_UN_AÑO_DE_HOY,precios_hace_un_año);
                        break;
                    case 1014:
                        guarda_precios_20DHA(HOY,precios_hoy);
                        guarda_precios_20DHA(HACE_UNA_SEMANA_DE_HOY,precios_hace_una_semana);
                        guarda_precios_20DHA(HACE_UN_AÑO_DE_HOY,precios_hace_un_año);
                        break;
                    case 1015:
                        guarda_precios_20DHS(HOY,precios_hoy);
                        guarda_precios_20DHS(HACE_UNA_SEMANA_DE_HOY,precios_hace_una_semana);
                        guarda_precios_20DHS(HACE_UN_AÑO_DE_HOY,precios_hace_un_año);
                        break;
                }
                carga_datos_inicial_hoy(indicador);

            }catch (Exception e){
                new utils.AsyncTask_Guardar_Error().execute(new Pair<Context, String>(getApplicationContext(),e.toString()));
            }finally {
                mProgressBar.setVisibility(View.INVISIBLE);
                mProgressBar.invalidate();
            }
        }

        void guarda_precios_20A(int dia, List<Float> precios) {
            try {
                if (precios.size() > 0) {
                    switch (dia) {
                        case HOY:
                            for (Integer i = 0; i < 24; i++) {
                                precios_hoy_tarifa_20A[i] = precios.get(i);
                            }

                            break;

                        case HACE_UN_AÑO_DE_HOY:
                            for (Integer i = 0; i < 24; i++) {
                                precios_hace_un_año_de_hoy_tarifa_20A[i] = precios.get(i);
                            }

                            break;

                        case HACE_UNA_SEMANA_DE_HOY:
                            for (Integer i = 0; i < 24; i++) {
                                precios_hace_una_semana_de_hoy_tarifa_20A[i] = precios.get(i);
                            }

                            break;

                    }
                } else {
                    switch (dia) {
                        case HOY:
                            for (Integer i = 0; i < 24; i++) {
                                precios_hoy_tarifa_20A[i] = -1.0f;

                            }
                            break;

                        case HACE_UN_AÑO_DE_HOY:
                            for (Integer i = 0; i < 24; i++) {
                                precios_hace_un_año_de_hoy_tarifa_20A[i] = -1.0f;

                            }
                            break;

                        case HACE_UNA_SEMANA_DE_HOY:
                            for (Integer i = 0; i < 24; i++) {
                                precios_hace_una_semana_de_hoy_tarifa_20A[i] = -1.0f;

                            }
                            break;

                    }
                }
            } catch (Exception e) {
                new utils.AsyncTask_Guardar_Error().execute(new Pair<Context, String>(getApplicationContext(),e.toString()));
            }
        }
        void guarda_precios_20DHA(int dia, List<Float> precios) {
            try {
                if (precios.size() > 0) {
                    switch (dia) {
                        case HOY:

                            for (Integer i = 0; i < 24; i++) {
                                precios_hoy_tarifa_20DHA[i] = precios.get(i);
                            }

                            break;

                        case HACE_UN_AÑO_DE_HOY:

                            for (Integer i = 0; i < 24; i++) {
                                precios_hace_un_año_de_hoy_tarifa_20DHA[i] = precios.get(i);
                            }

                            break;

                        case HACE_UNA_SEMANA_DE_HOY:

                            for (Integer i = 0; i < 24; i++) {
                                precios_hace_una_semana_de_hoy_tarifa_20DHA[i] = precios.get(i);
                            }

                            break;

                    }
                } else {
                    switch (dia) {
                        case HOY:
                            for (Integer i = 0; i < 24; i++) {
                                precios_hoy_tarifa_20DHA[i] = -1.0f;
                            }
                            break;

                        case HACE_UN_AÑO_DE_HOY:
                            for (Integer i = 0; i < 24; i++) {
                                precios_hace_un_año_de_hoy_tarifa_20DHA[i] = -1.0f;
                            }
                            break;

                        case HACE_UNA_SEMANA_DE_HOY:
                            for (Integer i = 0; i < 24; i++) {
                                precios_hace_una_semana_de_hoy_tarifa_20DHA[i] = -1.0f;
                            }
                            break;
                    }
                }
            } catch (Exception e) {
                new utils.AsyncTask_Guardar_Error().execute(new Pair<Context, String>(getApplicationContext(),e.toString()));
            }
        }
        void guarda_precios_20DHS(int dia, List<Float> precios) {
            try {
                if (precios.size() > 0) {
                    switch (dia) {
                        case HOY:

                            for (Integer i = 0; i < 24; i++) {
                                precios_hoy_tarifa_20DHS[i] = precios.get(i);
                            }
                            break;

                        case HACE_UN_AÑO_DE_HOY:

                            for (Integer i = 0; i < 24; i++) {
                                precios_hace_un_año_de_hoy_tarifa_20DHS[i] = precios.get(i);
                            }
                            break;

                        case HACE_UNA_SEMANA_DE_HOY:

                            for (Integer i = 0; i < 24; i++) {
                                precios_hace_una_semana_de_hoy_tarifa_20DHS[i] = precios.get(i);
                            }
                            break;

                    }
                } else {
                    switch (dia) {
                        case HOY:
                            for (Integer i = 0; i < 24; i++) {

                                precios_hoy_tarifa_20DHS[i] = -1.0f;
                            }
                            break;

                        case HACE_UN_AÑO_DE_HOY:
                            for (Integer i = 0; i < 24; i++) {

                                precios_hace_un_año_de_hoy_tarifa_20DHS[i] = -1.0f;
                            }
                            break;

                        case HACE_UNA_SEMANA_DE_HOY:
                            for (Integer i = 0; i < 24; i++) {

                                precios_hace_una_semana_de_hoy_tarifa_20DHS[i] = -1.0f;
                            }
                            break;

                    }
                }
                //editor.commit();
            } catch (Exception e) {
                new utils.AsyncTask_Guardar_Error().execute(new Pair<Context, String>(getApplicationContext(),e.toString()));
            }
        }
    }

    private class AsyncTask_getPrecios_mañana extends AsyncTask<Integer, Void, Pair> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressBar.setVisibility(View.VISIBLE);
            mProgressBar.invalidate();
        }

        @Override
        protected Pair doInBackground(Integer... params) {
            JSONArray[] mJSONObject=new JSONArray[3];
            Integer indicador= params[0];

            try {
                if (isNetworkAvailable()) {
                    Calendar mCalendar=Calendar.getInstance();
                    mCalendar.add(Calendar.DAY_OF_YEAR, 1);  //PARA COJER EL DE MAÑANA AÑADIMOS UN DIA MAS

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
                    mJSONObject[0] = aux_02;

					mCalendar.add(Calendar.DAY_OF_YEAR, -7);
                    int año_1=mCalendar.get(Calendar.YEAR);
                    String mes_1=String.format("%02d", mCalendar.get(Calendar.MONTH)+1);
                    int dia_1=mCalendar.get(Calendar.DAY_OF_MONTH);

                    URL mURL_para_hace_una_semana = new URL("https://api.esios.ree.es/indicators/"+indicador+"?start_date="+año_1+"-"+mes_1+"-"+dia_1+"T00:00:00&end_date="+año_1+"-"+mes_1+"-"+dia_1+"T23:59:59");

                    HttpURLConnection mHttpURLConnection_1= (HttpURLConnection) mURL_para_hace_una_semana.openConnection();
                    mHttpURLConnection_1.setRequestMethod("GET");
                    mHttpURLConnection_1.setRequestProperty("Accept","application/json; application/vnd.esios-api-v1+json");
                    mHttpURLConnection_1.setRequestProperty("Content-type","application/json");
                    mHttpURLConnection_1.setRequestProperty("Host","api.esios.ree.es");
                    mHttpURLConnection_1.setRequestProperty("Authorization","Token token=\""+getResources().getString(R.string.TOKEN_ESIOS)+"\"");
                    mHttpURLConnection_1.setRequestProperty("Cookie"," ");

                    InputStream mInputStream_1= mHttpURLConnection_1.getInputStream();

                    JSONParser jsonParser_1 = new JSONParser();
                    JSONObject aux_11= (JSONObject)jsonParser_1.parse(new InputStreamReader(mInputStream_1, "UTF-8"));
                    JSONArray aux_12=((JSONArray)((JSONObject)aux_11.get("indicator")).get("values"));
                    mJSONObject[1] = aux_12;

					mCalendar.add(Calendar.YEAR, -1);
                    int año_2=mCalendar.get(Calendar.YEAR);
                    String mes_2=String.format("%02d", mCalendar.get(Calendar.MONTH)+1);
                    int dia_2=mCalendar.get(Calendar.DAY_OF_MONTH);

                    URL mURL_para_hace_un_año = new URL("https://api.esios.ree.es/indicators/"+indicador+"?start_date="+año_2+"-"+mes_2+"-"+dia_2+"T00:00:00&end_date="+año_2+"-"+mes_2+"-"+dia_2+"T23:59:59");

                    HttpURLConnection mHttpURLConnection_2= (HttpURLConnection) mURL_para_hace_un_año.openConnection();
                    mHttpURLConnection_2.setRequestMethod("GET");
                    mHttpURLConnection_2.setRequestProperty("Accept","application/json; application/vnd.esios-api-v1+json");
                    mHttpURLConnection_2.setRequestProperty("Content-type","application/json");
                    mHttpURLConnection_2.setRequestProperty("Host","api.esios.ree.es");
                    mHttpURLConnection_2.setRequestProperty("Authorization","Token token=\""+getResources().getString(R.string.TOKEN_ESIOS)+"\"");
                    mHttpURLConnection_2.setRequestProperty("Cookie"," ");

                    InputStream mInputStream_2= mHttpURLConnection_2.getInputStream();

                    JSONParser jsonParser_2 = new JSONParser();
                    JSONObject aux_21= (JSONObject)jsonParser_2.parse(new InputStreamReader(mInputStream_2, "UTF-8"));
                    JSONArray aux_22=((JSONArray)((JSONObject)aux_21.get("indicator")).get("values"));
                    mJSONObject[2] = aux_22;

                }
            } catch (Exception e) {
                LOGGER.severe("Error en AsyncTask_getPrecios (doInBackground) de MainActivity " + e.toString());
            }

            return new Pair(indicador,mJSONObject);
        }

        @Override
        protected void onPostExecute(Pair mPair) {
            try {
                Integer indicador= (Integer)mPair.first;
                JSONArray[] mJSONArray= (JSONArray[])mPair.second;
                ArrayList<Float> precios_hoy=new ArrayList<Float>() {};
                ArrayList<Float> precios_hace_una_semana=new ArrayList<Float>() {};
                ArrayList<Float> precios_hace_un_año=new ArrayList<Float>() {};
                for (int i = 0; i < 24; i++) {

                    //Puede que aún no estén disponibles los precios de mañana
                    if (mJSONArray[0].size()==24){
                        JSONObject mJSONObject_0 = (JSONObject)mJSONArray[0].get(i);
                        precios_hoy.add(i,((Double)mJSONObject_0.get("value")).floatValue()/1000);
                    }
                    JSONObject mJSONObject_1 = (JSONObject)mJSONArray[1].get(i);
                    JSONObject mJSONObject_2 = (JSONObject)mJSONArray[2].get(i);

                    precios_hace_una_semana.add(i,((Double)mJSONObject_1.get("value")).floatValue()/1000);
                    precios_hace_un_año.add(i,((Double)mJSONObject_2.get("value")).floatValue()/1000);
                }

                switch (indicador){
                    case 1013:
                        guarda_precios_20A(MAÑANA,precios_hoy);
                        guarda_precios_20A(HACE_UNA_SEMANA_DE_MAÑANA,precios_hace_una_semana);
                        guarda_precios_20A(HACE_UN_AÑO_DE_MAÑANA,precios_hace_un_año);
                        break;
                    case 1014:
                        guarda_precios_20DHA(MAÑANA,precios_hoy);
                        guarda_precios_20DHA(HACE_UNA_SEMANA_DE_MAÑANA,precios_hace_una_semana);
                        guarda_precios_20DHA(HACE_UN_AÑO_DE_MAÑANA,precios_hace_un_año);
                        break;
                    case 1015:
                        guarda_precios_20DHS(MAÑANA,precios_hoy);
                        guarda_precios_20DHS(HACE_UNA_SEMANA_DE_MAÑANA,precios_hace_una_semana);
                        guarda_precios_20DHS(HACE_UN_AÑO_DE_MAÑANA,precios_hace_un_año);
                        break;
                }
                carga_datos_inicial_mañana(indicador);

            }catch (Exception e){
                new utils.AsyncTask_Guardar_Error().execute(new Pair<Context, String>(getApplicationContext(),e.toString()));
            }finally {
                mProgressBar.setVisibility(View.INVISIBLE);
                mProgressBar.invalidate();
            }
        }


        void guarda_precios_20A(int dia, List<Float> precios) {
            try {
                if (precios.size() > 0) {
                    switch (dia) {

                        case MAÑANA:
                            for (Integer i = 0; i < 24; i++) {
                                precios_mañana_tarifa_20A[i] = precios.get(i);
                            }

                            break;

                        case HACE_UN_AÑO_DE_MAÑANA:
                            for (Integer i = 0; i < 24; i++) {
                                precios_hace_un_año_de_mañana_tarifa_20A[i] = precios.get(i);
                            }

                            break;

                        case HACE_UNA_SEMANA_DE_MAÑANA:
                            for (Integer i = 0; i < 24; i++) {
                                precios_hace_una_semana_de_mañana_tarifa_20A[i] = precios.get(i);
                            }

                            break;
                    }
                } else {
                    switch (dia) {

                        case MAÑANA:
                            for (Integer i = 0; i < 24; i++) {
                                precios_mañana_tarifa_20A[i] = -1.0f;

                            }
                            break;

                        case HACE_UN_AÑO_DE_MAÑANA:
                            for (Integer i = 0; i < 24; i++) {
                                precios_hace_un_año_de_mañana_tarifa_20A[i] = -1.0f;

                            }
                            break;

                        case HACE_UNA_SEMANA_DE_MAÑANA:
                            for (Integer i = 0; i < 24; i++) {
                                precios_hace_una_semana_de_mañana_tarifa_20A[i] = -1.0f;

                            }
                            break;
                    }
                }
            } catch (Exception e) {
                new utils.AsyncTask_Guardar_Error().execute(new Pair<Context, String>(getApplicationContext(),e.toString()));
            }
        }
        void guarda_precios_20DHA(int dia, List<Float> precios) {
            try {
                if (precios.size() > 0) {
                    switch (dia) {

                        case MAÑANA:

                            for (Integer i = 0; i < 24; i++) {
                                precios_mañana_tarifa_20DHA[i] = precios.get(i);
                            }

                            break;

                        case HACE_UN_AÑO_DE_MAÑANA:

                            for (Integer i = 0; i < 24; i++) {
                                precios_hace_un_año_de_mañana_tarifa_20DHA[i] = precios.get(i);
                            }

                            break;

                        case HACE_UNA_SEMANA_DE_MAÑANA:

                            for (Integer i = 0; i < 24; i++) {
                                precios_hace_una_semana_de_mañana_tarifa_20DHA[i] = precios.get(i);
                            }

                            break;
                    }
                } else {
                    switch (dia) {

                        case MAÑANA:
                            for (Integer i = 0; i < 24; i++) {
                                precios_mañana_tarifa_20DHA[i] = -1.0f;
                            }
                            break;

                        case HACE_UN_AÑO_DE_MAÑANA:
                            for (Integer i = 0; i < 24; i++) {
                                precios_hace_un_año_de_mañana_tarifa_20DHA[i] = -1.0f;
                            }
                            break;

                        case HACE_UNA_SEMANA_DE_MAÑANA:
                            for (Integer i = 0; i < 24; i++) {
                                precios_hace_una_semana_de_mañana_tarifa_20DHA[i] = -1.0f;
                            }
                            break;
                    }
                }
                //editor.commit();
            } catch (Exception e) {
                new utils.AsyncTask_Guardar_Error().execute(new Pair<Context, String>(getApplicationContext(),e.toString()));
            }
        }
        void guarda_precios_20DHS(int dia, List<Float> precios) {
            try {
                if (precios.size() > 0) {
                    switch (dia) {

                        case MAÑANA:

                            for (Integer i = 0; i < 24; i++) {
                                precios_mañana_tarifa_20DHS[i] = precios.get(i);
                            }
                            break;

                        case HACE_UN_AÑO_DE_MAÑANA:

                            for (Integer i = 0; i < 24; i++) {
                                precios_hace_un_año_de_mañana_tarifa_20DHS[i] = precios.get(i);
                            }
                            break;

                        case HACE_UNA_SEMANA_DE_MAÑANA:

                            for (Integer i = 0; i < 24; i++) {
                                precios_hace_una_semana_de_mañana_tarifa_20DHS[i] = precios.get(i);
                            }
                            break;
                    }
                } else {
                    switch (dia) {

                        case MAÑANA:
                            for (Integer i = 0; i < 24; i++) {

                                precios_mañana_tarifa_20DHS[i] = -1.0f;
                            }
                            break;

                        case HACE_UN_AÑO_DE_MAÑANA:
                            for (Integer i = 0; i < 24; i++) {

                                precios_hace_un_año_de_mañana_tarifa_20DHS[i] = -1.0f;
                            }
                            break;

                        case HACE_UNA_SEMANA_DE_MAÑANA:
                            for (Integer i = 0; i < 24; i++) {

                                precios_hace_una_semana_de_mañana_tarifa_20DHS[i] = -1.0f;
                            }
                            break;
                    }
                }
                //editor.commit();
            } catch (Exception e) {
                new utils.AsyncTask_Guardar_Error().execute(new Pair<Context, String>(getApplicationContext(),e.toString()));
            }
        }
    }



}