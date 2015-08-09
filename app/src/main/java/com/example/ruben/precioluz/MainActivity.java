package com.example.ruben.precioluz;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;
import java.text.SimpleDateFormat;
import jxl.*;
import jxl.Workbook;
import jxl.WorkbookSettings;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import com.example.ruben.myapplication.backend.beanApi.BeanApi;


public class MainActivity extends AppCompatActivity {
    private ViewPager pager= null;
    Logger LOGGER= Logger.getLogger(getClass().getName());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Calendar mCalendar = new GregorianCalendar();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);


        mCalendar.setTime(new Date());
        String yyyyMMdd_hoy= getFechaFormateada( mCalendar);
        mCalendar.add(Calendar.DATE, 1);
        String yyyyMMdd_mañana=getFechaFormateada(mCalendar);

        getPrecios_AsyncTask mgetPrecios= new getPrecios_AsyncTask();
        mgetPrecios.execute(yyyyMMdd_hoy);
    }
    private String getFechaFormateada(Calendar mCalendar){

        SimpleDateFormat sdf_año = new SimpleDateFormat("yyyy");
        SimpleDateFormat sdf_mes = new SimpleDateFormat("MM");
        SimpleDateFormat sdf_dia = new SimpleDateFormat("dd");
        return sdf_año.format(mCalendar.getTime())+sdf_mes.format(mCalendar.getTime())+sdf_dia.format(mCalendar.getTime());
    }

    private boolean isInTwoPaneMode() {
        return findViewById(R.id.main_layout) == null;
    }

    @Override
    public void onBackPressed() {
        // Return to previous page when we press back button
        if (this.pager.getCurrentItem() == 0)
            super.onBackPressed();
        else
            this.pager.setCurrentItem(this.pager.getCurrentItem() - 1);
    }

    class getPrecios_AsyncTask extends AsyncTask<String,Void,List<Float>>{
        @Override
        protected List<Float> doInBackground(String...params) {
            return getPrecios(params[0]);
        }

        @Override
        protected void onPostExecute(List<Float> result) {
            pinta_barras(result);
        }
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


            return  getPrecios(params[0]);
        }

        @Override
        protected void onPostExecute(List<Float> result) {
        }
    }

    public void pinta_barras(List<Float> precios){
        Calendar mCalendar = new GregorianCalendar();
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd",new Locale("es","ES"));
        Date hoy= mCalendar.getTime();
        mCalendar.add(Calendar.DAY_OF_YEAR, 1);
        Date mañana= mCalendar.getTime();

        if (isInTwoPaneMode()){
            //Con esto enlazamos el fragment de opciones con la clase opciones que lo gestion
            FragmentManager FM = getSupportFragmentManager();
            FragmentTransaction FT = FM.beginTransaction();
            FT.add(R.id.opciones_fragment, OpcionesFragment.newInstance());
            FT.addToBackStack(null);
            FT.commit();

            //Con esto creamos el ViewPager
            //e instanciamos los dos fragments de graficas
            this.pager= (ViewPager) findViewById(R.id.pager);
            // Create an adapter with the fragments we show on the ViewPager
            MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
            adapter.addFragment(GraficasFragment.newInstance("Precios de la electricidad para hoy " + mSimpleDateFormat.format(hoy),  precios));
            adapter.addFragment(GraficasFragment.newInstance("Precios de la electricidad para mañana " + mSimpleDateFormat.format(mañana),  precios));
            this.pager.setAdapter(adapter);
        }else {
            //si es un móvil simplemente creamos un ViewPager con 3 fragments (opciones,graficas_hoy y graficas_mañana
            this.pager= (ViewPager) findViewById(R.id.pager);
            MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
            adapter.addFragment(OpcionesFragment.newInstance());
            adapter.addFragment(GraficasFragment.newInstance("Precios de la electricidad para hoy "+ mSimpleDateFormat.format(hoy), precios));
            adapter.addFragment(GraficasFragment.newInstance("Precios de la electricidad para mañana "+ mSimpleDateFormat.format(mañana),  precios));

            this.pager.setAdapter(adapter);
            //ponemos para que se vea la graficas de hoy por defecto
            pager.setCurrentItem(1);
        }
    }

    private List<Float> getPrecios(String fecha){
        List<Float> precios_20A= new ArrayList<>();
        List<Float> precios_20DHA=new ArrayList<>();
        List<Float> precios_20DHS=new ArrayList<>();

        try{
            int num_bytes= 1024*4;
            byte[] buffer = new byte[num_bytes];


            String url = "http://www.esios.ree.es/Solicitar?fileName=PVPC_DETALLE_DD_"+fecha+"&fileType=xls&idioma=es";
            URL u= new URL(url);
            InputStream is = u.openStream();


            FileOutputStream fOut = openFileOutput("precios.xls",MODE_PRIVATE);
            int cantidad;
            while ((cantidad= is.read(buffer,0,num_bytes)) != -1){
                fOut.write(buffer, 0, cantidad);
            }
            fOut.close();

            //File file = new File("precios.xls");
            FileInputStream file = openFileInput("precios.xls");
            /*****************************************/
            WorkbookSettings ws = new WorkbookSettings();
            ws.setLocale(new Locale("es", "ES"));
            Workbook w = Workbook.getWorkbook(file,ws);


            // Gets the sheets from workbook
            for (int sheet = 0; sheet < w.getNumberOfSheets(); sheet++) {
                Sheet hoja = w.getSheet(sheet);


                Cell[] row = null;

                // Gets the cells from sheet
                int contador_tarifas = 0;
                int contador = 0;

                for (int i = 5; i < hoja.getRows(); i++) {
                    row = hoja.getRow(i);
                    if ((contador_tarifas % 24) == 0) {
                        contador = 0;
                    }
                    if (row.length > 0) {
                        if ((contador_tarifas < 24)) {
                            //TARIFAS 20A
                            precios_20A.add(Float.parseFloat(row[4].getContents().replace(",", ".")));
                            contador++;
                            contador_tarifas++;
                        } else if ((contador_tarifas > 24) && (contador_tarifas < 48)) {
                            //TARIFAS 20HDA
                            precios_20DHA.add(Float.parseFloat(row[4].getContents().replace(",", ".")));
                            contador++;
                            contador_tarifas++;
                        } else if ((contador_tarifas > 48)) {
                            //TARIFAS 20
                            precios_20DHS.add(Float.parseFloat(row[4].getContents().replace(",", ".")));
                            contador++;
                            contador_tarifas++;
                        }
                    }
                }
            }

            }catch(MalformedURLException e){
                LOGGER.warning(e.toString());
            }catch (IOException e){
                LOGGER.warning(e.toString());
            }catch (Exception e){
                LOGGER.warning(e.toString());
            }
        return precios_20A;
    }
}
