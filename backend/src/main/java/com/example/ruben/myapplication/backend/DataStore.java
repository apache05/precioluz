package com.example.ruben.myapplication.backend;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

/**
 * Created by ruben on 5/8/15.
 */
public class DataStore {
    private DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

    public float[] getPrecios(String dia){
        Key Key_dia = KeyFactory.stringToKey(dia);
        
        float precios_todas_tarifas_juntos[]= new float[72];
        
        try {
            Entity precios_dia = datastore.get(Key_dia);
            precios_todas_tarifas_juntos[0]= (float)precios_dia.getProperty("20A_00");
            precios_todas_tarifas_juntos[1]= (float)precios_dia.getProperty("20A_01");
            precios_todas_tarifas_juntos[2]= (float)precios_dia.getProperty("20A_02");
            precios_todas_tarifas_juntos[3]= (float)precios_dia.getProperty("20A_03");
            precios_todas_tarifas_juntos[4]= (float)precios_dia.getProperty("20A_04");
            precios_todas_tarifas_juntos[5]= (float)precios_dia.getProperty("20A_05");
            precios_todas_tarifas_juntos[6]= (float)precios_dia.getProperty("20A_06");
            precios_todas_tarifas_juntos[7]= (float)precios_dia.getProperty("20A_07");
            precios_todas_tarifas_juntos[8]= (float)precios_dia.getProperty("20A_08");
            precios_todas_tarifas_juntos[9]= (float)precios_dia.getProperty("20A_9");
            precios_todas_tarifas_juntos[10]= (float)precios_dia.getProperty("20A_10");
            precios_todas_tarifas_juntos[11]= (float)precios_dia.getProperty("20A_11");
            precios_todas_tarifas_juntos[12]= (float)precios_dia.getProperty("20A_12");
            precios_todas_tarifas_juntos[13]= (float)precios_dia.getProperty("20A_13");
            precios_todas_tarifas_juntos[14]= (float)precios_dia.getProperty("20A_14");
            precios_todas_tarifas_juntos[15]= (float)precios_dia.getProperty("20A_15");
            precios_todas_tarifas_juntos[16]= (float)precios_dia.getProperty("20A_16");
            precios_todas_tarifas_juntos[17]= (float)precios_dia.getProperty("20A_17");
            precios_todas_tarifas_juntos[18]= (float)precios_dia.getProperty("20A_18");
            precios_todas_tarifas_juntos[19]= (float)precios_dia.getProperty("20A_19");
            precios_todas_tarifas_juntos[20]= (float)precios_dia.getProperty("20A_20");
            precios_todas_tarifas_juntos[21]= (float)precios_dia.getProperty("20A_21");
            precios_todas_tarifas_juntos[22]= (float)precios_dia.getProperty("20A_22");
            precios_todas_tarifas_juntos[23]= (float)precios_dia.getProperty("20A_23");

            precios_todas_tarifas_juntos[24]= (float)precios_dia.getProperty("20DHA_00");
            precios_todas_tarifas_juntos[25]= (float)precios_dia.getProperty("20DHA_01");
            precios_todas_tarifas_juntos[26]= (float)precios_dia.getProperty("20DHA_02");
            precios_todas_tarifas_juntos[27]= (float)precios_dia.getProperty("20DHA_03");
            precios_todas_tarifas_juntos[28]= (float)precios_dia.getProperty("20DHA_04");
            precios_todas_tarifas_juntos[29]= (float)precios_dia.getProperty("20DHA_05");
            precios_todas_tarifas_juntos[30]= (float)precios_dia.getProperty("20DHA_06");
            precios_todas_tarifas_juntos[31]= (float)precios_dia.getProperty("20DHA_07");
            precios_todas_tarifas_juntos[32]= (float)precios_dia.getProperty("20DHA_08");
            precios_todas_tarifas_juntos[33]= (float)precios_dia.getProperty("20DHA_9");
            precios_todas_tarifas_juntos[34]= (float)precios_dia.getProperty("20DHA_10");
            precios_todas_tarifas_juntos[35]= (float)precios_dia.getProperty("20DHA_11");
            precios_todas_tarifas_juntos[36]= (float)precios_dia.getProperty("20DHA_12");
            precios_todas_tarifas_juntos[37]= (float)precios_dia.getProperty("20DHA_13");
            precios_todas_tarifas_juntos[38]= (float)precios_dia.getProperty("20DHA_14");
            precios_todas_tarifas_juntos[39]= (float)precios_dia.getProperty("20DHA_15");
            precios_todas_tarifas_juntos[40]= (float)precios_dia.getProperty("20DHA_16");
            precios_todas_tarifas_juntos[41]= (float)precios_dia.getProperty("20DHA_17");
            precios_todas_tarifas_juntos[42]= (float)precios_dia.getProperty("20DHA_18");
            precios_todas_tarifas_juntos[43]= (float)precios_dia.getProperty("20DHA_19");
            precios_todas_tarifas_juntos[44]= (float)precios_dia.getProperty("20DHA_20");
            precios_todas_tarifas_juntos[45]= (float)precios_dia.getProperty("20DHA_21");
            precios_todas_tarifas_juntos[46]= (float)precios_dia.getProperty("20DHA_22");
            precios_todas_tarifas_juntos[47]= (float)precios_dia.getProperty("20DHA_23");

            precios_todas_tarifas_juntos[48]= (float)precios_dia.getProperty("20DHS_00");
            precios_todas_tarifas_juntos[49]= (float)precios_dia.getProperty("20DHS_01");
            precios_todas_tarifas_juntos[50]= (float)precios_dia.getProperty("20DHS_02");
            precios_todas_tarifas_juntos[51]= (float)precios_dia.getProperty("20DHS_03");
            precios_todas_tarifas_juntos[52]= (float)precios_dia.getProperty("20DHS_04");
            precios_todas_tarifas_juntos[53]= (float)precios_dia.getProperty("20DHS_05");
            precios_todas_tarifas_juntos[54]= (float)precios_dia.getProperty("20DHS_06");
            precios_todas_tarifas_juntos[55]= (float)precios_dia.getProperty("20DHS_07");
            precios_todas_tarifas_juntos[56]= (float)precios_dia.getProperty("20DHS_08");
            precios_todas_tarifas_juntos[57]= (float)precios_dia.getProperty("20DHS_9");
            precios_todas_tarifas_juntos[58]= (float)precios_dia.getProperty("20DHS_10");
            precios_todas_tarifas_juntos[59]= (float)precios_dia.getProperty("20DHS_11");
            precios_todas_tarifas_juntos[60]= (float)precios_dia.getProperty("20DHS_12");
            precios_todas_tarifas_juntos[61]= (float)precios_dia.getProperty("20DHS_13");
            precios_todas_tarifas_juntos[62]= (float)precios_dia.getProperty("20DHS_14");
            precios_todas_tarifas_juntos[63]= (float)precios_dia.getProperty("20DHS_15");
            precios_todas_tarifas_juntos[64]= (float)precios_dia.getProperty("20DHS_16");
            precios_todas_tarifas_juntos[65]= (float)precios_dia.getProperty("20DHS_17");
            precios_todas_tarifas_juntos[66]= (float)precios_dia.getProperty("20DHS_18");
            precios_todas_tarifas_juntos[67]= (float)precios_dia.getProperty("20DHS_19");
            precios_todas_tarifas_juntos[68]= (float)precios_dia.getProperty("20DHS_20");
            precios_todas_tarifas_juntos[69]= (float)precios_dia.getProperty("20DHS_21");
            precios_todas_tarifas_juntos[70]= (float)precios_dia.getProperty("20DHS_22");
            precios_todas_tarifas_juntos[71]= (float)precios_dia.getProperty("20DHS_23");
        }catch (EntityNotFoundException e){

        }
        return precios_todas_tarifas_juntos;
    }
    public void setPrecios(String dia, float[] precios_del_dia_20A, float[] precios_del_dia_20DHA, float[] precios_del_dia_20DHS){
        Key Key_dia = KeyFactory.stringToKey(dia);

        Entity precios = new Entity(dia);

        precios.setProperty("20A_00", precios_del_dia_20A[0]);
        precios.setProperty("20A_01", precios_del_dia_20A[1]);
        precios.setProperty("20A_02", precios_del_dia_20A[2]);
        precios.setProperty("20A_03", precios_del_dia_20A[3]);
        precios.setProperty("20A_04", precios_del_dia_20A[4]);
        precios.setProperty("20A_05", precios_del_dia_20A[5]);
        precios.setProperty("20A_06", precios_del_dia_20A[6]);
        precios.setProperty("20A_07", precios_del_dia_20A[7]);
        precios.setProperty("20A_08", precios_del_dia_20A[8]);
        precios.setProperty("20A_09", precios_del_dia_20A[9]);
        precios.setProperty("20A_10", precios_del_dia_20A[10]);
        precios.setProperty("20A_11", precios_del_dia_20A[11]);
        precios.setProperty("20A_12", precios_del_dia_20A[12]);
        precios.setProperty("20A_13", precios_del_dia_20A[13]);
        precios.setProperty("20A_14", precios_del_dia_20A[14]);
        precios.setProperty("20A_15", precios_del_dia_20A[15]);
        precios.setProperty("20A_16", precios_del_dia_20A[16]);
        precios.setProperty("20A_17", precios_del_dia_20A[17]);
        precios.setProperty("20A_18", precios_del_dia_20A[18]);
        precios.setProperty("20A_19", precios_del_dia_20A[19]);
        precios.setProperty("20A_20", precios_del_dia_20A[20]);
        precios.setProperty("20A_21", precios_del_dia_20A[21]);
        precios.setProperty("20A_22", precios_del_dia_20A[22]);
        precios.setProperty("20A_23", precios_del_dia_20A[23]);

        precios.setProperty("20DHA_00", precios_del_dia_20DHA[0]);
        precios.setProperty("20DHA_01", precios_del_dia_20DHA[1]);
        precios.setProperty("20DHA_02", precios_del_dia_20DHA[2]);
        precios.setProperty("20DHA_03", precios_del_dia_20DHA[3]);
        precios.setProperty("20DHA_04", precios_del_dia_20DHA[4]);
        precios.setProperty("20DHA_05", precios_del_dia_20DHA[5]);
        precios.setProperty("20DHA_06", precios_del_dia_20DHA[6]);
        precios.setProperty("20DHA_07", precios_del_dia_20DHA[7]);
        precios.setProperty("20DHA_08", precios_del_dia_20DHA[8]);
        precios.setProperty("20DHA_09", precios_del_dia_20DHA[9]);
        precios.setProperty("20DHA_10", precios_del_dia_20DHA[10]);
        precios.setProperty("20DHA_11", precios_del_dia_20DHA[11]);
        precios.setProperty("20DHA_12", precios_del_dia_20DHA[12]);
        precios.setProperty("20DHA_13", precios_del_dia_20DHA[13]);
        precios.setProperty("20DHA_14", precios_del_dia_20DHA[14]);
        precios.setProperty("20DHA_15", precios_del_dia_20DHA[15]);
        precios.setProperty("20DHA_16", precios_del_dia_20DHA[16]);
        precios.setProperty("20DHA_17", precios_del_dia_20DHA[17]);
        precios.setProperty("20DHA_18", precios_del_dia_20DHA[18]);
        precios.setProperty("20DHA_19", precios_del_dia_20DHA[19]);
        precios.setProperty("20DHA_20", precios_del_dia_20DHA[20]);
        precios.setProperty("20DHA_21", precios_del_dia_20DHA[21]);
        precios.setProperty("20DHA_22", precios_del_dia_20DHA[22]);
        precios.setProperty("20DHA_23", precios_del_dia_20DHA[23]);

        precios.setProperty("20DHS_00", precios_del_dia_20DHS[0]);
        precios.setProperty("20DHS_01", precios_del_dia_20DHS[1]);
        precios.setProperty("20DHS_02", precios_del_dia_20DHS[2]);
        precios.setProperty("20DHS_03", precios_del_dia_20DHS[3]);
        precios.setProperty("20DHS_04", precios_del_dia_20DHS[4]);
        precios.setProperty("20DHS_05", precios_del_dia_20DHS[5]);
        precios.setProperty("20DHS_06", precios_del_dia_20DHS[6]);
        precios.setProperty("20DHS_07", precios_del_dia_20DHS[7]);
        precios.setProperty("20DHS_08", precios_del_dia_20DHS[8]);
        precios.setProperty("20DHS_09", precios_del_dia_20DHS[9]);
        precios.setProperty("20DHS_10", precios_del_dia_20DHS[10]);
        precios.setProperty("20DHS_11", precios_del_dia_20DHS[11]);
        precios.setProperty("20DHS_12", precios_del_dia_20DHS[12]);
        precios.setProperty("20DHS_13", precios_del_dia_20DHS[13]);
        precios.setProperty("20DHS_14", precios_del_dia_20DHS[14]);
        precios.setProperty("20DHS_15", precios_del_dia_20DHS[15]);
        precios.setProperty("20DHS_16", precios_del_dia_20DHS[16]);
        precios.setProperty("20DHS_17", precios_del_dia_20DHS[17]);
        precios.setProperty("20DHS_18", precios_del_dia_20DHS[18]);
        precios.setProperty("20DHS_19", precios_del_dia_20DHS[19]);
        precios.setProperty("20DHS_20", precios_del_dia_20DHS[20]);
        precios.setProperty("20DHS_21", precios_del_dia_20DHS[21]);
        precios.setProperty("20DHS_22", precios_del_dia_20DHS[22]);
        precios.setProperty("20DHS_23", precios_del_dia_20DHS[23]);
        datastore.put(precios);

    }
}
