package com.ruben.precioluz2.Fragments;

import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ruben.precioluz2.Activities.R;
import com.ruben.precioluz2.utils.utils;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;


public class LandscapeFragment extends Fragment {

    private static final String TITULO = "TITULO";

    private static final String PRECIO0 = "PRECIO0";
    private static final String PRECIO1 = "PRECIO1";
    private static final String PRECIO2 = "PRECIO2";
    private static final String PRECIO3 = "PRECIO3";
    private static final String PRECIO4 = "PRECIO4";
    private static final String PRECIO5 = "PRECIO5";
    private static final String PRECIO6 = "PRECIO6";
    private static final String PRECIO7 = "PRECIO7";
    private static final String PRECIO8 = "PRECIO8";
    private static final String PRECIO9 = "PRECIO9";
    private static final String PRECIO10 = "PRECIO10";
    private static final String PRECIO11 = "PRECIO11";
    private static final String PRECIO12 = "PRECIO12";
    private static final String PRECIO13 = "PRECIO13";
    private static final String PRECIO14 = "PRECIO14";
    private static final String PRECIO15 = "PRECIO15";
    private static final String PRECIO16 = "PRECIO16";
    private static final String PRECIO17 = "PRECIO17";
    private static final String PRECIO18 = "PRECIO18";
    private static final String PRECIO19 = "PRECIO19";
    private static final String PRECIO20 = "PRECIO20";
    private static final String PRECIO21 = "PRECIO21";
    private static final String PRECIO22 = "PRECIO22";
    private static final String PRECIO23 = "PRECIO23";

    private static final String HACE_UN_AÑO_PRECIO0 = "HACE_UN_AÑO_PRECIO0";
    private static final String HACE_UN_AÑO_PRECIO1 = "HACE_UN_AÑO_PRECIO1";
    private static final String HACE_UN_AÑO_PRECIO2 = "HACE_UN_AÑO_PRECIO2";
    private static final String HACE_UN_AÑO_PRECIO3 = "HACE_UN_AÑO_PRECIO3";
    private static final String HACE_UN_AÑO_PRECIO4 = "HACE_UN_AÑO_PRECIO4";
    private static final String HACE_UN_AÑO_PRECIO5 = "HACE_UN_AÑO_PRECIO5";
    private static final String HACE_UN_AÑO_PRECIO6 = "HACE_UN_AÑO_PRECIO6";
    private static final String HACE_UN_AÑO_PRECIO7 = "HACE_UN_AÑO_PRECIO7";
    private static final String HACE_UN_AÑO_PRECIO8 = "HACE_UN_AÑO_PRECIO8";
    private static final String HACE_UN_AÑO_PRECIO9 = "HACE_UN_AÑO_PRECIO9";
    private static final String HACE_UN_AÑO_PRECIO10 = "HACE_UN_AÑO_PRECIO10";
    private static final String HACE_UN_AÑO_PRECIO11 = "HACE_UN_AÑO_PRECIO11";
    private static final String HACE_UN_AÑO_PRECIO12 = "HACE_UN_AÑO_PRECIO12";
    private static final String HACE_UN_AÑO_PRECIO13 = "HACE_UN_AÑO_PRECIO13";
    private static final String HACE_UN_AÑO_PRECIO14 = "HACE_UN_AÑO_PRECIO14";
    private static final String HACE_UN_AÑO_PRECIO15 = "HACE_UN_AÑO_PRECIO15";
    private static final String HACE_UN_AÑO_PRECIO16 = "HACE_UN_AÑO_PRECIO16";
    private static final String HACE_UN_AÑO_PRECIO17 = "HACE_UN_AÑO_PRECIO17";
    private static final String HACE_UN_AÑO_PRECIO18 = "HACE_UN_AÑO_PRECIO18";
    private static final String HACE_UN_AÑO_PRECIO19 = "HACE_UN_AÑO_PRECIO19";
    private static final String HACE_UN_AÑO_PRECIO20 = "HACE_UN_AÑO_PRECIO20";
    private static final String HACE_UN_AÑO_PRECIO21 = "HACE_UN_AÑO_PRECIO21";
    private static final String HACE_UN_AÑO_PRECIO22 = "HACE_UN_AÑO_PRECIO22";
    private static final String HACE_UN_AÑO_PRECIO23 = "HACE_UN_AÑO_PRECIO23";
    
    private static final String HACE_UNA_SEMANA_PRECIO0 = "HACE_UNA_SEMANA_PRECIO0";
    private static final String HACE_UNA_SEMANA_PRECIO1 = "HACE_UNA_SEMANA_PRECIO1";
    private static final String HACE_UNA_SEMANA_PRECIO2 = "HACE_UNA_SEMANA_PRECIO2";
    private static final String HACE_UNA_SEMANA_PRECIO3 = "HACE_UNA_SEMANA_PRECIO3";
    private static final String HACE_UNA_SEMANA_PRECIO4 = "HACE_UNA_SEMANA_PRECIO4";
    private static final String HACE_UNA_SEMANA_PRECIO5 = "HACE_UNA_SEMANA_PRECIO5";
    private static final String HACE_UNA_SEMANA_PRECIO6 = "HACE_UNA_SEMANA_PRECIO6";
    private static final String HACE_UNA_SEMANA_PRECIO7 = "HACE_UNA_SEMANA_PRECIO7";
    private static final String HACE_UNA_SEMANA_PRECIO8 = "HACE_UNA_SEMANA_PRECIO8";
    private static final String HACE_UNA_SEMANA_PRECIO9 = "HACE_UNA_SEMANA_PRECIO9";
    private static final String HACE_UNA_SEMANA_PRECIO10 = "HACE_UNA_SEMANA_PRECIO10";
    private static final String HACE_UNA_SEMANA_PRECIO11 = "HACE_UNA_SEMANA_PRECIO11";
    private static final String HACE_UNA_SEMANA_PRECIO12 = "HACE_UNA_SEMANA_PRECIO12";
    private static final String HACE_UNA_SEMANA_PRECIO13 = "HACE_UNA_SEMANA_PRECIO13";
    private static final String HACE_UNA_SEMANA_PRECIO14 = "HACE_UNA_SEMANA_PRECIO14";
    private static final String HACE_UNA_SEMANA_PRECIO15 = "HACE_UNA_SEMANA_PRECIO15";
    private static final String HACE_UNA_SEMANA_PRECIO16 = "HACE_UNA_SEMANA_PRECIO16";
    private static final String HACE_UNA_SEMANA_PRECIO17 = "HACE_UNA_SEMANA_PRECIO17";
    private static final String HACE_UNA_SEMANA_PRECIO18 = "HACE_UNA_SEMANA_PRECIO18";
    private static final String HACE_UNA_SEMANA_PRECIO19 = "HACE_UNA_SEMANA_PRECIO19";
    private static final String HACE_UNA_SEMANA_PRECIO20 = "HACE_UNA_SEMANA_PRECIO20";
    private static final String HACE_UNA_SEMANA_PRECIO21 = "HACE_UNA_SEMANA_PRECIO21";
    private static final String HACE_UNA_SEMANA_PRECIO22 = "HACE_UNA_SEMANA_PRECIO22";
    private static final String HACE_UNA_SEMANA_PRECIO23 = "HACE_UNA_SEMANA_PRECIO23";

    private static final String PRECIOS_SEMANA_PASADA= "PRECIOS_SEMANA_PASADA";
    private static final String PRECIOS_AÑO_PASADO= "PRECIOS_AÑO_PASADO";

    private String titulo;
    private final Float[] precios= new Float[24]; //pueden ser los de hoy o los de mañana pq es el mismo fragment
    private final Float[] precios_hace_una_semana= new Float[24];
    private final Float[] precios_hace_un_año= new Float[24];
    private boolean precios_semana_pasada_activado;
    private boolean precios_año_pasado_activado;

    private final int ESCALA=Px2DP(1200);//Altura de las barras
    private final int ANCHURA=Px2DP(26);//anchura de las barras

