package com.example.TestEx2;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

public class Departments {

    private int id;
    private String depName;
    private String depDescription;
    private String room;

    public Departments(String depName, String depDescription, String room) {
        this.depName = depName;
        this.depDescription = depDescription;
        this.room = room;
    }

    public Departments(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getDepDescription() {
        return depDescription;
    }

    public void setDepDescription(String depDescription) {
        this.depDescription = depDescription;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }


    public static DiffUtil.ItemCallback<Departments> DIFF_CALLBACK = new DiffUtil.ItemCallback<Departments>() {
        @Override
        public boolean areItemsTheSame(@NonNull Departments item1, @NonNull Departments item2) {
            return item1.id == item2.id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Departments item1, @NonNull Departments item2) {
            return item1.equals(item2);
        }
    };

    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }

        Departments department = (Departments) obj;

        return department.id == this.id && department.depName.equals(this.depName) && department.depDescription.equals(this.depDescription) && department.room.equals(this.room);
    }

}
