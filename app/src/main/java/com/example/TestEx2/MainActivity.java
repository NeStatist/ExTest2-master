package com.example.TestEx2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AddDepartment.DepartmentDialogListener,
        RecyclerDepartmentAdapter.onDepartmentListener, DeleteDepartment.DeleteDialogListenerDepart {

    private DBHelper dbHelper;
    private List<Departments> departmentList = new ArrayList<Departments>();
    private RecyclerDepartmentAdapter adapter;
    private int delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this);

        RecyclerView recyclerView = findViewById(R.id.recyclerMain);

        departmentList.addAll(dbHelper.allDepartments());
        adapter = new RecyclerDepartmentAdapter(this);
        adapter.submitList(departmentList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.fabDep);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                departmentDialog();
            }
        });
    }

    private void departmentDialog() {
        DialogFragment dialog = new AddDepartment();
        dialog.show(getSupportFragmentManager(), "Dialog");
    }


    @Override
    public void onDialogPositiveClick(Departments departments) {
        dbHelper.addDepartment(departments);
        adapter.submitList(dbHelper.allDepartments());
    }

    @Override
    public void onDepartmentClick(int id) {

        Intent intent = new Intent(this, Activity2.class);
        List<Departments> departments = dbHelper.allDepartments();
        intent.putExtra("departmentId", departments.get(id).getId());
        startActivity(intent);

    }

    private void departmentDeleteDialog() {
        DialogFragment dialog = new DeleteDepartment();
        dialog.show(getSupportFragmentManager(), "Delete department");
    }


    @Override
    public void onDepartmentLongClick(int id) {
        delete = id;
        departmentDeleteDialog();

    }


    @Override
    public void onPositiveClickDeletDep() {

        List<Departments> departments = dbHelper.allDepartments();
        dbHelper.deleteDepartments(departments.get(delete).getId());
        adapter.submitList(dbHelper.allDepartments());
    }

    @Override
    public void OnNegativeClickDeleteDEp() {

    }


    @Override
    public void onDialogNegativeClick() {

    }

}
