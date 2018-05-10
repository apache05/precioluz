package com.ruben.precioluz2.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ruben.precioluz2.Activities.R;
import com.ruben.precioluz2.utils.utils;


public class ErrorFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2="param2";

    // TODO: Rename and change types of parameters
    private String texto_error;
    private int num_fragment;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param2 Parameter 2.
     * @return A new instance of fragment ErrorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ErrorFragment newInstance( int param2) {
        ErrorFragment fragment = new ErrorFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, "Error \n no hay conexión a Internet");
        args.putInt(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ErrorFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            texto_error = getArguments().getString(ARG_PARAM1);
            num_fragment= getArguments().getInt(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the animacion for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_error, container, false);
        try {
            TextView TextView_error = rootView.findViewById(R.id.texto_error);
            TextView_error.setText(texto_error);

            ImageView flechita_derecha = rootView.findViewById(R.id.flechita_derecha_error);
            ImageView flechita_izquierda = rootView.findViewById(R.id.flechita_izquierda_error);

            if (this.num_fragment == 1) {
                flechita_derecha.setVisibility(ImageView.INVISIBLE);
                flechita_izquierda.setVisibility(ImageView.VISIBLE);
            } else {
                flechita_derecha.setVisibility(ImageView.VISIBLE);
                flechita_izquierda.setVisibility(ImageView.INVISIBLE);
            }
        }catch (Exception e){
            new utils.AsyncTask_Guardar_Error().execute(new android.support.v4.util.Pair<>(getContext(), e.toString()));
        }
        return rootView;
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }


}
