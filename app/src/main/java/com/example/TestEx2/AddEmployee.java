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

public class AddEmployee extends DialogFragment {

    public interface EmployeeDialogListener {
       void onDialogPositiveClick(Employee employee);
       void onDialogNegativeClick();
    }

    private AddEmployee.EmployeeDialogListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (AddEmployee.EmployeeDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString()
                    + " must implement EmployeeDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.employee_dialog, null))
                .setMessage("Enter employee ")
                .setPositiveButton("Apply", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EditText employeeFirstName, employeeLastName, employeeYear, employeeSex;

                        employeeFirstName = getDialog().findViewById(R.id.addFirstName);
                        employeeLastName = getDialog().findViewById(R.id.addLastName);
                        employeeYear= getDialog().findViewById(R.id.addYear);
                        employeeSex= getDialog().findViewById(R.id.addPosition);

                        Employee employee = new Employee();

                        employee.setFirstName(employeeFirstName.getText().toString());
                        employee.setLastName(employeeLastName.getText().toString());
                        employee.setYear(employeeYear.getText().toString());
                        employee.setSex(employeeSex.getText().toString());

                        listener.onDialogPositiveClick(employee);
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

