package com.example.ntglens;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ntglens.Database.DatabaseClient;
import com.example.ntglens.Model.AssetModal;
import com.example.ntglens.Model.ViewModal;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.List;

public class AssetActivity extends AppCompatActivity {

    private ImageView  scan;
    private TextView text;
    private EditText searchText;
    private ViewModal viewmodal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initial();
        loadData();

        searchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    getBarCode(searchText.getText().toString());
                    return true;
                }
                return false;
            }
        });

        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(AssetActivity.this);
                intentIntegrator.setPrompt("Scan a barcode or QR Code");
                intentIntegrator.setOrientationLocked(true);
                intentIntegrator.initiateScan();
            }
        });
    }

    public void initial(){
        text = findViewById(R.id.or);
        scan = findViewById(R.id.scan);
        searchText = findViewById(R.id.edit_search);

        viewmodal = new ViewModal(getApplication());

    }

    public void loadData(){
        if(viewmodal.getAssets()==null){
           for(AssetModal model : Data.assetsList){
               viewmodal.insert(model);
           }
        }
    }

    public void getBarCode(String code) {

        class GetSavedBarCode extends AsyncTask<Void, Void, AssetModal> {
            @Override
            protected AssetModal doInBackground(Void... voids) {
                AssetModal asset = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .Dao()
                        .getAsset(code);
                return asset;
            }

            @Override
            protected void onPostExecute(AssetModal tasks) {
                super.onPostExecute(tasks);
                if(tasks!=null) {
                    String result = "Barcode: " + tasks.getBarCode() + " , " + "Description: " + tasks.getDescription() + " , " + "Category: " + tasks.getCategory();
                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                }else
                    Toast.makeText(getApplicationContext(),"BarCode is not exist",Toast.LENGTH_LONG).show();
            }
        }

        GetSavedBarCode savedTasks = new GetSavedBarCode();
        savedTasks.execute();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (intentResult != null) {
            if (intentResult.getContents() == null) {
                Toast.makeText(getBaseContext(), "Cancelled", Toast.LENGTH_SHORT).show();
            } else {
                getBarCode(intentResult.getContents());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}