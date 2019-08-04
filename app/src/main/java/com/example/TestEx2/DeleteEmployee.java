package com.example.TestEx2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DeleteEmployee extends DialogFragment {

    private DeleteDialogListenerEmpl listener;

    public interface DeleteDialogListenerEmpl {
        public void onPositiveClickDelete();
        public void onNegativeClickDelete();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (DeleteDialogListenerEmpl) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString()
                    + "DeleteDialogListenerEmpl");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.delete_employee, null))
                .setMessage("Delete employee")
                .setPositiveButton("Apply", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listener.onPositiveClickDelete();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listener.onNegativeClickDelete();
                    }
                });
        return builder.create();
    }
}
