package com.example.yogeshdarji.breakout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by Yogesh Darji on 11/28/2015.
 */


public class MainActivity extends AppCompatActivity {
    public int brick[][]=new int[3][9];
    int screenwidth=0;
    int screenheight=0;
    //paddle p;
    timercount t=new timercount();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(new myview(this));





        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        screenwidth=metrics.heightPixels;
        screenheight=metrics.widthPixels;
        final background b=new background(this);
        b.setsize(screenwidth, screenheight);

        //  p=new paddle(this);
        //p.setsize(screenwidth,screenheight);
        setContentView(b);
        //p.invalidate();
        t.starttime();



    }





    //background b=new background(this);
    //b.setsize(screenwidth,screenheight);
    //b.invalidate();





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //to the item id
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
