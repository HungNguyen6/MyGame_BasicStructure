package com.example.basicstructure;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import java.io.BufferedInputStream;
import java.io.InputStream;

public class Element {
    Bitmap bitmap; //hinh anh
    int mX; //toa do x
    int mY; //toa do y
    public Element(Resources res, int x, int y)
    {
        InputStream is = res.openRawResource(+R.drawable.ic_icon_fuck);
        bitmap = BitmapFactory.decodeStream(new BufferedInputStream(is));
        mX=x-bitmap.getWidth()/2;
        mY=y-bitmap.getHeight()/2;
    }
    public Element(Resources res, int x, int y, int idHinh)
    {
        bitmap=BitmapFactory.decodeResource(res,idHinh);
        mX=x-bitmap.getWidth()/2;
        mY=y-bitmap.getHeight()/2;
    }
    public void doDraw(Canvas canvas)
    {
        canvas.drawBitmap(bitmap, mX,mY, null);
    }


}
