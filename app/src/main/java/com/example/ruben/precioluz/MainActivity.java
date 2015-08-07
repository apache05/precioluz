package com.example.ruben.precioluz;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.ruben.myapplication.backend.beanApi.BeanApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    private ViewPager pager= null;
    private List<Float>  precios= new ArrayList<Float>();
    Calendar mCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        for (int i=0; i<72; i++){
            precios.add(0f);
        }

        String yyyyMMdd_hoy= Integer.toString(mCalendar.YEAR)+Integer.toString(mCalendar.MONTH)+Integer.toString(mCalendar.DAY_OF_MONTH);
        String yyyyMMdd_mañana= Integer.toString(mCalendar.YEAR)+Integer.toString(mCalendar.MONTH)+Integer.toString(mCalendar.DAY_OF_MONTH+1);

        EndpointAsyncTask mEndpointAsynctask = new EndpointAsyncTask();
        mEndpointAsynctask.execute(yyyyMMdd_hoy);
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

    class EndpointAsyncTask extends AsyncTask<String, Void, List<Float>> {
        private BeanApi BeanApiService= null;
        public List<Float> lista_precios;

        @Override
        protected List<Float> doInBackground(String...params) {
            if(BeanApiService == null) {  // Only do this once
                /*BeanApi.Builder builder = new BeanApi.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null).setRootUrl("http://10.0.2.2:8080/_ah/api/").setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                                abstractGoogleClientRequest.setDisableGZipContent(true);
                            }
                        });*/

                BeanApi.Builder builder = new BeanApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null).setRootUrl("https://precioluz.appspot.com/_ah/api/");

                BeanApiService = builder.build();
            }

            try{
                String fecha= params[0];
                lista_precios  = BeanApiService.getBean(fecha).execute().getListapreciostodastarifas();
            }catch (IOException e){
                e.printStackTrace();
            }
            return  lista_precios;
        }

        @Override
        protected void onPostExecute(List<Float> result) {
            precios=result;
            pinta_barras();
        }
    }

    public void pinta_barras(){
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
}

