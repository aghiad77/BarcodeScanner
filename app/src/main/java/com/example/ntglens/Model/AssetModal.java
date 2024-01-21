package com.example.ntglens.Model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "asset_table")
public class AssetModal {


    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "barCode")
    private String barCode;

    @ColumnInfo(name = "Description")
    private String description;

    @ColumnInfo(name = "Category")
    private String category;

    public AssetModal(){}

    public AssetModal(int id , String barCode, String Description, String Category) {
        this.id = id;
        this.barCode = barCode;
        this.description = Description;
        this.category = Category;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String Description) {
        this.description = Description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String Category) {
        this.category = Category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
