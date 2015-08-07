package com.example.ruben.myapplication.backend;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Calendar;
import java.util.Locale;
import jxl.*;

/**
 * Created by ruben on 5/8/15.
 */
public class cron {


    protected void getPreciosHoy() {
        Calendar calendario =  Calendar.getInstance();
        String hoy= Integer.toString(calendario.YEAR)+Integer.toString(calendario.MONTH)+Integer.toString(calendario.DAY_OF_MONTH);
        try
        {

            /*****************  EN ESTA PARTE DESCARGAMOS EL FICHERO XLS  ***********************/
            OutputStream os = (OutputStream)new FileOutputStream(new File("precios.xls"));
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF8");
            BufferedWriter bw = new BufferedWriter(osw);

            String url = "www.esios.ree.es/Solicitar?fileName=PVPC_DETALLE_DD_"+hoy+"&fileType=xls&idioma=es";
            URL u= new URL(url);
            InputStream is = u.openStream();
            BufferedReader br=new BufferedReader(new InputStreamReader(is));

            String linea;

            while (( linea = br.readLine()) != null)
            {bw.write(linea);}
            bw.flush();
            bw.close();


            /*****************  EN ESTA PARTE LO LEEMOS Y LO GUARDAMOS EN EL DATASTORE  ***********************/
            //Excel document to be imported
            WorkbookSettings ws = new WorkbookSettings();
            ws.setLocale(new Locale("es", "ES"));
            Workbook w = Workbook.getWorkbook(new File("precios.xls"),ws);


            float[] precios_20A= new float[24];
            float[] precios_20DHA=new float[24];
            float[] precios_20DHS= new float[24];

            // Gets the sheets from workbook
            for (int sheet = 0; sheet < w.getNumberOfSheets(); sheet++)
            {
                Sheet hoja = w.getSheet(sheet);

                bw.write(hoja.getName());
                bw.newLine();

                Cell[] row = null;

                // Gets the cells from sheet
                int contador_tarifas=0;
                int contador=0;;
                for (int i = 5 ; i < hoja.getRows() ; i++)
                {
                    row = hoja.getRow(i);
                    if ((contador_tarifas %24) == 0){
                        contador=0;
                    }
                    if (row.length > 0)
                    {
                        if ((contador_tarifas < 24)){
                            //TARIFAS 20A
                            precios_20A[contador]= Float.parseFloat(row[4].getContents().replace(",", "."));
                            contador++;
                            contador_tarifas++;
                        }else if ((contador_tarifas > 24)&& (contador_tarifas < 48)){
                            //TARIFAS 20HDA
                            precios_20DHA[contador]= Float.parseFloat(row[4].getContents().replace(",", "."));
                            contador++;
                            contador_tarifas++;
                        }else if ((contador_tarifas > 48)){
                            //TARIFAS 20
                            precios_20DHS[contador]= Float.parseFloat(row[4].getContents().replace(",", "."));
                            contador++;
                            contador_tarifas++;
                        }
                    }
                }
            }

            new DataStore().setPrecios(hoy,precios_20A,precios_20DHA,precios_20DHS);
        }
        catch (UnsupportedEncodingException e)
        {
            System.err.println(e.toString());
        }
        catch (IOException e)
        {
            System.err.println(e.toString());
        }
        catch (Exception e)
        {
            System.err.println(e.toString());
        }finally {
            // faltaria borrar el fichero precios.xls
        }
    }
}
