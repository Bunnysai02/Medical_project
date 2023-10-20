package com.sample.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {
    private String[][] doctor_details1={{"Doctor Name : Sai kumar", "Hospital address : SR nagar","Exp : 4yrs","Mobile No:9550721611","600"},
            {"Doctor Name : Sandeep", "Hospital address : Ameerpet","Exp : 2yrs","Mobile No:7095858550","300"},
            {"Doctor Name : Sam", "Hospital address : Madhapur","Exp : 3yrs","Mobile No:9390494654","500"},
            {"Doctor Name : Bunny", "Hospital address : Durgam Cheruvu","Exp : 5yrs","Mobile No:830943911","800"},
            {"Doctor Name : Adhithya", "Hospital address : yusufguda","Exp : 3yrs","Mobile No:9550721611","400"},
    };
    private String[][] doctor_details2={{"Doctor Name : Sai kumar", "Hospital address : SR nagar","Exp : 4yrs","Mobile No:9550721611","600"},
            {"Doctor Name : Sandeep", "Hospital address : Ameerpet","Exp : 2yrs","Mobile No:7095858550","300"},
            {"Doctor Name : Sam", "Hospital address : Madhapur","Exp : 3yrs","Mobile No:9390494654","500"},
            {"Doctor Name : Bunny", "Hospital address : Durgam Cheruvu","Exp : 5yrs","Mobile No:830943911","800"},
            {"Doctor Name : Adhithya", "Hospital address : yusufguda","Exp : 3yrs","Mobile No:9550721611","400"},
    };
    private String[][] doctor_details3={{"Doctor Name : Sai kumar", "Hospital address : SR nagar","Exp : 4yrs","Mobile No:9550721611","600"},
            {"Doctor Name : Sandeep", "Hospital address : Ameerpet","Exp : 2yrs","Mobile No:7095858550","300"},
            {"Doctor Name : Sam", "Hospital address : Madhapur","Exp : 3yrs","Mobile No:9390494654","500"},
            {"Doctor Name : Bunny", "Hospital address : Durgam Cheruvu","Exp : 5yrs","Mobile No:830943911","800"},
            {"Doctor Name : Adhithya", "Hospital address : yusufguda","Exp : 3yrs","Mobile No:9550721611","400"},
    };
    private String[][] doctor_details4={{"Doctor Name : Sai kumar", "Hospital address : SR nagar","Exp : 4yrs","Mobile No:9550721611","600"},
            {"Doctor Name : Sandeep", "Hospital address : Ameerpet","Exp : 2yrs","Mobile No:7095858550","300"},
            {"Doctor Name : Sam", "Hospital address : Madhapur","Exp : 3yrs","Mobile No:9390494654","500"},
            {"Doctor Name : Bunny", "Hospital address : Durgam Cheruvu","Exp : 5yrs","Mobile No:830943911","800"},
            {"Doctor Name : Adhithya", "Hospital address : yusufguda","Exp : 3yrs","Mobile No:9550721611","400"},
    };
    private String[][] doctor_details5={{"Doctor Name : Sai kumar", "Hospital address : SR nagar","Exp : 4yrs","Mobile No:9550721611","600"},
            {"Doctor Name : Sandeep", "Hospital address : Ameerpet","Exp : 2yrs","Mobile No:7095858550","300"},
            {"Doctor Name : Sam", "Hospital address : Madhapur","Exp : 3yrs","Mobile No:9390494654","500"},
            {"Doctor Name : Bunny", "Hospital address : Durgam Cheruvu","Exp : 5yrs","Mobile No:830943911","800"},
            {"Doctor Name : Adhithya", "Hospital address : yusufguda","Exp : 3yrs","Mobile No:9550721611","400"},
    };
    TextView textView;
    Button btn;
    String [][] doctor_details={};
    ArrayList list;
    HashMap<String,String> item;
    SimpleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);
        textView=findViewById(R.id.textViewHATitle);
        btn=findViewById(R.id.buttonBMBack);
        Intent intent=getIntent();
        String title=intent.getStringExtra("title");
        textView.setText(title);

        if(title.compareTo("Family Physician")==0)
            doctor_details=doctor_details1;
        else
        if(title.compareTo("Dietician")==0)
            doctor_details=doctor_details2;
        else
        if(title.compareTo("Dentist")==0)
            doctor_details=doctor_details3;
        else
        if(title.compareTo("Surgeon")==0)
            doctor_details=doctor_details4;
        else
            doctor_details=doctor_details5;


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorDetailsActivity.this, FindDoctorActivity.class));
            }
        });
        list=new ArrayList();
        for(int i=0;i<doctor_details.length;i++){
            item=new HashMap<String,String>();
            item.put("line1",doctor_details[i][0]);
            item.put("line2",doctor_details[i][1]);
            item.put("line3",doctor_details[i][2]);
            item.put("line4",doctor_details[i][3]);
            item.put("line5","Cons Fees:"+doctor_details[i][4]+"/-");
            list.add(item);
        }
        adapter=new SimpleAdapter(this,list,
                R.layout.multi_line,new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        ListView lst=findViewById(R.id.listViewOD);
        lst.setAdapter(adapter);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent1=new Intent(DoctorDetailsActivity.this, BookAppointmentActivity.class);
                intent1.putExtra("text1",title);
                intent1.putExtra("text2",doctor_details[i][0]);
                intent1.putExtra("text3",doctor_details[i][1]);
                intent1.putExtra("text4",doctor_details[i][3]);
                intent1.putExtra("text5",doctor_details[i][4]);
                startActivity(intent1);
            }
        });

    }
}