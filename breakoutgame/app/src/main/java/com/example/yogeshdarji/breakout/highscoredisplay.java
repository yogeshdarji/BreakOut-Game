package com.example.yogeshdarji.breakout;

/**
 * Created by Yogesh Darji on 11/28/2015.
 */


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class highscoredisplay extends AppCompatActivity {
 Button playb,exitb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscoredisplay);
        listgenerator();

        playb=(Button) findViewById(R.id.playb);
        exitb=(Button) findViewById(R.id.exitb);
        playb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(highscoredisplay.this,MainActivity.class);
                startActivity(intent);
            }
        });
        exitb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    public void onPause(){
        super.onPause();
        finish();
    }
    public void listgenerator()
    {
        String filename="scoreslistg.txt";
        FileOutputStream op;
        FileInputStream ip;
        BufferedReader datareader;
        int i=0;
        Log.d("im in list"," generator");
        final List name=new ArrayList<String>();
        final ListView displaylist=(ListView)findViewById(R.id.displaylist);
        try{
            String line;
            final  String columns[][]=new String[100][2];
            FileInputStream in = openFileInput(filename);
            InputStreamReader inputStreamReader = new InputStreamReader(in);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            while ((line = bufferedReader.readLine()) != null) {
                Log.d("entered while","loop");
                columns[i] = line.split("\t");
                String s;
                s = columns[i][0] + " " + columns[i][1];
                name.add(s);
                i++;
            }
            in.close();
            inputStreamReader.close();
            bufferedReader.close();
        }
        catch (Exception e1)
        {
            Log.d("TAG",e1.toString());
        }


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                name );

        displaylist.setAdapter(arrayAdapter);





       /* ArrayAdapter<String> adapter = new ArrayAdapter<String> (this, android.R.layout.simple_list_item_1, android.R.id.text1,name) {
            @Override
            public View getView(int position,View convertView, ViewGroup parent) {

                View view = super.getView(position, convertView, parent);

                TextView text1 = (TextView) view.findViewById(android.R.id.text1);

                text1.setText((String)name.get(position));

                return view;
            }

        };*/



    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_highscoredisplay, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
