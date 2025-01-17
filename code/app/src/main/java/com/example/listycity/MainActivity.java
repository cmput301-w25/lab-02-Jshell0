package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;

    EditText edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EdgeToEdge.enable(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        cityList = findViewById(R.id.city_list);
        edit = findViewById(R.id.plain_text_input);


        String []cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        Button button = findViewById(R.id.add_button);
        // https://www.geeksforgeeks.org/how-to-dynamically-add-elements-to-a-listview-in-android/  --> Used to learn how to use buttons to add to a listview
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_input = edit.getText().toString();
                if (!user_input.isEmpty()) {
                    dataList.add(user_input);
                    // notify tells the adapter that something changed, and will display the new change
                    cityAdapter.notifyDataSetChanged();
                }
            }
        });

        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            // if the user clicks on a city, the app will delete that city
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                dataList.remove(i);
                cityAdapter.notifyDataSetChanged();
            }
        });
    }
}