package com.example.yogeshdarji.breakout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * Created by Megha Sharma on 11/28/2015.
 */


public class scoreactivity extends AppCompatActivity {
  public Button playb,highscoreb,exitb;
    public TextView scorelabel;
    public EditText displayscore;
    public int flag=0;
    public String score=new String();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreactivity);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            flag = 1;
             score= extras.getString("score is");
        }
        displayscore = (EditText) findViewById(R.id.displayscore);
        displayscore.setText(score);
        scorelabel = (TextView) findViewById(R.id.scorelabel);

        if(score.contentEquals("0"))
    {
       displayscore.setVisibility(View.INVISIBLE);
        scorelabel.setVisibility(View.INVISIBLE);
    }
        else
        {
            displayscore.setVisibility(View.VISIBLE);
            scorelabel.setVisibility(View.VISIBLE);
        }
        playb=(Button) findViewById(R.id.playb);
        highscoreb=(Button) findViewById(R.id.highscoreb);
        exitb=(Button) findViewById(R.id.exitb);
        playb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(scoreactivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        exitb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             finish();
                System.exit(0);
            }
        });





        highscoreb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(scoreactivity.this, highscoredisplay.class);
                startActivity(intent);


            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scoreactivity, menu);
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
