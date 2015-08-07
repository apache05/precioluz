package com.example.ruben.precioluz;

import java.util.ArrayList;
import java.util.Calendar;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;


public class MainActivity extends AppCompatActivity {
    private ViewPager pager= null;
    private List<Float>  precios= new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Calendar mCalendar = Calendar.getInstance();
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("dd-MM-yyyy",new Locale("es","ES"));
        Date hoy= mCalendar.getTime();
        mCalendar.add(Calendar.DAY_OF_YEAR, 1);
        Date mañana= mCalendar.getTime();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);



        EndpointAsyncTask mEndpointAsynctask = new EndpointAsyncTask();
        mEndpointAsynctask.execute(hoy.toString());
        //precios= mEndpointAsynctask.lista;
        //Toast.makeText(getApplicationContext(), precios.get(2).toString(), Toast.LENGTH_LONG).show();

        Random rnd = new Random();
        for (int i=0; i<72; i++){
            precios.add(rnd.nextFloat());
        }
        //EndpointAsyncTask_aux mEndpointAsynctask_aux = new EndpointAsyncTask_aux();
        //mEndpointAsynctask_aux.execute(new Pair<String,List<Float>>(hoy.toString(),precios));

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

    private boolean isInTwoPaneMode() {

        return findViewById(R.id.main_layout) == null;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {

        // Return to previous page when we press back button
        if (this.pager.getCurrentItem() == 0)
            super.onBackPressed();
        else
            this.pager.setCurrentItem(this.pager.getCurrentItem() - 1);

    }
}
