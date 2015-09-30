package com.ruben.precioluz;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.Boolean;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;
import java.text.SimpleDateFormat;
import jxl.*;

import jxl.read.biff.BiffException;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.ruben.myapplication.backend.beanCompruebaVersionApi.BeanCompruebaVersionApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

public class MainActivity extends AppCompatActivity {
    private ViewPager pager=null;
    private MyFragmentPagerAdapter adapter=null;
    private final Logger LOGGER= Logger.getLogger(getClass().getName());

    private SharedPreferences prefs= null;

    private TutorialFragment fragment_tutorial;
    private GraficasFragment_Landscape fragment_hoy_Landscape;
    private GraficasFragment_Landscape fragment_mañana_Landscape;
    private GraficasFragment_Portrait fragment_hoy_Portrait;
    private GraficasFragment_Portrait fragment_mañana_Portrait;
    
    private final Double version_de_esta_instancia= 1.1;

    private final int TARIFA_20A = 0;
    private final int TARIFA_20DHA = 1;
    private final int TARIFA_20DHS = 2;


    //PARA GUARDAR EN EL SHAREDPREFERENCES
    private final String TAG_HACE_UNA_SEMANA_DE_HOY = "HACE_UNA_SEMANA_DE_HOY";
    private final String TAG_HACE_UN_AÑO_DE_HOY= "HACE_UN_ANYO_DE_HOY";
    private final String TAG_HACE_UNA_SEMANA_DE_MAÑANA = "HACE_UNA_SEMANA_DE_MANYANA";
    private final String TAG_HACE_UN_AÑO_DE_MAÑANA= "HACE_UN_ANYO_DE_MANYANA";

    private final String TAG_TARIFA_ACTUAL= "TARIFA_ACTUAL";

    private final String FRAGMENT_ACTUAL= "FRAGMENT_ACTUAL";

    private final Float[] precios_hoy_tarifa_20A = new Float[24];
    private final Float[] precios_hoy_tarifa_20DHA= new Float[24];
    private final Float[] precios_hoy_tarifa_20DHS= new Float[24];
    private final Float[] precios_mañana_tarifa_20A= new Float[24];
    private final Float[] precios_mañana_tarifa_20DHA= new Float[24];
    private final Float[] precios_mañana_tarifa_20DHS= new Float[24];
    private final Float[] precios_hace_una_semana_de_hoy_tarifa_20A= new Float[24];
    private final Float[] precios_hace_una_semana_de_hoy_tarifa_20DHA= new Float[24];
    private final Float[] precios_hace_una_semana_de_hoy_tarifa_20DHS= new Float[24];
    private final Float[] precios_hace_un_año_de_hoy_tarifa_20A= new Float[24];
    private final Float[] precios_hace_un_año_de_hoy_tarifa_20DHA= new Float[24];
    private final Float[] precios_hace_un_año_de_hoy_tarifa_20DHS= new Float[24];
    private final Float[] precios_hace_una_semana_de_mañana_tarifa_20A= new Float[24];
    private final Float[] precios_hace_una_semana_de_mañana_tarifa_20DHA= new Float[24];
    private final Float[] precios_hace_una_semana_de_mañana_tarifa_20DHS= new Float[24];
    private final Float[] precios_hace_un_año_de_mañana_tarifa_20A= new Float[24];
    private final Float[] precios_hace_un_año_de_mañana_tarifa_20DHA= new Float[24];
    private final Float[] precios_hace_un_año_de_mañana_tarifa_20DHS= new Float[24];

    private final int HOY=0;
    private final int MAÑANA= 1;
    private final int HACE_UNA_SEMANA_DE_HOY = 2;
    private final int HACE_UN_AÑO_DE_HOY= 3;
    private final int HACE_UNA_SEMANA_DE_MAÑANA = 4;
    private final int HACE_UN_AÑO_DE_MAÑANA= 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*********** inserta el layout ***********/
        setContentView(R.layout.main_layout);

