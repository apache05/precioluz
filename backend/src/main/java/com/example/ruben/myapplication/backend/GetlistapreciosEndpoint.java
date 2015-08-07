package com.example.ruben.myapplication.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "listapreciosApi",
        version = "v1",
        resource = "listapreciosBean",
        namespace = @ApiNamespace(
                ownerDomain = "backend.myapplication.ruben.example.com",
                ownerName = "backend.myapplication.ruben.example.com",
                packagePath = ""
        )
)
public class GetlistapreciosEndpoint {

    @ApiMethod(name = "getlistapreciosBean")
    public listapreciosBean getlistapreciosBean(@Named ("dia") String dia) {
        //DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
        //Key Key_dia = KeyFactory.stringToKey(dia);
        //List<Float> precios_todas_tarifas_juntos= new ArrayList<>();


        try {
            //Entity precios_dia = datastoreService.get(Key_dia);
            //precios_todas_tarifas_juntos.add((float)precios_dia.getProperty("20A_00"));
            //precios_todas_tarifas_juntos.add((float)precios_dia.getProperty("20A_01"));
            //precios_todas_tarifas_juntos.add((float)precios_dia.getProperty("20A_02"));
            //precios_todas_tarifas_juntos.add((float)precios_dia.getProperty("20A_03"));
            //precios_todas_tarifas_juntos.add((float)precios_dia.getProperty("20A_04"));
            //precios_todas_tarifas_juntos.add((float)precios_dia.getProperty("20A_05"));
            //precios_todas_tarifas_juntos.add((float)precios_dia.getProperty("20A_06"));
            //precios_todas_tarifas_juntos.add((float)precios_dia.getProperty("20A_07"));
            //precios_todas_tarifas_juntos.add((float)precios_dia.getProperty("20A_08"));
            //precios_todas_tarifas_juntos.add((float)precios_dia.getProperty("20A_09"));
            //precios_todas_tarifas_juntos.add((float)precios_dia.getProperty("20A_10"));
            //precios_todas_tarifas_juntos.add((float)precios_dia.getProperty("20A_11"));
            //precios_todas_tarifas_juntos.add((float)precios_dia.getProperty("20A_12"));
            //precios_todas_tarifas_juntos.add((float)precios_dia.getProperty("20A_13"));
            //precios_todas_tarifas_juntos.add((float)precios_dia.getProperty("20A_14"));
            //precios_todas_tarifas_juntos.add((float)precios_dia.getProperty("20A_15"));
            //precios_todas_tarifas_juntos.add((float)precios_dia.getProperty("20A_16"));
            //precios_todas_tarifas_juntos.add((float)precios_dia.getProperty("20A_17"));
            //precios_todas_tarifas_juntos.add((float)precios_dia.getProperty("20A_18"));
            //precios_todas_tarifas_juntos.add((float)precios_dia.getProperty("20A_19"));
            //precios_todas_tarifas_juntos.add((float)precios_dia.getProperty("20A_20"));
            //precios_todas_tarifas_juntos.add((float)precios_dia.getProperty("20A_21"));
            //precios_todas_tarifas_juntos.add((float)precios_dia.getProperty("20A_22"));
            //precios_todas_tarifas_juntos.add((float)precios_dia.getProperty("20A_23"));

            //precios_todas_tarifas_juntos.add((float)precios_dia.getProperty("20DHA_00"));
            //precios_todas_tarifas_juntos.add((float)precios_dia.getProperty("20DHA_01"));
            //precios_todas_tarifas_juntos.add((float)precios_dia.getProperty("20DHA_02"));
            //precios_todas_tarifas_juntos.add((float)precios_dia.getProperty("20DHA_03"));
            //precios_todas_tarifas_juntos.add((float)precios_dia.getProperty("20DHA_04"));
            //precios_todas_tarifas_juntos.add((float)precios_dia.getProperty("20DHA_05"));
            //precios_todas_tarifas_juntos.add((float)precios_dia.getProperty("20DHA_06"));
            //precios_todas_tarifas_juntos.add((float)precios_dia.getProperty("20DHA_07"));
            //precios_todas_tarifas_juntos.add((float)precios_dia.getProperty("20DHA_08"));
            //precios_todas_tarifas_juntos.add((float)precios_dia.getProperty("20DHA_09"));
            //precios_todas_tarifas_juntos.add((float)precios_dia.getProperty("20DHA_10"));
            //precios_todas_tarifas_juntos.add((float)precios_dia.getProperty("20DHA_11"));
            //precios_todas_tarifas_juntos.add((float)precios_dia.getProperty("20DHA_12"));
            //precios_todas_tarifas_juntos.add((float)precios_dia.getProperty("20DHA_13"));
            //precios_todas_tarifas_juntos.add((float)precios_dia.getProperty("20DHA_14"));
            //precios_todas_tarifas_juntos.add((float)precios_dia.getProperty("20DHA_15"));
            //precios_todas_tarifas_juntos.add((float)precios_dia.getProperty("20DHA_16"));
            //precios_todas_tarifas_juntos.add((float)precios_dia.getProperty("20DHA_17"));
            //precios_todas_tarifas_juntos.add((float)precios_dia.getProperty("20DHA_18"));
            //precios_todas_tarifas_juntos.add((float)precios_dia.getProperty("20DHA_19"));
            //precios_todas_tarifas_juntos.add((float)precios_dia.getProperty("20DHA_20"));
            //precios_todas_tarifas_juntos.add((float)precios_dia.getProperty("20DHA_21"));
            //precios_todas_tarifas_juntos.add((float)precios_dia.getProperty("20DHA_22"));
            //precios_todas_tarifas_juntos.add((float)precios_dia.getProperty("20DHA_23"));

            //precios_todas_tarifas_juntos.add((float)precios_dia.getProperty("20DHS_00"));
            //precios_todas_tarifas_juntos.add((float)precios_dia.getProperty("20DHS_01"));
            //precios_todas_tarifas_juntos.add((float)precios_dia.getProperty("20DHS_02"));
            //precios_todas_tarifas_juntos.add((float)precios_dia.getProperty("20DHS_03"));
            //precios_todas_tarifas_juntos.add((float)precios_dia.getProperty("20DHS_04"));
            //precios_todas_tarifas_juntos.add((float)precios_dia.getProperty("20DHS_05"));
            //precios_todas_tarifas_juntos.add((float)precios_dia.getProperty("20DHS_06"));
            //precios_todas_tarifas_juntos.add((float)precios_dia.getProperty("20DHS_07"));
            //precios_todas_tarifas_juntos.add((float)precios_dia.getProperty("20DHS_08"));
            //precios_todas_tarifas_juntos.add((float)precios_dia.getProperty("20DHS_09"));
            //precios_todas_tarifas_juntos.add((float)precios_dia.getProperty("20DHS_10"));
            //precios_todas_tarifas_juntos.add((float)precios_dia.getProperty("20DHS_11"));
            //precios_todas_tarifas_juntos.add((float)precios_dia.getProperty("20DHS_12"));
            //precios_todas_tarifas_juntos.add((float)precios_dia.getProperty("20DHS_13"));
            //precios_todas_tarifas_juntos.add((float)precios_dia.getProperty("20DHS_14"));
            //precios_todas_tarifas_juntos.add((float)precios_dia.getProperty("20DHS_15"));
            //precios_todas_tarifas_juntos.add((float)precios_dia.getProperty("20DHS_16"));
            //precios_todas_tarifas_juntos.add((float)precios_dia.getProperty("20DHS_17"));
            //precios_todas_tarifas_juntos.add((float)precios_dia.getProperty("20DHS_18"));
            //precios_todas_tarifas_juntos.add((float)precios_dia.getProperty("20DHS_19"));
            //precios_todas_tarifas_juntos.add((float)precios_dia.getProperty("20DHS_20"));
            //precios_todas_tarifas_juntos.add((float)precios_dia.getProperty("20DHS_21"));
            //precios_todas_tarifas_juntos.add((float)precios_dia.getProperty("20DHS_22"));
            //precios_todas_tarifas_juntos.add((float)precios_dia.getProperty("20DHS_23"));
        }catch (Exception e){

        }
        listapreciosBean mlistapreciosBean= new listapreciosBean();
        //mlistapreciosBean.setListaPrecios(precios_todas_tarifas_juntos);
        return mlistapreciosBean;
    }

}