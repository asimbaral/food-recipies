package com.example.fridgefood;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class InfoDialog extends AppCompatDialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("What is NUTRICIPE?")
                .setMessage("NUTRICIPE is an easy to use mobile nutrition app that anybody can use "
                        + "to take one step closer to eating healthier. NUTRICIPE provides healthy,"
                        + " nutritious, and easy to prepare meals based on the ingredients on hand "
                        + "and allows for multiple customizable and healthy options, encouraging "
                        + "healthier and more fulfilling eating habits and overall lifestyle.")
                .setPositiveButton("Return", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        return builder.create();
    }
}
