package com.example.tp_crud_sqlite_room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PaysDao {

    @Insert()
    void insert(PaysEntity entity);

    @Delete
    void delete(PaysEntity entity);

    @Query("UPDATE pays SET text= :sText,capital=:cText,habitants=:nText  where ID=:sID")
    void update(int sID, String sText, String cText, Float nText);

    @Query("SELECT * FROM pays")
    List<PaysEntity> getAll();
}
