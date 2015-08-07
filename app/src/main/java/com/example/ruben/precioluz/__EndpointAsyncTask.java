package com.example.ruben.precioluz;

import android.os.AsyncTask;

import com.example.ruben.myapplication.backend.beanApi.BeanApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.api.client.util.escape.Escaper;

import java.io.IOException;
import java.util.List;

/**
 * Created by ruben on 5/8/15.
 */
class EndpointAsyncTask extends AsyncTask <String, Void, List<Float>> {

    private static BeanApi BeanApiService= null;
    public List<Float> lista_precios;

    @Override
    protected List<Float> doInBackground(String...params) {
        if(BeanApiService == null) {  // Only do this once
            BeanApi.Builder builder = new BeanApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });

            /*BeanApi.Builder builder = new BeanApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://precioluz.appspot.com/_ah/api/");*/


            BeanApiService = builder.build();
        }

        try{
            String fecha= params[0];
            //lista_precios  = BeanApiService.getBean(fecha).execute().getListapreciostodastarifas();
        }catch (Exception e){

        }
        return  lista_precios;
    }

    @Override
    protected void onPostExecute(List<Float> result) {
        lista_precios=result;
    }

}
