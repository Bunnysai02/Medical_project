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

public class BuyMedicineActivity extends AppCompatActivity {
    private String[][] packages =
            {
                    {"Uprise D3 1000IU capsule","","","","50"},
                    {"Dolo 360 Tablet","","","","30"},
                    {"Crocin 360 advance Tablet","","","","50"},
                    {"Calcium 1MG + Vitamin D3","","","","30"},
                    {"Feronia XT-Tablet","","","","130"}
            };
    private String[] package_details={
            "Building and keeping the bones and teeth Strong\n" +
                    "Reducing stress and muscular pains",
            "Relive fever and bring down a higher temperature\n" +
                    "Suitable for people with heart condition and high blood pressure",
            "provide a warm and comforting feeling during the sore throat ",
            "promotes mobility and flexibility of joints",
            "Helps to reduce iron deficiency due to cronic blood loss or low intake of iron "
    };
    ArrayList list;
    HashMap<String,String> item;
    SimpleAdapter adapter;
    ListView lst;
    Button btnBack,btnGotoCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine);
        lst=findViewById(R.id.listViewBM);
        btnBack=findViewById(R.id.buttonBMBack);
        btnGotoCart=findViewById(R.id.buttonBMGotoCart);
        btnGotoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyMedicineActivity.this,CartBuyMedicineActivity.class));
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyMedicineActivity.this, HomeActivity.class));
            }
        });
        list=new ArrayList();
        for(int i=0;i<packages.length;i++){
            item=new HashMap<String,String>();
            item.put("line1",packages[i][0]);
            item.put("line2",packages[i][1]);
            item.put("line3",packages[i][2]);
            item.put("line4",packages[i][3]);
            item.put("line5","Total cost:"+packages[i][4]+"/-");
            list.add(item);
        }
        adapter=new SimpleAdapter(this,list,
                R.layout.multi_line,new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        lst.setAdapter(adapter);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent1=new Intent(BuyMedicineActivity.this, BuyMedicineDetailsActivity.class);
                intent1.putExtra("text1",packages[i][0]);
                intent1.putExtra("text2",package_details[i]);
                intent1.putExtra("text3",packages[i][4]);
                startActivity(intent1);
            }
        });


    }
}