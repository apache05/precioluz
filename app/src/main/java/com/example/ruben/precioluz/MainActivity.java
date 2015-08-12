package com.example.ruben.precioluz;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.ruben.myapplication.backend.beanApi.BeanApi;


public class MainActivity extends AppCompatActivity {
    ViewPager pager;
    Logger LOGGER= Logger.getLogger(getClass().getName());
    String TAG_TARIFA_ACTUAL= "TARIFA ACTUAL";
    final int TARIFA_20A = 1;
    final int TARIFA_20DHA = 2;
    final int TARIFA_20DHS = 3;
    final String TAG_HOY="HOY";
    final String TAG_MAÑANA= "MAÑANA";
    final String TAG_TARIFA_20A= "TARIFA_20A";
    final String TAG_TARIFA_20DHA= "TARIFA_20DHA";
    final String TAG_TARIFA_20DHS= "TARIFA_20DHS";
    final String TAG_NOMBRE_FICHERO= "DATOS";
    final String TAG_HOY_XLS= "PRECIOS_HOY.XLS";
    final String TAG_MAÑANA_XLS= "PRECIOS_MANYANA.XLS";
    final String TAG_FRAGMENT_ACTUAL= "FRAGMENT ACTUAL";
    SharedPreferences prefs= null;
    GraficasFragment fragment1;
    GraficasFragment fragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        prefs=getSharedPreferences(TAG_NOMBRE_FICHERO,Context.MODE_PRIVATE);
        getPrecios_AsyncTask mgetPrecios= new getPrecios_AsyncTask();
        mgetPrecios.execute();
    }

    class getPrecios_AsyncTask extends AsyncTask<Void,Void,Void>{
        @Override
        protected Void doInBackground(Void...params) {
            SimpleDateFormat sdf_año = new SimpleDateFormat("yyyy");
            SimpleDateFormat sdf_mes = new SimpleDateFormat("MM");
            SimpleDateFormat sdf_dia = new SimpleDateFormat("dd");

            baja_xls(TAG_HOY_XLS,hoy(sdf_año)+hoy(sdf_mes)+hoy(sdf_dia));
            setSharedPreferences(TAG_HOY, extrae_Precios(TAG_HOY_XLS));

            baja_xls(TAG_MAÑANA_XLS,mañana(sdf_año) + mañana(sdf_mes) + mañana(sdf_dia));
            setSharedPreferences(TAG_MAÑANA, extrae_Precios(TAG_MAÑANA_XLS));

            return null;
        }

        @Override
        protected void onPostExecute(Void algo) {
            pinta_barras();
        }
    }

    void setSharedPreferences(String TAG_DIA,List<Float> precios){
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
        }else{
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
    }
    void setSharedPreferences(String Key,int tarifa){
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(Key,tarifa);
        editor.commit();
    }
    void setSharedPreferences(int Fragment_actual){
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(TAG_FRAGMENT_ACTUAL,Fragment_actual);
        editor.commit();
    }
    List<Float> getSharedPreferences(String TAG_DIA){
        List<Float> precios_segun_tarifa= new ArrayList<>();

        switch (prefs.getInt(TAG_TARIFA_ACTUAL,1)) {
            case TARIFA_20A:
                for (Integer i = 0; i < 24; i++) {precios_segun_tarifa.add(prefs.getFloat(TAG_DIA+"_"+ TAG_TARIFA_20A+"_" + i.toString(), -1));}
                break;
            case TARIFA_20DHA:
                for (Integer i = 24; i < 48; i++) {precios_segun_tarifa.add(prefs.getFloat(TAG_DIA+"_" + TAG_TARIFA_20DHA+"_" + i.toString(), -1));}
                break;
            case TARIFA_20DHS:
                for (Integer i = 48; i < 72; i++) {precios_segun_tarifa.add(prefs.getFloat(TAG_DIA+"_" + TAG_TARIFA_20DHS+"_" + i.toString(), -1));}
                break;
        }
        return precios_segun_tarifa;
    }
    int getSharedPreferences(){
        return prefs.getInt(TAG_FRAGMENT_ACTUAL,0);
    }
    public  void pinta_barras() {
        SimpleDateFormat  mSimpleDateFormat= new SimpleDateFormat("yyyy-MM-dd", new Locale("es", "ES"));
        MyFragmentPagerAdapter adapter;


        List<Float> precios_hoy_segun_tarifa = getSharedPreferences(TAG_HOY);
        List<Float> precios_mañana_segun_tarifa = getSharedPreferences(TAG_MAÑANA);

        try {
            if (isInTwoPaneMode()) {
                //Con esto creamos el ViewPager
                //e instanciamos los dos fragments de graficas
                pager = (ViewPager) findViewById(R.id.pager);
                // Create an adapter with the fragments we show on the ViewPager
                adapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
                fragment1=  GraficasFragment.newInstance("Precios de la electricidad para hoy " + hoy(mSimpleDateFormat), precios_hoy_segun_tarifa);
                fragment2= GraficasFragment.newInstance("Precios de la electricidad para mañana " + mañana(mSimpleDateFormat), precios_mañana_segun_tarifa);
                adapter.addFragment(fragment1);
                adapter.addFragment(fragment2);
                pager.setAdapter(adapter);
                this.pager.setCurrentItem(getSharedPreferences());

            } else {
                //si es un móvil simplemente creamos un ViewPager con 3 fragments (opciones,graficas_hoy y graficas_mañana
                pager = (ViewPager) findViewById(R.id.pager);
                adapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
                adapter.addFragment(GraficasFragment.newInstance("Precios de la electricidad para hoy " + hoy(mSimpleDateFormat), precios_hoy_segun_tarifa));
                adapter.addFragment(GraficasFragment.newInstance("Precios de la electricidad para mañana " + mañana(mSimpleDateFormat), precios_mañana_segun_tarifa));

                pager.setAdapter(adapter);
                //ponemos para que se vea la graficas de hoy por defecto
                pager.setCurrentItem(1);
            }
        } catch (Exception e) {
            LOGGER.warning(e.toString());
        }
    }

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
            LOGGER.warning(e.toString());
        }catch (IOException e){
            LOGGER.warning(e.toString());
        }catch (Exception e){
            LOGGER.warning(e.toString());
        }finally {

        }
    }
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
            //LOGGER.warning(e.toString());
        }catch (BiffException e){
            //LOGGER.warning(e.toString());
        }catch (Exception e){
            LOGGER.warning(e.toString());
        }finally {
            getApplicationContext().deleteFile(Nombre_archivo);
        }
        return precios;
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
    private boolean isInTwoPaneMode() {
        return findViewById(R.id.main_layout) == null;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()){
            case R.id.tarifa_20A:
                setSharedPreferences(TAG_TARIFA_ACTUAL,TARIFA_20A);
                setSharedPreferences(this.pager.getCurrentItem());
                getSupportFragmentManager().beginTransaction().remove(fragment1).commit();
                getSupportFragmentManager().beginTransaction().remove(fragment2).commit();
                pinta_barras();
                break;
            case R.id.tarifa_20DHA:
                setSharedPreferences(TAG_TARIFA_ACTUAL, TARIFA_20DHA);
                setSharedPreferences(this.pager.getCurrentItem());
                getSupportFragmentManager().beginTransaction().remove(fragment1).commit();
                getSupportFragmentManager().beginTransaction().remove(fragment2).commit();
                pinta_barras();
                break;
            case R.id.tarifa_20DHS:
                setSharedPreferences(TAG_TARIFA_ACTUAL, TARIFA_20DHS);
                setSharedPreferences(this.pager.getCurrentItem());
                getSupportFragmentManager().beginTransaction().remove(fragment1).commit();
                getSupportFragmentManager().beginTransaction().remove(fragment2).commit();
                pinta_barras();
                break;
            case R.id.hace_una_semana:
                break;
            case R.id.hace_un_año:
                break;
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
        // Return to previous page when we press back button
        if (this.pager.getCurrentItem() == 0)
            super.onBackPressed();
        else
            this.pager.setCurrentItem(this.pager.getCurrentItem() - 1);
    }

    class EndpointAsyncTask extends AsyncTask<String, Void, List<Float>> {
        private BeanApi BeanApiService= null;
        public List<Float> lista_precios;

        @Override
        protected List<Float> doInBackground(String...params) {


           /* if(BeanApiService == null) {  // Only do this once
                /*BeanApi.Builder builder = new BeanApi.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null).setRootUrl("http://10.0.2.2:8080/_ah/api/").setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                    @Override
                    public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                        abstractGoogleClientRequest.setDisableGZipContent(true);
                    }
                });*/

            //BeanApi.Builder builder = new BeanApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null).setRootUrl("https://precioluz.appspot.com/_ah/api/");

            //BeanApiService = builder.build();
            //lista_precios  = BeanApiService.getBean(fecha).execute().getListapreciostodastarifas();
            //}


            return  null;
        }

        @Override
        protected void onPostExecute(List<Float> result) {
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
