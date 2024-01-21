package com.example.ntglens.Repository;

import android.app.Application;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.ntglens.Database.AssetDatabase;
import com.example.ntglens.Interface.AssetDao;
import com.example.ntglens.Model.AssetModal;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class AssetRepository {

    private AssetDatabase database;
    private AssetDao dao;
    private static List<AssetModal> assets;

    public AssetRepository(Application application) {
        database = AssetDatabase.getInstance(application);
        dao = database.Dao();
        //assets = dao.getAssets();
    }

    public void insert(AssetModal model) {
        new InsertAssetsAsyncTask(dao).execute(model);
    }

    public List<AssetModal> getAssets() {
        new getAssetsAsyncTask(dao).execute();
        return assets;
    }

    private static class InsertAssetsAsyncTask extends AsyncTask<AssetModal, Void, Void> {
        private AssetDao dao;

        private InsertAssetsAsyncTask(AssetDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(AssetModal... model) {
            // below line is use to insert our modal in dao.
            dao.insert(model[0]);
            return null;
        }
    }

    private static class getAssetsAsyncTask extends AsyncTask<Void, Void, List<AssetModal>> {

        private AssetDao dao;

        private getAssetsAsyncTask(AssetDao dao) {
            this.dao = dao;
        }

        @Override
        protected List<AssetModal> doInBackground(Void... voids) {
            List<AssetModal> taskList = dao.getAssets();
            return taskList;
        }

        @Override
        protected void onPostExecute(List<AssetModal> tasks) {
            super.onPostExecute(tasks);
            assets = tasks;
        }
    }

}
