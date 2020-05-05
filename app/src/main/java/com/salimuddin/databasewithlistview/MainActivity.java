package com.salimuddin.databasewithlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Declare Valuables
    private EditText idEditText, nameEditText;
    private Button saveBtn, showBtn, updateBtn, deleteBtn;
    MyDatabasehelper myDatabasehelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create object of MyDataBasehelper class
        myDatabasehelper = new MyDatabasehelper(this);
        SQLiteDatabase sqLiteDatabase = myDatabasehelper.getWritableDatabase();

        //initialize Valuables
        idEditText = findViewById(R.id.idEditTextId);
        nameEditText = findViewById(R.id.nameEditTextId);
        saveBtn = findViewById(R.id.saveBtnId);
        showBtn = findViewById(R.id.showBtnId);
        updateBtn = findViewById(R.id.updateBtnId);
        deleteBtn = findViewById(R.id.updateBtnId);

        //Set Linear to Buttons
        saveBtn.setOnClickListener(this);
        showBtn.setOnClickListener(this);
        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        //Converting EditText to String
        String id = idEditText.getText().toString();
        String name = nameEditText.getText().toString();

        if (v.getId()==R.id.saveBtnId){

            //Save Button

            if (id.equals("") && name.equals("")){
                Toast.makeText(getApplicationContext(), "Please Enter All Data", Toast.LENGTH_SHORT).show();
            }
            else {
                long rowid = myDatabasehelper.saveData(id, name);

                if (rowid > -1){
                    Toast.makeText(getApplicationContext(), "Data Saved", Toast.LENGTH_SHORT).show();
                    idEditText.setText("");
                    nameEditText.setText("");
                }
                else {
                    Toast.makeText(getApplicationContext(), "Data is not Saved", Toast.LENGTH_SHORT).show();
                }
            }

        }
        else if (v.getId()==R.id.showBtnId){

            //Show button

            Intent intent = new Intent(MainActivity.this, ListDataView.class);
            startActivity(intent);

        }
        else if (v.getId()==R.id.updateBtnId){

            //Update button

            Boolean isupdated = myDatabasehelper.updateData(id, name);

            if (isupdated == true){
                Toast.makeText(getApplicationContext(), "Data is updated", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(getApplicationContext(), "Data not is updated", Toast.LENGTH_SHORT).show();
            }

        }
        else if (v.getId()==R.id.deleteBtnId){

            //Delete Button

            int value = myDatabasehelper.deleteData(id);

            if (value<0){
                Toast.makeText(getApplicationContext(), "Data is Deleted", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(getApplicationContext(), "Data is Deleted", Toast.LENGTH_SHORT).show();
            }

        }

    }
}
