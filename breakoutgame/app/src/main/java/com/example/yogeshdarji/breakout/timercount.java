package com.example.yogeshdarji.breakout;

import android.util.Log;

/**
 * Created by Yogesh Darji on 11/28/2015.
 */
public class timercount {
    long tEnd,tStart,time;

    public void starttime(){
        tStart=System.currentTimeMillis();
    }
    public void endtime(){
        tEnd=System.currentTimeMillis();
    }
    public void calculate(){
        time=(tEnd-tStart)/1000;
        Log.d("Timer elapsed", "time=" + time);
    }
}
