package com.sample.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class BookAppointmentActivity extends AppCompatActivity {
    TextView textView;
    EditText edFullName,edAddress,edPhnNo,edFees;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    Button dateButton,timeButton,btnBook,btnBack;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);
        textView=findViewById(R.id.textViewBMBAppTitle);
        edFullName=findViewById(R.id.editTextLTBFullName);
        edAddress=findViewById(R.id.editTextLTBAddress);
        edPhnNo=findViewById(R.id.editTextLTBPincode);
        edFees=findViewById(R.id.editTextLTBContact);
        dateButton=findViewById(R.id.buttonCartDate);
        timeButton=findViewById(R.id.buttonAppTime);
        btnBook=findViewById(R.id.buttonBookAPP);
        btnBack=findViewById(R.id.buttonAppBack);
        //datePicker
        initDatePicker();
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });
        //timePicker
        initTimePicker();
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerDialog.show();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BookAppointmentActivity.this, FindDoctorActivity.class));
            }
        });
        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Database db=new Database(getApplicationContext());
                SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username=sharedPreferences.getString("username","").toString();
                if(db.checkAppointmentExists(username,edFullName.getText().toString(),edAddress.getText().toString()))

            }
        });

        edFullName.setKeyListener(null);
        edAddress.setKeyListener(null);
        edPhnNo.setKeyListener(null);
        edFees.setKeyListener(null);
        //fetch the data
        Intent intent=getIntent();
        String title=intent.getStringExtra("text1");
        String fullName=intent.getStringExtra("text2");
        String address=intent.getStringExtra("text3");
        String contact=intent.getStringExtra("text4");
        String fees=intent.getStringExtra("text5");
        //display all data
        textView.setText(title);
        edFullName.setText(fullName);
        edAddress.setText(address);
        edPhnNo.setText(contact);
        edFees.setText("Cons Fees:"+fees+"/-");

    }
    //get Date
    public void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1 = i1 + 1;
                dateButton.setText(i2+"/"+i1+"/"+i);
            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_DARK;
        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis() + 86400000);
    }
    //get Time
    public void initTimePicker(){
        TimePickerDialog.OnTimeSetListener timeSetListener=new TimePickerDialog.OnTimeSetListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                i1 = i1 + 1;
                timeButton.setText(i+":"+i1);
            }
        };
        Calendar cal = Calendar.getInstance();
        int hrs=cal.get(Calendar.HOUR);
        int mins=cal.get(Calendar.MINUTE);

        int style=AlertDialog.THEME_HOLO_DARK;
        timePickerDialog=new TimePickerDialog(this,style,timeSetListener,hrs,mins,true);
    }
}