    public static LandscapeFragment newInstance(String titulo, Float[] precios, boolean boolean_precios_semana_pasada, boolean boolean_precios_año_pasado, Float[] precios_hace_un_año, Float[] precios_hace_una_semana) {
        // Instantiate a new fragment
        LandscapeFragment fragment = new LandscapeFragment();
        // Save the parameters
        Bundle bundle = new Bundle();
        try {
            if ((precios != null) && (precios.length != 0)) {
                bundle.putString(TITULO, titulo);
                bundle.putFloat(PRECIO0, precios[0]);
                bundle.putFloat(PRECIO1, precios[1]);
                bundle.putFloat(PRECIO2, precios[2]);
                bundle.putFloat(PRECIO3, precios[3]);
                bundle.putFloat(PRECIO4, precios[4]);
                bundle.putFloat(PRECIO5, precios[5]);
                bundle.putFloat(PRECIO6, precios[6]);
                bundle.putFloat(PRECIO7, precios[7]);
                bundle.putFloat(PRECIO8, precios[8]);
                bundle.putFloat(PRECIO9, precios[9]);
                bundle.putFloat(PRECIO10, precios[10]);
                bundle.putFloat(PRECIO11, precios[11]);
                bundle.putFloat(PRECIO12, precios[12]);
                bundle.putFloat(PRECIO13, precios[13]);
                bundle.putFloat(PRECIO14, precios[14]);
                bundle.putFloat(PRECIO15, precios[15]);
                bundle.putFloat(PRECIO16, precios[16]);
                bundle.putFloat(PRECIO17, precios[17]);
                bundle.putFloat(PRECIO18, precios[18]);
                bundle.putFloat(PRECIO19, precios[19]);
                bundle.putFloat(PRECIO20, precios[20]);
                bundle.putFloat(PRECIO21, precios[21]);
                bundle.putFloat(PRECIO22, precios[22]);
                bundle.putFloat(PRECIO23, precios[23]);

                bundle.putFloat(HACE_UNA_SEMANA_PRECIO0, precios_hace_una_semana[0]);
                bundle.putFloat(HACE_UNA_SEMANA_PRECIO1, precios_hace_una_semana[1]);
                bundle.putFloat(HACE_UNA_SEMANA_PRECIO2, precios_hace_una_semana[2]);
                bundle.putFloat(HACE_UNA_SEMANA_PRECIO3, precios_hace_una_semana[3]);
                bundle.putFloat(HACE_UNA_SEMANA_PRECIO4, precios_hace_una_semana[4]);
                bundle.putFloat(HACE_UNA_SEMANA_PRECIO5, precios_hace_una_semana[5]);
                bundle.putFloat(HACE_UNA_SEMANA_PRECIO6, precios_hace_una_semana[6]);
                bundle.putFloat(HACE_UNA_SEMANA_PRECIO7, precios_hace_una_semana[7]);
                bundle.putFloat(HACE_UNA_SEMANA_PRECIO8, precios_hace_una_semana[8]);
                bundle.putFloat(HACE_UNA_SEMANA_PRECIO9, precios_hace_una_semana[9]);
                bundle.putFloat(HACE_UNA_SEMANA_PRECIO10, precios_hace_una_semana[10]);
                bundle.putFloat(HACE_UNA_SEMANA_PRECIO11, precios_hace_una_semana[11]);
                bundle.putFloat(HACE_UNA_SEMANA_PRECIO12, precios_hace_una_semana[12]);
                bundle.putFloat(HACE_UNA_SEMANA_PRECIO13, precios_hace_una_semana[13]);
                bundle.putFloat(HACE_UNA_SEMANA_PRECIO14, precios_hace_una_semana[14]);
                bundle.putFloat(HACE_UNA_SEMANA_PRECIO15, precios_hace_una_semana[15]);
                bundle.putFloat(HACE_UNA_SEMANA_PRECIO16, precios_hace_una_semana[16]);
                bundle.putFloat(HACE_UNA_SEMANA_PRECIO17, precios_hace_una_semana[17]);
                bundle.putFloat(HACE_UNA_SEMANA_PRECIO18, precios_hace_una_semana[18]);
                bundle.putFloat(HACE_UNA_SEMANA_PRECIO19, precios_hace_una_semana[19]);
                bundle.putFloat(HACE_UNA_SEMANA_PRECIO20, precios_hace_una_semana[20]);
                bundle.putFloat(HACE_UNA_SEMANA_PRECIO21, precios_hace_una_semana[21]);
                bundle.putFloat(HACE_UNA_SEMANA_PRECIO22, precios_hace_una_semana[22]);
                bundle.putFloat(HACE_UNA_SEMANA_PRECIO23, precios_hace_una_semana[23]);

                bundle.putFloat(HACE_UN_AÑO_PRECIO0, precios_hace_un_año[0]);
                bundle.putFloat(HACE_UN_AÑO_PRECIO1, precios_hace_un_año[1]);
                bundle.putFloat(HACE_UN_AÑO_PRECIO2, precios_hace_un_año[2]);
                bundle.putFloat(HACE_UN_AÑO_PRECIO3, precios_hace_un_año[3]);
                bundle.putFloat(HACE_UN_AÑO_PRECIO4, precios_hace_un_año[4]);
                bundle.putFloat(HACE_UN_AÑO_PRECIO5, precios_hace_un_año[5]);
                bundle.putFloat(HACE_UN_AÑO_PRECIO6, precios_hace_un_año[6]);
                bundle.putFloat(HACE_UN_AÑO_PRECIO7, precios_hace_un_año[7]);
                bundle.putFloat(HACE_UN_AÑO_PRECIO8, precios_hace_un_año[8]);
                bundle.putFloat(HACE_UN_AÑO_PRECIO9, precios_hace_un_año[9]);
                bundle.putFloat(HACE_UN_AÑO_PRECIO10, precios_hace_un_año[10]);
                bundle.putFloat(HACE_UN_AÑO_PRECIO11, precios_hace_un_año[11]);
                bundle.putFloat(HACE_UN_AÑO_PRECIO12, precios_hace_un_año[12]);
                bundle.putFloat(HACE_UN_AÑO_PRECIO13, precios_hace_un_año[13]);
                bundle.putFloat(HACE_UN_AÑO_PRECIO14, precios_hace_un_año[14]);
                bundle.putFloat(HACE_UN_AÑO_PRECIO15, precios_hace_un_año[15]);
                bundle.putFloat(HACE_UN_AÑO_PRECIO16, precios_hace_un_año[16]);
                bundle.putFloat(HACE_UN_AÑO_PRECIO17, precios_hace_un_año[17]);
                bundle.putFloat(HACE_UN_AÑO_PRECIO18, precios_hace_un_año[18]);
                bundle.putFloat(HACE_UN_AÑO_PRECIO19, precios_hace_un_año[19]);
                bundle.putFloat(HACE_UN_AÑO_PRECIO20, precios_hace_un_año[20]);
                bundle.putFloat(HACE_UN_AÑO_PRECIO21, precios_hace_un_año[21]);
                bundle.putFloat(HACE_UN_AÑO_PRECIO22, precios_hace_un_año[22]);
                bundle.putFloat(HACE_UN_AÑO_PRECIO23, precios_hace_un_año[23]);

                bundle.putBoolean(PRECIOS_SEMANA_PASADA, boolean_precios_semana_pasada);
                bundle.putBoolean(PRECIOS_AÑO_PASADO, boolean_precios_año_pasado);
            } else {
                bundle = null;
            }
        }catch (Exception ignored){

        }finally {
            fragment.setArguments(bundle);
            fragment.setRetainInstance(true);
        }
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        try{
            super.onCreate(savedInstanceState);

            // Load parameters when the initial creation of the fragment is done
            this.titulo = (getArguments() != null) ? getArguments().getString(TITULO) : "ERROR";
            this.precios[0] = (getArguments() != null) ? getArguments().getFloat(PRECIO0) : -1;
            this.precios[1] = (getArguments() != null) ? getArguments().getFloat(PRECIO1) : -1;
            this.precios[2] = (getArguments() != null) ? getArguments().getFloat(PRECIO2) : -1;
            this.precios[3] = (getArguments() != null) ? getArguments().getFloat(PRECIO3) : -1;
            this.precios[4] = (getArguments() != null) ? getArguments().getFloat(PRECIO4) : -1;
            this.precios[5] = (getArguments() != null) ? getArguments().getFloat(PRECIO5) : -1;
            this.precios[6] = (getArguments() != null) ? getArguments().getFloat(PRECIO6) : -1;
            this.precios[7] = (getArguments() != null) ? getArguments().getFloat(PRECIO7) : -1;
            this.precios[8] = (getArguments() != null) ? getArguments().getFloat(PRECIO8) : -1;
            this.precios[9] = (getArguments() != null) ? getArguments().getFloat(PRECIO9) : -1;
            this.precios[10] = (getArguments() != null) ? getArguments().getFloat(PRECIO10) : -1;
            this.precios[11] = (getArguments() != null) ? getArguments().getFloat(PRECIO11) : -1;
            this.precios[12] = (getArguments() != null) ? getArguments().getFloat(PRECIO12) : -1;
            this.precios[13] = (getArguments() != null) ? getArguments().getFloat(PRECIO13) : -1;
            this.precios[14] = (getArguments() != null) ? getArguments().getFloat(PRECIO14) : -1;
            this.precios[15] = (getArguments() != null) ? getArguments().getFloat(PRECIO15) : -1;
            this.precios[16] = (getArguments() != null) ? getArguments().getFloat(PRECIO16) : -1;
            this.precios[17] = (getArguments() != null) ? getArguments().getFloat(PRECIO17) : -1;
            this.precios[18] = (getArguments() != null) ? getArguments().getFloat(PRECIO18) : -1;
            this.precios[19] = (getArguments() != null) ? getArguments().getFloat(PRECIO19) : -1;
            this.precios[20] = (getArguments() != null) ? getArguments().getFloat(PRECIO20) : -1;
            this.precios[21] = (getArguments() != null) ? getArguments().getFloat(PRECIO21) : -1;
            this.precios[22] = (getArguments() != null) ? getArguments().getFloat(PRECIO22) : -1;
            this.precios[23] = (getArguments() != null) ? getArguments().getFloat(PRECIO23) : -1;

            this.precios_hace_un_año[0] = (getArguments() != null) ? getArguments().getFloat(HACE_UN_AÑO_PRECIO0) : -1;
            this.precios_hace_un_año[1] = (getArguments() != null) ? getArguments().getFloat(HACE_UN_AÑO_PRECIO1) : -1;
            this.precios_hace_un_año[2] = (getArguments() != null) ? getArguments().getFloat(HACE_UN_AÑO_PRECIO2) : -1;
            this.precios_hace_un_año[3] = (getArguments() != null) ? getArguments().getFloat(HACE_UN_AÑO_PRECIO3) : -1;
            this.precios_hace_un_año[4] = (getArguments() != null) ? getArguments().getFloat(HACE_UN_AÑO_PRECIO4) : -1;
            this.precios_hace_un_año[5] = (getArguments() != null) ? getArguments().getFloat(HACE_UN_AÑO_PRECIO5) : -1;
            this.precios_hace_un_año[6] = (getArguments() != null) ? getArguments().getFloat(HACE_UN_AÑO_PRECIO6) : -1;
            this.precios_hace_un_año[7] = (getArguments() != null) ? getArguments().getFloat(HACE_UN_AÑO_PRECIO7) : -1;
            this.precios_hace_un_año[8] = (getArguments() != null) ? getArguments().getFloat(HACE_UN_AÑO_PRECIO8) : -1;
            this.precios_hace_un_año[9] = (getArguments() != null) ? getArguments().getFloat(HACE_UN_AÑO_PRECIO9) : -1;
            this.precios_hace_un_año[10] = (getArguments() != null) ? getArguments().getFloat(HACE_UN_AÑO_PRECIO10) : -1;
            this.precios_hace_un_año[11] = (getArguments() != null) ? getArguments().getFloat(HACE_UN_AÑO_PRECIO11) : -1;
            this.precios_hace_un_año[12] = (getArguments() != null) ? getArguments().getFloat(HACE_UN_AÑO_PRECIO12) : -1;
            this.precios_hace_un_año[13] = (getArguments() != null) ? getArguments().getFloat(HACE_UN_AÑO_PRECIO13) : -1;
            this.precios_hace_un_año[14] = (getArguments() != null) ? getArguments().getFloat(HACE_UN_AÑO_PRECIO14) : -1;
            this.precios_hace_un_año[15] = (getArguments() != null) ? getArguments().getFloat(HACE_UN_AÑO_PRECIO15) : -1;
            this.precios_hace_un_año[16] = (getArguments() != null) ? getArguments().getFloat(HACE_UN_AÑO_PRECIO16) : -1;
            this.precios_hace_un_año[17] = (getArguments() != null) ? getArguments().getFloat(HACE_UN_AÑO_PRECIO17) : -1;
            this.precios_hace_un_año[18] = (getArguments() != null) ? getArguments().getFloat(HACE_UN_AÑO_PRECIO18) : -1;
            this.precios_hace_un_año[19] = (getArguments() != null) ? getArguments().getFloat(HACE_UN_AÑO_PRECIO19) : -1;
            this.precios_hace_un_año[20] = (getArguments() != null) ? getArguments().getFloat(HACE_UN_AÑO_PRECIO20) : -1;
            this.precios_hace_un_año[21] = (getArguments() != null) ? getArguments().getFloat(HACE_UN_AÑO_PRECIO21) : -1;
            this.precios_hace_un_año[22] = (getArguments() != null) ? getArguments().getFloat(HACE_UN_AÑO_PRECIO22) : -1;
            this.precios_hace_un_año[23] = (getArguments() != null) ? getArguments().getFloat(HACE_UN_AÑO_PRECIO23) : -1;

            this.precios_hace_una_semana[0] = (getArguments() != null) ? getArguments().getFloat(HACE_UNA_SEMANA_PRECIO0) : -1;
            this.precios_hace_una_semana[1] = (getArguments() != null) ? getArguments().getFloat(HACE_UNA_SEMANA_PRECIO1) : -1;
            this.precios_hace_una_semana[2] = (getArguments() != null) ? getArguments().getFloat(HACE_UNA_SEMANA_PRECIO2) : -1;
            this.precios_hace_una_semana[3] = (getArguments() != null) ? getArguments().getFloat(HACE_UNA_SEMANA_PRECIO3) : -1;
            this.precios_hace_una_semana[4] = (getArguments() != null) ? getArguments().getFloat(HACE_UNA_SEMANA_PRECIO4) : -1;
            this.precios_hace_una_semana[5] = (getArguments() != null) ? getArguments().getFloat(HACE_UNA_SEMANA_PRECIO5) : -1;
            this.precios_hace_una_semana[6] = (getArguments() != null) ? getArguments().getFloat(HACE_UNA_SEMANA_PRECIO6) : -1;
            this.precios_hace_una_semana[7] = (getArguments() != null) ? getArguments().getFloat(HACE_UNA_SEMANA_PRECIO7) : -1;
            this.precios_hace_una_semana[8] = (getArguments() != null) ? getArguments().getFloat(HACE_UNA_SEMANA_PRECIO8) : -1;
            this.precios_hace_una_semana[9] = (getArguments() != null) ? getArguments().getFloat(HACE_UNA_SEMANA_PRECIO9) : -1;
            this.precios_hace_una_semana[10] = (getArguments() != null) ? getArguments().getFloat(HACE_UNA_SEMANA_PRECIO10) : -1;
            this.precios_hace_una_semana[11] = (getArguments() != null) ? getArguments().getFloat(HACE_UNA_SEMANA_PRECIO11) : -1;
            this.precios_hace_una_semana[12] = (getArguments() != null) ? getArguments().getFloat(HACE_UNA_SEMANA_PRECIO12) : -1;
            this.precios_hace_una_semana[13] = (getArguments() != null) ? getArguments().getFloat(HACE_UNA_SEMANA_PRECIO13) : -1;
            this.precios_hace_una_semana[14] = (getArguments() != null) ? getArguments().getFloat(HACE_UNA_SEMANA_PRECIO14) : -1;
            this.precios_hace_una_semana[15] = (getArguments() != null) ? getArguments().getFloat(HACE_UNA_SEMANA_PRECIO15) : -1;
            this.precios_hace_una_semana[16] = (getArguments() != null) ? getArguments().getFloat(HACE_UNA_SEMANA_PRECIO16) : -1;
            this.precios_hace_una_semana[17] = (getArguments() != null) ? getArguments().getFloat(HACE_UNA_SEMANA_PRECIO17) : -1;
            this.precios_hace_una_semana[18] = (getArguments() != null) ? getArguments().getFloat(HACE_UNA_SEMANA_PRECIO18) : -1;
            this.precios_hace_una_semana[19] = (getArguments() != null) ? getArguments().getFloat(HACE_UNA_SEMANA_PRECIO19) : -1;
            this.precios_hace_una_semana[20] = (getArguments() != null) ? getArguments().getFloat(HACE_UNA_SEMANA_PRECIO20) : -1;
            this.precios_hace_una_semana[21] = (getArguments() != null) ? getArguments().getFloat(HACE_UNA_SEMANA_PRECIO21) : -1;
            this.precios_hace_una_semana[22] = (getArguments() != null) ? getArguments().getFloat(HACE_UNA_SEMANA_PRECIO22) : -1;
            this.precios_hace_una_semana[23] = (getArguments() != null) ? getArguments().getFloat(HACE_UNA_SEMANA_PRECIO23) : -1;
            this.precios_semana_pasada_activado= (getArguments() != null) && getArguments().getBoolean(PRECIOS_SEMANA_PASADA);
            this.precios_año_pasado_activado= (getArguments() != null) && getArguments().getBoolean(PRECIOS_AÑO_PASADO);
        }catch (Exception e){
            new utils.AsyncTask_Guardar_Error().execute(new Pair(this,e.toString()));
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

       // set_parametros_segun_screensize();

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.layout_graficas_land, container, false);
        try {
            TextView TextView_titulo = rootView.findViewById(R.id.precios);
            TextView_titulo.setText(this.titulo);

            pinta_titulo(rootView/*, TAMAÑO_TEXTO_TITULO*/);
            pinta_flechitas(rootView);

            pinta_barras(rootView);
            if (precios[0].compareTo(-1.0f) != 0) {
                pinta_rayitas_semana_pasada(rootView);
                pinta_rayitas_año_pasado(rootView);
                pinta_texto_horas(rootView/*, TAMAÑO_TEXTO_HORAS*/);
                pinta_texto_precios(rootView/*,TAMAÑO_TEXTO_PRECIO*/);
            } else {
                TextView TextView_todavia_no_hay_precios = rootView.findViewById(R.id.TextView_todavia_no_hay_precios);
                TextView_todavia_no_hay_precios.setVisibility(View.VISIBLE);
            }
        }catch (Exception e){
            new utils.AsyncTask_Guardar_Error().execute(new Pair(this,e.toString()));
        }
        return rootView;
    }

