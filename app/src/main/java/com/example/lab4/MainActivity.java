package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn_add;
    Button btn_view;
    EditText et_name;
    EditText et_age;

    ListView lv_StudentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_add=findViewById(R.id.btn_add);
        btn_view=findViewById(R.id.btn_view);
        et_name=findViewById(R.id.et_name);
        et_age=findViewById(R.id.et_age);
        lv_StudentList=findViewById(R.id.lv_StudentList);

        DataBaseHelper dataBaseHelper =new DataBaseHelper(MainActivity.this);
        ShowStudentsOnListView(dataBaseHelper);




        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StudentMod studentMod;

                try{
                    studentMod = new StudentMod(-1,et_name.getText().toString(),Integer.parseInt(et_age.getText().toString()));
                    Toast.makeText(MainActivity.this,studentMod.toString(),Toast.LENGTH_SHORT).show();

                }
                catch(Exception e){
                    Toast.makeText(MainActivity.this ,"Enter valid input",Toast.LENGTH_SHORT).show();
                    studentMod = new StudentMod(-1,"Erorr",0);
                }


                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
                boolean b = dataBaseHelper.addOne(studentMod);
                Toast.makeText(MainActivity.this, "SUCCESS= "+ b, Toast.LENGTH_SHORT).show();

                ShowStudentsOnListView(dataBaseHelper);




            }
        });

        btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
                ShowStudentsOnListView(dataBaseHelper);

            }
        });


}

    private void ShowStudentsOnListView(DataBaseHelper dataBaseHelper) {
        ArrayAdapter  studentArrayAdapter = new ArrayAdapter<StudentMod>(MainActivity.this, android.R.layout.simple_list_item_1, dataBaseHelper.getEveryone());
        lv_StudentList.setAdapter(studentArrayAdapter);
    }

}