package com.example.ruben.myapplication.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Transaction;


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
public class SetlistapreciosEndpoint {
    @ApiMethod(name = "setlistapreciosBean")
    public void setlistapreciosBean(@Named ("dia") String dia, @Named ("lista_precios")List <Float> precios){
        DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
        Transaction mTransaction = datastoreService.beginTransaction();
        try {
            Key Key_dia = KeyFactory.stringToKey(dia);
            Entity lista_precios_entity = new Entity(Key_dia);
            lista_precios_entity.setProperty("20A_00", precios.get(0));
            lista_precios_entity.setProperty("20A_01", precios.get(1));
            lista_precios_entity.setProperty("20A_02", precios.get(2));
            lista_precios_entity.setProperty("20A_03", precios.get(3));
            lista_precios_entity.setProperty("20A_04", precios.get(4));
            lista_precios_entity.setProperty("20A_05", precios.get(5));
            lista_precios_entity.setProperty("20A_06", precios.get(6));
            lista_precios_entity.setProperty("20A_07", precios.get(7));
            lista_precios_entity.setProperty("20A_08", precios.get(8));
            lista_precios_entity.setProperty("20A_09", precios.get(9));
            lista_precios_entity.setProperty("20A_10", precios.get(10));
            lista_precios_entity.setProperty("20A_11", precios.get(11));
            lista_precios_entity.setProperty("20A_12", precios.get(12));
            lista_precios_entity.setProperty("20A_13", precios.get(13));
            lista_precios_entity.setProperty("20A_14", precios.get(14));
            lista_precios_entity.setProperty("20A_15", precios.get(15));
            lista_precios_entity.setProperty("20A_16", precios.get(16));
            lista_precios_entity.setProperty("20A_17", precios.get(17));
            lista_precios_entity.setProperty("20A_18", precios.get(18));
            lista_precios_entity.setProperty("20A_19", precios.get(19));
            lista_precios_entity.setProperty("20A_20", precios.get(20));
            lista_precios_entity.setProperty("20A_21", precios.get(21));
            lista_precios_entity.setProperty("20A_22", precios.get(22));
            lista_precios_entity.setProperty("20A_23", precios.get(23));

            lista_precios_entity.setProperty("20DHA_00", precios.get(24));
            lista_precios_entity.setProperty("20DHA_01", precios.get(25));
            lista_precios_entity.setProperty("20DHA_02", precios.get(26));
            lista_precios_entity.setProperty("20DHA_03", precios.get(27));
            lista_precios_entity.setProperty("20DHA_04", precios.get(28));
            lista_precios_entity.setProperty("20DHA_05", precios.get(29));
            lista_precios_entity.setProperty("20DHA_06", precios.get(30));
            lista_precios_entity.setProperty("20DHA_07", precios.get(31));
            lista_precios_entity.setProperty("20DHA_08", precios.get(32));
            lista_precios_entity.setProperty("20DHA_09", precios.get(33));
            lista_precios_entity.setProperty("20DHA_10", precios.get(34));
            lista_precios_entity.setProperty("20DHA_11", precios.get(35));
            lista_precios_entity.setProperty("20DHA_12", precios.get(36));
            lista_precios_entity.setProperty("20DHA_13", precios.get(37));
            lista_precios_entity.setProperty("20DHA_14", precios.get(38));
            lista_precios_entity.setProperty("20DHA_15", precios.get(39));
            lista_precios_entity.setProperty("20DHA_16", precios.get(40));
            lista_precios_entity.setProperty("20DHA_17", precios.get(41));
            lista_precios_entity.setProperty("20DHA_18", precios.get(42));
            lista_precios_entity.setProperty("20DHA_19", precios.get(43));
            lista_precios_entity.setProperty("20DHA_20", precios.get(44));
            lista_precios_entity.setProperty("20DHA_21", precios.get(45));
            lista_precios_entity.setProperty("20DHA_22", precios.get(46));
            lista_precios_entity.setProperty("20DHA_23", precios.get(47));

            lista_precios_entity.setProperty("20DHS_00", precios.get(48));
            lista_precios_entity.setProperty("20DHS_01", precios.get(49));
            lista_precios_entity.setProperty("20DHS_02", precios.get(50));
            lista_precios_entity.setProperty("20DHS_03", precios.get(51));
            lista_precios_entity.setProperty("20DHS_04", precios.get(52));
            lista_precios_entity.setProperty("20DHS_05", precios.get(53));
            lista_precios_entity.setProperty("20DHS_06", precios.get(54));
            lista_precios_entity.setProperty("20DHS_07", precios.get(55));
            lista_precios_entity.setProperty("20DHS_08", precios.get(56));
            lista_precios_entity.setProperty("20DHS_09", precios.get(57));
            lista_precios_entity.setProperty("20DHS_10", precios.get(58));
            lista_precios_entity.setProperty("20DHS_11", precios.get(59));
            lista_precios_entity.setProperty("20DHS_12", precios.get(60));
            lista_precios_entity.setProperty("20DHS_13", precios.get(61));
            lista_precios_entity.setProperty("20DHS_14", precios.get(62));
            lista_precios_entity.setProperty("20DHS_15", precios.get(63));
            lista_precios_entity.setProperty("20DHS_16", precios.get(64));
            lista_precios_entity.setProperty("20DHS_17", precios.get(65));
            lista_precios_entity.setProperty("20DHS_18", precios.get(66));
            lista_precios_entity.setProperty("20DHS_19", precios.get(67));
            lista_precios_entity.setProperty("20DHS_20", precios.get(68));
            lista_precios_entity.setProperty("20DHS_21", precios.get(69));
            lista_precios_entity.setProperty("20DHS_22", precios.get(70));
            lista_precios_entity.setProperty("20DHS_23", precios.get(71));
            datastoreService.put(lista_precios_entity);
            mTransaction.commit();
        } finally {
            if (mTransaction.isActive()) {
                mTransaction.rollback();
            }
        }
        listapreciosBean mlistaprecios= new listapreciosBean();
        mlistaprecios.setListaPrecios(precios);
    }
}