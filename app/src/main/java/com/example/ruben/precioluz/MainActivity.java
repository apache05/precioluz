package com.example.ruben.precioluz;

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
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.ruben.myapplication.backend.beanCompruebaVersionApi.BeanCompruebaVersionApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

public class MainActivity extends AppCompatActivity {
    ViewPager pager;
    Logger LOGGER= Logger.getLogger(getClass().getName());
    String TAG_TARIFA_ACTUAL= "TARIFA ACTUAL";

    //PARA LAS COMPROBACIONES DE SI ESTA ACTIVADO O NO
    String PRECIOS_SEMANA_PASADA_DE_MAÑANA_ACTIVADO= "PRECIOS_SEMANA_PASADA_DE_MANYANA";
    String PRECIOS_AÑO_PASADO_DE_MAÑANA_ACTIVADO="PRECIOS_AÑO_PASADO_DE_MANYANA";
    String PRECIOS_SEMANA_PASADA_DE_HOY_ACTIVADO= "PRECIOS_SEMANA_PASADA_DE_HOY";
    String PRECIOS_AÑO_PASADO_DE_HOY_ACTIVADO="PRECIOS_AÑO_PASADO_DE_HOY";

    final int TARIFA_20A = 1;
    final int TARIFA_20DHA = 2;
    final int TARIFA_20DHS = 3;

    //PARA GUARDAR EN EL SHAREDPREFERENCES
    final String TAG_HOY="HOY";
    final String TAG_MAÑANA= "MAÑANA";
    final String TAG_HACE_UNA_SEMANA_DE_HOY = "HACE_UNA_SEMANA_DE_HOY";
    final String TAG_HACE_UN_AÑO_DE_HOY= "HACE_UN_ANYO_DE_HOY";
    final String TAG_HACE_UNA_SEMANA_DE_MAÑANA = "HACE_UNA_SEMANA_DE_MANYANA";
    final String TAG_HACE_UN_AÑO_DE_MAÑANA= "HACE_UN_ANYO_DE_MANYANA";
    final String TAG_TARIFA_20A= "TARIFA_20A";
    final String TAG_TARIFA_20DHA= "TARIFA_20DHA";
    final String TAG_TARIFA_20DHS= "TARIFA_20DHS";

    /** NOMBRES DE LOS FICHEROS AUXILIARES **/
    final String TAG_HOY_XLS= "PRECIOS_HOY.XLS";
    final String TAG_MAÑANA_XLS= "PRECIOS_MANYANA.XLS";
    final String TAG_HACE_UN_AÑO_DE_HOY_XLS= "PRECIOS_HACE_UN_ANYO_DE_HOY.XLS";
    final String TAG_HACE_UN_AÑO_DE_MAÑANA_XLS= "PRECIOS_HACE_UN_ANYO_DE_MANYANA.XLS";
    final String TAG_HACE_UNA_SEMANA_DE_HOY_XLS= "PRECIOS_HACE_UNA_SEMANA_DE_HOY.XLS";
    final String TAG_HACE_UNA_SEMANA_DE_MAÑANA_XLS= "PRECIOS_HACE_UNA_SEMANA_DE_MANYANA.XLS";

    final String FRAGMENT_ACTUAL= "FRAGMENT ACTUAL";
    SharedPreferences prefs= null;
    GraficasFragment fragment1;
    GraficasFragment fragment2;
    final Double version_de_esta_instancia= 0.1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*********** inserta el layout ***********/
        setContentView(R.layout.main_layout);