        try {
            //Con esto creamos el ViewPager e instanciamos los dos fragments de graficas
            pager = (ViewPager) findViewById(R.id.pager);

            /*********** prepara las preferencias para guardar datos internamente ***********/
            prefs = PreferenceManager.getDefaultSharedPreferences(this);
            crea_fragments();

            if (isNetworkAvailable())  {
                Comprobacion_inicial_actualizaciones_AsyncTask comprueba_actualizaciones = new Comprobacion_inicial_actualizaciones_AsyncTask();
                comprueba_actualizaciones.execute(version_de_esta_instancia);
            }
            getPrecios_AsyncTask mgetPrecios = new getPrecios_AsyncTask();
            mgetPrecios.execute();

        }catch (Exception e){
            LOGGER.severe("Error en OnCreate de MainActivity "+e.toString());
        }
    }

    private void crea_fragments(){
        ErrorFragment fragment_error1;
        ErrorFragment fragment_error2;
        MyFragmentPagerAdapter adapter_error;
        SimpleDateFormat  mSimpleDateFormat= new SimpleDateFormat("dd-MM-yyyy", new Locale("es", "ES"));
        Float[] array_vacio= new Float[24];
        try {
            boolean boolean_precios_semana_pasada_de_hoy = prefs.getBoolean(TAG_HACE_UNA_SEMANA_DE_HOY, false);
            boolean boolean_precios_semana_pasada_de_mañana = prefs.getBoolean(TAG_HACE_UNA_SEMANA_DE_MAÑANA, false);
            boolean boolean_precios_año_pasado_de_hoy = prefs.getBoolean(TAG_HACE_UN_AÑO_DE_HOY, false);
            boolean boolean_precios_año_pasado_de_mañana = prefs.getBoolean(TAG_HACE_UN_AÑO_DE_MAÑANA, false);

            if (isNetworkAvailable()) {
                adapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
                
                for (int i=0; i<24; i++){array_vacio[i]=-1.0f;}
                
                fragment_tutorial = TutorialFragment.newInstance();
                adapter.addFragment(fragment_tutorial);
                if (getResources().getConfiguration().orientation == getResources().getConfiguration().ORIENTATION_LANDSCAPE){
                    fragment_hoy_Landscape = GraficasFragment_Landscape.newInstance("Precios para hoy " + hoy(mSimpleDateFormat) + "\n" + get_Tarifa_SharedPreferences(), array_vacio, boolean_precios_semana_pasada_de_hoy, boolean_precios_año_pasado_de_hoy, array_vacio, array_vacio);
                    fragment_mañana_Landscape = GraficasFragment_Landscape.newInstance("Precios para mañana " + mañana(mSimpleDateFormat) + "\n" + get_Tarifa_SharedPreferences(), array_vacio, boolean_precios_semana_pasada_de_mañana, boolean_precios_año_pasado_de_mañana, array_vacio, array_vacio);
                    adapter.addFragment(fragment_hoy_Landscape);
                    adapter.addFragment(fragment_mañana_Landscape);
                }else{
                    fragment_hoy_Portrait = GraficasFragment_Portrait.newInstance("Precios para hoy " + hoy(mSimpleDateFormat) + "\n" + get_Tarifa_SharedPreferences(), array_vacio, boolean_precios_semana_pasada_de_hoy, boolean_precios_año_pasado_de_hoy, array_vacio, array_vacio);
                    fragment_mañana_Portrait = GraficasFragment_Portrait.newInstance("Precios para mañana " + mañana(mSimpleDateFormat) + "\n" + get_Tarifa_SharedPreferences(), array_vacio, boolean_precios_semana_pasada_de_mañana, boolean_precios_año_pasado_de_mañana, array_vacio, array_vacio);
                    adapter.addFragment(fragment_hoy_Portrait);
                    adapter.addFragment(fragment_mañana_Portrait);
                }

                pager.setAdapter(adapter);
            } else {
                adapter_error = new MyFragmentPagerAdapter(getSupportFragmentManager());
                fragment_tutorial = TutorialFragment.newInstance();
                fragment_error1 = ErrorFragment.newInstance(1);
                fragment_error2 = ErrorFragment.newInstance(2);
                adapter_error.addFragment(fragment_tutorial);
                adapter_error.addFragment(fragment_error1);
                adapter_error.addFragment(fragment_error2);
                pager.setAdapter(adapter_error);
            }
            //PONEMOS EL PAGER QUE HABIA UTILIZADO POR ULTIMA VEZ EL USUARIO
            pager.setCurrentItem(get_Fragment_actual_SharedPreferences());
        } catch (Exception e) {
            LOGGER.severe("Error en modificar_datos  " + e.toString());
        }
    }
    private void replace_fragments(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        boolean boolean_precios_semana_pasada_de_hoy = prefs.getBoolean(TAG_HACE_UNA_SEMANA_DE_HOY, false);
        boolean boolean_precios_semana_pasada_de_mañana = prefs.getBoolean(TAG_HACE_UNA_SEMANA_DE_MAÑANA, false);
        boolean boolean_precios_año_pasado_de_hoy = prefs.getBoolean(TAG_HACE_UN_AÑO_DE_HOY, false);
        boolean boolean_precios_año_pasado_de_mañana = prefs.getBoolean(TAG_HACE_UN_AÑO_DE_MAÑANA, false);

        SimpleDateFormat  mSimpleDateFormat= new SimpleDateFormat("dd-MM-yyyy", new Locale("es", "ES"));
        Float[] array_vacio= new Float[24];

        for (int i=0; i<24; i++){array_vacio[i]=-1.0f;}
        adapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(fragment_tutorial);
        int tarifa = prefs.getInt(TAG_TARIFA_ACTUAL, 0);

        if (getResources().getConfiguration().orientation == getResources().getConfiguration().ORIENTATION_LANDSCAPE) {
            transaction.remove(fragment_mañana_Portrait);
            transaction.remove(fragment_hoy_Portrait);
            transaction.commit();
            fragment_hoy_Landscape = GraficasFragment_Landscape.newInstance("Precios para hoy " + hoy(mSimpleDateFormat) + "\n" + get_Tarifa_SharedPreferences(), getPrecios_hoy_segun_tarifa(tarifa), boolean_precios_semana_pasada_de_hoy, boolean_precios_año_pasado_de_hoy,  getPrecios_hace_un_año_de_hoy_segun_tarifa(tarifa), getPrecios_hace_una_semana_de_hoy_segun_tarifa(tarifa));
            fragment_mañana_Landscape = GraficasFragment_Landscape.newInstance("Precios para mañana " + mañana(mSimpleDateFormat) + "\n" + get_Tarifa_SharedPreferences(), getPrecios_mañana_segun_tarifa(tarifa), boolean_precios_semana_pasada_de_mañana, boolean_precios_año_pasado_de_mañana, getPrecios_hace_un_año_de_mañana_segun_tarifa(tarifa), getPrecios_hace_una_semana_de_mañana_segun_tarifa(tarifa));
            adapter.addFragment(fragment_hoy_Landscape);
            adapter.addFragment(fragment_mañana_Landscape);
        } else{
            transaction.remove(fragment_mañana_Landscape);
            transaction.remove(fragment_hoy_Landscape);
            transaction.commit();
            fragment_hoy_Portrait = GraficasFragment_Portrait.newInstance("Precios para hoy " + hoy(mSimpleDateFormat) + "\n" + get_Tarifa_SharedPreferences(), getPrecios_hoy_segun_tarifa(tarifa), boolean_precios_semana_pasada_de_hoy, boolean_precios_año_pasado_de_hoy,  getPrecios_hace_un_año_de_hoy_segun_tarifa(tarifa), getPrecios_hace_una_semana_de_hoy_segun_tarifa(tarifa));
            fragment_mañana_Portrait = GraficasFragment_Portrait.newInstance("Precios para mañana " + mañana(mSimpleDateFormat) + "\n" + get_Tarifa_SharedPreferences(), getPrecios_mañana_segun_tarifa(tarifa), boolean_precios_semana_pasada_de_mañana, boolean_precios_año_pasado_de_mañana, getPrecios_hace_un_año_de_mañana_segun_tarifa(tarifa), getPrecios_hace_una_semana_de_mañana_segun_tarifa(tarifa));
            adapter.addFragment(fragment_hoy_Portrait);
            adapter.addFragment(fragment_mañana_Portrait);
        }

        pager.setAdapter(adapter);
        pager.setCurrentItem(get_Fragment_actual_SharedPreferences());
    }
    private void carga_datos_inicial(){
        int tarifa = prefs.getInt(TAG_TARIFA_ACTUAL, 0);
        if (getResources().getConfiguration().orientation == getResources().getConfiguration().ORIENTATION_LANDSCAPE) {
            fragment_mañana_Landscape.set_todos_los_precios(getPrecios_mañana_segun_tarifa(tarifa), getPrecios_hace_un_año_de_mañana_segun_tarifa(tarifa), getPrecios_hace_una_semana_de_mañana_segun_tarifa(tarifa));
            fragment_hoy_Landscape.set_todos_los_precios(getPrecios_hoy_segun_tarifa(tarifa), getPrecios_hace_un_año_de_hoy_segun_tarifa(tarifa), getPrecios_hace_una_semana_de_hoy_segun_tarifa(tarifa));
        }else{
            fragment_mañana_Portrait.set_todos_los_precios(getPrecios_mañana_segun_tarifa(tarifa), getPrecios_hace_un_año_de_mañana_segun_tarifa(tarifa), getPrecios_hace_una_semana_de_mañana_segun_tarifa(tarifa));
            fragment_hoy_Portrait.set_todos_los_precios(getPrecios_hoy_segun_tarifa(tarifa), getPrecios_hace_un_año_de_hoy_segun_tarifa(tarifa), getPrecios_hace_una_semana_de_hoy_segun_tarifa(tarifa));
        }
        pager.getAdapter().notifyDataSetChanged();
    }

    private void set_Tarifa_SharedPreferences(int tarifa){
        /*********** guarda la ultima tarifa que ha seleccioado el usuario***********/
        try {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt(TAG_TARIFA_ACTUAL, tarifa);
            editor.apply();
        }catch (Exception e){
            LOGGER.severe("Error en set_Tarifa_SharedPreferences  "+e.toString());
        }

    }
    private String get_Tarifa_SharedPreferences(){
        String tarifa_actual= "TARIFA 20A";
        try {
            switch (prefs.getInt(TAG_TARIFA_ACTUAL, 0)) {
                case 0:
                    tarifa_actual ="TARIFA 20A";
                    break;
                case 1:
                    tarifa_actual= "TARIFA 20DHA";
                    break;
                case 2:
                    tarifa_actual= "TARIFA 20DHS";
                    break;
                default:
                    tarifa_actual= "TARIFA 20A";
                    break;
            }
        }catch (Exception e){
            LOGGER.severe("Error en get_Tarifa_SharedPreferences  " + e.toString());
        }
        return tarifa_actual;

    }
    private void set_Fragment_actual_SharedPreferences(int Fragment_actual){
        /*********** guarda el ultimo fragmente (hoy o mañana) que ha seleccionado el usuario en la memoria interna ***********/
        try {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt(FRAGMENT_ACTUAL,Fragment_actual);
            editor.apply();
        }catch (Exception e){
            LOGGER.severe("Error en set_Fragment_actual_SharedPreferences  "+e.toString());
        }
    }
    private int get_Fragment_actual_SharedPreferences(){
        /*********** extrae el fragmen actual (hoy o mañana) ***********/
        int fragment_actual=0;
        try{
            fragment_actual= prefs.getInt(FRAGMENT_ACTUAL,0);
        }catch (Exception e){
            LOGGER.severe("Error en get_Fragment_actual_SharedPreferences "+e.toString());
        }
        return fragment_actual;

    }
    private void set_Si_Precios_Pasados_SharedPreferences(String precios_pasados){
        /*********** guarda si ha seleccionado la opcion de precios de la semana pasada o del año pasado  en la memoria interna ***********/
        try {
            SharedPreferences.Editor editor = prefs.edit();
            if (prefs.getBoolean(precios_pasados, false)) {
                editor.putBoolean(precios_pasados, false);
            } else {
                editor.putBoolean(precios_pasados, true);
            }
            editor.apply();
        }catch (Exception e){
            LOGGER.severe("Error en set_Si_Precios_Pasados_SharedPreferences "+e.toString());
        }
    }

    private String hoy(SimpleDateFormat mSimpleDateFormat){
        Calendar mCalendar =  Calendar.getInstance();
        return mSimpleDateFormat.format(mCalendar.getTime());
    }
    private String mañana(SimpleDateFormat mSimpleDateFormat){
        Calendar mCalendar = new GregorianCalendar();
        mCalendar.add(Calendar.DAY_OF_YEAR, 1);
        return mSimpleDateFormat.format(mCalendar.getTime());
    }
    private String hace_un_año_de_hoy(SimpleDateFormat mSimpleDateFormat){
        Calendar mCalendar = new GregorianCalendar();
        mCalendar.add(Calendar.YEAR, -1);
        return mSimpleDateFormat.format(mCalendar.getTime());
    }
    private String hace_una_semana_de_hoy(SimpleDateFormat mSimpleDateFormat){
        Calendar mCalendar = new GregorianCalendar();
        mCalendar.add(Calendar.WEEK_OF_YEAR, -1);
        return mSimpleDateFormat.format(mCalendar.getTime());
    }
    String hace_un_año_de_mañana(SimpleDateFormat mSimpleDateFormat){
        Calendar mCalendar = new GregorianCalendar();
        mCalendar.add(Calendar.YEAR, -1);
        mCalendar.add(Calendar.DATE, 1);
        return mSimpleDateFormat.format(mCalendar.getTime());
    }
    private String hace_una_semana_de_mañana(SimpleDateFormat mSimpleDateFormat){
        Calendar mCalendar = new GregorianCalendar();
        mCalendar.add(Calendar.WEEK_OF_YEAR, -1);
        mCalendar.add(Calendar.DATE, 1);
        return mSimpleDateFormat.format(mCalendar.getTime());
    }

    private Float[] getPrecios_hoy_segun_tarifa(int tarifa){
        Float[] precios= null;
        switch (tarifa){
            case TARIFA_20A:
                precios= precios_hoy_tarifa_20A;
                break;
            case TARIFA_20DHA:
                precios= precios_hoy_tarifa_20DHA;
                break;
            case TARIFA_20DHS:
                precios= precios_hoy_tarifa_20DHS;
                break;
        }
        return precios;
    }
    private Float[] getPrecios_mañana_segun_tarifa(int tarifa){
        Float[] precios= null;
        switch (tarifa){
            case TARIFA_20A:
                precios= precios_mañana_tarifa_20A;
                break;
            case TARIFA_20DHA:
                precios= precios_mañana_tarifa_20DHA;
                break;
            case TARIFA_20DHS:
                precios= precios_mañana_tarifa_20DHS;
                break;
        }
        return precios;
    }
    private Float[] getPrecios_hace_una_semana_de_hoy_segun_tarifa(int tarifa){
        Float[] precios= null;
        switch (tarifa){
            case TARIFA_20A:
                precios= precios_hace_una_semana_de_hoy_tarifa_20A;
                break;
            case TARIFA_20DHA:
                precios= precios_hace_una_semana_de_hoy_tarifa_20DHA;
                break;
            case TARIFA_20DHS:
                precios= precios_hace_una_semana_de_hoy_tarifa_20DHS;
                break;
        }
        return precios;
    }
    private Float[] getPrecios_hace_una_semana_de_mañana_segun_tarifa(int tarifa){
        Float[] precios= null;
        switch (tarifa){
            case TARIFA_20A:
                precios= precios_hace_una_semana_de_mañana_tarifa_20A;
                break;
            case TARIFA_20DHA:
                precios= precios_hace_una_semana_de_mañana_tarifa_20DHA;
                break;
            case TARIFA_20DHS:
                precios= precios_hace_una_semana_de_mañana_tarifa_20DHS;
                break;
        }
        return precios;
    }
    private Float[] getPrecios_hace_un_año_de_hoy_segun_tarifa(int tarifa){
        Float[] precios= null;
        switch (tarifa){
            case TARIFA_20A:
                precios= precios_hace_un_año_de_hoy_tarifa_20A;
                break;
            case TARIFA_20DHA:
                precios= precios_hace_un_año_de_hoy_tarifa_20DHA;
                break;
            case TARIFA_20DHS:
                precios= precios_hace_un_año_de_hoy_tarifa_20DHS;
                break;
        }
        return precios;
    }
    private Float[] getPrecios_hace_un_año_de_mañana_segun_tarifa(int tarifa){
        Float[] precios= null;
        switch (tarifa){
            case TARIFA_20A:
                precios= precios_hace_un_año_de_mañana_tarifa_20A;
                break;
            case TARIFA_20DHA:
                precios= precios_hace_un_año_de_mañana_tarifa_20DHA;
                break;
            case TARIFA_20DHS:
                precios= precios_hace_un_año_de_mañana_tarifa_20DHS;
                break;
        }
        return precios;
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int fragment_actual= this.pager.getCurrentItem();
        try {
            if (isNetworkAvailable()) {
                set_Fragment_actual_SharedPreferences(fragment_actual);
                SimpleDateFormat  mSimpleDateFormat= new SimpleDateFormat("dd-MM-yyyy", new Locale("es", "ES"));

                switch (item.getItemId()) {
                    case R.id.tarifa_20A:
                        set_Tarifa_SharedPreferences(TARIFA_20A);
                        if (getResources().getConfiguration().orientation == getResources().getConfiguration().ORIENTATION_LANDSCAPE) {
                            fragment_hoy_Landscape.setTitulo("Precios para hoy " + hoy(mSimpleDateFormat) + "\n" + get_Tarifa_SharedPreferences());
                            fragment_hoy_Landscape.set_todos_los_precios(precios_hoy_tarifa_20A, precios_hace_un_año_de_hoy_tarifa_20A, precios_hace_una_semana_de_hoy_tarifa_20A);
                            fragment_mañana_Landscape.setTitulo("Precios para mañana " + mañana(mSimpleDateFormat) + "\n" + get_Tarifa_SharedPreferences());
                            fragment_mañana_Landscape.set_todos_los_precios(precios_mañana_tarifa_20A, precios_hace_un_año_de_mañana_tarifa_20A, precios_hace_una_semana_de_mañana_tarifa_20A);
                        }else{
                            fragment_hoy_Portrait.setTitulo("Precios para hoy " + hoy(mSimpleDateFormat) + "\n" + get_Tarifa_SharedPreferences());
                            fragment_hoy_Portrait.set_todos_los_precios(precios_hoy_tarifa_20A, precios_hace_un_año_de_hoy_tarifa_20A, precios_hace_una_semana_de_hoy_tarifa_20A);
                            fragment_mañana_Portrait.setTitulo("Precios para mañana " + mañana(mSimpleDateFormat) + "\n" + get_Tarifa_SharedPreferences());
                            fragment_mañana_Portrait.set_todos_los_precios(precios_mañana_tarifa_20A, precios_hace_un_año_de_mañana_tarifa_20A, precios_hace_una_semana_de_mañana_tarifa_20A);
                        }
                        pager.getAdapter().notifyDataSetChanged();
                        break;
                    case R.id.tarifa_20DHA:       
                        set_Tarifa_SharedPreferences( TARIFA_20DHA);
                        if (getResources().getConfiguration().orientation == getResources().getConfiguration().ORIENTATION_LANDSCAPE) {
                            fragment_hoy_Landscape.setTitulo("Precios para hoy " + hoy(mSimpleDateFormat) + "\n" + get_Tarifa_SharedPreferences());
                            fragment_hoy_Landscape.set_todos_los_precios(precios_hoy_tarifa_20DHA, precios_hace_un_año_de_hoy_tarifa_20DHA, precios_hace_una_semana_de_hoy_tarifa_20DHA);
                            fragment_mañana_Landscape.setTitulo("Precios para mañana " + mañana(mSimpleDateFormat) + "\n" + get_Tarifa_SharedPreferences());
                            fragment_mañana_Landscape.set_todos_los_precios(precios_mañana_tarifa_20DHA, precios_hace_un_año_de_mañana_tarifa_20DHA, precios_hace_una_semana_de_mañana_tarifa_20DHA);
                        }else{
                            fragment_hoy_Portrait.setTitulo("Precios para hoy " + hoy(mSimpleDateFormat) + "\n" + get_Tarifa_SharedPreferences());
                            fragment_hoy_Portrait.set_todos_los_precios(precios_hoy_tarifa_20DHA, precios_hace_un_año_de_hoy_tarifa_20DHA, precios_hace_una_semana_de_hoy_tarifa_20DHA);
                            fragment_mañana_Portrait.setTitulo("Precios para mañana " + mañana(mSimpleDateFormat) + "\n" + get_Tarifa_SharedPreferences());
                            fragment_mañana_Portrait.set_todos_los_precios(precios_mañana_tarifa_20DHA, precios_hace_un_año_de_mañana_tarifa_20DHA, precios_hace_una_semana_de_mañana_tarifa_20DHA);   
                        }
                        pager.getAdapter().notifyDataSetChanged();
                        break;
                    case R.id.tarifa_20DHS:
                        set_Tarifa_SharedPreferences( TARIFA_20DHS);
                        if (getResources().getConfiguration().orientation == getResources().getConfiguration().ORIENTATION_LANDSCAPE) {
                            fragment_hoy_Landscape.setTitulo("Precios para hoy " + hoy(mSimpleDateFormat) + "\n" + get_Tarifa_SharedPreferences());
                            fragment_hoy_Landscape.set_todos_los_precios(precios_hoy_tarifa_20DHS, precios_hace_un_año_de_hoy_tarifa_20DHS, precios_hace_una_semana_de_hoy_tarifa_20DHS);
                            fragment_mañana_Landscape.setTitulo("Precios para mañana " + mañana(mSimpleDateFormat) + "\n" + get_Tarifa_SharedPreferences());
                            fragment_mañana_Landscape.set_todos_los_precios(precios_mañana_tarifa_20DHS, precios_hace_un_año_de_mañana_tarifa_20DHS, precios_hace_una_semana_de_mañana_tarifa_20DHS);
                        }else{
                            fragment_hoy_Portrait.setTitulo("Precios para hoy " + hoy(mSimpleDateFormat) + "\n" + get_Tarifa_SharedPreferences());
                            fragment_hoy_Portrait.set_todos_los_precios(precios_hoy_tarifa_20DHS, precios_hace_un_año_de_hoy_tarifa_20DHS, precios_hace_una_semana_de_hoy_tarifa_20DHS);
                            fragment_mañana_Portrait.setTitulo("Precios para mañana " + mañana(mSimpleDateFormat) + "\n" + get_Tarifa_SharedPreferences());
                            fragment_mañana_Portrait.set_todos_los_precios(precios_mañana_tarifa_20DHS, precios_hace_un_año_de_mañana_tarifa_20DHS, precios_hace_una_semana_de_mañana_tarifa_20DHS);
                        }
                            pager.getAdapter().notifyDataSetChanged();
                        break;
                    case R.id.hace_una_semana:
                        if (fragment_actual == 1) {
                            set_Si_Precios_Pasados_SharedPreferences(TAG_HACE_UNA_SEMANA_DE_HOY);
                            if (getResources().getConfiguration().orientation == getResources().getConfiguration().ORIENTATION_LANDSCAPE) {
                                fragment_hoy_Landscape.setPrecios_semana_pasada_activado(prefs.getBoolean(TAG_HACE_UNA_SEMANA_DE_HOY, false));
                            }else{
                                fragment_hoy_Portrait.setPrecios_semana_pasada_activado(prefs.getBoolean(TAG_HACE_UNA_SEMANA_DE_HOY, false));
                            }
                                pager.getAdapter().notifyDataSetChanged();
                        } else if (fragment_actual == 2) {
                            set_Si_Precios_Pasados_SharedPreferences(TAG_HACE_UNA_SEMANA_DE_MAÑANA);
                            if (getResources().getConfiguration().orientation == getResources().getConfiguration().ORIENTATION_LANDSCAPE) {
                                fragment_mañana_Landscape.setPrecios_semana_pasada_activado(prefs.getBoolean(TAG_HACE_UNA_SEMANA_DE_MAÑANA, false));
                            }else{
                                fragment_mañana_Portrait.setPrecios_semana_pasada_activado(prefs.getBoolean(TAG_HACE_UNA_SEMANA_DE_MAÑANA, false));
                            }
                                pager.getAdapter().notifyDataSetChanged();
                        }
                        break;
                    case R.id.hace_un_año:
                        if (fragment_actual == 1) {
                            set_Si_Precios_Pasados_SharedPreferences(TAG_HACE_UN_AÑO_DE_HOY);
                            if (getResources().getConfiguration().orientation == getResources().getConfiguration().ORIENTATION_LANDSCAPE) {
                                fragment_hoy_Landscape.setPrecios_año_pasado_activado(prefs.getBoolean(TAG_HACE_UN_AÑO_DE_HOY, false));
                            }else{
                                fragment_hoy_Portrait.setPrecios_año_pasado_activado(prefs.getBoolean(TAG_HACE_UN_AÑO_DE_HOY, false));
                            }
                            pager.getAdapter().notifyDataSetChanged();                        }
                        else if (fragment_actual == 2) {
                            set_Si_Precios_Pasados_SharedPreferences(TAG_HACE_UN_AÑO_DE_MAÑANA);
                            if (getResources().getConfiguration().orientation == getResources().getConfiguration().ORIENTATION_LANDSCAPE) {
                                fragment_mañana_Landscape.setPrecios_año_pasado_activado(prefs.getBoolean(TAG_HACE_UN_AÑO_DE_MAÑANA, false));
                            }else{
                                fragment_mañana_Portrait.setPrecios_año_pasado_activado(prefs.getBoolean(TAG_HACE_UN_AÑO_DE_MAÑANA, false));
                            }
                            pager.getAdapter().notifyDataSetChanged();
                        }
                        break;
                    case R.id.actualizar_app:
                        Comprobacion_explicita_actualizaciones_AsyncTask comprobar_actualizaciones = new Comprobacion_explicita_actualizaciones_AsyncTask();
                        comprobar_actualizaciones.execute();
                        break;
                }
            }
        }catch (Exception e){
            LOGGER.severe("Error en getTarifa  " + e.toString());
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
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        set_Fragment_actual_SharedPreferences(this.pager.getCurrentItem());
        replace_fragments();

    }

    class Comprobacion_inicial_actualizaciones_AsyncTask extends AsyncTask<Double, Void, Boolean> {
        private BeanCompruebaVersionApi BeanCompruebaVersionService=null;

        @Override
        protected Boolean doInBackground(Double...params) {
            boolean resultado= false;
            if (BeanCompruebaVersionService == null) {
                BeanCompruebaVersionApi.Builder builder = new BeanCompruebaVersionApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null).setRootUrl("https://com.ruben.precioluz.appspot.com/_ah/api/");
                BeanCompruebaVersionService = builder.build();
            }
            try {
                Double version_actual = BeanCompruebaVersionService.getBeanCompruebaVersion().execute().getVersionActual();
                if (version_actual.compareTo(params[0]) != 0) {
                    resultado= true;
                }
            }catch (IOException e){
                LOGGER.warning(e.toString());
            }
            return  resultado;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result)
                Toast.makeText(getApplicationContext(), "Hay una versión nueva", Toast.LENGTH_LONG).show();
        }
    }

    class Comprobacion_explicita_actualizaciones_AsyncTask extends AsyncTask<Void, Void, Boolean> {
        private BeanCompruebaVersionApi BeanCompruebaVersionService=null;

        @Override
        protected Boolean doInBackground(Void...params) {
            if (BeanCompruebaVersionService == null) {
                BeanCompruebaVersionApi.Builder builder = new BeanCompruebaVersionApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null).setRootUrl("https://com.ruben.precioluz.appspot.com/_ah/api/");
                BeanCompruebaVersionService = builder.build();
            }
            try {
                Double version_actual = BeanCompruebaVersionService.getBeanCompruebaVersion().execute().getVersionActual();
                return version_actual.compareTo(version_de_esta_instancia) != 0;

            }catch (IOException e){
                LOGGER.warning(e.toString());
            }
            return  false;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result){
                //enviar a la web de descargas
            }else{
                Toast.makeText(getApplicationContext(), "No hay actualizaciones disponibles", Toast.LENGTH_LONG).show();
            }
        }
    }

    class getPrecios_AsyncTask extends AsyncTask<Void,Void,Void>{
        @Override
        protected Void doInBackground(Void...params) {
             final String TAG_HOY_XLS= "PRECIOS_HOY.XLS";
             final String TAG_MAÑANA_XLS= "PRECIOS_MANYANA.XLS";
             final String TAG_HACE_UN_AÑO_DE_HOY_XLS= "PRECIOS_HACE_UN_ANYO_DE_HOY.XLS";
             final String TAG_HACE_UN_AÑO_DE_MAÑANA_XLS= "PRECIOS_HACE_UN_ANYO_DE_MANYANA.XLS";
             final String TAG_HACE_UNA_SEMANA_DE_HOY_XLS= "PRECIOS_HACE_UNA_SEMANA_DE_HOY.XLS";
             final String TAG_HACE_UNA_SEMANA_DE_MAÑANA_XLS= "PRECIOS_HACE_UNA_SEMANA_DE_MANYANA.XLS";
            try {
                if (isNetworkAvailable()) {
                    SimpleDateFormat sdf_año = new SimpleDateFormat("yyyy");
                    SimpleDateFormat sdf_mes = new SimpleDateFormat("MM");
                    SimpleDateFormat sdf_dia = new SimpleDateFormat("dd");

                    baja_xls(TAG_HOY_XLS, hoy(sdf_año) + hoy(sdf_mes) + hoy(sdf_dia));
                    guarda_precios(HOY, extrae_Precios(TAG_HOY_XLS));

                    baja_xls(TAG_MAÑANA_XLS, mañana(sdf_año) + mañana(sdf_mes) + mañana(sdf_dia));
                    guarda_precios(MAÑANA, extrae_Precios(TAG_MAÑANA_XLS));

                    baja_xls(TAG_HACE_UN_AÑO_DE_HOY_XLS, hace_un_año_de_hoy(sdf_año) + hace_un_año_de_hoy(sdf_mes) + hace_un_año_de_hoy(sdf_dia));
                    guarda_precios(HACE_UN_AÑO_DE_HOY, extrae_Precios(TAG_HACE_UN_AÑO_DE_HOY_XLS));

                    baja_xls(TAG_HACE_UNA_SEMANA_DE_HOY_XLS, hace_una_semana_de_hoy(sdf_año) + hace_una_semana_de_hoy(sdf_mes) + hace_una_semana_de_hoy(sdf_dia));
                    guarda_precios(HACE_UNA_SEMANA_DE_HOY, extrae_Precios(TAG_HACE_UNA_SEMANA_DE_HOY_XLS));

                    baja_xls(TAG_HACE_UN_AÑO_DE_MAÑANA_XLS, hace_un_año_de_mañana(sdf_año) + hace_un_año_de_mañana(sdf_mes) + hace_un_año_de_mañana(sdf_dia));
                    guarda_precios(HACE_UN_AÑO_DE_MAÑANA, extrae_Precios(TAG_HACE_UN_AÑO_DE_MAÑANA_XLS));

                    baja_xls(TAG_HACE_UNA_SEMANA_DE_MAÑANA_XLS, hace_una_semana_de_mañana(sdf_año) + hace_una_semana_de_mañana(sdf_mes) + hace_una_semana_de_mañana(sdf_dia));
                    guarda_precios(HACE_UNA_SEMANA_DE_MAÑANA, extrae_Precios(TAG_HACE_UNA_SEMANA_DE_MAÑANA_XLS));
                }
            }catch (Exception e){
                LOGGER.severe("Error en getPrecios_AsyncTask (doInBackground) de MainActivity "+e.toString());
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void algo) {
            if (isNetworkAvailable()) {
                carga_datos_inicial();
            }
        }

        /*********** descarga el xml con los precios***********/
        private void baja_xls(String Nombre_archivo,String fecha){
            int num_bytes= 1024*4;
            byte[] buffer = new byte[num_bytes];

            try {
                String url = "http://www.esios.ree.es/Solicitar?fileName=PVPC_DETALLE_DD_" + fecha + "&fileType=xls&idioma=es";
                URL u = new URL(url);
                InputStream is = u.openStream();


                FileOutputStream fOut = openFileOutput(Nombre_archivo, MODE_PRIVATE);
                int cantidad;
                while ((cantidad = is.read(buffer, 0, num_bytes)) != -1) {
                    fOut.write(buffer, 0, cantidad);
                }
                fOut.close();
            }catch(MalformedURLException e){
                LOGGER.severe("MalformedURLException en baja_xls  " + e.toString());
            }catch (IOException e){
                LOGGER.severe("IOException en baja_xls  " + e.toString());
            }catch (Exception e){
                LOGGER.severe("Exception en baja_xls  " + e.toString());
            }
        }
        /*********** extrae la información que nos interesa del xml y devuelve en un array de precios ***********/
        private List<Float> extrae_Precios(String Nombre_archivo){
            List<Float> precios= new ArrayList<>();
            FileInputStream file;
            try{
                file = openFileInput(Nombre_archivo);
                /*****************************************/
                WorkbookSettings ws = new WorkbookSettings();
                ws.setLocale(new Locale("es", "ES"));
                Workbook w = Workbook.getWorkbook(file, ws);

                // Gets the sheets from workbook
                for (int sheet = 0; sheet < w.getNumberOfSheets(); sheet++) {
                    Sheet hoja = w.getSheet(sheet);

                    Cell[] row;

                    for (int i = 5; i < hoja.getRows(); i++) {
                        row = hoja.getRow(i);
                        if (!row[4].getContents().equals(""))
                            precios.add(Float.parseFloat(row[4].getContents().replace(",", "."))/1000);
                    }
                }
                file.close();
                w.close();
            }catch (IOException e){
                LOGGER.severe("IOException en extrae_Precios  " + e.toString());
            }catch (BiffException e){
                LOGGER.severe("BiffException en extrae_Precios  "+e.toString());
            }catch (Exception e){
                LOGGER.severe("Exception en extrae_Precios  " + e.toString());
            }
            return precios;
        }

        /*********** guarda los precios desglosados por tarifas en la memoria interna ***********/
        void guarda_precios(int dia,List<Float> precios){
            try {
                if (precios.size() > 0) {
                    switch (dia){
                        case HOY:
                            for (Integer i = 0; i < 24; i++) {
                                precios_hoy_tarifa_20A[i]=precios.get(i);
                            }
                            for (Integer i = 24; i < 48; i++) {
                                precios_hoy_tarifa_20DHA[i-24]=precios.get(i);
                            }
                            for (Integer i = 48; i < 72; i++) {
                                precios_hoy_tarifa_20DHS[i-48]=precios.get(i);
                            }
                            break;
                        case MAÑANA:
                            for (Integer i = 0; i < 24; i++) {
                                precios_mañana_tarifa_20A[i]=precios.get(i);
                            }
                            for (Integer i = 24; i < 48; i++) {
                                precios_mañana_tarifa_20DHA[i-24]=precios.get(i);
                            }
                            for (Integer i = 48; i < 72; i++) {
                                precios_mañana_tarifa_20DHS[i-48]=precios.get(i);
                            }
                            break;
                        case HACE_UN_AÑO_DE_HOY:
                            for (Integer i = 0; i < 24; i++) {
                                precios_hace_un_año_de_hoy_tarifa_20A[i]=precios.get(i);
                            }
                            for (Integer i = 24; i < 48; i++) {
                                precios_hace_un_año_de_hoy_tarifa_20DHA[i-24]=precios.get(i);
                            }
                            for (Integer i = 48; i < 72; i++) {
                                precios_hace_un_año_de_hoy_tarifa_20DHS[i-48]=precios.get(i);
                            }
                            break;
                        case HACE_UN_AÑO_DE_MAÑANA:
                            for (Integer i = 0; i < 24; i++) {
                                precios_hace_un_año_de_mañana_tarifa_20A[i]=precios.get(i);
                            }
                            for (Integer i = 24; i < 48; i++) {
                                precios_hace_un_año_de_mañana_tarifa_20DHA[i-24]=precios.get(i);
                            }
                            for (Integer i = 48; i < 72; i++) {
                                precios_hace_un_año_de_mañana_tarifa_20DHS[i-48]=precios.get(i);
                            }
                            break;
                        case HACE_UNA_SEMANA_DE_HOY:
                            for (Integer i = 0; i < 24; i++) {
                                precios_hace_una_semana_de_hoy_tarifa_20A[i]=precios.get(i);
                            }
                            for (Integer i = 24; i < 48; i++) {
                                precios_hace_una_semana_de_hoy_tarifa_20DHA[i-24]=precios.get(i);
                            }
                            for (Integer i = 48; i < 72; i++) {
                                precios_hace_una_semana_de_hoy_tarifa_20DHS[i-48]=precios.get(i);
                            }
                            break;
                        case HACE_UNA_SEMANA_DE_MAÑANA:
                            for (Integer i = 0; i < 24; i++) {
                                precios_hace_una_semana_de_mañana_tarifa_20A[i]=precios.get(i);
                            }
                            for (Integer i = 24; i < 48; i++) {
                                precios_hace_una_semana_de_mañana_tarifa_20DHA[i-24]=precios.get(i);
                            }
                            for (Integer i = 48; i < 72; i++) {
                                precios_hace_una_semana_de_mañana_tarifa_20DHS[i-48]=precios.get(i);
                            }
                            break;
                    }
                } else {
                    switch (dia){
                        case HOY:
                            for (Integer i = 0; i < 24; i++) {
                                precios_hoy_tarifa_20A[i]=-1.0f;
                                precios_hoy_tarifa_20DHA[i]=-1.0f;
                                precios_hoy_tarifa_20DHS[i]=-1.0f;
                            }
                            break;
                        case MAÑANA:
                            for (Integer i = 0; i < 24; i++) {
                                precios_mañana_tarifa_20A[i]=-1.0f;
                                precios_mañana_tarifa_20DHA[i]=-1.0f;
                                precios_mañana_tarifa_20DHS[i]=-1.0f;
                            }
                            break;
                        case HACE_UN_AÑO_DE_HOY:
                            for (Integer i = 0; i < 24; i++) {
                                precios_hace_un_año_de_hoy_tarifa_20A[i]=-1.0f;
                                precios_hace_un_año_de_hoy_tarifa_20DHA[i]=-1.0f;
                                precios_hace_un_año_de_hoy_tarifa_20DHS[i]=-1.0f;
                            }
                            break;
                        case HACE_UN_AÑO_DE_MAÑANA:
                            for (Integer i = 0; i < 24; i++) {
                                precios_hace_un_año_de_mañana_tarifa_20A[i]=-1.0f;
                                precios_hace_un_año_de_mañana_tarifa_20DHA[i]=-1.0f;
                                precios_hace_un_año_de_mañana_tarifa_20DHS[i]=-1.0f;
                            }
                            break;
                        case HACE_UNA_SEMANA_DE_HOY:
                            for (Integer i = 0; i < 24; i++) {
                                precios_hace_una_semana_de_hoy_tarifa_20A[i]=-1.0f;
                                precios_hace_una_semana_de_hoy_tarifa_20DHA[i]=-1.0f;
                                precios_hace_una_semana_de_hoy_tarifa_20DHS[i]=-1.0f;
                            }
                            break;
                        case HACE_UNA_SEMANA_DE_MAÑANA:
                            for (Integer i = 0; i < 24; i++) {
                                precios_hace_una_semana_de_mañana_tarifa_20A[i]=-1.0f;
                                precios_hace_una_semana_de_mañana_tarifa_20DHA[i]=-1.0f;
                                precios_hace_una_semana_de_mañana_tarifa_20DHS[i]=-1.0f;
                            }
                            break;
                    }
                }
                //editor.commit();
            }catch (Exception e){
                LOGGER.severe("Error en set_precios_SharedPreferences "+e.toString());
            }
        }
    }
}