    public void set_todos_los_precios( Float[] precios,Float[] precios_hace_un_año,Float[] precios_hace_una_semana){
        try {
            for (int i = 0; i < 24; i++) {
                this.precios[i] = precios[i];
                this.precios_hace_un_año[i] = precios_hace_un_año[i];
                this.precios_hace_una_semana[i] = precios_hace_una_semana[i];
            }
        }catch (Exception e){
            new utils.AsyncTask_Guardar_Error().execute(new Pair(this,e.toString()));
        }
    }

    private void pinta_titulo(ViewGroup rootView/*, int tamaño_letra*/){
        TextView TextView_titulo = rootView.findViewById(R.id.precios);
        TextView_titulo.setText(this.titulo);
    }
    private void pinta_barras(ViewGroup rootView){
        try {
            ImageView barra0 = rootView.findViewById(R.id.barra0);
            ImageView barra1 = rootView.findViewById(R.id.barra1);
            ImageView barra2 = rootView.findViewById(R.id.barra2);
            ImageView barra3 = rootView.findViewById(R.id.barra3);
            ImageView barra4 = rootView.findViewById(R.id.barra4);
            ImageView barra5 = rootView.findViewById(R.id.barra5);
            ImageView barra6 = rootView.findViewById(R.id.barra6);
            ImageView barra7 = rootView.findViewById(R.id.barra7);
            ImageView barra8 = rootView.findViewById(R.id.barra8);
            ImageView barra9 = rootView.findViewById(R.id.barra9);
            ImageView barra10 = rootView.findViewById(R.id.barra10);
            ImageView barra11 = rootView.findViewById(R.id.barra11);
            ImageView barra12 = rootView.findViewById(R.id.barra12);
            ImageView barra13 = rootView.findViewById(R.id.barra13);
            ImageView barra14 = rootView.findViewById(R.id.barra14);
            ImageView barra15 = rootView.findViewById(R.id.barra15);
            ImageView barra16 = rootView.findViewById(R.id.barra16);
            ImageView barra17 = rootView.findViewById(R.id.barra17);
            ImageView barra18 = rootView.findViewById(R.id.barra18);
            ImageView barra19 = rootView.findViewById(R.id.barra19);
            ImageView barra20 = rootView.findViewById(R.id.barra20);
            ImageView barra21 = rootView.findViewById(R.id.barra21);
            ImageView barra22 = rootView.findViewById(R.id.barra22);
            ImageView barra23 = rootView.findViewById(R.id.barra23);

            RelativeLayout.LayoutParams mLayoutParams_barra0 = new RelativeLayout.LayoutParams(ANCHURA, (int) (precios[0] * ESCALA));
            RelativeLayout.LayoutParams mLayoutParams_barra1 = new RelativeLayout.LayoutParams(ANCHURA, (int) (precios[1] * ESCALA));
            RelativeLayout.LayoutParams mLayoutParams_barra2 = new RelativeLayout.LayoutParams(ANCHURA, (int) (precios[2] * ESCALA));
            RelativeLayout.LayoutParams mLayoutParams_barra3 = new RelativeLayout.LayoutParams(ANCHURA, (int) (precios[3] * ESCALA));
            RelativeLayout.LayoutParams mLayoutParams_barra4 = new RelativeLayout.LayoutParams(ANCHURA, (int) (precios[4] * ESCALA));
            RelativeLayout.LayoutParams mLayoutParams_barra5 = new RelativeLayout.LayoutParams(ANCHURA, (int) (precios[5] * ESCALA));
            RelativeLayout.LayoutParams mLayoutParams_barra6 = new RelativeLayout.LayoutParams(ANCHURA, (int) (precios[6] * ESCALA));
            RelativeLayout.LayoutParams mLayoutParams_barra7 = new RelativeLayout.LayoutParams(ANCHURA, (int) (precios[7] * ESCALA));
            RelativeLayout.LayoutParams mLayoutParams_barra8 = new RelativeLayout.LayoutParams(ANCHURA, (int) (precios[8] * ESCALA));
            RelativeLayout.LayoutParams mLayoutParams_barra9 = new RelativeLayout.LayoutParams(ANCHURA, (int) (precios[9] * ESCALA));
            RelativeLayout.LayoutParams mLayoutParams_barra10 = new RelativeLayout.LayoutParams(ANCHURA, (int) (precios[10] * ESCALA));
            RelativeLayout.LayoutParams mLayoutParams_barra11 = new RelativeLayout.LayoutParams(ANCHURA, (int) (precios[11] * ESCALA));
            RelativeLayout.LayoutParams mLayoutParams_barra12 = new RelativeLayout.LayoutParams(ANCHURA, (int) (precios[12] * ESCALA));
            RelativeLayout.LayoutParams mLayoutParams_barra13 = new RelativeLayout.LayoutParams(ANCHURA, (int) (precios[13] * ESCALA));
            RelativeLayout.LayoutParams mLayoutParams_barra14 = new RelativeLayout.LayoutParams(ANCHURA, (int) (precios[14] * ESCALA));
            RelativeLayout.LayoutParams mLayoutParams_barra15 = new RelativeLayout.LayoutParams(ANCHURA, (int) (precios[15] * ESCALA));
            RelativeLayout.LayoutParams mLayoutParams_barra16 = new RelativeLayout.LayoutParams(ANCHURA, (int) (precios[16] * ESCALA));
            RelativeLayout.LayoutParams mLayoutParams_barra17 = new RelativeLayout.LayoutParams(ANCHURA, (int) (precios[17] * ESCALA));
            RelativeLayout.LayoutParams mLayoutParams_barra18 = new RelativeLayout.LayoutParams(ANCHURA, (int) (precios[18] * ESCALA));
            RelativeLayout.LayoutParams mLayoutParams_barra19 = new RelativeLayout.LayoutParams(ANCHURA, (int) (precios[19] * ESCALA));
            RelativeLayout.LayoutParams mLayoutParams_barra20 = new RelativeLayout.LayoutParams(ANCHURA, (int) (precios[20] * ESCALA));
            RelativeLayout.LayoutParams mLayoutParams_barra21 = new RelativeLayout.LayoutParams(ANCHURA, (int) (precios[21] * ESCALA));
            RelativeLayout.LayoutParams mLayoutParams_barra22 = new RelativeLayout.LayoutParams(ANCHURA, (int) (precios[22] * ESCALA));
            RelativeLayout.LayoutParams mLayoutParams_barra23 = new RelativeLayout.LayoutParams(ANCHURA, (int) (precios[23] * ESCALA));

            mLayoutParams_barra0.addRule(RelativeLayout.CENTER_HORIZONTAL);
            mLayoutParams_barra1.addRule(RelativeLayout.CENTER_HORIZONTAL);
            mLayoutParams_barra2.addRule(RelativeLayout.CENTER_HORIZONTAL);
            mLayoutParams_barra3.addRule(RelativeLayout.CENTER_HORIZONTAL);
            mLayoutParams_barra4.addRule(RelativeLayout.CENTER_HORIZONTAL);
            mLayoutParams_barra5.addRule(RelativeLayout.CENTER_HORIZONTAL);
            mLayoutParams_barra6.addRule(RelativeLayout.CENTER_HORIZONTAL);
            mLayoutParams_barra7.addRule(RelativeLayout.CENTER_HORIZONTAL);
            mLayoutParams_barra8.addRule(RelativeLayout.CENTER_HORIZONTAL);
            mLayoutParams_barra9.addRule(RelativeLayout.CENTER_HORIZONTAL);
            mLayoutParams_barra10.addRule(RelativeLayout.CENTER_HORIZONTAL);
            mLayoutParams_barra11.addRule(RelativeLayout.CENTER_HORIZONTAL);
            mLayoutParams_barra12.addRule(RelativeLayout.CENTER_HORIZONTAL);
            mLayoutParams_barra13.addRule(RelativeLayout.CENTER_HORIZONTAL);
            mLayoutParams_barra14.addRule(RelativeLayout.CENTER_HORIZONTAL);
            mLayoutParams_barra15.addRule(RelativeLayout.CENTER_HORIZONTAL);
            mLayoutParams_barra16.addRule(RelativeLayout.CENTER_HORIZONTAL);
            mLayoutParams_barra17.addRule(RelativeLayout.CENTER_HORIZONTAL);
            mLayoutParams_barra18.addRule(RelativeLayout.CENTER_HORIZONTAL);
            mLayoutParams_barra19.addRule(RelativeLayout.CENTER_HORIZONTAL);
            mLayoutParams_barra20.addRule(RelativeLayout.CENTER_HORIZONTAL);
            mLayoutParams_barra21.addRule(RelativeLayout.CENTER_HORIZONTAL);
            mLayoutParams_barra22.addRule(RelativeLayout.CENTER_HORIZONTAL);
            mLayoutParams_barra23.addRule(RelativeLayout.CENTER_HORIZONTAL);

           barra0.setLayoutParams(mLayoutParams_barra0);
            barra1.setLayoutParams(mLayoutParams_barra1);
            barra2.setLayoutParams(mLayoutParams_barra2);
            barra3.setLayoutParams(mLayoutParams_barra3);
            barra4.setLayoutParams(mLayoutParams_barra4);
            barra5.setLayoutParams(mLayoutParams_barra5);
            barra6.setLayoutParams(mLayoutParams_barra6);
            barra7.setLayoutParams(mLayoutParams_barra7);
            barra8.setLayoutParams(mLayoutParams_barra8);
            barra9.setLayoutParams(mLayoutParams_barra9);
            barra10.setLayoutParams(mLayoutParams_barra10);
            barra11.setLayoutParams(mLayoutParams_barra11);
            barra12.setLayoutParams(mLayoutParams_barra12);
            barra13.setLayoutParams(mLayoutParams_barra13);
            barra14.setLayoutParams(mLayoutParams_barra14);
            barra15.setLayoutParams(mLayoutParams_barra15);
            barra16.setLayoutParams(mLayoutParams_barra16);
            barra17.setLayoutParams(mLayoutParams_barra17);
            barra18.setLayoutParams(mLayoutParams_barra18);
            barra19.setLayoutParams(mLayoutParams_barra19);
            barra20.setLayoutParams(mLayoutParams_barra20);
            barra21.setLayoutParams(mLayoutParams_barra21);
            barra22.setLayoutParams(mLayoutParams_barra22);
            barra23.setLayoutParams(mLayoutParams_barra23);

            Float[] precios_ordenados = precios.clone();

            Arrays.sort(precios_ordenados, Collections.reverseOrder());
           barra0.setColorFilter(getColor(precios_ordenados, precios[0]));
            barra1.setColorFilter(getColor(precios_ordenados, precios[1]));
            barra2.setColorFilter(getColor(precios_ordenados, precios[2]));
            barra3.setColorFilter(getColor(precios_ordenados, precios[3]));
            barra4.setColorFilter(getColor(precios_ordenados, precios[4]));
            barra5.setColorFilter(getColor(precios_ordenados, precios[5]));
            barra6.setColorFilter(getColor(precios_ordenados, precios[6]));
            barra7.setColorFilter(getColor(precios_ordenados, precios[7]));
            barra8.setColorFilter(getColor(precios_ordenados, precios[8]));
            barra9.setColorFilter(getColor(precios_ordenados, precios[9]));
            barra10.setColorFilter(getColor(precios_ordenados, precios[10]));
            barra11.setColorFilter(getColor(precios_ordenados, precios[11]));
            barra12.setColorFilter(getColor(precios_ordenados, precios[12]));
            barra13.setColorFilter(getColor(precios_ordenados, precios[13]));
            barra14.setColorFilter(getColor(precios_ordenados, precios[14]));
            barra15.setColorFilter(getColor(precios_ordenados, precios[15]));
            barra16.setColorFilter(getColor(precios_ordenados, precios[16]));
            barra17.setColorFilter(getColor(precios_ordenados, precios[17]));
            barra18.setColorFilter(getColor(precios_ordenados, precios[18]));
            barra19.setColorFilter(getColor(precios_ordenados, precios[19]));
            barra20.setColorFilter(getColor(precios_ordenados, precios[20]));
            barra21.setColorFilter(getColor(precios_ordenados, precios[21]));
            barra22.setColorFilter(getColor(precios_ordenados, precios[22]));
            barra23.setColorFilter(getColor(precios_ordenados, precios[23]));
        }catch(Exception e){
            new utils.AsyncTask_Guardar_Error().execute(new Pair(this,e.toString()));
        }
    }
    private void pinta_texto_horas(ViewGroup rootView/*, int tamaño_letra*/){
        try {

            TextView mTextView0 = rootView.findViewById(R.id.texto0);
            TextView mTextView1 = rootView.findViewById(R.id.texto1);
            TextView mTextView2 = rootView.findViewById(R.id.texto2);
            TextView mTextView3 = rootView.findViewById(R.id.texto3);
            TextView mTextView4 = rootView.findViewById(R.id.texto4);
            TextView mTextView5 = rootView.findViewById(R.id.texto5);
            TextView mTextView6 = rootView.findViewById(R.id.texto6);
            TextView mTextView7 = rootView.findViewById(R.id.texto7);
            TextView mTextView8 = rootView.findViewById(R.id.texto8);
            TextView mTextView9 = rootView.findViewById(R.id.texto9);
            TextView mTextView10 = rootView.findViewById(R.id.texto10);
            TextView mTextView11 = rootView.findViewById(R.id.texto11);
            TextView mTextView12 = rootView.findViewById(R.id.texto12);
            TextView mTextView13 = rootView.findViewById(R.id.texto13);
            TextView mTextView14 = rootView.findViewById(R.id.texto14);
            TextView mTextView15 = rootView.findViewById(R.id.texto15);
            TextView mTextView16 = rootView.findViewById(R.id.texto16);
            TextView mTextView17 = rootView.findViewById(R.id.texto17);
            TextView mTextView18 = rootView.findViewById(R.id.texto18);
            TextView mTextView19 = rootView.findViewById(R.id.texto19);
            TextView mTextView20 = rootView.findViewById(R.id.texto20);
            TextView mTextView21 = rootView.findViewById(R.id.texto21);
            TextView mTextView22 = rootView.findViewById(R.id.texto22);
            TextView mTextView23 = rootView.findViewById(R.id.texto23);


            if (this.titulo.contains("hoy")) {
                DateFormat df = new SimpleDateFormat("HH");
                Integer hora = Integer.valueOf(df.format(Calendar.getInstance().getTime()));
                switch (hora) {
                    case 0:
                        mTextView0.setTextColor(getResources().getColor(R.color.Black));
                        mTextView0.setTypeface(null, Typeface.BOLD);
                        break;
                    case 1:
                        mTextView1.setTextColor(getResources().getColor(R.color.Black));
                        mTextView1.setTypeface(null, Typeface.BOLD);
                        break;
                    case 2:
                        mTextView2.setTextColor(getResources().getColor(R.color.Black));
                        mTextView2.setTypeface(null, Typeface.BOLD);
                        break;
                    case 3:
                        mTextView3.setTextColor(getResources().getColor(R.color.Black));
                        mTextView3.setTypeface(null, Typeface.BOLD);
                        break;
                    case 4:
                        mTextView4.setTextColor(getResources().getColor(R.color.Black));
                        mTextView4.setTypeface(null, Typeface.BOLD);
                        break;
                    case 5:
                        mTextView5.setTextColor(getResources().getColor(R.color.Black));
                        mTextView5.setTypeface(null, Typeface.BOLD);
                        break;
                    case 6:
                        mTextView6.setTextColor(getResources().getColor(R.color.Black));
                        mTextView6.setTypeface(null, Typeface.BOLD);
                        break;
                    case 7:
                        mTextView7.setTextColor(getResources().getColor(R.color.Black));
                        mTextView7.setTypeface(null, Typeface.BOLD);
                        break;
                    case 8:
                        mTextView8.setTextColor(getResources().getColor(R.color.Black));
                        mTextView8.setTypeface(null, Typeface.BOLD);
                        break;
                    case 9:
                        mTextView9.setTextColor(getResources().getColor(R.color.Black));
                        mTextView9.setTypeface(null, Typeface.BOLD);
                        break;
                    case 10:
                        mTextView10.setTextColor(getResources().getColor(R.color.Black));
                        mTextView10.setTypeface(null, Typeface.BOLD);
                        break;
                    case 11:
                        mTextView11.setTextColor(getResources().getColor(R.color.Black));
                        mTextView11.setTypeface(null, Typeface.BOLD);
                        break;
                    case 12:
                        mTextView12.setTextColor(getResources().getColor(R.color.Black));
                        mTextView12.setTypeface(null, Typeface.BOLD);
                        break;
                    case 13:
                        mTextView13.setTextColor(getResources().getColor(R.color.Black));
                        mTextView13.setTypeface(null, Typeface.BOLD);
                        break;
                    case 14:
                        mTextView14.setTextColor(getResources().getColor(R.color.Black));
                        mTextView14.setTypeface(null, Typeface.BOLD);
                        break;
                    case 15:
                        mTextView15.setTextColor(getResources().getColor(R.color.Black));
                        mTextView15.setTypeface(null, Typeface.BOLD);
                        break;
                    case 16:
                        mTextView16.setTextColor(getResources().getColor(R.color.Black));
                        mTextView16.setTypeface(null, Typeface.BOLD);
                        break;
                    case 17:
                        mTextView17.setTextColor(getResources().getColor(R.color.Black));
                        mTextView17.setTypeface(null, Typeface.BOLD);
                        break;
                    case 18:
                        mTextView18.setTextColor(getResources().getColor(R.color.Black));
                        mTextView18.setTypeface(null, Typeface.BOLD);
                        break;
                    case 19:
                        mTextView19.setTextColor(getResources().getColor(R.color.Black));
                        mTextView19.setTypeface(null, Typeface.BOLD);
                        break;
                    case 20:
                        mTextView20.setTextColor(getResources().getColor(R.color.Black));
                        mTextView20.setTypeface(null, Typeface.BOLD);
                        break;
                    case 21:
                        mTextView21.setTextColor(getResources().getColor(R.color.Black));
                        mTextView21.setTypeface(null, Typeface.BOLD);
                        break;
                    case 22:
                        mTextView22.setTextColor(getResources().getColor(R.color.Black));
                        mTextView22.setTypeface(null, Typeface.BOLD);
                        break;
                    case 23:
                        mTextView23.setTextColor(getResources().getColor(R.color.Black));
                        mTextView23.setTypeface(null, Typeface.BOLD);
                        break;
                }
            }
        }catch (Exception e){
            new utils.AsyncTask_Guardar_Error().execute(new Pair(this,e.toString()));
        }

    }
    private void pinta_rayitas_semana_pasada(ViewGroup rootView){
        try {
            ImageView rayita_semana_pasada_00 = rootView.findViewById(R.id.rayita_semana_pasada_00);
            ImageView rayita_semana_pasada_01 = rootView.findViewById(R.id.rayita_semana_pasada_01);
            ImageView rayita_semana_pasada_02 = rootView.findViewById(R.id.rayita_semana_pasada_02);
            ImageView rayita_semana_pasada_03 = rootView.findViewById(R.id.rayita_semana_pasada_03);
            ImageView rayita_semana_pasada_04 = rootView.findViewById(R.id.rayita_semana_pasada_04);
            ImageView rayita_semana_pasada_05 = rootView.findViewById(R.id.rayita_semana_pasada_05);
            ImageView rayita_semana_pasada_06 = rootView.findViewById(R.id.rayita_semana_pasada_06);
            ImageView rayita_semana_pasada_07 = rootView.findViewById(R.id.rayita_semana_pasada_07);
            ImageView rayita_semana_pasada_08 = rootView.findViewById(R.id.rayita_semana_pasada_08);
            ImageView rayita_semana_pasada_09 = rootView.findViewById(R.id.rayita_semana_pasada_09);
            ImageView rayita_semana_pasada_10 = rootView.findViewById(R.id.rayita_semana_pasada_10);
            ImageView rayita_semana_pasada_11 = rootView.findViewById(R.id.rayita_semana_pasada_11);
            ImageView rayita_semana_pasada_12 = rootView.findViewById(R.id.rayita_semana_pasada_12);
            ImageView rayita_semana_pasada_13 = rootView.findViewById(R.id.rayita_semana_pasada_13);
            ImageView rayita_semana_pasada_14 = rootView.findViewById(R.id.rayita_semana_pasada_14);
            ImageView rayita_semana_pasada_15 = rootView.findViewById(R.id.rayita_semana_pasada_15);
            ImageView rayita_semana_pasada_16 = rootView.findViewById(R.id.rayita_semana_pasada_16);
            ImageView rayita_semana_pasada_17 = rootView.findViewById(R.id.rayita_semana_pasada_17);
            ImageView rayita_semana_pasada_18 = rootView.findViewById(R.id.rayita_semana_pasada_18);
            ImageView rayita_semana_pasada_19 = rootView.findViewById(R.id.rayita_semana_pasada_19);
            ImageView rayita_semana_pasada_20 = rootView.findViewById(R.id.rayita_semana_pasada_20);
            ImageView rayita_semana_pasada_21 = rootView.findViewById(R.id.rayita_semana_pasada_21);
            ImageView rayita_semana_pasada_22 = rootView.findViewById(R.id.rayita_semana_pasada_22);
            ImageView rayita_semana_pasada_23 = rootView.findViewById(R.id.rayita_semana_pasada_23);

            if (precios_semana_pasada_activado) {
                rayita_semana_pasada_00.setVisibility(ImageView.VISIBLE);
                rayita_semana_pasada_01.setVisibility(ImageView.VISIBLE);
                rayita_semana_pasada_02.setVisibility(ImageView.VISIBLE);
                rayita_semana_pasada_03.setVisibility(ImageView.VISIBLE);
                rayita_semana_pasada_04.setVisibility(ImageView.VISIBLE);
                rayita_semana_pasada_05.setVisibility(ImageView.VISIBLE);
                rayita_semana_pasada_06.setVisibility(ImageView.VISIBLE);
                rayita_semana_pasada_07.setVisibility(ImageView.VISIBLE);
                rayita_semana_pasada_08.setVisibility(ImageView.VISIBLE);
                rayita_semana_pasada_09.setVisibility(ImageView.VISIBLE);
                rayita_semana_pasada_10.setVisibility(ImageView.VISIBLE);
                rayita_semana_pasada_11.setVisibility(ImageView.VISIBLE);
                rayita_semana_pasada_12.setVisibility(ImageView.VISIBLE);
                rayita_semana_pasada_13.setVisibility(ImageView.VISIBLE);
                rayita_semana_pasada_14.setVisibility(ImageView.VISIBLE);
                rayita_semana_pasada_15.setVisibility(ImageView.VISIBLE);
                rayita_semana_pasada_16.setVisibility(ImageView.VISIBLE);
                rayita_semana_pasada_17.setVisibility(ImageView.VISIBLE);
                rayita_semana_pasada_18.setVisibility(ImageView.VISIBLE);
                rayita_semana_pasada_19.setVisibility(ImageView.VISIBLE);
                rayita_semana_pasada_20.setVisibility(ImageView.VISIBLE);
                rayita_semana_pasada_21.setVisibility(ImageView.VISIBLE);
                rayita_semana_pasada_22.setVisibility(ImageView.VISIBLE);
                rayita_semana_pasada_23.setVisibility(ImageView.VISIBLE);

                rayita_semana_pasada_00.setY((int) ((-1) * precios_hace_una_semana[0] * ESCALA));
                rayita_semana_pasada_01.setY((int) ((-1) * precios_hace_una_semana[1] * ESCALA));
                rayita_semana_pasada_02.setY((int) ((-1) * precios_hace_una_semana[2] * ESCALA));
                rayita_semana_pasada_03.setY((int) ((-1) * precios_hace_una_semana[3] * ESCALA));
                rayita_semana_pasada_04.setY((int) ((-1) * precios_hace_una_semana[4] * ESCALA));
                rayita_semana_pasada_05.setY((int) ((-1) * precios_hace_una_semana[5] * ESCALA));
                rayita_semana_pasada_06.setY((int) ((-1) * precios_hace_una_semana[6] * ESCALA));
                rayita_semana_pasada_07.setY((int) ((-1) * precios_hace_una_semana[7] * ESCALA));
                rayita_semana_pasada_08.setY((int) ((-1) * precios_hace_una_semana[8] * ESCALA));
                rayita_semana_pasada_09.setY((int) ((-1) * precios_hace_una_semana[9] * ESCALA));
                rayita_semana_pasada_10.setY((int) ((-1) * precios_hace_una_semana[10] * ESCALA));
                rayita_semana_pasada_11.setY((int) ((-1) * precios_hace_una_semana[11] * ESCALA));
                rayita_semana_pasada_12.setY((int) ((-1) * precios_hace_una_semana[12] * ESCALA));
                rayita_semana_pasada_13.setY((int) ((-1) * precios_hace_una_semana[13] * ESCALA));
                rayita_semana_pasada_14.setY((int) ((-1) * precios_hace_una_semana[14] * ESCALA));
                rayita_semana_pasada_15.setY((int) ((-1) * precios_hace_una_semana[15] * ESCALA));
                rayita_semana_pasada_16.setY((int) ((-1) * precios_hace_una_semana[16] * ESCALA));
                rayita_semana_pasada_17.setY((int) ((-1) * precios_hace_una_semana[17] * ESCALA));
                rayita_semana_pasada_18.setY((int) ((-1) * precios_hace_una_semana[18] * ESCALA));
                rayita_semana_pasada_19.setY((int) ((-1) * precios_hace_una_semana[19] * ESCALA));
                rayita_semana_pasada_20.setY((int) ((-1) * precios_hace_una_semana[20] * ESCALA));
                rayita_semana_pasada_21.setY((int) ((-1) * precios_hace_una_semana[21] * ESCALA));
                rayita_semana_pasada_22.setY((int) ((-1) * precios_hace_una_semana[22] * ESCALA));
                rayita_semana_pasada_23.setY((int) ((-1) * precios_hace_una_semana[23] * ESCALA));

                RelativeLayout.LayoutParams mLayoutParams_rayita0 = new RelativeLayout.LayoutParams(ANCHURA, 2);
                RelativeLayout.LayoutParams mLayoutParams_rayita1 = new RelativeLayout.LayoutParams(ANCHURA, 2);
                RelativeLayout.LayoutParams mLayoutParams_rayita2 = new RelativeLayout.LayoutParams(ANCHURA, 2);
                RelativeLayout.LayoutParams mLayoutParams_rayita3 = new RelativeLayout.LayoutParams(ANCHURA, 2);
                RelativeLayout.LayoutParams mLayoutParams_rayita4 = new RelativeLayout.LayoutParams(ANCHURA, 2);
                RelativeLayout.LayoutParams mLayoutParams_rayita5 = new RelativeLayout.LayoutParams(ANCHURA, 2);
                RelativeLayout.LayoutParams mLayoutParams_rayita6 = new RelativeLayout.LayoutParams(ANCHURA, 2);
                RelativeLayout.LayoutParams mLayoutParams_rayita7 = new RelativeLayout.LayoutParams(ANCHURA, 2);
                RelativeLayout.LayoutParams mLayoutParams_rayita8 = new RelativeLayout.LayoutParams(ANCHURA, 2);
                RelativeLayout.LayoutParams mLayoutParams_rayita9 = new RelativeLayout.LayoutParams(ANCHURA, 2);
                RelativeLayout.LayoutParams mLayoutParams_rayita10 = new RelativeLayout.LayoutParams(ANCHURA, 2);
                RelativeLayout.LayoutParams mLayoutParams_rayita11 = new RelativeLayout.LayoutParams(ANCHURA, 2);
                RelativeLayout.LayoutParams mLayoutParams_rayita12 = new RelativeLayout.LayoutParams(ANCHURA, 2);
                RelativeLayout.LayoutParams mLayoutParams_rayita13 = new RelativeLayout.LayoutParams(ANCHURA, 2);
                RelativeLayout.LayoutParams mLayoutParams_rayita14 = new RelativeLayout.LayoutParams(ANCHURA, 2);
                RelativeLayout.LayoutParams mLayoutParams_rayita15 = new RelativeLayout.LayoutParams(ANCHURA, 2);
                RelativeLayout.LayoutParams mLayoutParams_rayita16 = new RelativeLayout.LayoutParams(ANCHURA, 2);
                RelativeLayout.LayoutParams mLayoutParams_rayita17 = new RelativeLayout.LayoutParams(ANCHURA, 2);
                RelativeLayout.LayoutParams mLayoutParams_rayita18 = new RelativeLayout.LayoutParams(ANCHURA, 2);
                RelativeLayout.LayoutParams mLayoutParams_rayita19 = new RelativeLayout.LayoutParams(ANCHURA, 2);
                RelativeLayout.LayoutParams mLayoutParams_rayita20 = new RelativeLayout.LayoutParams(ANCHURA, 2);
                RelativeLayout.LayoutParams mLayoutParams_rayita21 = new RelativeLayout.LayoutParams(ANCHURA, 2);
                RelativeLayout.LayoutParams mLayoutParams_rayita22 = new RelativeLayout.LayoutParams(ANCHURA, 2);
                RelativeLayout.LayoutParams mLayoutParams_rayita23 = new RelativeLayout.LayoutParams(ANCHURA, 2);

                mLayoutParams_rayita0.addRule(RelativeLayout.CENTER_HORIZONTAL);
                mLayoutParams_rayita1.addRule(RelativeLayout.CENTER_HORIZONTAL);
                mLayoutParams_rayita2.addRule(RelativeLayout.CENTER_HORIZONTAL);
                mLayoutParams_rayita3.addRule(RelativeLayout.CENTER_HORIZONTAL);
                mLayoutParams_rayita4.addRule(RelativeLayout.CENTER_HORIZONTAL);
                mLayoutParams_rayita5.addRule(RelativeLayout.CENTER_HORIZONTAL);
                mLayoutParams_rayita6.addRule(RelativeLayout.CENTER_HORIZONTAL);
                mLayoutParams_rayita7.addRule(RelativeLayout.CENTER_HORIZONTAL);
                mLayoutParams_rayita8.addRule(RelativeLayout.CENTER_HORIZONTAL);
                mLayoutParams_rayita9.addRule(RelativeLayout.CENTER_HORIZONTAL);
                mLayoutParams_rayita10.addRule(RelativeLayout.CENTER_HORIZONTAL);
                mLayoutParams_rayita11.addRule(RelativeLayout.CENTER_HORIZONTAL);
                mLayoutParams_rayita12.addRule(RelativeLayout.CENTER_HORIZONTAL);
                mLayoutParams_rayita13.addRule(RelativeLayout.CENTER_HORIZONTAL);
                mLayoutParams_rayita14.addRule(RelativeLayout.CENTER_HORIZONTAL);
                mLayoutParams_rayita15.addRule(RelativeLayout.CENTER_HORIZONTAL);
                mLayoutParams_rayita16.addRule(RelativeLayout.CENTER_HORIZONTAL);
                mLayoutParams_rayita17.addRule(RelativeLayout.CENTER_HORIZONTAL);
                mLayoutParams_rayita18.addRule(RelativeLayout.CENTER_HORIZONTAL);
                mLayoutParams_rayita19.addRule(RelativeLayout.CENTER_HORIZONTAL);
                mLayoutParams_rayita20.addRule(RelativeLayout.CENTER_HORIZONTAL);
                mLayoutParams_rayita21.addRule(RelativeLayout.CENTER_HORIZONTAL);
                mLayoutParams_rayita22.addRule(RelativeLayout.CENTER_HORIZONTAL);
                mLayoutParams_rayita23.addRule(RelativeLayout.CENTER_HORIZONTAL);

                mLayoutParams_rayita0.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.barra0);
                mLayoutParams_rayita1.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.barra1);
                mLayoutParams_rayita2.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.barra2);
                mLayoutParams_rayita3.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.barra3);
                mLayoutParams_rayita4.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.barra4);
                mLayoutParams_rayita5.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.barra5);
                mLayoutParams_rayita6.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.barra6);
                mLayoutParams_rayita7.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.barra7);
                mLayoutParams_rayita8.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.barra8);
                mLayoutParams_rayita9.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.barra9);
                mLayoutParams_rayita10.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.barra10);
                mLayoutParams_rayita11.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.barra11);
                mLayoutParams_rayita12.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.barra12);
                mLayoutParams_rayita13.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.barra13);
                mLayoutParams_rayita14.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.barra14);
                mLayoutParams_rayita15.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.barra15);
                mLayoutParams_rayita16.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.barra16);
                mLayoutParams_rayita17.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.barra17);
                mLayoutParams_rayita18.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.barra18);
                mLayoutParams_rayita19.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.barra19);
                mLayoutParams_rayita20.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.barra20);
                mLayoutParams_rayita21.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.barra21);
                mLayoutParams_rayita22.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.barra22);
                mLayoutParams_rayita23.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.barra23);

                rayita_semana_pasada_00.setLayoutParams(mLayoutParams_rayita0);
                rayita_semana_pasada_01.setLayoutParams(mLayoutParams_rayita1);
                rayita_semana_pasada_02.setLayoutParams(mLayoutParams_rayita2);
                rayita_semana_pasada_03.setLayoutParams(mLayoutParams_rayita3);
                rayita_semana_pasada_04.setLayoutParams(mLayoutParams_rayita4);
                rayita_semana_pasada_05.setLayoutParams(mLayoutParams_rayita5);
                rayita_semana_pasada_06.setLayoutParams(mLayoutParams_rayita6);
                rayita_semana_pasada_07.setLayoutParams(mLayoutParams_rayita7);
                rayita_semana_pasada_08.setLayoutParams(mLayoutParams_rayita8);
                rayita_semana_pasada_09.setLayoutParams(mLayoutParams_rayita9);
                rayita_semana_pasada_10.setLayoutParams(mLayoutParams_rayita10);
                rayita_semana_pasada_11.setLayoutParams(mLayoutParams_rayita11);
                rayita_semana_pasada_12.setLayoutParams(mLayoutParams_rayita12);
                rayita_semana_pasada_13.setLayoutParams(mLayoutParams_rayita13);
                rayita_semana_pasada_14.setLayoutParams(mLayoutParams_rayita14);
                rayita_semana_pasada_15.setLayoutParams(mLayoutParams_rayita15);
                rayita_semana_pasada_16.setLayoutParams(mLayoutParams_rayita16);
                rayita_semana_pasada_17.setLayoutParams(mLayoutParams_rayita17);
                rayita_semana_pasada_18.setLayoutParams(mLayoutParams_rayita18);
                rayita_semana_pasada_19.setLayoutParams(mLayoutParams_rayita19);
                rayita_semana_pasada_20.setLayoutParams(mLayoutParams_rayita20);
                rayita_semana_pasada_21.setLayoutParams(mLayoutParams_rayita21);
                rayita_semana_pasada_22.setLayoutParams(mLayoutParams_rayita22);
                rayita_semana_pasada_23.setLayoutParams(mLayoutParams_rayita23);
            } else {
                rayita_semana_pasada_00.setVisibility(View.INVISIBLE);
                rayita_semana_pasada_01.setVisibility(View.INVISIBLE);
                rayita_semana_pasada_02.setVisibility(View.INVISIBLE);
                rayita_semana_pasada_03.setVisibility(View.INVISIBLE);
                rayita_semana_pasada_04.setVisibility(View.INVISIBLE);
                rayita_semana_pasada_05.setVisibility(View.INVISIBLE);
                rayita_semana_pasada_06.setVisibility(View.INVISIBLE);
                rayita_semana_pasada_07.setVisibility(View.INVISIBLE);
                rayita_semana_pasada_08.setVisibility(View.INVISIBLE);
                rayita_semana_pasada_09.setVisibility(View.INVISIBLE);
                rayita_semana_pasada_10.setVisibility(View.INVISIBLE);
                rayita_semana_pasada_11.setVisibility(View.INVISIBLE);
                rayita_semana_pasada_12.setVisibility(View.INVISIBLE);
                rayita_semana_pasada_13.setVisibility(View.INVISIBLE);
                rayita_semana_pasada_14.setVisibility(View.INVISIBLE);
                rayita_semana_pasada_15.setVisibility(View.INVISIBLE);
                rayita_semana_pasada_16.setVisibility(View.INVISIBLE);
                rayita_semana_pasada_17.setVisibility(View.INVISIBLE);
                rayita_semana_pasada_18.setVisibility(View.INVISIBLE);
                rayita_semana_pasada_19.setVisibility(View.INVISIBLE);
                rayita_semana_pasada_20.setVisibility(View.INVISIBLE);
                rayita_semana_pasada_21.setVisibility(View.INVISIBLE);
                rayita_semana_pasada_22.setVisibility(View.INVISIBLE);
                rayita_semana_pasada_23.setVisibility(View.INVISIBLE);
            }

        }catch(Exception e){
            new utils.AsyncTask_Guardar_Error().execute(new Pair(this,e.toString()));
        }
    }
    private void pinta_rayitas_año_pasado(ViewGroup rootView){
        try {

            ImageView rayita_anyo_pasado_00 = rootView.findViewById(R.id.rayita_anyo_pasado_00);
            ImageView rayita_anyo_pasado_01 = rootView.findViewById(R.id.rayita_anyo_pasado_01);
            ImageView rayita_anyo_pasado_02 = rootView.findViewById(R.id.rayita_anyo_pasado_02);
            ImageView rayita_anyo_pasado_03 = rootView.findViewById(R.id.rayita_anyo_pasado_03);
            ImageView rayita_anyo_pasado_04 = rootView.findViewById(R.id.rayita_anyo_pasado_04);
            ImageView rayita_anyo_pasado_05 = rootView.findViewById(R.id.rayita_anyo_pasado_05);
            ImageView rayita_anyo_pasado_06 = rootView.findViewById(R.id.rayita_anyo_pasado_06);
            ImageView rayita_anyo_pasado_07 = rootView.findViewById(R.id.rayita_anyo_pasado_07);
            ImageView rayita_anyo_pasado_08 = rootView.findViewById(R.id.rayita_anyo_pasado_08);
            ImageView rayita_anyo_pasado_09 = rootView.findViewById(R.id.rayita_anyo_pasado_09);
            ImageView rayita_anyo_pasado_10 = rootView.findViewById(R.id.rayita_anyo_pasado_10);
            ImageView rayita_anyo_pasado_11 = rootView.findViewById(R.id.rayita_anyo_pasado_11);
            ImageView rayita_anyo_pasado_12 = rootView.findViewById(R.id.rayita_anyo_pasado_12);
            ImageView rayita_anyo_pasado_13 = rootView.findViewById(R.id.rayita_anyo_pasado_13);
            ImageView rayita_anyo_pasado_14 = rootView.findViewById(R.id.rayita_anyo_pasado_14);
            ImageView rayita_anyo_pasado_15 = rootView.findViewById(R.id.rayita_anyo_pasado_15);
            ImageView rayita_anyo_pasado_16 = rootView.findViewById(R.id.rayita_anyo_pasado_16);
            ImageView rayita_anyo_pasado_17 = rootView.findViewById(R.id.rayita_anyo_pasado_17);
            ImageView rayita_anyo_pasado_18 = rootView.findViewById(R.id.rayita_anyo_pasado_18);
            ImageView rayita_anyo_pasado_19 = rootView.findViewById(R.id.rayita_anyo_pasado_19);
            ImageView rayita_anyo_pasado_20 = rootView.findViewById(R.id.rayita_anyo_pasado_20);
            ImageView rayita_anyo_pasado_21 = rootView.findViewById(R.id.rayita_anyo_pasado_21);
            ImageView rayita_anyo_pasado_22 = rootView.findViewById(R.id.rayita_anyo_pasado_22);
            ImageView rayita_anyo_pasado_23 = rootView.findViewById(R.id.rayita_anyo_pasado_23);

            if (precios_año_pasado_activado) {
                rayita_anyo_pasado_00.setVisibility(ImageView.VISIBLE);
                rayita_anyo_pasado_01.setVisibility(ImageView.VISIBLE);
                rayita_anyo_pasado_02.setVisibility(ImageView.VISIBLE);
                rayita_anyo_pasado_03.setVisibility(ImageView.VISIBLE);
                rayita_anyo_pasado_04.setVisibility(ImageView.VISIBLE);
                rayita_anyo_pasado_05.setVisibility(ImageView.VISIBLE);
                rayita_anyo_pasado_06.setVisibility(ImageView.VISIBLE);
                rayita_anyo_pasado_07.setVisibility(ImageView.VISIBLE);
                rayita_anyo_pasado_08.setVisibility(ImageView.VISIBLE);
                rayita_anyo_pasado_09.setVisibility(ImageView.VISIBLE);
                rayita_anyo_pasado_10.setVisibility(ImageView.VISIBLE);
                rayita_anyo_pasado_11.setVisibility(ImageView.VISIBLE);
                rayita_anyo_pasado_12.setVisibility(ImageView.VISIBLE);
                rayita_anyo_pasado_13.setVisibility(ImageView.VISIBLE);
                rayita_anyo_pasado_14.setVisibility(ImageView.VISIBLE);
                rayita_anyo_pasado_15.setVisibility(ImageView.VISIBLE);
                rayita_anyo_pasado_16.setVisibility(ImageView.VISIBLE);
                rayita_anyo_pasado_17.setVisibility(ImageView.VISIBLE);
                rayita_anyo_pasado_18.setVisibility(ImageView.VISIBLE);
                rayita_anyo_pasado_19.setVisibility(ImageView.VISIBLE);
                rayita_anyo_pasado_20.setVisibility(ImageView.VISIBLE);
                rayita_anyo_pasado_21.setVisibility(ImageView.VISIBLE);
                rayita_anyo_pasado_22.setVisibility(ImageView.VISIBLE);
                rayita_anyo_pasado_23.setVisibility(ImageView.VISIBLE);



				RelativeLayout.LayoutParams mLayoutParams_rayita0 = new RelativeLayout.LayoutParams(ANCHURA, 2);
				RelativeLayout.LayoutParams mLayoutParams_rayita1 = new RelativeLayout.LayoutParams(ANCHURA, 2);
				RelativeLayout.LayoutParams mLayoutParams_rayita2 = new RelativeLayout.LayoutParams(ANCHURA, 2);
				RelativeLayout.LayoutParams mLayoutParams_rayita3 = new RelativeLayout.LayoutParams(ANCHURA, 2);
				RelativeLayout.LayoutParams mLayoutParams_rayita4 = new RelativeLayout.LayoutParams(ANCHURA, 2);
				RelativeLayout.LayoutParams mLayoutParams_rayita5 = new RelativeLayout.LayoutParams(ANCHURA, 2);
				RelativeLayout.LayoutParams mLayoutParams_rayita6 = new RelativeLayout.LayoutParams(ANCHURA, 2);
				RelativeLayout.LayoutParams mLayoutParams_rayita7 = new RelativeLayout.LayoutParams(ANCHURA, 2);
				RelativeLayout.LayoutParams mLayoutParams_rayita8 = new RelativeLayout.LayoutParams(ANCHURA, 2);
				RelativeLayout.LayoutParams mLayoutParams_rayita9 = new RelativeLayout.LayoutParams(ANCHURA, 2);
				RelativeLayout.LayoutParams mLayoutParams_rayita10 = new RelativeLayout.LayoutParams(ANCHURA, 2);
				RelativeLayout.LayoutParams mLayoutParams_rayita11 = new RelativeLayout.LayoutParams(ANCHURA, 2);
				RelativeLayout.LayoutParams mLayoutParams_rayita12 = new RelativeLayout.LayoutParams(ANCHURA, 2);
				RelativeLayout.LayoutParams mLayoutParams_rayita13 = new RelativeLayout.LayoutParams(ANCHURA, 2);
				RelativeLayout.LayoutParams mLayoutParams_rayita14 = new RelativeLayout.LayoutParams(ANCHURA, 2);
				RelativeLayout.LayoutParams mLayoutParams_rayita15 = new RelativeLayout.LayoutParams(ANCHURA, 2);
				RelativeLayout.LayoutParams mLayoutParams_rayita16 = new RelativeLayout.LayoutParams(ANCHURA, 2);
				RelativeLayout.LayoutParams mLayoutParams_rayita17 = new RelativeLayout.LayoutParams(ANCHURA, 2);
				RelativeLayout.LayoutParams mLayoutParams_rayita18 = new RelativeLayout.LayoutParams(ANCHURA, 2);
				RelativeLayout.LayoutParams mLayoutParams_rayita19 = new RelativeLayout.LayoutParams(ANCHURA, 2);
				RelativeLayout.LayoutParams mLayoutParams_rayita20 = new RelativeLayout.LayoutParams(ANCHURA, 2);
				RelativeLayout.LayoutParams mLayoutParams_rayita21 = new RelativeLayout.LayoutParams(ANCHURA, 2);
				RelativeLayout.LayoutParams mLayoutParams_rayita22 = new RelativeLayout.LayoutParams(ANCHURA, 2);
				RelativeLayout.LayoutParams mLayoutParams_rayita23 = new RelativeLayout.LayoutParams(ANCHURA, 2);

				mLayoutParams_rayita0.addRule(RelativeLayout.CENTER_HORIZONTAL);
				mLayoutParams_rayita1.addRule(RelativeLayout.CENTER_HORIZONTAL);
				mLayoutParams_rayita2.addRule(RelativeLayout.CENTER_HORIZONTAL);
				mLayoutParams_rayita3.addRule(RelativeLayout.CENTER_HORIZONTAL);
				mLayoutParams_rayita4.addRule(RelativeLayout.CENTER_HORIZONTAL);
				mLayoutParams_rayita5.addRule(RelativeLayout.CENTER_HORIZONTAL);
				mLayoutParams_rayita6.addRule(RelativeLayout.CENTER_HORIZONTAL);
				mLayoutParams_rayita7.addRule(RelativeLayout.CENTER_HORIZONTAL);
				mLayoutParams_rayita8.addRule(RelativeLayout.CENTER_HORIZONTAL);
				mLayoutParams_rayita9.addRule(RelativeLayout.CENTER_HORIZONTAL);
				mLayoutParams_rayita10.addRule(RelativeLayout.CENTER_HORIZONTAL);
				mLayoutParams_rayita11.addRule(RelativeLayout.CENTER_HORIZONTAL);
				mLayoutParams_rayita12.addRule(RelativeLayout.CENTER_HORIZONTAL);
				mLayoutParams_rayita13.addRule(RelativeLayout.CENTER_HORIZONTAL);
				mLayoutParams_rayita14.addRule(RelativeLayout.CENTER_HORIZONTAL);
				mLayoutParams_rayita15.addRule(RelativeLayout.CENTER_HORIZONTAL);
				mLayoutParams_rayita16.addRule(RelativeLayout.CENTER_HORIZONTAL);
				mLayoutParams_rayita17.addRule(RelativeLayout.CENTER_HORIZONTAL);
				mLayoutParams_rayita18.addRule(RelativeLayout.CENTER_HORIZONTAL);
				mLayoutParams_rayita19.addRule(RelativeLayout.CENTER_HORIZONTAL);
				mLayoutParams_rayita20.addRule(RelativeLayout.CENTER_HORIZONTAL);
				mLayoutParams_rayita21.addRule(RelativeLayout.CENTER_HORIZONTAL);
				mLayoutParams_rayita22.addRule(RelativeLayout.CENTER_HORIZONTAL);
				mLayoutParams_rayita23.addRule(RelativeLayout.CENTER_HORIZONTAL);

				mLayoutParams_rayita0.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.barra0);
				mLayoutParams_rayita1.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.barra1);
				mLayoutParams_rayita2.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.barra2);
				mLayoutParams_rayita3.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.barra3);
				mLayoutParams_rayita4.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.barra4);
				mLayoutParams_rayita5.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.barra5);
				mLayoutParams_rayita6.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.barra6);
				mLayoutParams_rayita7.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.barra7);
				mLayoutParams_rayita8.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.barra8);
				mLayoutParams_rayita9.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.barra9);
				mLayoutParams_rayita10.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.barra10);
				mLayoutParams_rayita11.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.barra11);
				mLayoutParams_rayita12.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.barra12);
				mLayoutParams_rayita13.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.barra13);
				mLayoutParams_rayita14.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.barra14);
				mLayoutParams_rayita15.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.barra15);
				mLayoutParams_rayita16.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.barra16);
				mLayoutParams_rayita17.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.barra17);
				mLayoutParams_rayita18.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.barra18);
				mLayoutParams_rayita19.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.barra19);
				mLayoutParams_rayita20.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.barra20);
				mLayoutParams_rayita21.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.barra21);
				mLayoutParams_rayita22.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.barra22);
				mLayoutParams_rayita23.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.barra23);

				rayita_anyo_pasado_00.setLayoutParams(mLayoutParams_rayita0);
				rayita_anyo_pasado_01.setLayoutParams(mLayoutParams_rayita1);
				rayita_anyo_pasado_02.setLayoutParams(mLayoutParams_rayita2);
				rayita_anyo_pasado_03.setLayoutParams(mLayoutParams_rayita3);
				rayita_anyo_pasado_04.setLayoutParams(mLayoutParams_rayita4);
				rayita_anyo_pasado_05.setLayoutParams(mLayoutParams_rayita5);
				rayita_anyo_pasado_06.setLayoutParams(mLayoutParams_rayita6);
				rayita_anyo_pasado_07.setLayoutParams(mLayoutParams_rayita7);
				rayita_anyo_pasado_08.setLayoutParams(mLayoutParams_rayita8);
				rayita_anyo_pasado_09.setLayoutParams(mLayoutParams_rayita9);
				rayita_anyo_pasado_10.setLayoutParams(mLayoutParams_rayita10);
				rayita_anyo_pasado_11.setLayoutParams(mLayoutParams_rayita11);
				rayita_anyo_pasado_12.setLayoutParams(mLayoutParams_rayita12);
				rayita_anyo_pasado_13.setLayoutParams(mLayoutParams_rayita13);
				rayita_anyo_pasado_14.setLayoutParams(mLayoutParams_rayita14);
				rayita_anyo_pasado_15.setLayoutParams(mLayoutParams_rayita15);
				rayita_anyo_pasado_16.setLayoutParams(mLayoutParams_rayita16);
				rayita_anyo_pasado_17.setLayoutParams(mLayoutParams_rayita17);
				rayita_anyo_pasado_18.setLayoutParams(mLayoutParams_rayita18);
				rayita_anyo_pasado_19.setLayoutParams(mLayoutParams_rayita19);
				rayita_anyo_pasado_20.setLayoutParams(mLayoutParams_rayita20);
				rayita_anyo_pasado_21.setLayoutParams(mLayoutParams_rayita21);
				rayita_anyo_pasado_22.setLayoutParams(mLayoutParams_rayita22);
				rayita_anyo_pasado_23.setLayoutParams(mLayoutParams_rayita23);

				rayita_anyo_pasado_00.setY((int) ((-1) * precios_hace_un_año[0] * ESCALA));
				rayita_anyo_pasado_01.setY((int) ((-1) * precios_hace_un_año[1] * ESCALA));
				rayita_anyo_pasado_02.setY((int) ((-1) * precios_hace_un_año[2] * ESCALA));
				rayita_anyo_pasado_03.setY((int) ((-1) * precios_hace_un_año[3] * ESCALA));
				rayita_anyo_pasado_04.setY((int) ((-1) * precios_hace_un_año[4] * ESCALA));
				rayita_anyo_pasado_05.setY((int) ((-1) * precios_hace_un_año[5] * ESCALA));
				rayita_anyo_pasado_06.setY((int) ((-1) * precios_hace_un_año[6] * ESCALA));
				rayita_anyo_pasado_07.setY((int) ((-1) * precios_hace_un_año[7] * ESCALA));
				rayita_anyo_pasado_08.setY((int) ((-1) * precios_hace_un_año[8] * ESCALA));
				rayita_anyo_pasado_09.setY((int) ((-1) * precios_hace_un_año[9] * ESCALA));
				rayita_anyo_pasado_10.setY((int) ((-1) * precios_hace_un_año[10] * ESCALA));
				rayita_anyo_pasado_11.setY((int) ((-1) * precios_hace_un_año[11] * ESCALA));
				rayita_anyo_pasado_12.setY((int) ((-1) * precios_hace_un_año[12] * ESCALA));
				rayita_anyo_pasado_13.setY((int) ((-1) * precios_hace_un_año[13] * ESCALA));
				rayita_anyo_pasado_14.setY((int) ((-1) * precios_hace_un_año[14] * ESCALA));
				rayita_anyo_pasado_15.setY((int) ((-1) * precios_hace_un_año[15] * ESCALA));
				rayita_anyo_pasado_16.setY((int) ((-1) * precios_hace_un_año[16] * ESCALA));
				rayita_anyo_pasado_17.setY((int) ((-1) * precios_hace_un_año[17] * ESCALA));
				rayita_anyo_pasado_18.setY((int) ((-1) * precios_hace_un_año[18] * ESCALA));
				rayita_anyo_pasado_19.setY((int) ((-1) * precios_hace_un_año[19] * ESCALA));
				rayita_anyo_pasado_20.setY((int) ((-1) * precios_hace_un_año[20] * ESCALA));
				rayita_anyo_pasado_21.setY((int) ((-1) * precios_hace_un_año[21] * ESCALA));
				rayita_anyo_pasado_22.setY((int) ((-1) * precios_hace_un_año[22] * ESCALA));
				rayita_anyo_pasado_23.setY((int) ((-1) * precios_hace_un_año[23] * ESCALA));
            } else {
               rayita_anyo_pasado_00.setVisibility(View.INVISIBLE);
                rayita_anyo_pasado_01.setVisibility(View.INVISIBLE);
                rayita_anyo_pasado_02.setVisibility(View.INVISIBLE);
                rayita_anyo_pasado_03.setVisibility(View.INVISIBLE);
                rayita_anyo_pasado_04.setVisibility(View.INVISIBLE);
                rayita_anyo_pasado_05.setVisibility(View.INVISIBLE);
                rayita_anyo_pasado_06.setVisibility(View.INVISIBLE);
                rayita_anyo_pasado_07.setVisibility(View.INVISIBLE);
                rayita_anyo_pasado_08.setVisibility(View.INVISIBLE);
                rayita_anyo_pasado_09.setVisibility(View.INVISIBLE);
                rayita_anyo_pasado_10.setVisibility(View.INVISIBLE);
                rayita_anyo_pasado_11.setVisibility(View.INVISIBLE);
                rayita_anyo_pasado_12.setVisibility(View.INVISIBLE);
                rayita_anyo_pasado_13.setVisibility(View.INVISIBLE);
                rayita_anyo_pasado_14.setVisibility(View.INVISIBLE);
                rayita_anyo_pasado_15.setVisibility(View.INVISIBLE);
                rayita_anyo_pasado_16.setVisibility(View.INVISIBLE);
                rayita_anyo_pasado_17.setVisibility(View.INVISIBLE);
                rayita_anyo_pasado_18.setVisibility(View.INVISIBLE);
                rayita_anyo_pasado_19.setVisibility(View.INVISIBLE);
                rayita_anyo_pasado_20.setVisibility(View.INVISIBLE);
                rayita_anyo_pasado_21.setVisibility(View.INVISIBLE);
                rayita_anyo_pasado_22.setVisibility(View.INVISIBLE);
                rayita_anyo_pasado_23.setVisibility(View.INVISIBLE);
            }

        }catch(Exception e){
            new utils.AsyncTask_Guardar_Error().execute(new Pair(this,e.toString()));
        }
    }
    private void pinta_flechitas(ViewGroup rootView){
        ImageView flechita_derecha = rootView.findViewById(R.id.flechita_derecha);
        ImageView flechita_izquierda = rootView.findViewById(R.id.flechita_izquierda);

        if(this.titulo.contains("hoy")) {
            flechita_derecha.setVisibility(ImageView.INVISIBLE);
            flechita_izquierda.setVisibility(ImageView.VISIBLE);
        }else{
            flechita_derecha.setVisibility(ImageView.VISIBLE);
            flechita_izquierda.setVisibility(ImageView.INVISIBLE);
        }
    }
    private void pinta_texto_precios(ViewGroup rootView/*, int tamaño_letra*/){
        try {

            TextView texto_precio0 = rootView.findViewById(R.id.texto_precio0);
            TextView texto_precio1 = rootView.findViewById(R.id.texto_precio1);
            TextView texto_precio2 = rootView.findViewById(R.id.texto_precio2);
            TextView texto_precio3 = rootView.findViewById(R.id.texto_precio3);
            TextView texto_precio4 = rootView.findViewById(R.id.texto_precio4);
            TextView texto_precio5 = rootView.findViewById(R.id.texto_precio5);
            TextView texto_precio6 = rootView.findViewById(R.id.texto_precio6);
            TextView texto_precio7 = rootView.findViewById(R.id.texto_precio7);
            TextView texto_precio8 = rootView.findViewById(R.id.texto_precio8);
            TextView texto_precio9 = rootView.findViewById(R.id.texto_precio9);
            TextView texto_precio10 = rootView.findViewById(R.id.texto_precio10);
            TextView texto_precio11 = rootView.findViewById(R.id.texto_precio11);
            TextView texto_precio12 = rootView.findViewById(R.id.texto_precio12);
            TextView texto_precio13 = rootView.findViewById(R.id.texto_precio13);
            TextView texto_precio14 = rootView.findViewById(R.id.texto_precio14);
            TextView texto_precio15 = rootView.findViewById(R.id.texto_precio15);
            TextView texto_precio16 = rootView.findViewById(R.id.texto_precio16);
            TextView texto_precio17 = rootView.findViewById(R.id.texto_precio17);
            TextView texto_precio18 = rootView.findViewById(R.id.texto_precio18);
            TextView texto_precio19 = rootView.findViewById(R.id.texto_precio19);
            TextView texto_precio20 = rootView.findViewById(R.id.texto_precio20);
            TextView texto_precio21 = rootView.findViewById(R.id.texto_precio21);
            TextView texto_precio22 = rootView.findViewById(R.id.texto_precio22);
            TextView texto_precio23 = rootView.findViewById(R.id.texto_precio23);

            DecimalFormat df = new DecimalFormat(".0000");


            if (precios[0].compareTo(-1.0f) == 0) {
                texto_precio0.setText("0.0");
            } else {
                texto_precio0.setText(df.format(precios[0]));
            }
            if (precios[1].compareTo(-1.0f) == 0) {
                texto_precio1.setText("0.0");
            } else {
                texto_precio1.setText(df.format(precios[1]));
            }
            if (precios[2].compareTo(-1.0f) == 0) {
                texto_precio2.setText("0.0");
            } else {
                texto_precio2.setText(df.format(precios[2]));
            }
            if (precios[3].compareTo(-1.0f) == 0) {
                texto_precio3.setText("0.0");
            } else {
                texto_precio3.setText(df.format(precios[3]));
            }
            if (precios[4].compareTo(-1.0f) == 0) {
                texto_precio4.setText("0.0");
            } else {
                texto_precio4.setText(df.format(precios[4]));
            }
            if (precios[5].compareTo(-1.0f) == 0) {
                texto_precio5.setText("0.0");
            } else {
                texto_precio5.setText(df.format(precios[5]));
            }
            if (precios[6].compareTo(-1.0f) == 0) {
                texto_precio6.setText("0.0");
            } else {
                texto_precio6.setText(df.format(precios[6]));
            }
            if (precios[7].compareTo(-1.0f) == 0) {
                texto_precio7.setText("0.0");
            } else {
                texto_precio7.setText(df.format(precios[7]));
            }
            if (precios[8].compareTo(-1.0f) == 0) {
                texto_precio8.setText("0.0");
            } else {
                texto_precio8.setText(df.format(precios[8]));
            }
            if (precios[9].compareTo(-1.0f) == 0) {
                texto_precio9.setText("0.0");
            } else {
                texto_precio9.setText(df.format(precios[9]));
            }
            if (precios[10].compareTo(-1.0f) == 0) {
                texto_precio10.setText("0.0");
            } else {
                texto_precio10.setText(df.format(precios[10]));
            }
            if (precios[11].compareTo(-1.0f) == 0) {
                texto_precio11.setText("0.0");
            } else {
                texto_precio11.setText(df.format(precios[11]));
            }
            if (precios[12].compareTo(-1.0f) == 0) {
                texto_precio12.setText("0.0");
            } else {
                texto_precio12.setText(df.format(precios[12]));
            }
            if (precios[13].compareTo(-1.0f) == 0) {
                texto_precio13.setText("0.0");
            } else {
                texto_precio13.setText(df.format(precios[13]));
            }
            if (precios[14].compareTo(-1.0f) == 0) {
                texto_precio14.setText("0.0");
            } else {
                texto_precio14.setText(df.format(precios[14]));
            }
            if (precios[15].compareTo(-1.0f) == 0) {
                texto_precio15.setText("0.0");
            } else {
                texto_precio15.setText(df.format(precios[15]));
            }
            if (precios[16].compareTo(-1.0f) == 0) {
                texto_precio16.setText("0.0");
            } else {
                texto_precio16.setText(df.format(precios[16]));
            }
            if (precios[17].compareTo(-1.0f) == 0) {
                texto_precio17.setText("0.0");
            } else {
                texto_precio17.setText(df.format(precios[17]));
            }
            if (precios[18].compareTo(-1.0f) == 0) {
                texto_precio18.setText("0.0");
            } else {
                texto_precio18.setText(df.format(precios[18]));
            }
            if (precios[19].compareTo(-1.0f) == 0) {
                texto_precio19.setText("0.0");
            } else {
                texto_precio19.setText(df.format(precios[19]));
            }
            if (precios[20].compareTo(-1.0f) == 0) {
                texto_precio20.setText("0.0");
            } else {
                texto_precio20.setText(df.format(precios[20]));
            }
            if (precios[21].compareTo(-1.0f) == 0) {
                texto_precio21.setText("0.0");
            } else {
                texto_precio21.setText(df.format(precios[21]));
            }
            if (precios[22].compareTo(-1.0f) == 0) {
                texto_precio22.setText("0.0");
            } else {
                texto_precio22.setText(df.format(precios[22]));
            }
            if (precios[23].compareTo(-1.0f) == 0) {
                texto_precio23.setText("0.0");
            } else {
                texto_precio23.setText(df.format(precios[23]));
            }
        }catch (Exception e){
            new utils.AsyncTask_Guardar_Error().execute(new Pair(this,e.toString()));
        }
    }

    private int Px2DP(int px){
        Float pd;
        if (Resources.getSystem().getDisplayMetrics().density == 0.75f) {pd=px*0.75f;}
        else if (Resources.getSystem().getDisplayMetrics().density == 1.0f){pd= (float) px;}
        else if (Resources.getSystem().getDisplayMetrics().density == 1.5f){pd=px*1.5f;}
        else if (Resources.getSystem().getDisplayMetrics().density == 2.0f){pd=px*2.0f;}
        else if (Resources.getSystem().getDisplayMetrics().density == 3.0f){pd=px*3.0f;}
        else if (Resources.getSystem().getDisplayMetrics().density == 3.5f){pd=px*3.5f;}
        else{  pd=px*4.0f;}
        return pd.intValue();
    }

    private int getColor(Float[] precios_ordenados,float precio){
        int i=0;
        while (precio != precios_ordenados[i]){
            i++;
        }
        switch (i){
            case 0:
                return getResources().getColor(R.color._0);
            case 1:
                return getResources().getColor(R.color._1);
            case 2:
                return getResources().getColor(R.color._2);
            case 3:
                return getResources().getColor(R.color._3);
            case 4:
                return getResources().getColor(R.color._4);
            case 5:
                return getResources().getColor(R.color._5);
            case 6:
                return getResources().getColor(R.color._6);
            case 7:
                return getResources().getColor(R.color._7);
            case 8:
                return getResources().getColor(R.color._8);
            case 9:
                return getResources().getColor(R.color._9);
            case 10:
                return getResources().getColor(R.color._10);
            case 11:
                return getResources().getColor(R.color._11);
            case 12:
                return getResources().getColor(R.color._12);
            case 13:
                return getResources().getColor(R.color._13);
            case 14:
                return getResources().getColor(R.color._14);
            case 15:
                return getResources().getColor(R.color._15);
            case 16:
                return getResources().getColor(R.color._16);
            case 17:
                return getResources().getColor(R.color._17);
            case 18:
                return getResources().getColor(R.color._18);
            case 19:
                return getResources().getColor(R.color._19);
            case 20:
                return getResources().getColor(R.color._20);
            case 21:
                return getResources().getColor(R.color._21);
            case 22:
                return getResources().getColor(R.color._22);
            case 23:
                return getResources().getColor(R.color._23);
            default:
                return getResources().getColor(R.color._0);
        }
    }
}

