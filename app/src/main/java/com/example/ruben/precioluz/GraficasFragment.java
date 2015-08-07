package com.example.ruben.precioluz;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;


public class GraficasFragment extends Fragment {

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

    private String titulo;
    private Float[]  precio= new Float[72];


    public static GraficasFragment newInstance(String titulo, List<Float> precio) {

        // Instantiate a new fragment
        GraficasFragment fragment = new GraficasFragment();

        // Save the parameters
        Bundle bundle = new Bundle();
        bundle.putString(TITULO, titulo);
        bundle.putFloat(PRECIO0, precio.get(0));
        bundle.putFloat(PRECIO1, precio.get(1));
        bundle.putFloat(PRECIO2, precio.get(2));
        bundle.putFloat(PRECIO3, precio.get(3));
        bundle.putFloat(PRECIO4, precio.get(4));
        bundle.putFloat(PRECIO5, precio.get(5));
        bundle.putFloat(PRECIO6, precio.get(6));
        bundle.putFloat(PRECIO7, precio.get(7));
        bundle.putFloat(PRECIO8, precio.get(8));
        bundle.putFloat(PRECIO9, precio.get(9));
        bundle.putFloat(PRECIO10, precio.get(10));
        bundle.putFloat(PRECIO11, precio.get(11));
        bundle.putFloat(PRECIO12, precio.get(12));
        bundle.putFloat(PRECIO13, precio.get(13));
        bundle.putFloat(PRECIO14, precio.get(14));
        bundle.putFloat(PRECIO15, precio.get(15));
        bundle.putFloat(PRECIO16, precio.get(16));
        bundle.putFloat(PRECIO17, precio.get(17));
        bundle.putFloat(PRECIO18, precio.get(18));
        bundle.putFloat(PRECIO19, precio.get(19));
        bundle.putFloat(PRECIO20, precio.get(20));
        bundle.putFloat(PRECIO21, precio.get(21));
        bundle.putFloat(PRECIO22, precio.get(22));
        bundle.putFloat(PRECIO23, precio.get(23));
        fragment.setArguments(bundle);
        fragment.setRetainInstance(true);

        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Load parameters when the initial creation of the fragment is done
        this.titulo = (getArguments() != null) ? getArguments().getString(TITULO) : "NO HAY TITULO";
        this.precio[0] = (getArguments() != null) ? getArguments().getFloat(PRECIO0) : -1;
        this.precio[1] = (getArguments() != null) ? getArguments().getFloat(PRECIO1) : -1;
        this.precio[2] = (getArguments() != null) ? getArguments().getFloat(PRECIO2) : -1;
        this.precio[3] = (getArguments() != null) ? getArguments().getFloat(PRECIO3) : -1;
        this.precio[4] = (getArguments() != null) ? getArguments().getFloat(PRECIO4) : -1;
        this.precio[5] = (getArguments() != null) ? getArguments().getFloat(PRECIO5) : -1;
        this.precio[6] = (getArguments() != null) ? getArguments().getFloat(PRECIO6) : -1;
        this.precio[7] = (getArguments() != null) ? getArguments().getFloat(PRECIO7) : -1;
        this.precio[8] = (getArguments() != null) ? getArguments().getFloat(PRECIO8) : -1;
        this.precio[9] = (getArguments() != null) ? getArguments().getFloat(PRECIO9) : -1;
        this.precio[10] = (getArguments() != null) ? getArguments().getFloat(PRECIO10) : -1;
        this.precio[11] = (getArguments() != null) ? getArguments().getFloat(PRECIO11) : -1;
        this.precio[12] = (getArguments() != null) ? getArguments().getFloat(PRECIO12) : -1;
        this.precio[13] = (getArguments() != null) ? getArguments().getFloat(PRECIO13) : -1;
        this.precio[14] = (getArguments() != null) ? getArguments().getFloat(PRECIO14) : -1;
        this.precio[15] = (getArguments() != null) ? getArguments().getFloat(PRECIO15) : -1;
        this.precio[16] = (getArguments() != null) ? getArguments().getFloat(PRECIO16) : -1;
        this.precio[17] = (getArguments() != null) ? getArguments().getFloat(PRECIO17) : -1;
        this.precio[18] = (getArguments() != null) ? getArguments().getFloat(PRECIO18) : -1;
        this.precio[19] = (getArguments() != null) ? getArguments().getFloat(PRECIO19) : -1;
        this.precio[20] = (getArguments() != null) ? getArguments().getFloat(PRECIO20) : -1;
        this.precio[21] = (getArguments() != null) ? getArguments().getFloat(PRECIO21) : -1;
        this.precio[22] = (getArguments() != null) ? getArguments().getFloat(PRECIO22) : -1;
        this.precio[23] = (getArguments() != null) ? getArguments().getFloat(PRECIO23) : -1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        int PADDING_IMAGEVIEW_LEFT_IN_TWO_PANE_MODE=getPixels(0);
        int PADDING_IMAGEVIEW_RIGHT_IN_TWO_PANE_MODE=getPixels(0);
        int PADDING_IMAGEVIEW_BOTTOM_IN_TWO_PANE_MODE=getPixels(1);
        int PADDING_TEXTVIEW_LEFT_IN_TWO_PANE_MODE= getPixels(8);
        int PADDING_TEXTVIEW_TOP_IN_TWO_PANE_MODE=getPixels(0);
        int PADDING_TEXTVIEW_RIGHT_IN_TWO_PANE_MODE=getPixels(0);
        int PADDING_TEXTVIEW_BOTTOM_IN_TWO_PANE_MODE=getPixels(0);

        int PADDING_IMAGEVIEW_LEFT_IN_ONE_PANE_MODE=getPixels(0);
        int PADDING_IMAGEVIEW_RIGHT_IN_ONE_PANE_MODE=getPixels(0);
        int PADDING_IMAGEVIEW_BOTTOM_IN_ONE_PANE_MODE=getPixels(1);
        int PADDING_TEXTVIEW_LEFT_IN_ONE_PANE_MODE= getPixels(0);
        int PADDING_TEXTVIEW_TOP_IN_ONE_PANE_MODE=getPixels(0);
        int PADDING_TEXTVIEW_RIGHT_IN_ONE_PANE_MODE=getPixels(0);
        int PADDING_TEXTVIEW_BOTTOM_IN_ONE_PANE_MODE=getPixels(0);

        int ESCALA_IN_TWO_PANE_MODE=800; //Altura de las barras
        int ESCALA_IN_ONE_PANE_MODE=400;

        int ANCHURA_IN_TWO_PANE_MODE= getPixels(16); //Anchura de las barras
        int ANCHURA_IN_ONE_PANE_MODE=getPixels(7);

        int TAMAÑO_TITULO_IN_TWO_PANE_MODE=getPixels(11);
        int TAMAÑO_TITULO_IN_ONE_PANE_MODE= getPixels(8);

        int TAMAÑO_LEYENDA_IN_TWO_PANE_MODE=getPixels(7);
        int TAMAÑO_LEYENDA_IN_ONE_PANE_MODE=getPixels(4);

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.graficas_layout, container, false);

