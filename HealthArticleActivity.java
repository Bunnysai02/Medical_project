package com.sample.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class HealthArticleActivity extends AppCompatActivity {
    private String[][] health_details=
            {
                    {"Walking Daily","","","","Click More details"},
                    {"Home care of Covid-19","","","","Click More details"},
                    {"Stop Smoking","","","","Click More details"},
                    {"Menstrual Cramps","","","","Click More details"},
                    {"Health gut","","","","Click More details"}
            };
    private int[] images={
            R.drawable.health1,
            R.drawable.health2,
            R.drawable.health3,
            R.drawable.health4,
            R.drawable.health5
    };
    HashMap<String,String > hashMap;
    ArrayList list;
    SimpleAdapter adapter;
    Button btnBack;
    ListView lst;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_article);
        btnBack=findViewById(R.id.buttonHABack);
        lst=findViewById(R.id.listViewHA);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HealthArticleActivity.this, HomeActivity.class));
            }
        });

        list=new ArrayList();
        for (int i=0;i<health_details.length;i++){
            hashMap=new HashMap<String ,String>();
            hashMap.put("line1",health_details[i][0]);
            hashMap.put("line2",health_details[i][1]);
            hashMap.put("line3",health_details[i][2]);
            hashMap.put("line4",health_details[i][3]);
            hashMap.put("line5",health_details[i][4]);
            list.add(hashMap);
        }
        adapter=new SimpleAdapter(this,list,
                R.layout.multi_line,new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});

        lst.setAdapter(adapter);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(HealthArticleActivity.this, HealthArticleDetailsActivity.class);
                intent.putExtra("text1",health_details[i][0]);
                intent.putExtra("text2",images[i]);
                startActivity(intent);

            }
        });
    }
}