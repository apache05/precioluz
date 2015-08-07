package com.example.ruben.precioluz;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class OpcionesFragment extends Fragment {

    public static OpcionesFragment newInstance() {

        // Instantiate a new fragment
        OpcionesFragment fragment = new OpcionesFragment();

        // Save the parameters
        Bundle bundle = new Bundle();

        fragment.setArguments(bundle);
        fragment.setRetainInstance(true);

        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.opciones_layout, container, false);
        rootView.setBackgroundColor(getResources().getColor(R.color.Tan));
        return rootView;

    }
}