        TextView TextView_titulo = (TextView) rootView.findViewById(R.id.precios);
        TextView_titulo.setText(this.titulo);


        if (isInTwoPaneMode()) {
            formatea_barras(rootView,
                    ESCALA_IN_TWO_PANE_MODE,
                    ANCHURA_IN_TWO_PANE_MODE,
                    PADDING_IMAGEVIEW_LEFT_IN_TWO_PANE_MODE,
                    PADDING_IMAGEVIEW_RIGHT_IN_TWO_PANE_MODE,
                    PADDING_IMAGEVIEW_BOTTOM_IN_TWO_PANE_MODE,
                    PADDING_TEXTVIEW_LEFT_IN_TWO_PANE_MODE,
                    PADDING_TEXTVIEW_TOP_IN_TWO_PANE_MODE,
                    PADDING_TEXTVIEW_RIGHT_IN_TWO_PANE_MODE,
                    PADDING_TEXTVIEW_BOTTOM_IN_TWO_PANE_MODE,
                    TAMAÑO_LEYENDA_IN_TWO_PANE_MODE,
                    Gravity.LEFT);

            TextView_titulo.setTextSize(TypedValue.COMPLEX_UNIT_DIP ,TAMAÑO_TITULO_IN_TWO_PANE_MODE);
        }else{
            formatea_barras(rootView,
                    ESCALA_IN_ONE_PANE_MODE,
                    ANCHURA_IN_ONE_PANE_MODE,
                    PADDING_IMAGEVIEW_LEFT_IN_ONE_PANE_MODE,
                    PADDING_IMAGEVIEW_RIGHT_IN_ONE_PANE_MODE,
                    PADDING_IMAGEVIEW_BOTTOM_IN_ONE_PANE_MODE,
                    PADDING_TEXTVIEW_LEFT_IN_ONE_PANE_MODE,
                    PADDING_TEXTVIEW_TOP_IN_ONE_PANE_MODE,
                    PADDING_TEXTVIEW_RIGHT_IN_ONE_PANE_MODE,
                    PADDING_TEXTVIEW_BOTTOM_IN_ONE_PANE_MODE,
                    TAMAÑO_LEYENDA_IN_ONE_PANE_MODE,
                    Gravity.CENTER);
            TextView_titulo.setTextSize(TypedValue.COMPLEX_UNIT_DIP, TAMAÑO_TITULO_IN_ONE_PANE_MODE);
        }
        

