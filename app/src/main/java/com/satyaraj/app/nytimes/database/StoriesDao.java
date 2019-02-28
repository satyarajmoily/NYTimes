package com.satyaraj.app.nytimes.database;


import com.satyaraj.app.nytimes.pojo.Stories;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface StoriesDao {
    @Query("SELECT * FROM stories")
    List<Stories> getAll();

    @Insert
    void insertAll(List<Stories> storiesList);

    @Insert
    void insertStory(Stories stories);

    @Query("DELETE FROM stories")
    void delete();

}