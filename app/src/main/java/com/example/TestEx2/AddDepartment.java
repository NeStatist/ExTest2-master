package com.example.TestEx2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AddDepartment extends DialogFragment {

    public interface DepartmentDialogListener {
        void onDialogPositiveClick(Departments departments);
        void onDialogNegativeClick();
    }

    private DepartmentDialogListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (DepartmentDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString()
                    + " must implement DepartmentDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.department_dialog, null))
                .setMessage("Enter departments ")
                .setPositiveButton("Apply", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EditText departnmentName, departmentDescription, departmentNumOfCab;

                        departnmentName = getDialog().findViewById(R.id.addDepName);
                        departmentDescription = getDialog().findViewById(R.id.addDepartmentDescription);
                        departmentNumOfCab= getDialog().findViewById(R.id.addDepartmentNumOfCab);

                        Departments departments = new Departments();

                        departments.setDepName(departnmentName.getText().toString());
                        departments.setDepDescription(departmentDescription.getText().toString());
                        departments.setRoom(departmentNumOfCab.getText().toString());

                        listener.onDialogPositiveClick(departments);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listener.onDialogNegativeClick();
                    }
                });
        return builder.create();
    }
}
