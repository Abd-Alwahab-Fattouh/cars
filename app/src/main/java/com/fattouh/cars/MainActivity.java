package com.fattouh.cars;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final int ADD_CAR_REQ_CODE = 1;
    private static final int EDIT_CAR_REQ_CODE = 1;
    public static final String CAR_KEY = "car_key";
    private RecyclerView rv;
    private FloatingActionButton fab;
    private Toolbar toolbar ;
    private RecyclerViewAdapter adapter ;
    private DatabaseAccess db;
    ActivityResultLauncher<Intent> launcher;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        rv=findViewById(R.id.main_rv);
        fab=findViewById(R.id.main_fab);

        DatabaseAccess.getInstance(this);
        db.open();

        ArrayList<Car>cars= db.getAllCars();
        db.close();
        adapter=new RecyclerViewAdapter(cars);
        rv.setAdapter(adapter);
        RecyclerView.LayoutManager lm = new GridLayoutManager(this,2);
        rv.setLayoutManager(lm);
        rv.setHasFixedSize(true);

        launcher=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == MainActivity.ADD_CAR_REQ_CODE) {
                            Intent intent = result.getData();
                            // Handle the Intent
                        }
                    }
                });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launcher.launch(new Intent(getBaseContext(), ViewCarActivity.class));
            }
        });


    }







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        SearchView searchView=(SearchView) menu.getItem(R.id.main_search).getActionView();
        //to add button to search ok
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            //use when cheaked the button
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            //use whe change text
            public boolean onQueryTextChange(String newText) {
                return false;
            }

        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            //use when cheaked the close button
            public boolean onClose() {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}



