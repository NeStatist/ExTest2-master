package com.example.TestEx2;

import android.provider.BaseColumns;

public class PersonalInfo {

    private PersonalInfo() {}

    public static class DepartmentTable implements BaseColumns {
        public static final String TABLE_NAME = "departments";
        public static final String NAME = "name";
        public static final String DESCRIPTION = "description";
        public static final String ROOM = "numroom";
    }

    public static class EmployeeTable implements BaseColumns {
        public static final String TABLE_NAME = "employee";
        public static final String DEPARTMENT_ID = "departmentId";
        public static final String FIRST_NAME = "firstname";
        public static final String SURNAME = "surname";
        public static final String YEAR = "year";
        public static final String POSITION = "position";

    }
}
