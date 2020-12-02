package com.example.basicstructure;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;

import java.util.Random;

public class KeThu {
    int x;
    int y;
    Bitmap bitmap;
    int[] tocdo={10};
    int[] manghinh = {R.drawable.dogwearglass};
    int songaunhien;

    public KeThu(Resources resources,int hei,int wit){
        Random rand=new Random();
        songaunhien=rand.nextInt(3);
        Log.d("nn",""+songaunhien);
        this.x=wit;//x tu phai
        int a=0+(int)(Math.random()*((hei-0)+1));
        this.y=a;
        bitmap= BitmapFactory.decodeResource(resources,manghinh[songaunhien]);
    }
    public KeThu(Resources res,int x,int y, int hinh)
    {
        this.x=x;
        this.y=y;
        bitmap=BitmapFactory.decodeResource(res, hinh);
    }
    public void doDraw(Canvas canvas)
    {
        canvas.drawBitmap(bitmap, x,y, null);
        x-=tocdo[songaunhien]; //tru vi chay phai sang trai
    }
    public void setXY(int x,int y)
    {
        this.x=x;
        this.y=y;
    }

    public int getWidth()
    {
        return bitmap.getWidth();
    }
    public int getHeight()
    {
        return bitmap.getHeight();
    }
    public int gettamX()
    {
        return x+(bitmap.getWidth()/2);
    }
    public int gettamY()
    {
        return y+(bitmap.getHeight()/2);
    }

}
