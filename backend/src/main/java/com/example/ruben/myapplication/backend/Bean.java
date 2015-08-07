package com.example.ruben.myapplication.backend;

import java.util.List;

/**
 * Created by ruben on 7/8/15.
 */
public class Bean {
    private Float[] listapreciostodastarifas= new Float[72];


    public  void  setListapreciostodastarifas (Float[] precios) {
        listapreciostodastarifas=precios;
    }

    public Float[] getListapreciostodastarifas(){
        return listapreciostodastarifas;
    }
}
