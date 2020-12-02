package com.example.basicstructure;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Bullet {
    private int X;
    private int Y;
    private Bitmap bitmap;
    private int tocdo=20;

    public Bullet(Resources resources,int X,int Y){
        this.X=X;
        this.Y=Y;
        bitmap= BitmapFactory.decodeResource(resources,R.drawable.bulet);
    }
    public Bullet(Resources resources,int X,int Y,int viendan){
        this.X=X;
        this.Y=Y;
        bitmap=BitmapFactory.decodeResource(resources,viendan);
    }
    public void doDraw(Canvas canvas){
        canvas.drawBitmap(bitmap,X,Y,null);
        X+=tocdo;

    }
    public void setXY(int X,int Y){
        this.X=X;
        this.Y=Y;
    }
    public void setTocDo(int X){
        this.tocdo=X;
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
        return X+(bitmap.getWidth()/2);
    }
    public int gettamY()
    {
        return Y+(bitmap.getHeight()/2);
    }

}
