package com.example.ruben.myapplication.backend;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ruben on 6/8/15.
 */
public class listapreciosBean {
    private List <Float> mlista_precios= new ArrayList<>();

    private String myData;

    public String getData() {
        return myData;
    }
    public void setData(String data) {
        myData = data;
    }
    public void setListaPrecios(List<Float> lista_precios){
        mlista_precios=lista_precios;
    }
    public List <Float> getListaPrecios(){
        return mlista_precios;
    }
}