        return rootView;

    }
    private void formatea_barras(ViewGroup rootView,
                                 int escala,
                                 int anchura,
                                 int padding_ImageView_left,
                                 int padding_ImageView_right,
                                 int padding_ImageView_Bottom,
                                 int padding_TextView_left,
                                 int padding_TextView_Top,
                                 int padding_TextView_Right,
                                 int padding_TextView_Bottom,
                                 int tamaño_letra,
                                 int gravity_leyenda
                                 ){


        LinearLayout.LayoutParams mLayoutParams_grafico= new LinearLayout.LayoutParams(getPixels(anchura+2), LinearLayout.LayoutParams.MATCH_PARENT);

        ImageView barra0= (ImageView) rootView.findViewById(R.id.barra0);
        LinearLayout grafico0= (LinearLayout) rootView.findViewById(R.id.grafico0);
        TextView mTextView0= (TextView) rootView.findViewById(R.id.texto0);
        LinearLayout.LayoutParams mLayoutParams_barra0= new LinearLayout.LayoutParams(getPixels(anchura), (int) (precio[0]*escala));
        barra0.setLayoutParams(mLayoutParams_barra0);
        barra0.setPadding(padding_ImageView_left, 0, padding_ImageView_right, padding_ImageView_Bottom);
        grafico0.setLayoutParams(mLayoutParams_grafico);
        grafico0.setGravity(Gravity.BOTTOM);
        mTextView0.setPadding(padding_TextView_left, padding_TextView_Top, padding_TextView_Right, padding_TextView_Bottom);
        mTextView0.setTextSize(tamaño_letra);
        mTextView0.setGravity(gravity_leyenda);
        
        ImageView barra1= (ImageView) rootView.findViewById(R.id.barra1);
        LinearLayout grafico1= (LinearLayout) rootView.findViewById(R.id.grafico1);
        TextView mTextView1= (TextView) rootView.findViewById(R.id.texto1);
        LinearLayout.LayoutParams mLayoutParams_barra1= new LinearLayout.LayoutParams(getPixels(anchura), (int) (precio[1]*escala));
        barra1.setLayoutParams(mLayoutParams_barra1);
        barra1.setPadding(padding_ImageView_left, 0, padding_ImageView_right, padding_ImageView_Bottom);
        grafico1.setLayoutParams(mLayoutParams_grafico);
        grafico1.setGravity(Gravity.BOTTOM);
        mTextView1.setPadding(padding_TextView_left, padding_TextView_Top, padding_TextView_Right, padding_TextView_Bottom);
        mTextView1.setTextSize(tamaño_letra);
        mTextView1.setGravity(gravity_leyenda);

        ImageView barra2= (ImageView) rootView.findViewById(R.id.barra2);
        LinearLayout grafico2= (LinearLayout) rootView.findViewById(R.id.grafico2);
        TextView mTextView2= (TextView) rootView.findViewById(R.id.texto2);
        LinearLayout.LayoutParams mLayoutParams_barra2= new LinearLayout.LayoutParams(getPixels(anchura), (int) (precio[2]*escala));
        barra2.setLayoutParams(mLayoutParams_barra2);
        barra2.setPadding(padding_ImageView_left, 0, padding_ImageView_right, padding_ImageView_Bottom);
        grafico2.setLayoutParams(mLayoutParams_grafico);
        grafico2.setGravity(Gravity.BOTTOM);
        mTextView2.setPadding(padding_TextView_left, padding_TextView_Top, padding_TextView_Right, padding_TextView_Bottom);
        mTextView2.setTextSize(tamaño_letra);
        mTextView2.setGravity(gravity_leyenda);

        ImageView barra3= (ImageView) rootView.findViewById(R.id.barra3);
        LinearLayout grafico3= (LinearLayout) rootView.findViewById(R.id.grafico3);
        TextView mTextView3= (TextView) rootView.findViewById(R.id.texto3);
        LinearLayout.LayoutParams mLayoutParams_barra3= new LinearLayout.LayoutParams(getPixels(anchura), (int) (precio[3]*escala));
        barra3.setLayoutParams(mLayoutParams_barra3);
        barra3.setPadding(padding_ImageView_left, 0, padding_ImageView_right, padding_ImageView_Bottom);
        grafico3.setLayoutParams(mLayoutParams_grafico);
        grafico3.setGravity(Gravity.BOTTOM);
        mTextView3.setPadding(padding_TextView_left, padding_TextView_Top, padding_TextView_Right, padding_TextView_Bottom);
        mTextView3.setTextSize(tamaño_letra);
        mTextView3.setGravity(gravity_leyenda);

        ImageView barra4= (ImageView) rootView.findViewById(R.id.barra4);
        LinearLayout grafico4= (LinearLayout) rootView.findViewById(R.id.grafico4);
        TextView mTextView4= (TextView) rootView.findViewById(R.id.texto4);
        LinearLayout.LayoutParams mLayoutParams_barra4= new LinearLayout.LayoutParams(getPixels(anchura), (int) (precio[4]*escala));
        barra4.setLayoutParams(mLayoutParams_barra4);
        barra4.setPadding(padding_ImageView_left, 0, padding_ImageView_right, padding_ImageView_Bottom);
        grafico4.setLayoutParams(mLayoutParams_grafico);
        grafico4.setGravity(Gravity.BOTTOM);
        mTextView4.setPadding(padding_TextView_left, padding_TextView_Top, padding_TextView_Right, padding_TextView_Bottom);
        mTextView4.setTextSize(tamaño_letra);
        mTextView4.setGravity(gravity_leyenda);

        ImageView barra5= (ImageView) rootView.findViewById(R.id.barra5);
        LinearLayout grafico5= (LinearLayout) rootView.findViewById(R.id.grafico5);
        TextView mTextView5= (TextView) rootView.findViewById(R.id.texto5);
        LinearLayout.LayoutParams mLayoutParams_barra5= new LinearLayout.LayoutParams(getPixels(anchura), (int) (precio[5]*escala));
        barra5.setLayoutParams(mLayoutParams_barra5);
        barra5.setPadding(padding_ImageView_left, 0, padding_ImageView_right, padding_ImageView_Bottom);
        grafico5.setLayoutParams(mLayoutParams_grafico);
        grafico5.setGravity(Gravity.BOTTOM);
        mTextView5.setPadding(padding_TextView_left, padding_TextView_Top, padding_TextView_Right, padding_TextView_Bottom);
        mTextView5.setTextSize(tamaño_letra);
        mTextView5.setGravity(gravity_leyenda);

        ImageView barra6= (ImageView) rootView.findViewById(R.id.barra6);
        LinearLayout grafico6= (LinearLayout) rootView.findViewById(R.id.grafico6);
        TextView mTextView6= (TextView) rootView.findViewById(R.id.texto6);
        LinearLayout.LayoutParams mLayoutParams_barra6= new LinearLayout.LayoutParams(getPixels(anchura), (int) (precio[6]*escala));
        barra6.setLayoutParams(mLayoutParams_barra6);
        barra6.setPadding(padding_ImageView_left, 0, padding_ImageView_right, padding_ImageView_Bottom);
        grafico6.setLayoutParams(mLayoutParams_grafico);
        grafico6.setGravity(Gravity.BOTTOM);
        mTextView6.setPadding(padding_TextView_left, padding_TextView_Top, padding_TextView_Right, padding_TextView_Bottom);
        mTextView6.setTextSize(tamaño_letra);
        mTextView6.setGravity(gravity_leyenda);

        ImageView barra7= (ImageView) rootView.findViewById(R.id.barra7);
        LinearLayout grafico7= (LinearLayout) rootView.findViewById(R.id.grafico7);
        TextView mTextView7= (TextView) rootView.findViewById(R.id.texto7);
        LinearLayout.LayoutParams mLayoutParams_barra7= new LinearLayout.LayoutParams(getPixels(anchura), (int) (precio[7]*escala));
        barra7.setLayoutParams(mLayoutParams_barra7);
        barra7.setPadding(padding_ImageView_left, 0, padding_ImageView_right, padding_ImageView_Bottom);
        grafico7.setLayoutParams(mLayoutParams_grafico);
        grafico7.setGravity(Gravity.BOTTOM);
        mTextView7.setPadding(padding_TextView_left, padding_TextView_Top, padding_TextView_Right, padding_TextView_Bottom);
        mTextView7.setTextSize(tamaño_letra);
        mTextView7.setGravity(gravity_leyenda);

        ImageView barra8= (ImageView) rootView.findViewById(R.id.barra8);
        LinearLayout grafico8= (LinearLayout) rootView.findViewById(R.id.grafico8);
        TextView mTextView8= (TextView) rootView.findViewById(R.id.texto8);
        LinearLayout.LayoutParams mLayoutParams_barra8= new LinearLayout.LayoutParams(getPixels(anchura), (int) (precio[8]*escala));
        barra8.setLayoutParams(mLayoutParams_barra8);
        barra8.setPadding(padding_ImageView_left, 0, padding_ImageView_right, padding_ImageView_Bottom);
        grafico8.setLayoutParams(mLayoutParams_grafico);
        grafico8.setGravity(Gravity.BOTTOM);
        mTextView8.setPadding(padding_TextView_left, padding_TextView_Top, padding_TextView_Right, padding_TextView_Bottom);
        mTextView8.setTextSize(tamaño_letra);
        mTextView8.setGravity(gravity_leyenda);

        ImageView barra9= (ImageView) rootView.findViewById(R.id.barra9);
        LinearLayout grafico9= (LinearLayout) rootView.findViewById(R.id.grafico9);
        TextView mTextView9= (TextView) rootView.findViewById(R.id.texto9);
        LinearLayout.LayoutParams mLayoutParams_barra9= new LinearLayout.LayoutParams(getPixels(anchura), (int) (precio[9]*escala));
        barra9.setLayoutParams(mLayoutParams_barra9);
        barra9.setPadding(padding_ImageView_left, 0, padding_ImageView_right, padding_ImageView_Bottom);
        grafico9.setLayoutParams(mLayoutParams_grafico);
        grafico9.setGravity(Gravity.BOTTOM);
        mTextView9.setPadding(padding_TextView_left, padding_TextView_Top, padding_TextView_Right, padding_TextView_Bottom);
        mTextView9.setTextSize(tamaño_letra);
        mTextView9.setGravity(gravity_leyenda);

        ImageView barra10= (ImageView) rootView.findViewById(R.id.barra10);
        LinearLayout grafico10= (LinearLayout) rootView.findViewById(R.id.grafico10);
        TextView mTextView10= (TextView) rootView.findViewById(R.id.texto10);
        LinearLayout.LayoutParams mLayoutParams_barra10= new LinearLayout.LayoutParams(getPixels(anchura), (int) (precio[10]*escala));
        barra10.setLayoutParams(mLayoutParams_barra10);
        barra10.setPadding(padding_ImageView_left, 0, padding_ImageView_right, padding_ImageView_Bottom);
        grafico10.setLayoutParams(mLayoutParams_grafico);
        grafico10.setGravity(Gravity.BOTTOM);
        mTextView10.setPadding(padding_TextView_left, padding_TextView_Top, padding_TextView_Right, padding_TextView_Bottom);
        mTextView10.setTextSize(tamaño_letra);
        mTextView10.setGravity(gravity_leyenda);

        ImageView barra11= (ImageView) rootView.findViewById(R.id.barra11);
        LinearLayout grafico11= (LinearLayout) rootView.findViewById(R.id.grafico11);
        TextView mTextView11= (TextView) rootView.findViewById(R.id.texto11);
        LinearLayout.LayoutParams mLayoutParams_barra11= new LinearLayout.LayoutParams(getPixels(anchura), (int) (precio[11]*escala));
        barra11.setLayoutParams(mLayoutParams_barra11);
        barra11.setPadding(padding_ImageView_left, 0, padding_ImageView_right, padding_ImageView_Bottom);
        grafico11.setLayoutParams(mLayoutParams_grafico);
        grafico11.setGravity(Gravity.BOTTOM);
        mTextView11.setPadding(padding_TextView_left, padding_TextView_Top, padding_TextView_Right, padding_TextView_Bottom);
        mTextView11.setTextSize(tamaño_letra);
        mTextView11.setGravity(gravity_leyenda);

        ImageView barra12= (ImageView) rootView.findViewById(R.id.barra12);
        LinearLayout grafico12= (LinearLayout) rootView.findViewById(R.id.grafico12);
        TextView mTextView12= (TextView) rootView.findViewById(R.id.texto12);
        LinearLayout.LayoutParams mLayoutParams_barra12= new LinearLayout.LayoutParams(getPixels(anchura), (int) (precio[12]*escala));
        barra12.setLayoutParams(mLayoutParams_barra12);
        barra12.setPadding(padding_ImageView_left, 0, padding_ImageView_right, padding_ImageView_Bottom);
        grafico12.setLayoutParams(mLayoutParams_grafico);
        grafico12.setGravity(Gravity.BOTTOM);
        mTextView12.setPadding(padding_TextView_left, padding_TextView_Top, padding_TextView_Right, padding_TextView_Bottom);
        mTextView12.setTextSize(tamaño_letra);
        mTextView12.setGravity(gravity_leyenda);

        ImageView barra13= (ImageView) rootView.findViewById(R.id.barra13);
        LinearLayout grafico13= (LinearLayout) rootView.findViewById(R.id.grafico13);
        TextView mTextView13= (TextView) rootView.findViewById(R.id.texto13);
        LinearLayout.LayoutParams mLayoutParams_barra13= new LinearLayout.LayoutParams(getPixels(anchura), (int) (precio[13]*escala));
        barra13.setLayoutParams(mLayoutParams_barra13);
        barra13.setPadding(padding_ImageView_left, 0, padding_ImageView_right, padding_ImageView_Bottom);
        grafico13.setLayoutParams(mLayoutParams_grafico);
        grafico13.setGravity(Gravity.BOTTOM);
        mTextView13.setPadding(padding_TextView_left, padding_TextView_Top, padding_TextView_Right, padding_TextView_Bottom);
        mTextView13.setTextSize(tamaño_letra);
        mTextView13.setGravity(gravity_leyenda);

        ImageView barra14= (ImageView) rootView.findViewById(R.id.barra14);
        LinearLayout grafico14= (LinearLayout) rootView.findViewById(R.id.grafico14);
        TextView mTextView14= (TextView) rootView.findViewById(R.id.texto14);
        LinearLayout.LayoutParams mLayoutParams_barra14= new LinearLayout.LayoutParams(getPixels(anchura), (int) (precio[14]*escala));
        barra14.setLayoutParams(mLayoutParams_barra14);
        barra14.setPadding(padding_ImageView_left, 0, padding_ImageView_right, padding_ImageView_Bottom);
        grafico14.setLayoutParams(mLayoutParams_grafico);
        grafico14.setGravity(Gravity.BOTTOM);
        mTextView14.setPadding(padding_TextView_left, padding_TextView_Top, padding_TextView_Right, padding_TextView_Bottom);
        mTextView14.setTextSize(tamaño_letra);
        mTextView14.setGravity(gravity_leyenda);

        ImageView barra15= (ImageView) rootView.findViewById(R.id.barra15);
        LinearLayout grafico15= (LinearLayout) rootView.findViewById(R.id.grafico15);
        TextView mTextView15= (TextView) rootView.findViewById(R.id.texto15);
        LinearLayout.LayoutParams mLayoutParams_barra15= new LinearLayout.LayoutParams(getPixels(anchura), (int) (precio[15]*escala));
        barra15.setLayoutParams(mLayoutParams_barra15);
        barra15.setPadding(padding_ImageView_left, 0, padding_ImageView_right, padding_ImageView_Bottom);
        grafico15.setLayoutParams(mLayoutParams_grafico);
        grafico15.setGravity(Gravity.BOTTOM);
        mTextView15.setPadding(padding_TextView_left, padding_TextView_Top, padding_TextView_Right, padding_TextView_Bottom);
        mTextView15.setTextSize(tamaño_letra);
        mTextView15.setGravity(gravity_leyenda);

        ImageView barra16= (ImageView) rootView.findViewById(R.id.barra16);
        LinearLayout grafico16= (LinearLayout) rootView.findViewById(R.id.grafico16);
        TextView mTextView16= (TextView) rootView.findViewById(R.id.texto16);
        LinearLayout.LayoutParams mLayoutParams_barra16= new LinearLayout.LayoutParams(getPixels(anchura), (int) (precio[16]*escala));
        barra16.setLayoutParams(mLayoutParams_barra16);
        barra16.setPadding(padding_ImageView_left, 0, padding_ImageView_right, padding_ImageView_Bottom);
        grafico16.setLayoutParams(mLayoutParams_grafico);
        grafico16.setGravity(Gravity.BOTTOM);
        mTextView16.setPadding(padding_TextView_left, padding_TextView_Top, padding_TextView_Right, padding_TextView_Bottom);
        mTextView16.setTextSize(tamaño_letra);
        mTextView16.setGravity(gravity_leyenda);

        ImageView barra17= (ImageView) rootView.findViewById(R.id.barra17);
        LinearLayout grafico17= (LinearLayout) rootView.findViewById(R.id.grafico17);
        TextView mTextView17= (TextView) rootView.findViewById(R.id.texto17);
        LinearLayout.LayoutParams mLayoutParams_barra17= new LinearLayout.LayoutParams(getPixels(anchura), (int) (precio[17]*escala));
        barra17.setLayoutParams(mLayoutParams_barra17);
        barra17.setPadding(padding_ImageView_left, 0, padding_ImageView_right, padding_ImageView_Bottom);
        grafico17.setLayoutParams(mLayoutParams_grafico);
        grafico17.setGravity(Gravity.BOTTOM);
        mTextView17.setPadding(padding_TextView_left, padding_TextView_Top, padding_TextView_Right, padding_TextView_Bottom);
        mTextView17.setTextSize(tamaño_letra);
        mTextView17.setGravity(gravity_leyenda);

        ImageView barra18= (ImageView) rootView.findViewById(R.id.barra18);
        LinearLayout grafico18= (LinearLayout) rootView.findViewById(R.id.grafico18);
        TextView mTextView18= (TextView) rootView.findViewById(R.id.texto18);
        LinearLayout.LayoutParams mLayoutParams_barra18= new LinearLayout.LayoutParams(getPixels(anchura), (int) (precio[18]*escala));
        barra18.setLayoutParams(mLayoutParams_barra18);
        barra18.setPadding(padding_ImageView_left, 0, padding_ImageView_right, padding_ImageView_Bottom);
        grafico18.setLayoutParams(mLayoutParams_grafico);
        grafico18.setGravity(Gravity.BOTTOM);
        mTextView18.setPadding(padding_TextView_left, padding_TextView_Top, padding_TextView_Right, padding_TextView_Bottom);
        mTextView18.setTextSize(tamaño_letra);
        mTextView18.setGravity(gravity_leyenda);

        ImageView barra19= (ImageView) rootView.findViewById(R.id.barra19);
        LinearLayout grafico19= (LinearLayout) rootView.findViewById(R.id.grafico19);
        TextView mTextView19= (TextView) rootView.findViewById(R.id.texto19);
        LinearLayout.LayoutParams mLayoutParams_barra19= new LinearLayout.LayoutParams(getPixels(anchura), (int) (precio[19]*escala));
        barra19.setLayoutParams(mLayoutParams_barra19);
        barra19.setPadding(padding_ImageView_left, 0, padding_ImageView_right, padding_ImageView_Bottom);
        grafico19.setLayoutParams(mLayoutParams_grafico);
        grafico19.setGravity(Gravity.BOTTOM);
        mTextView19.setPadding(padding_TextView_left, padding_TextView_Top, padding_TextView_Right, padding_TextView_Bottom);
        mTextView19.setTextSize(tamaño_letra);
        mTextView19.setGravity(gravity_leyenda);

        ImageView barra20= (ImageView) rootView.findViewById(R.id.barra20);
        LinearLayout grafico20= (LinearLayout) rootView.findViewById(R.id.grafico20);
        TextView mTextView20= (TextView) rootView.findViewById(R.id.texto20);
        LinearLayout.LayoutParams mLayoutParams_barra20= new LinearLayout.LayoutParams(getPixels(anchura), (int) (precio[20]*escala));
        barra20.setLayoutParams(mLayoutParams_barra20);
        barra20.setPadding(padding_ImageView_left, 0, padding_ImageView_right, padding_ImageView_Bottom);
        grafico20.setLayoutParams(mLayoutParams_grafico);
        grafico20.setGravity(Gravity.BOTTOM);
        mTextView20.setPadding(padding_TextView_left, padding_TextView_Top, padding_TextView_Right, padding_TextView_Bottom);
        mTextView20.setTextSize(tamaño_letra);
        mTextView20.setGravity(gravity_leyenda);

        ImageView barra21= (ImageView) rootView.findViewById(R.id.barra21);
        LinearLayout grafico21= (LinearLayout) rootView.findViewById(R.id.grafico21);
        TextView mTextView21= (TextView) rootView.findViewById(R.id.texto21);
        LinearLayout.LayoutParams mLayoutParams_barra21= new LinearLayout.LayoutParams(getPixels(anchura), (int) (precio[21]*escala));
        barra21.setLayoutParams(mLayoutParams_barra21);
        barra21.setPadding(padding_ImageView_left, 0, padding_ImageView_right, padding_ImageView_Bottom);
        grafico21.setLayoutParams(mLayoutParams_grafico);
        grafico21.setGravity(Gravity.BOTTOM);
        mTextView21.setPadding(padding_TextView_left, padding_TextView_Top, padding_TextView_Right, padding_TextView_Bottom);
        mTextView21.setTextSize(tamaño_letra);
        mTextView21.setGravity(gravity_leyenda);

        ImageView barra22= (ImageView) rootView.findViewById(R.id.barra22);
        LinearLayout grafico22= (LinearLayout) rootView.findViewById(R.id.grafico22);
        TextView mTextView22= (TextView) rootView.findViewById(R.id.texto22);
        LinearLayout.LayoutParams mLayoutParams_barra22= new LinearLayout.LayoutParams(getPixels(anchura), (int) (precio[22]*escala));
        barra22.setLayoutParams(mLayoutParams_barra22);
        barra22.setPadding(padding_ImageView_left, 0, padding_ImageView_right, padding_ImageView_Bottom);
        grafico22.setLayoutParams(mLayoutParams_grafico);
        grafico22.setGravity(Gravity.BOTTOM);
        mTextView22.setPadding(padding_TextView_left, padding_TextView_Top, padding_TextView_Right, padding_TextView_Bottom);
        mTextView22.setTextSize(tamaño_letra);
        mTextView22.setGravity(gravity_leyenda);

        ImageView barra23= (ImageView) rootView.findViewById(R.id.barra23);
        LinearLayout grafico23= (LinearLayout) rootView.findViewById(R.id.grafico23);
        TextView mTextView23= (TextView) rootView.findViewById(R.id.texto23);
        LinearLayout.LayoutParams mLayoutParams_barra23= new LinearLayout.LayoutParams(getPixels(anchura), (int) (precio[23]*escala));
        barra23.setLayoutParams(mLayoutParams_barra23);
        barra23.setPadding(padding_ImageView_left, 0, padding_ImageView_right, padding_ImageView_Bottom);
        grafico23.setLayoutParams(mLayoutParams_grafico);
        grafico23.setGravity(Gravity.BOTTOM);
        mTextView23.setPadding(padding_TextView_left, padding_TextView_Top, padding_TextView_Right, padding_TextView_Bottom);
        mTextView23.setTextSize(tamaño_letra);
        mTextView23.setGravity(gravity_leyenda);
    }
    private int getPixels(int dipValue){
        Resources r = getResources();
        return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, r.getDisplayMetrics());
    }
    private boolean isInTwoPaneMode() {

        return getActivity().findViewById(R.id.main_layout) == null;

    }
}