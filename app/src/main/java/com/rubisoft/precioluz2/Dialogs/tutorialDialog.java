package com.rubisoft.precioluz2.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.rubisoft.precioluz2.Activities.R;


public class tutorialDialog extends DialogFragment {

    String TAG_NO_MOSTRAR_TUTORIAL= "NO_MOSTRAR_TUTORIAL";


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final SharedPreferences prefs= PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());

        View checkBoxView = View.inflate(getActivity().getApplicationContext(), R.layout.checkbox, null);
        CheckBox mi_checkBox = (CheckBox) checkBoxView.findViewById(R.id.checkbox);
        mi_checkBox.setChecked(prefs.getBoolean(TAG_NO_MOSTRAR_TUTORIAL,false));

        TextView mi_textView= (TextView) checkBoxView.findViewById(R.id.texto);
        mi_textView.setText(R.string.texto_tutorial);
        mi_checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                SharedPreferences.Editor editor = prefs.edit();
                if(isChecked) {
                    editor.putBoolean(TAG_NO_MOSTRAR_TUTORIAL, true);
                }else{
                    editor.putBoolean(TAG_NO_MOSTRAR_TUTORIAL, false);
                }
                editor.apply();
                // Save to shared preferences
            }
        });

        final CharSequence[] items = {"nunca_mas"};
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(checkBoxView)
                .setTitle(R.string.app_name)
                .setPositiveButton(R.string.OK, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}