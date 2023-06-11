package com.example.tp_crud_sqlite_room;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName ="pays")
public class PaysEntity implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name="text")
    private String text;

    @ColumnInfo(name="capital")
    private String capital;

    @ColumnInfo(name = "habitants")
    private Float habitants;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public Float getHabitants() {
        return habitants;
    }

    public void setHabitants(Float habitants) {
        this.habitants = habitants;
    }
}
