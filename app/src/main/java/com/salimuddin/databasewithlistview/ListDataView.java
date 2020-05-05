package com.salimuddin.databasewithlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListDataView extends AppCompatActivity {

    //Declare Valuables
    private ListView listView;
    private MyDatabasehelper myDatabasehelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data_view);

        //Create object of MyDataBasehelper class
        listView = findViewById(R.id.listViewId);
        myDatabasehelper = new MyDatabasehelper(this);

        //Load loadData method
        loadData();
    }

    //LoadData Method
    private void loadData(){

        //Create ArrayList
        ArrayList<String> listData = new ArrayList<>();

        //call MyDatabasehelper class showAllData Method and return cursor

        Cursor cursor = myDatabasehelper.showAllData();

        if (cursor.getCount() == 0){
            Toast.makeText(getApplicationContext(), "No Data Found", Toast.LENGTH_SHORT).show();
        }
        else {
            while (cursor.moveToNext()){
                listData.add(cursor.getString(0)+" \t "+cursor.getString(1));
            }
        }

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.list_item, R.id.textView, listData);
        listView.setAdapter(adapter);


        //Set listener on listView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String selecteditem = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Selected Value: "+selecteditem, Toast.LENGTH_SHORT).show();

            }
        });

    }

}
