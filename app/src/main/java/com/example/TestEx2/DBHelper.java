package com.example.TestEx2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 7;
    public static final String DATABASE_NAME = "DepartmentEmployee";

    private static final String CREATE_DEPARTMENTS = " CREATE TABLE " +
            PersonalInfo.DepartmentTable.TABLE_NAME +
            " (" +
            PersonalInfo.DepartmentTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            PersonalInfo.DepartmentTable.NAME + " TEXT, " +
            PersonalInfo.DepartmentTable.DESCRIPTION + " TEXT," +
            PersonalInfo.DepartmentTable.ROOM + " TEXT" +
            ")";

    private static final String CREATE_EMPLOYEE = " CREATE TABLE " +
            PersonalInfo.EmployeeTable.TABLE_NAME +
            " (" +
            PersonalInfo.EmployeeTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            PersonalInfo.EmployeeTable.DEPARTMENT_ID +  " INTEGER, " +
            PersonalInfo.EmployeeTable.FIRST_NAME + " TEXT, " +
            PersonalInfo.EmployeeTable.SURNAME + " TEXT," +
            PersonalInfo.EmployeeTable.YEAR + " TEXT," +
            PersonalInfo.EmployeeTable.POSITION + " TEXT " +
            ")";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DEPARTMENTS);
        db.execSQL(CREATE_EMPLOYEE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_DEPARTMENTS);
        db.execSQL(DELETE_EMPLOYEE);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    private static final String DELETE_DEPARTMENTS = "DROP TABLE IF EXISTS " +
            PersonalInfo.DepartmentTable.TABLE_NAME;

    private static final String DELETE_EMPLOYEE = "DROP TABLE IF EXISTS " +
            PersonalInfo.EmployeeTable.TABLE_NAME;


    public List<Departments> allDepartments() {
        List<Departments> departments = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + PersonalInfo.DepartmentTable.TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()) {
            do {
                Departments department = new Departments();
                department.setId(cursor.getInt(0));
                department.setDepName(cursor.getString(1));
                department.setDepDescription(cursor.getString(3));
                department.setRoom(cursor.getString(3));
                departments.add(department);
            } while (cursor.moveToNext());
        }

        db.close();
        return departments;
    }


    public String getDescriprionId(int id) {
        String description = "";
        String selectQuery = "SELECT " + PersonalInfo.DepartmentTable.DESCRIPTION + " FROM " +
                PersonalInfo.DepartmentTable.TABLE_NAME + " WHERE " +
                PersonalInfo.DepartmentTable._ID + " = " + id;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()) {
            description += cursor.getString(0);
        }

        db.close();
        return description;
    }


    public String getDepName(int id) {
        String depName = "";
        String selectQuery = "SELECT " + PersonalInfo.DepartmentTable.NAME + " FROM " +
                PersonalInfo.DepartmentTable.TABLE_NAME + " WHERE " +
                PersonalInfo.DepartmentTable._ID + " = " + id;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()) {
            depName += cursor.getString(0);
        }

        db.close();
        return depName;
    }


    public List<Employee> getEmployeeId(int id) {

        List<Employee> employees = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + PersonalInfo.EmployeeTable.TABLE_NAME + " WHERE " + PersonalInfo.EmployeeTable.DEPARTMENT_ID + " = " + id;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()) {
            do {
                Employee employee = new Employee();
                employee.setId(cursor.getInt(0));
                employee.setDepartmentId(cursor.getInt(1));
                employee.setFirstName(cursor.getString(2));
                employee.setLastName(cursor.getString(3));
                employee.setYear(cursor.getString(4));
                employee.setSex(cursor.getString(5));
                employees.add(employee);
            }while (cursor.moveToNext());
        }

        db.close();
        return employees;
    }


    public void addDepartment(Departments departments) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PersonalInfo.DepartmentTable.NAME, departments.getDepName());
        values.put(PersonalInfo.DepartmentTable.DESCRIPTION, departments.getDepDescription());
        values.put(PersonalInfo.DepartmentTable.ROOM, departments.getRoom());

        db.insert(PersonalInfo.DepartmentTable.TABLE_NAME, null, values);
        db.close();
    }

    public void addEmployee(Employee employee) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PersonalInfo.EmployeeTable.DEPARTMENT_ID, employee.getDepartmentId());
        values.put(PersonalInfo.EmployeeTable.FIRST_NAME, employee.getFirstName());
        values.put(PersonalInfo.EmployeeTable.SURNAME, employee.getLastName());
        values.put(PersonalInfo.EmployeeTable.YEAR, employee.getYear());
        values.put(PersonalInfo.EmployeeTable.POSITION, employee.getSex());

        db.insert(PersonalInfo.EmployeeTable.TABLE_NAME, null, values);
        db.close();
    }


    public void deleteDepartments(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(PersonalInfo.DepartmentTable.TABLE_NAME,
                PersonalInfo.DepartmentTable._ID + " = " + id, null);
        db.close();
    }

    public void deleteEmployees(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(PersonalInfo.EmployeeTable.TABLE_NAME,
                PersonalInfo.EmployeeTable._ID + " = " + id, null);
        db.close();
    }

}
