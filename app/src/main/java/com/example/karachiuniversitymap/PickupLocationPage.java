package com.example.karachiuniversitymap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Arrays;

public class PickupLocationPage extends AppCompatActivity {
    ArrayList<String> location;
    ListView listView;
    pickupLocationAdapter adp;
    ImageButton backbtn;
    EditText searchtxt;
    int textlength=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickup_location_page);
        String[] locationarr = getResources().getStringArray(R.array.LocationArray);
        listView = findViewById(R.id.pickupList);
        String[] arr  = getResources().getStringArray(R.array.LocationArray);
        location = new ArrayList<>(Arrays.asList(arr));
        backbtn = findViewById(R.id.backbtn);
        searchtxt = findViewById(R.id.searchtxt);

        // for Adapter showing locations
        adp = new pickupLocationAdapter(PickupLocationPage.this,location);
        listView.setAdapter(adp);



        // back button listener
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(PickupLocationPage.this,MainActivity.class);
                startActivity(intent);
            }
        });

        // for Intent on listview

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int index = listView.getPositionForView(view);
                    Intent intent = new Intent(PickupLocationPage.this, THEMAP.class);
                    intent.putExtra("location", index);
                    startActivity(intent);
                }
        });



        // search functionality in listview

        searchtxt.addTextChangedListener(new TextWatcher()
        {
            public void afterTextChanged(Editable s) {}
            public void beforeTextChanged(CharSequence s,int start, int count, int after) {}
            public void onTextChanged(CharSequence s,int start, int before, int count)
            {
                textlength = searchtxt.getText().length();
                location.clear();
                for (String value : locationarr) {
                    if (textlength <= value.length()) {
                        if (searchtxt.getText().toString().equalsIgnoreCase(
                                (String)
                                        value.subSequence(0,
                                                textlength))) {
                            location.add(value);
                        }
                    }
                }
                adp = new pickupLocationAdapter(PickupLocationPage.this,location);
                listView.setAdapter(adp);
            }
        });
    }
}