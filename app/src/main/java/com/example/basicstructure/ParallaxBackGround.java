package com.example.basicstructure;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class ParallaxBackGround {
    private int toadonenX=0;
    private int toadonenY=0;
    private Bitmap hinhnenchuyendong1;
    private Bitmap hinhnenchuyendong2;

    public ParallaxBackGround(Resources resources){
        hinhnenchuyendong1= BitmapFactory.decodeResource(resources,R.drawable.ic_anhnenchuyendong);
        hinhnenchuyendong2= BitmapFactory.decodeResource(resources,R.drawable.ic_anhnenchuyendong1);
    }
    public void doDrawRunning(Canvas canvas) {
        //giam toa do de dich chuyen cho nen1
        toadonenX = toadonenX - 1;
        //giam toa do de dich chuyen cho nen2
        toadonenY = toadonenY - 4;
        // tinh do lech cho hinh 2 (xem hinh minh hoa)
        int toadonen1_phu_X = hinhnenchuyendong1.getWidth() - (-toadonenX);
        //da di chuyen het thi quay lai tu dau
        if (toadonen1_phu_X <= 0) {
            toadonenX = 0;
            // chi can ve 1 tam
            canvas.drawBitmap(hinhnenchuyendong1,0, 0, null);
        } else {
            // ve 1 tam lech va tam 2 noi duoi theo
            canvas.drawBitmap(hinhnenchuyendong1, toadonenX, 0, null);
            canvas.drawBitmap(hinhnenchuyendong1, toadonen1_phu_X, 0, null);
        }
        int toadonen2_phu_X = hinhnenchuyendong2.getWidth() - (-toadonenY);
        if (toadonen2_phu_X <= 0) {
            toadonenY = 0;
            canvas.drawBitmap(hinhnenchuyendong2, toadonenY, 0, null);
        } else {
            canvas.drawBitmap(hinhnenchuyendong2, toadonenY, 0, null);
            canvas.drawBitmap(hinhnenchuyendong2, toadonen2_phu_X, 0, null);
        }
    }

}
