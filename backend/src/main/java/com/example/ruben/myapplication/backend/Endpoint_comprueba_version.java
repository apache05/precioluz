package com.example.ruben.myapplication.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

import java.util.logging.Logger;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "beanCompruebaVersionApi",
        version = "v1",
        resource = "bean_comprueba_version",
        namespace = @ApiNamespace(
                ownerDomain = "backend.myapplication.ruben.example.com",
                ownerName = "backend.myapplication.ruben.example.com",
                packagePath = ""
        )
)
public class Endpoint_comprueba_version {
    private static final Logger logger = Logger.getLogger(Endpoint_comprueba_version.class.getName());

    @ApiMethod(name = "getBean_comprueba_version")
    public Bean_comprueba_version getBean_comprueba_version() {
        Bean_comprueba_version mBean_comprueba_version= new Bean_comprueba_version();
        try{
            DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
            Query q = new Query("versiones");
            PreparedQuery pq =datastore.prepare(q);
            Entity result= pq.asSingleEntity();
            mBean_comprueba_version.setVersion_actual((Double)result.getProperty("version"));
        }catch (Exception e){
            logger.severe(e.toString());
        }

        return   mBean_comprueba_version;
    }

}