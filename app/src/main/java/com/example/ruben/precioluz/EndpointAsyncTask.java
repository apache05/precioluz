package com.example.ruben.precioluz;

import android.content.Context;
import android.os.AsyncTask;

import com.example.ruben.myapplication.backend.listapreciosApi.ListapreciosApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;
import java.util.List;

/**
 * Created by ruben on 5/8/15.
 */
public class EndpointAsyncTask extends AsyncTask <String, Void, List<Float>> {
    private static ListapreciosApi myApiService = null;
    private String palabra="hola";
    public List<Float> lista;

    @Override
    protected List<Float> doInBackground(String...params) {
        if(myApiService == null) {  // Only do this once
           /* ListapreciosApi.Builder builder = new ListapreciosApi.Builder(AndroidHttp.newCompatibleTransport(),
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
                    });*/

             ListapreciosApi.Builder builder = new ListapreciosApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://precioluz.appspot.com/_ah/api/");


            myApiService = builder.build();
        }
        List<Float> list= null;

        try{
            String dia= params[0];
            palabra  = myApiService.getlistapreciosBean(dia).execute().getAuxi();
        }catch (IOException e){

        }
        return list;
    }

    @Override
    protected void onPostExecute(List<Float> floats) {
        super.onPostExecute(floats);
        lista= floats;
    }
}
