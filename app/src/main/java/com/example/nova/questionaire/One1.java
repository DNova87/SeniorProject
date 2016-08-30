package com.example.nova.questionaire;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class One1 extends AppCompatActivity {
    static final int READ_BLOCK_SIZE = 20;
    String v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.one1);

        String MY_FILE_NAME = "questionairefile.txt";
        // Create a new output file stream
        try {
            FileOutputStream fileos = openFileOutput(MY_FILE_NAME, Context.MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Button button = (Button) findViewById(R.id.button);
        final RadioButton rate1 = (RadioButton) findViewById(R.id.radioButton);
        RadioButton rate2 = (RadioButton) findViewById(R.id.radioButton2);
        RadioButton rate3 = (RadioButton) findViewById(R.id.radioButton3);
        RadioButton rate4 = (RadioButton) findViewById(R.id.radioButton4);
        RadioButton rate5 = (RadioButton) findViewById(R.id.radioButton5);
        RadioButton rate6 = (RadioButton) findViewById(R.id.radioButton6);
        RadioButton rate7 = (RadioButton) findViewById(R.id.radioButton7);
        RadioButton rate8 = (RadioButton) findViewById(R.id.radioButton8);
        RadioButton rate9 = (RadioButton) findViewById(R.id.radioButton9);
        RadioButton rate10 = (RadioButton) findViewById(R.id.radioButton10);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                if (rate1.isChecked()) {
                    System.err.println("***************************************************");

                    v = "1";
                    WriteBtn(v);
                    Intent intent1 = new Intent(getApplicationContext(), One2.class);

                    startActivity(intent1);
                } else
                    System.err.println("---------------------------------------------------");
            }
        });
    }

    // write text to file
    public void WriteBtn(String v) {
        // add-write text into file
        try {
            FileOutputStream fileout = openFileOutput("questionairefile.txt", MODE_APPEND|MODE_WORLD_READABLE);
            OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
            outputWriter.write("Analyized" + v);
            outputWriter.close();

            //display file saved message
            Toast.makeText(getBaseContext(), "File saved successfully!",
                    Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
