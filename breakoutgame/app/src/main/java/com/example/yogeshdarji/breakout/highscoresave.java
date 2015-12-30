package com.example.yogeshdarji.breakout;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

/**
 * Created by Megha Sharma on 11/28/2015.
 */

public class highscoresave extends AppCompatActivity {
String score=new String();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            score= extras.getString("score is");
        }
        setContentView(R.layout.activity_highscoresave);
        EditText scoretext = (EditText) findViewById(R.id.editText2);
        scoretext.setText(score);
        Button save=(Button)findViewById(R.id.button);
        if(save==null){
            Log.d("save is null","it is null");
        }
        else{
            Log.d("save is not null","it is not null");
        }
        if(save!=null) {
            Log.d("if","not null");
            save.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Log.d("cliking", "in onclick");
                                            savedetails();
                                        }
                                    }
            );
        }
        else{
            Log.d("else","it is null");
        }
    }

    public void savedetails() {
        Log.d("save details", "entered");
        EditText name = (EditText) findViewById(R.id.editText);
        String sname = name.getText().toString();
        Log.d("field data", "data is:" + sname);
        //String scores=score.toString();
        String scores=new String();
        scores=score;
        Context context = getApplicationContext();
        String filename = "scoreslistg.txt";
        FileOutputStream op;
        FileInputStream ip;
        BufferedReader datareader;
        int i=0;
        String [][] temp1=new String[1][1];
        String[][] data=new String[11][2];
        try{
            String line;
            op = openFileOutput(filename, Context.MODE_APPEND);
            FileInputStream in = openFileInput(filename);
            InputStreamReader inputStreamReader = new InputStreamReader(in);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            while ((line = bufferedReader.readLine()) != null) {
                Log.d("TAG1", line);
                data[i]=line.split("\t");
                i++;
                Log.d("i is",":"+i);
            }
            Log.d("i val is ",":"+i);
            data[i][0]=sname;
            data[i][1]=scores;
            Log.d("data[i][1]",":"+data[i][1]);
            for(int j=0;j<i;j++) {
                for (int l = j + 1; l < i+1; l++) {
                    int k = data[j][1].compareTo(data[l][1]);
                    if(k<0){
                        temp1[0]=data[j];
                       // temp2=data[j][0];
                        data[j]=data[l];
                        data[l]=temp1[0];
                        //data[l][0]=temp2;
                    }
                }
            }

            inputStreamReader.close();
            in.close();
            bufferedReader.close();

        }
        catch (Exception e1) {
            Log.d("TAG", e1.getMessage());
        }
        if(i==10){
            i=9;
        }
        try {
            op = openFileOutput(filename, Context.MODE_PRIVATE);
            for(int a=0;a<=i;a++) {
                Log.d("data[0][1]",":"+data[a][0]+data[a][1]);
                op.write(data[a][0].getBytes());
                op.write("\t".getBytes());
                op.write(data[a][1].getBytes());
                op.write("\n".getBytes());
            }
        }
        catch(Exception e){
            Log.d("exception in writing",e.getMessage());
        }
        Log.d("file data","data is:"+data[0][0]);
        String score="0";
        Intent intent = new Intent(highscoresave.this, scoreactivity.class);
        intent.putExtra("score is",score);
        startActivity(intent);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_highscoresave, menu);
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
