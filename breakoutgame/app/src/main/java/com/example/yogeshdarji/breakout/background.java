package com.example.yogeshdarji.breakout;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by Yogesh Darji on 11/28/2015.
 */


public class background extends View{

    public int intentflag=0;
    public int dx=4,dy=150;
    public int height;
    public int width;
    public int tapflag=0;
    public int brickstatus[][]=new int[3][9];
    public brick b[]=new brick[24];
    public paddle p=new paddle();
    public ball ba=new ball();
    public Timer timer;
    public TimerTask timerTask;
    public Sensor mySensor;
    public SensorManager SM;
   public Button startb;
    public long tStart,tEnd;
    public long timescore,brickscore=0;
  public long totalscore;
    // public paddle p;
    public background(Context context) {
        super(context);
        setWillNotDraw(false);
        setBackgroundColor(Color.GRAY);
        for ( int k=0; k<b.length; k++) {
            b[k]=new brick();

        }

        randomnumbergenerator();

        setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {

                if(tapflag==0) {
                    tapflag = 1;
                    tStart=System.currentTimeMillis();
                    Log.d("starttime",":"+tStart);
                    moveball();
                    invalidate();
                }
               else {
                    switch (event.getAction() & MotionEvent.ACTION_MASK) {
                        case MotionEvent.ACTION_DOWN:
                            break;
                        case MotionEvent.ACTION_MOVE:

                            if (event.getX() >= p.rect.left) {
                                p.rect.left = event.getX()+width/12;
                                p.rect.right = p.rect.left+width/6;
                        //        Log.d("new paddle coordinates", ":" + p.rect.left);
                            } else {
                                p.rect.left = event.getX()-width/12;
                                p.rect.right = p.rect.left+width/6;
                          //      Log.d("action down", "im here2");
                            }

                           break;


                    }
                }
                return true;

            }
        });
        timer=new Timer();
        timerTask =new TimerTask() {
            @Override
            public void run() {
                if(tapflag==1) {
                    moveball();
                    postInvalidate();
                }
            }
        };



        timer.schedule(timerTask,500,500);

    }




    public void setsize(int screenwidth, int screenheight) {
        height=screenwidth;
        width=screenheight;

        ba.x=width/2-width/24;
        ba.y=2*height/3-width/12;
        ba.angle=15;


        //setting paddle dimensions
        p.rect.left=width / 2 - width / 12;
        p.rect.top=2 * height / 3;
        p.rect.right=width / 2 + width / 12;
        p.rect.bottom= 2 * height / 3 + height / 24;

    }
