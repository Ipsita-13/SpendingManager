package com.example.spendingmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
TextView dailylimit,currentvalue,totalspent;
EditText limitdash,spendingvalue;
Button save,calculate,reset;
int ts=0;
String s="";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentvalue=findViewById(R.id.currentvalue);
        limitdash=findViewById(R.id.limitdash);
         save=findViewById(R.id.save);
         calculate=findViewById(R.id.calculate);
         spendingvalue=findViewById(R.id.enter_spending_value);
         totalspent=findViewById(R.id.total_spent);
         reset=findViewById(R.id.reset);
// Storing data into SharedPreferences

        if(currentvalue.getText().toString().equals(null))
        {
            Toast.makeText(this, "Set your daily limit", Toast.LENGTH_SHORT).show();
        }

         save.setOnClickListener(new View.OnClickListener()
         {
             @Override
             public void onClick(View view) {

                 currentvalue.setText(limitdash.getText().toString().trim());
                 if(totalspent.getText().toString().isEmpty())
                 {
                     totalspent.setText("0");
                 }

             }
         });
        //here the calculation will be done and notification will be sent

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                //the value entered will be set to total spent.

             ts=Integer.parseInt(totalspent.getText().toString())+
                     Integer.parseInt(spendingvalue.getText().toString());
             int cv=Integer.parseInt(currentvalue.getText().toString());
             int sp=Integer.parseInt(spendingvalue.getText().toString());
             if(ts>cv || sp>cv)
             {
                 Toast.makeText(MainActivity.this, "Overspending", Toast.LENGTH_SHORT).show();
             }


               else
                {
                    s=Integer.toString(ts);
                    totalspent.setText(s);
                }

            }
        });
        reset.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                totalspent.setText("0");
            }
        });





    }



    @Override
    protected void onResume() {
        super.onResume();

        // Fetching the stored data
        // from the SharedPreference
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);

        String s1 = sh.getString("currentvalue", "");
        String s2=sh.getString("totalspent","");

        // Setting the fetched data
        // in the EditTexts
        currentvalue.setText(s1);
        totalspent.setText(s2);
       // limitdash.setText(s1);

    }
    @Override
    protected void onPause() {
        super.onPause();

        // Creating a shared pref object
        // with a file name "MySharedPref"
        // in private mode
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();

        // write all the data entered by the user in SharedPreference and apply
        myEdit.putString("currentvalue", currentvalue.getText().toString());
        myEdit.putString("totalspent",totalspent.getText().toString());
       // myEdit.putString("currentvalue",currentvalue.getText().toString());
        myEdit.apply();
    }




}