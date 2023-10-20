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

public class LabTestActivity extends AppCompatActivity {
    private String [][] packages={
            {"package 1 : Full Body Checkup","","","","999"},
            {"package 2 : Blood Glucose pasting","","","","299"},
            {"package 3 : Covid-19 AntiBody-IgB","","","","899"},
            {"package 4 : Thyroid Check","","","","499"},
            {"package 5 : Immunity Check","","","","699"}
    };
    private String[] package_details={
            "Full Body CheckUp:\n" +
                    " Complete Hemogram\n" +
                    " HbA1C\n" +
                    " Kidney Function Test\n" +
                    " LDH Lactate Dehydrogenase, Serum\n" +
                    " Lipid Profile\n" +
                    " Liver Function Test\n",
            "Blood Glucose pasting",
            "Covid-19 AntiBody-IgB",
            "Thyroid Profile-Total(T3, T4 & TSH ultra-sensitive)",
            "Complete Hemogram\n" +
                    "CRP (C Reactive Protein) Quantitative, Serum\n" +
                    " Iron Studies\n" +
                    "Kidney Function Test\n" +
                    "Vitamin-D total-25 Hydroxy\n" +
                    "Liver Function Test\n" +
                    "Lipid Profile"
    };
    HashMap<String ,String> hashMap;
    ArrayList list;
    SimpleAdapter adapter;
    Button btnGoToCart,btnBack;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test);

        btnGoToCart=findViewById(R.id.buttonBMGotoCart);
        btnBack=findViewById(R.id.buttonBMBack);
        listView=findViewById(R.id.listViewBM);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LabTestActivity.this, HomeActivity.class));
            }
        });
        list=new ArrayList();
        for(int i=0;i<packages.length;i++){
            hashMap=new HashMap<String,String>();
            hashMap.put("line1",packages[i][0]);
            hashMap.put("line2",packages[i][1]);
            hashMap.put("line3",packages[i][2]);
            hashMap.put("line4",packages[i][3]);
            hashMap.put("line5","Total Cost:"+packages[i][4]+"/-");
            list.add(hashMap);
        }
        adapter=new SimpleAdapter(this,list,
                R.layout.multi_line,new String[] {"line1","line2","line3","line4","line5"},
                new int[] {R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(LabTestActivity.this, LabTestDetailsActivity.class);
                intent.putExtra("text1",packages[i][0]);
                intent.putExtra("text2",package_details[i]);
                intent.putExtra("text3",packages[i][4]);
                startActivity(intent);
            }
        });
        btnGoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LabTestActivity.this, CartLabActivity.class));

            }
        });
    }

}