public void scorecalculator()
{
    timescore=(tEnd-tStart)/10000;
    Log.d("StartTime",":"+tStart);
    Log.d("Endtime",":"+tEnd);
    Log.d("time elapsed",":"+(tEnd-tStart));
    Log.d("timscore",":"+timescore);
    totalscore=1000-timescore+brickscore;
}

    public void moveball(){

        ba.x=ba.x+Math.cos(Math.toRadians(ba.angle))+dx;
        ba.y=ba.y+Math.sin(Math.toRadians(ba.angle))-dy;
        ba.rect.top=(float)ba.y;
        ba.rect.left=(float)ba.x;
        ba.rect.right=(float)(ba.x + width / 12);
        ba.rect.bottom=(float)(ba.y+width/12);

        }

    public void collisiondetection()
    {
        //checking for collision with paddle
        if(RectF.intersects(ba.rect,p.rect))
        {
            dy=-dy;
        }

        //checking for collision with bricks
        for(int i = 0; i<24; i++){
            if (b[i].visibility==1){

                if(RectF.intersects(b[i].rect, ba.rect)) {
                         brickscore=brickscore+10;
                    if(b[i].bcolor==1)
                    {
                        b[i].visibility=0;
                    }
                    else
                    {
                        b[i].bcolor=b[i].bcolor-1;
                    }
                    dy=-dy;
                    break;
                }
            }
        }
    }



    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    protected void onDraw(Canvas C)
    {
        String score;

        collisiondetection();
        int j=0;

        Paint paint=new Paint();
        Paint paint1=new Paint();
        paint1.setStrokeWidth(1);
        paint1.setColor(Color.BLACK);
        int left=0,right=0,top=0,bottom=0;


        for(int i=0;i<24;i++)
        {
            if(b[i].bcolor==1)
                paint.setColor(Color.WHITE);
            if(b[i].bcolor==2)
                paint.setColor(Color.BLUE);
            if(b[i].bcolor==3)
                paint.setColor(Color.GREEN);
            if(b[i].bcolor==4)
                paint.setColor(Color.RED);
            if(b[i].bcolor==5)
                paint.setColor(Color.BLACK);

            if(i>=0&&i<=8)
            {
                if(b[i].visibility==1) {
                    C.drawRect(left, top, left + width / 9, height / 12, paint1);
                    C.drawRect(left + 1, top + 1, -1 + left + width / 9, -1 + height / 12, paint);
                }
                    b[i].rect.left = left;
                    b[i].rect.right = left + width / 9;
                    b[i].rect.top = top;
                    b[i].rect.bottom = top + height / 12;
                    left = left + width / 9;

            }
            else if(i>=9&&i<=16)
            {
                if(i==9)
                {
                    top=height/12;
                    left=0;
                }
                if (b[i].visibility==1) {
                    C.drawRect(left, top, left + width / 8, height / 6, paint1);
                    C.drawRect(left + 1, top + 1, left - 1 + width / 8, -1 + height / 6, paint);
                }
                //setting up values of brick objects
                b[i].rect.left=left;
                b[i].rect.right=left+width/8;
                b[i].rect.top=top;
                b[i].rect.bottom=top+height/12;

                left=left+width/8;
            }
            else
            {
                if(i==17)
                {
                    top=top+(height/12);
                    left=0;
                }
                if (b[i].visibility==1) {
                    C.drawRect(left, top, left + width / 7, height / 4, paint1);
                    C.drawRect(left + 1, top + 1, -1 + left + width / 7, -1 + height / 4, paint);
                }
                b[i].rect.left=left;
                b[i].rect.right=left+width/7;
                b[i].rect.top=top;
                b[i].rect.bottom=top+height/12;

                left=left+width/7;
            }


        }



        paint.setColor(Color.BLACK);
        C.drawRect(p.rect.left, p.rect.top, p.rect.right, p.rect.bottom, paint);




        paint.setColor(Color.WHITE);
        C.drawOval((float) ba.x, (float) ba.y, (float) (ba.x + width / 12), (float) (ba.y + width / 12), paint);
        ba.rect.top=(float)ba.y;
        ba.rect.left=(float)ba.x;
        ba.rect.right=(float)(ba.x + width / 12);
        ba.rect.bottom=(float)(ba.y+width/12);

        if(tapflag==0)
        {
            paint.setColor(Color.BLACK);
            paint.setStrokeWidth(5);
            paint.setTextSize(100);
            paint.setTypeface(Typeface.DEFAULT_BOLD);
            C.drawText("tap to play",width/4,3*height/4,paint);
        }
        if(tapflag==1) {

            if (ba.x < 0 || ba.x > width||ba.y>2*height/3) {
                 scorecalculator();

                score=String.valueOf(totalscore);
                scorecheck();
                if(intentflag==1) {
                    Intent intent = new Intent(this.getContext(), highscoresave.class);

                    intent.putExtra("score is", score);
                                       this.getContext().startActivity(intent);
                }
                else{
                    Intent intent = new Intent(this.getContext(), scoreactivity.class);

                    intent.putExtra("score is", score);

                    this.getContext().startActivity(intent);
                }
            }
        }

    }

    public void scorecheck(){
        Log.d("score check","background");
            String filename = "scoreslistg.txt";
            FileOutputStream op;
            FileInputStream ip;
            BufferedReader datareader;
            int i=0;
            String temp1,temp2;
            String[][] data=new String[11][2];
            String score=new String();
            score=String.valueOf(totalscore);
            try{
                String line;
                op = this.getContext().openFileOutput(filename, Context.MODE_APPEND);
                FileInputStream in = this.getContext().openFileInput(filename);
                InputStreamReader inputStreamReader = new InputStreamReader(in);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                while ((line = bufferedReader.readLine()) != null) {
                    Log.d("TAG1", line);
                    data[i]=line.split("\t");
                    i++;
                    Log.d("i is",":"+i);
                }
                Log.d("i val is ",":"+i);

                Log.d("data[i][1]",":"+data[i][1]);
                if(i<10){
                    intentflag=1;
                }
                else {
                    Log.d("data[9][1]=",""+data[9][1]);
                    int n = score.compareTo(data[9][1]);
                    if (n > 0) {
                        intentflag = 1;
                    }
                }
                inputStreamReader.close();
                in.close();
                bufferedReader.close();

            }
            catch (Exception e1) {
                Log.d("TAG", e1.getMessage());
            }
    }

       public void randomnumbergenerator()
    {
        int minimum=1;
        int maximum=5;
        int num;
        Random rm=new Random();
        int z=0;
        for(int i=0;i<3;i++)
        {
            for (int j=0;j<9;j++)
            {
                num=rm.nextInt(maximum-minimum+1)+minimum;
                if(j-1>=0)
                {

                    while(brickstatus[i][j-1]==num)        {
                        num=rm.nextInt(maximum-minimum+1)+minimum;
                    }
                }
                brickstatus[i][j]=num;
                if((i==1&&j>7)||(i==2&&j>6)) {
                    continue;
                }
                b[z].bcolor=brickstatus[i][j];
                z++;

            }
        }

    }



}
