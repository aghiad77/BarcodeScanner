package com.example.ntglens.Model;

import android.app.Application;
import com.example.ntglens.Repository.AssetRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class ViewModal extends AndroidViewModel {

    // creating a new variable for course repository.
    private AssetRepository repository;
    private final List<AssetModal> assets;

    // constructor for our view modal.
    public ViewModal(Application application) {
        super(application);
        repository = new AssetRepository(application);
        assets = repository.getAssets();
    }

    // method is use to insert the data to our repository.
    public void insert(AssetModal model) {
        repository.insert(model);
    }

    public List<AssetModal> getAssets() {
        return assets;
    }

}
