package com.sample.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LabTestDetailsActivity extends AppCompatActivity {
    TextView tvPackageName,tvTotalCost;
    EditText editDetails;
    Button btnAddCart,btnBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_details);
        tvPackageName=findViewById(R.id.textViewBMPackagename);
        tvTotalCost=findViewById(R.id.textViewCostTotalCost);
        editDetails=findViewById(R.id.editTextBMDTextMultiLine);
        editDetails.setKeyListener(null);

        Intent intent=getIntent();
        tvPackageName.setText(intent.getStringExtra("text1"));
        editDetails.setText(intent.getStringExtra("text2"));
        tvTotalCost.setText("Total Cost : "+intent.getStringExtra("text3")+"/-");
        btnAddCart=findViewById(R.id.buttonBMCartCheckout);
        btnBack=findViewById(R.id.buttonCartBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LabTestDetailsActivity.this, LabTestActivity.class));
            }
        });
        btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username=sharedPreferences.getString("username","").toString();
                String product=tvPackageName.getText().toString();
                float price=Float.parseFloat(intent.getStringExtra("text3").toString());
                Database db=new Database(getApplicationContext());

                if(db.checkCart(username,product)==1){
                    Toast.makeText(LabTestDetailsActivity.this, "Product already added", Toast.LENGTH_SHORT).show();
                }else{
                    db.addCart(username,product,price,"lab");
                    Toast.makeText(LabTestDetailsActivity.this, "Record Inserted to Cart", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LabTestDetailsActivity.this, LabTestActivity.class));
                }

            }
        });


    }
}