        try {
            Comprobacion_inicial_actualizaciones_AsyncTask comprueba_actualizaciones = new Comprobacion_inicial_actualizaciones_AsyncTask();
            comprueba_actualizaciones.execute();
            /*********** prepara las preferencias para guardar datos internamente ***********/
            prefs = PreferenceManager.getDefaultSharedPreferences(this);
            set_Fragment_actual_SharedPreferences(0);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt(FRAGMENT_ACTUAL,0);
            editor.putBoolean(PRECIOS_AÑO_PASADO_DE_MAÑANA_ACTIVADO, false);
            editor.putBoolean(PRECIOS_SEMANA_PASADA_DE_MAÑANA_ACTIVADO, false);
            editor.putBoolean(PRECIOS_SEMANA_PASADA_DE_HOY_ACTIVADO, false);
            editor.putBoolean(PRECIOS_AÑO_PASADO_DE_HOY_ACTIVADO, false);
            editor.commit();
            /*********** llama a mgetPrecios de forma asincrona ***********/
            getPrecios_AsyncTask mgetPrecios = new getPrecios_AsyncTask();
            mgetPrecios.execute();

            //Con esto creamos el ViewPager e instanciamos los dos fragments de graficas
            pager = (ViewPager) findViewById(R.id.pager);
        }catch (Exception e){
            LOGGER.severe("Error en OnCreate de MainActivity "+e.toString());
        }
    }

    class getPrecios_AsyncTask extends AsyncTask<Void,Void,Void>{
        @Override
        protected Void doInBackground(Void...params) {
            try {
                SimpleDateFormat sdf_año = new SimpleDateFormat("yyyy");
                SimpleDateFormat sdf_mes = new SimpleDateFormat("MM");
                SimpleDateFormat sdf_dia = new SimpleDateFormat("dd");

                baja_xls(TAG_HOY_XLS, hoy(sdf_año) + hoy(sdf_mes) + hoy(sdf_dia));
                set_precios_SharedPreferences(TAG_HOY, extrae_Precios(TAG_HOY_XLS));

                baja_xls(TAG_MAÑANA_XLS, mañana(sdf_año) + mañana(sdf_mes) + mañana(sdf_dia));
                set_precios_SharedPreferences(TAG_MAÑANA, extrae_Precios(TAG_MAÑANA_XLS));

                baja_xls(TAG_HACE_UN_AÑO_DE_HOY_XLS, hace_un_año_de_hoy(sdf_año) + hace_un_año_de_hoy(sdf_mes) + hace_un_año_de_hoy(sdf_dia));
                set_precios_SharedPreferences(TAG_HACE_UN_AÑO_DE_HOY, extrae_Precios(TAG_HACE_UN_AÑO_DE_HOY_XLS));

                baja_xls(TAG_HACE_UNA_SEMANA_DE_HOY_XLS, hace_una_semana_de_hoy(sdf_año) + hace_una_semana_de_hoy(sdf_mes) + hace_una_semana_de_hoy(sdf_dia));
                set_precios_SharedPreferences(TAG_HACE_UNA_SEMANA_DE_HOY, extrae_Precios(TAG_HACE_UNA_SEMANA_DE_HOY_XLS));

                baja_xls(TAG_HACE_UN_AÑO_DE_MAÑANA_XLS, hace_un_año_de_mañana(sdf_año) + hace_un_año_de_mañana(sdf_mes) + hace_un_año_de_mañana(sdf_dia));
                set_precios_SharedPreferences(TAG_HACE_UN_AÑO_DE_MAÑANA, extrae_Precios(TAG_HACE_UN_AÑO_DE_MAÑANA_XLS));

                baja_xls(TAG_HACE_UNA_SEMANA_DE_MAÑANA_XLS, hace_una_semana_de_mañana(sdf_año) + hace_una_semana_de_mañana(sdf_mes) + hace_una_semana_de_mañana(sdf_dia));
                set_precios_SharedPreferences(TAG_HACE_UNA_SEMANA_DE_MAÑANA, extrae_Precios(TAG_HACE_UNA_SEMANA_DE_MAÑANA_XLS));
            }catch (Exception e){
                LOGGER.severe("Error en getPrecios_AsyncTask (doInBackground) de MainActivity "+e.toString());
            }finally {
                return null;

            }
        }

        @Override
        protected void onPostExecute(Void algo) {
            muestra_layout();
        }
    }

    /*********** guarda los precios desglosados por tarifas en la memoria interna ***********/
    void set_precios_SharedPreferences(String TAG_DIA,List<Float> precios){
        try {
            SharedPreferences.Editor editor = prefs.edit();
            if (precios.size() > 0) {
                for (Integer i = 0; i < 24; i++) {
                    editor.putFloat(TAG_DIA + "_" + TAG_TARIFA_20A + "_" + i.toString(), precios.get(i));
                }
                for (Integer i = 24; i < 48; i++) {
                    editor.putFloat(TAG_DIA + "_" + TAG_TARIFA_20DHA + "_" + i.toString(), precios.get(i));
                }
                for (Integer i = 48; i < 72; i++) {
                    editor.putFloat(TAG_DIA + "_" + TAG_TARIFA_20DHS + "_" + i.toString(), precios.get(i));
                }
                editor.commit();
            } else {
                for (Integer i = 0; i < 24; i++) {
                    editor.putFloat(TAG_DIA + "_" + TAG_TARIFA_20A + "_" + i.toString(), -1);
                }
                for (Integer i = 24; i < 48; i++) {
                    editor.putFloat(TAG_DIA + "_" + TAG_TARIFA_20DHA + "_" + i.toString(), -1);
                }
                for (Integer i = 48; i < 72; i++) {
                    editor.putFloat(TAG_DIA + "_" + TAG_TARIFA_20DHS + "_" + i.toString(), -1);
                }
            }
            editor.commit();
        }catch (Exception e){
            LOGGER.severe("Error en setSharedPrefences 1 "+e.toString());
        }
    }
    /*********** guarda la ultima tarifa que ha seleccioado el usuario***********/
    void set_Tarifa_SharedPreferences(String Key,int tarifa){
        try {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt(Key,tarifa);
            editor.commit();
        }catch (Exception e){
            LOGGER.severe("Error en setSharedPrefences 2 "+e.toString());
        }

    }
    /*********** guarda el ultimo fragmente (hoy o mañana) que ha seleccionado el usuario en la memoria interna ***********/
    void set_Fragment_actual_SharedPreferences(int Fragment_actual){
        try {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt(FRAGMENT_ACTUAL,Fragment_actual);
            editor.commit();
        }catch (Exception e){
            LOGGER.severe("Error en setSharedPrefences 3 "+e.toString());
        }
    }
    /*********** guarda si ha seleccionado la opcion de precios de la semana pasada o del año pasado  en la memoria interna ***********/
    void set_Si_Precios_Pasados_SharedPreferences(String precios_pasados){
        try {
            SharedPreferences.Editor editor = prefs.edit();
            if (prefs.getBoolean(precios_pasados, false)) {
                editor.putBoolean(precios_pasados, false);
            } else {
                editor.putBoolean(precios_pasados, true);
            }
            editor.commit();
        }catch (Exception e){
            LOGGER.severe("Error en setSharedPrefences 4 "+e.toString());
        }
    }
    /*********** extrae los precios de un dia y de una tarifa de la memoria interna ***********/
    List<Float> get_Precios_SharedPreferences(String TAG_DIA){
        List<Float> precios_segun_tarifa= new ArrayList<>();

        try {
            switch (prefs.getInt(TAG_TARIFA_ACTUAL, 1)) {
                case TARIFA_20A:
                    for (Integer i = 0; i < 24; i++) {
                        precios_segun_tarifa.add(prefs.getFloat(TAG_DIA + "_" + TAG_TARIFA_20A + "_" + i.toString(), -1));
                    }
                    break;
                case TARIFA_20DHA:
                    for (Integer i = 24; i < 48; i++) {
                        precios_segun_tarifa.add(prefs.getFloat(TAG_DIA + "_" + TAG_TARIFA_20DHA + "_" + i.toString(), -1));
                    }
                    break;
                case TARIFA_20DHS:
                    for (Integer i = 48; i < 72; i++) {
                        precios_segun_tarifa.add(prefs.getFloat(TAG_DIA + "_" + TAG_TARIFA_20DHS + "_" + i.toString(), -1));
                    }
                    break;
            }
        }catch (Exception e){
            LOGGER.severe("Error en getSharedPrefences 1 "+e.toString());
        }finally {
            return precios_segun_tarifa;
        }
    }
    /*********** extrae el fragmen actual (hoy o mañana) ***********/
    int get_Fragment_actual_SharedPreferences(){
        int fragment_actual=0;
        try{
            fragment_actual= prefs.getInt(FRAGMENT_ACTUAL,0);
        }catch (Exception e){
            LOGGER.severe("Error en setSharedPrefences 1 "+e.toString());
        }
        finally {
            return fragment_actual;
        }
    }

    /*********** recoge todos los datos necesarios para crear lo dos fragments ***********/
    public  void muestra_layout() {
        SimpleDateFormat  mSimpleDateFormat= new SimpleDateFormat("dd-MM-yyyy", new Locale("es", "ES"));
        MyFragmentPagerAdapter adapter;

        try {

            //RECOGEMOS TODOS LOS PRECIOS QUE ESTAN GUARDADOS EN SHAREDPREFERENCES
            List<Float> precios_hoy_segun_tarifa = get_Precios_SharedPreferences(TAG_HOY);
            List<Float> precios_mañana_segun_tarifa = get_Precios_SharedPreferences(TAG_MAÑANA);
            List<Float> precios_hace_un_año_de_hoy_segun_tarifa = get_Precios_SharedPreferences(TAG_HACE_UN_AÑO_DE_HOY);
            List<Float> precios_hace_una_semana_de_hoy_segun_tarifa = get_Precios_SharedPreferences(TAG_HACE_UNA_SEMANA_DE_HOY);
            List<Float> precios_hace_un_año_de_mañana_segun_tarifa = get_Precios_SharedPreferences(TAG_HACE_UN_AÑO_DE_MAÑANA);
            List<Float> precios_hace_una_semana_de_mañana_segun_tarifa = get_Precios_SharedPreferences(TAG_HACE_UNA_SEMANA_DE_MAÑANA);

            boolean boolean_precios_semana_pasada_de_hoy= prefs.getBoolean(PRECIOS_SEMANA_PASADA_DE_HOY_ACTIVADO, false);
            boolean boolean_precios_año_pasado_de_hoy= prefs.getBoolean(PRECIOS_AÑO_PASADO_DE_HOY_ACTIVADO, false);
            boolean boolean_precios_semana_pasada_de_mañana= prefs.getBoolean(PRECIOS_SEMANA_PASADA_DE_MAÑANA_ACTIVADO, false);
            boolean boolean_precios_año_pasado_de_mañana= prefs.getBoolean(PRECIOS_AÑO_PASADO_DE_MAÑANA_ACTIVADO,false);


            // Create an adapter with the fragments we show on the ViewPager
            adapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
            fragment1=  GraficasFragment.newInstance("Precios de la electricidad para hoy " + hoy(mSimpleDateFormat)+" "+getTAGTarifa(),      precios_hoy_segun_tarifa,   boolean_precios_semana_pasada_de_hoy,   boolean_precios_año_pasado_de_hoy,   precios_hace_un_año_de_hoy_segun_tarifa,   precios_hace_una_semana_de_hoy_segun_tarifa);
            fragment2= GraficasFragment.newInstance("Precios de la electricidad para mañana " + mañana(mSimpleDateFormat)+" "+getTAGTarifa(), precios_mañana_segun_tarifa,boolean_precios_semana_pasada_de_mañana,boolean_precios_año_pasado_de_mañana,precios_hace_un_año_de_mañana_segun_tarifa,precios_hace_una_semana_de_mañana_segun_tarifa);
            adapter.addFragment(fragment1);
            adapter.addFragment(fragment2);
            pager.setAdapter(adapter);

            //PONEMOS EL PAGER QUE HABIA UTILIZADO POR ULTIMA VEZ EL USUARIO
            pager.setCurrentItem(get_Fragment_actual_SharedPreferences());

        } catch (Exception e) {
            LOGGER.severe("Error en muestra_layout  " + e.toString());
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
            LOGGER.severe("Error en baja_xls  " + e.toString());
        }catch (IOException e){
            LOGGER.severe("Error en baja_xls  " + e.toString());
        }catch (Exception e){
            LOGGER.severe("Error en baja_xls  " + e.toString());
        }
    }
    /*********** extrae la información que nos interesa del xml y devuelve en un array de precios ***********/
    private List<Float> extrae_Precios(String Nombre_archivo){
        List<Float> precios= new ArrayList<>();
        FileInputStream file=null;
        try{
            file = openFileInput(Nombre_archivo);
            /*****************************************/
            WorkbookSettings ws = new WorkbookSettings();
            ws.setLocale(new Locale("es", "ES"));
            Workbook w = Workbook.getWorkbook(file, ws);

            // Gets the sheets from workbook
            for (int sheet = 0; sheet < w.getNumberOfSheets(); sheet++) {
                Sheet hoja = w.getSheet(sheet);

                Cell[] row = null;

                for (int i = 5; i < hoja.getRows(); i++) {
                    row = hoja.getRow(i);
                    if (row[4].getContents() != "")
                        precios.add(Float.parseFloat(row[4].getContents().replace(",", "."))/1000);
                }
            }
            file.close();
            w.close();
        }catch (IOException e){
            LOGGER.severe("Error en extrae_Precios  " + e.toString());
        }catch (BiffException e){
            LOGGER.severe("Error en extrae_Precios  "+e.toString());
        }catch (Exception e){
            LOGGER.severe("Error en extrae_Precios  " + e.toString());
        }finally {
            return precios;
        }
    }

    public String hoy(SimpleDateFormat mSimpleDateFormat){
        Calendar mCalendar =  Calendar.getInstance();
        String aux= mCalendar.getTime().toString();
        return mSimpleDateFormat.format(mCalendar.getTime());
    }
    public String mañana(SimpleDateFormat mSimpleDateFormat){
        Calendar mCalendar = new GregorianCalendar();
        mCalendar.add(Calendar.DAY_OF_YEAR, 1);
        return mSimpleDateFormat.format(mCalendar.getTime());
    }
    public String hace_un_año_de_hoy(SimpleDateFormat mSimpleDateFormat){
        Calendar mCalendar = new GregorianCalendar();
        mCalendar.add(Calendar.YEAR, -1);
        return mSimpleDateFormat.format(mCalendar.getTime());
    }
    public String hace_una_semana_de_hoy(SimpleDateFormat mSimpleDateFormat){
        Calendar mCalendar = new GregorianCalendar();
        mCalendar.add(Calendar.WEEK_OF_YEAR, -1);
        return mSimpleDateFormat.format(mCalendar.getTime());
    }
    public String hace_un_año_de_mañana(SimpleDateFormat mSimpleDateFormat){
        Calendar mCalendar = new GregorianCalendar();
        mCalendar.add(Calendar.YEAR, -1);
        mCalendar.add(Calendar.DATE, 1);
        return mSimpleDateFormat.format(mCalendar.getTime());
    }
    public String hace_una_semana_de_mañana(SimpleDateFormat mSimpleDateFormat){
        Calendar mCalendar = new GregorianCalendar();
        mCalendar.add(Calendar.WEEK_OF_YEAR, -1);
        mCalendar.add(Calendar.DATE, 1);
        return mSimpleDateFormat.format(mCalendar.getTime());
    }
    public String getTAGTarifa(){
        String tarifa_actual= "";
        try {
            switch (prefs.getInt(TAG_TARIFA_ACTUAL, 1)) {
                case 1:
                    tarifa_actual ="TARIFA 20A";
                    break;
                case 2:
                    tarifa_actual= "TARIFA 20DHA";
                    break;
                case 3:
                    tarifa_actual= "TARIFA 20DHS";
                    break;
                default:
                    tarifa_actual= "TARIFA 20A";
                    break;
            }
        }catch (Exception e){
            LOGGER.severe("Error en getTarifa  " + e.toString());
        }finally {
            return tarifa_actual;
        }
    }
    private boolean isInTwoPaneMode() {
        return findViewById(R.id.main_layout) == null;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        try {
            set_Fragment_actual_SharedPreferences(this.pager.getCurrentItem());
            switch (item.getItemId()) {
                case R.id.tarifa_20A:
                    set_Tarifa_SharedPreferences(TAG_TARIFA_ACTUAL, TARIFA_20A);
                    getSupportFragmentManager().beginTransaction().remove(fragment1).commit();
                    getSupportFragmentManager().beginTransaction().remove(fragment2).commit();
                    muestra_layout();
                    break;
                case R.id.tarifa_20DHA:
                    set_Tarifa_SharedPreferences(TAG_TARIFA_ACTUAL, TARIFA_20DHA);
                    getSupportFragmentManager().beginTransaction().remove(fragment1).commit();
                    getSupportFragmentManager().beginTransaction().remove(fragment2).commit();
                    muestra_layout();
                    break;
                case R.id.tarifa_20DHS:
                    set_Tarifa_SharedPreferences(TAG_TARIFA_ACTUAL, TARIFA_20DHS);
                    getSupportFragmentManager().beginTransaction().remove(fragment1).commit();
                    getSupportFragmentManager().beginTransaction().remove(fragment2).commit();
                    muestra_layout();
                    break;
                case R.id.hace_una_semana:
                    if (get_Fragment_actual_SharedPreferences() == 0)
                        set_Si_Precios_Pasados_SharedPreferences(PRECIOS_SEMANA_PASADA_DE_HOY_ACTIVADO);
                    else
                        set_Si_Precios_Pasados_SharedPreferences(PRECIOS_SEMANA_PASADA_DE_MAÑANA_ACTIVADO);
                    getSupportFragmentManager().beginTransaction().remove(fragment1).commit();
                    getSupportFragmentManager().beginTransaction().remove(fragment2).commit();
                    muestra_layout();
                    break;
                case R.id.hace_un_año:
                    if (prefs.getInt(FRAGMENT_ACTUAL, 0) == 0)
                        set_Si_Precios_Pasados_SharedPreferences(PRECIOS_AÑO_PASADO_DE_HOY_ACTIVADO);
                    else
                        set_Si_Precios_Pasados_SharedPreferences(PRECIOS_AÑO_PASADO_DE_MAÑANA_ACTIVADO);
                    getSupportFragmentManager().beginTransaction().remove(fragment1).commit();
                    getSupportFragmentManager().beginTransaction().remove(fragment2).commit();
                    muestra_layout();
                    break;
                case R.id.actualizar:
                    Comprobacion_explicita_actualizaciones_AsyncTask comprobar_actualizaciones = new Comprobacion_explicita_actualizaciones_AsyncTask();
                    comprobar_actualizaciones.execute();
                    break;
                case R.id.sobre:
                    android.app.DialogFragment un_dialogo = new Dialogo();
                    un_dialogo.show(this.getFragmentManager(), "");
                    break;
            }
        }catch (Exception e){
            LOGGER.severe("Error en getTarifa  " + e.toString());
        }finally {
            return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public void onBackPressed() {
        // Return to previous page when we press back button
        if (this.pager.getCurrentItem() == 0)
            super.onBackPressed();
        else
            this.pager.setCurrentItem(this.pager.getCurrentItem() - 1);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
    }

    class Comprobacion_inicial_actualizaciones_AsyncTask extends AsyncTask<Void, Void, Boolean> {
        private BeanCompruebaVersionApi BeanCompruebaVersionService=null;

        @Override
        protected Boolean doInBackground(Void...params) {
            if (BeanCompruebaVersionService == null) {
                BeanCompruebaVersionApi.Builder builder = new BeanCompruebaVersionApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null).setRootUrl("https://precioluz.appspot.com/_ah/api/");
                BeanCompruebaVersionService = builder.build();
            }
            try {
                Double version_actual = BeanCompruebaVersionService.getBeanCompruebaVersion().execute().getVersionActual();
                if (version_actual != version_de_esta_instancia) {
                    return true;
                }else{
                    return false;
                }

            }catch (IOException e){
                LOGGER.warning(e.toString());
            }
            return  false;
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
                BeanCompruebaVersionApi.Builder builder = new BeanCompruebaVersionApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null).setRootUrl("https://precioluz.appspot.com/_ah/api/");
                BeanCompruebaVersionService = builder.build();
            }
            try {
                Double version_actual = BeanCompruebaVersionService.getBeanCompruebaVersion().execute().getVersionActual();
                if (version_actual != version_de_esta_instancia) {
                    return true;
                }else{
                    return false;
                }

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
}
