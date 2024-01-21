package com.example.ntglens.Database;

import android.content.Context;
import android.os.AsyncTask;
import com.example.ntglens.Interface.AssetDao;
import com.example.ntglens.Model.AssetModal;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {AssetModal.class}, version = 1, exportSchema = false)
public abstract  class AssetDatabase extends RoomDatabase{

    // below line is to create instance
    private static AssetDatabase instance;

    public abstract AssetDao Dao();

    // on below line we are getting instance for our database.
    public static synchronized AssetDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AssetDatabase.class, "asset_table")
                            .fallbackToDestructiveMigration()
                            .addCallback(roomCallback)
                            .build();
        }
        return instance;
    }

    // below line is to create a callback for our room database.
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    // we are creating an async task class to perform task in background.
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        PopulateDbAsyncTask(AssetDatabase instance) {
            AssetDao assetDao = instance.Dao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
