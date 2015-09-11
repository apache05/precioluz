package com.example.ruben.myapplication.backend;

import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.*;

/**
 * Created by ruben on 17/8/15.
 */
public class Bean_comprueba_version {
    private Double version_actual;

    public Double getVersion_actual(){
        return version_actual;
    }

    public void setVersion_actual(Double mVersion_actual){
        version_actual= mVersion_actual;
    }
}
