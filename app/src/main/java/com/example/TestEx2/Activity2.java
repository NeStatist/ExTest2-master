package com.example.TestEx2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Activity2 extends AppCompatActivity  implements AddEmployee.EmployeeDialogListener,
        RecyclerEmployeeAdapter.onEmployeeListener, DeleteEmployee.DeleteDialogListenerEmpl {

    private DBHelper dbHelper;
    private List<Employee> empList = new ArrayList<Employee>();
    private RecyclerEmployeeAdapter adapter;
    private int depId;
    private TextView depDesc, depName;
    private int delete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        depId = getIntent().getIntExtra("departmentId", 0);

        dbHelper = new DBHelper(this);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewSecond);
        depDesc = findViewById(R.id.depDescription);
        depName = findViewById(R.id.depNameEmpl);

        depDesc.setText(dbHelper.getDescriprionId(depId));
        depName.setText(dbHelper.getDepName(depId));

        empList.addAll(dbHelper.getEmployeeId(depId));
        adapter = new RecyclerEmployeeAdapter(this);
        adapter.submitList(empList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.fabEmployee);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                employeeDialog();
            }
        });
    }

    private void employeeDialog() {
        DialogFragment dialog = new AddEmployee();
        dialog.show(getSupportFragmentManager(), "Dialog");
    }

    @Override
    public void onDialogPositiveClick(Employee employee) {
        employee.setDepartmentId(depId);
        dbHelper.addEmployee(employee);
        adapter.submitList(dbHelper.getEmployeeId(depId));
    }

    private void employeeDialogDelete() {
        DialogFragment dialog = new DeleteEmployee();
        dialog.show(getSupportFragmentManager(), "Delete employee");
    }

    @Override
    public void onDialogNegativeClick() {

    }

    @Override
    public void onEmployeeClick(int id) {

    }

    @Override
    public void onEmployeeLongClick(int id) {
        delete = id;
        employeeDialogDelete();
    }


    @Override
    public void onPositiveClickDelete() {

          List<Employee> employees = dbHelper.getEmployeeId(depId);
        dbHelper.deleteEmployees(employees.get(delete).getId());
        adapter.submitList(dbHelper.getEmployeeId(depId));
    }

    @Override
    public void onNegativeClickDelete() {

    }
}
