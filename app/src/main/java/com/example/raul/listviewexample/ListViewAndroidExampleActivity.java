package com.example.raul.listviewexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListViewAndroidExampleActivity extends AppCompatActivity {

    ListView listView;
    City portugal, mexico, españa, toluca,praga,alaska;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_android_example);

        listView = (ListView) findViewById(R.id.list);
        mexico = new City("Mexico","4971871");
        portugal = new City("Lisbon","5060162");
        españa = new City("Madrid","4865871");
        toluca = new City("Toluca","3515302");
        praga = new City("Prague","3067696");
        alaska = new City("Alaska","5879092");

        City[] values = new City[] {mexico,portugal,españa,toluca,praga,alaska
        };

        ArrayAdapter<City> adapter = new ArrayAdapter<City>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);


        // Assign adapter to ListView
        listView.setAdapter(adapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // ListView Clicked item index
                int itemPosition     = position;

                // ListView Clicked item value
                City  itemValue    = (City) listView.getItemAtPosition(position);

                // Show Alert
                Toast.makeText(getApplicationContext(),
                         " Code : " + itemValue.getCode(), Toast.LENGTH_SHORT).show();

               Intent intent = new Intent(ListViewAndroidExampleActivity.this,WeatherInfo.class);
                intent.putExtra("Name", itemValue.getName());
                intent.putExtra("Code", itemValue.getCode());
                startActivity(intent);

            }
        });
    }
}
