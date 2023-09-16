package com.fattouh.cars;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class ViewCarActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextInputEditText et_model,et_color,et_dpl,et_description;
    private ImageView car_img;
    private int car_id=-1;
    private DatabaseAccess db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_car);

        car_img=findViewById(R.id.view_car_iv);
        et_model=findViewById(R.id.view_car_tv_model);
        et_color=findViewById(R.id.view_car_tv_color);
        et_dpl=findViewById(R.id.view_car_tv_dpl);
        et_description=findViewById(R.id.view_car_tv_description);
        toolbar=findViewById(R.id.details_Toolbar);
        setSupportActionBar(toolbar);

        db=DatabaseAccess.getInstance(this);
        Intent intent=getIntent();
        car_id = intent.getIntExtra(MainActivity.CAR_KEY,-1);
        if (car_id==-1){
            //ADD
        }
        else {
            //VIEW
            db.open();
             Car c = db.getCar(car_id);
            db.close();
            if (c!=null){
                    fillCarToFields(c);
            }
        }
    }
    private void fillCarToFields(Car car){
        car_img.setImageURI(Uri.parse(car.getImage()));
        et_model.setText(car.getModel());
        et_color.setText(car.getColor());
        et_dpl.setText(String.valueOf(car.getDpl()));
        et_description.setText(car.getDescription());
    }
    private void disableFields(){
        car_img.setEnabled(false);
        et_model.setEnabled(false);
        et_color.setEnabled(false);
        et_dpl.setEnabled(false);
        et_description.setEnabled(false);
    }
    private void enableFields(){
        car_img.setEnabled(true);
        et_model.setEnabled(true);
        et_color.setEnabled(true);
        et_dpl.setEnabled(true);
        et_description.setEnabled(true);
    }
    private void clearFields(){
        car_img.setImageURI(null);
        et_model.setText("");
        et_color.setText("");
        et_dpl.setText("");
        et_description.setText("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_deatails,menu);
        MenuItem save=  menu.getItem(R.id.details_menu_save);
        MenuItem edit=  menu.getItem(R.id.details_menu_edit);
        MenuItem delete=  menu.getItem(R.id.details_menu_delete);
        if (car_id==-1){
            //ADD
            save.setVisible(true);
            edit.setVisible(false);
            delete.setVisible(false);

        }
        else {
            //VIEW
            save.setVisible(false);
            edit.setVisible(true);
            delete.setVisible(true);
        }
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId();
        if (itemId == R.id.details_menu_save) {
            return true;
        } else if (itemId == R.id.details_menu_edit) {
            return true;
        } else if (itemId == R.id.details_menu_delete) {
            return true;
        }
        return false;
    }
}