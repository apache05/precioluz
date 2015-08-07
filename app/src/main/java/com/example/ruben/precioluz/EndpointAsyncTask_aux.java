package com.example.ruben.precioluz;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Pair;
import android.widget.Toast;

import com.example.ruben.myapplication.backend.listapreciosApi.ListapreciosApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ruben on 5/8/15.
 */
public class EndpointAsyncTask_aux extends AsyncTask <Pair<String, List<Float>> , Void, String> {
    private static ListapreciosApi myApiService = null;


    @Override
    protected String doInBackground(Pair<String , List<Float>>...params) {
        List <Float> precios= new ArrayList<>();
        String dia;

        if(myApiService == null) {  // Only do this once
            ListapreciosApi.Builder builder = new ListapreciosApi.Builder(AndroidHttp.newCompatibleTransport(),
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

            /*ListapreciosApi.Builder builder = new ListapreciosApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://precioluz.appspot.com/_ah/api/");*/
            // end options for devappserver

            myApiService = builder.build();
        }

        try{
            dia= params[0].first;
            precios= params[0].second;
            myApiService.setlistapreciosBean(dia,precios).execute();
        }catch (Exception e){

        }
        return null;
    }


}
