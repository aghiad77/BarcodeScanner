package com.example.ntglens.Interface;

import com.example.ntglens.Model.AssetModal;
import com.example.ntglens.Model.ViewModal;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.RawQuery;
import androidx.room.Update;
import androidx.sqlite.db.SupportSQLiteQuery;

@Dao
public interface AssetDao {

    // add data to database.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(AssetModal model);

    @Query("SELECT * FROM asset_table")
    List<AssetModal> getAssets();

    @Query("SELECT * FROM asset_table WHERE barCode=:code ")
    AssetModal getAsset(String code);

}
