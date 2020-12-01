package com.example.basicstructure;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import androidx.annotation.NonNull;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    // khoi tao bien thread
    private MainThread thread;
    Element element;
//    int chamX;
//    int chamY;
//    Bitmap bitmap;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        element = new Element(getResources(),(int)event.getX(),(int)event.getY());
//        return super.onTouchEvent(event);
        if (element==null){
            element=new Element(getResources(),(int)event.getX(),(int)event.getY());
            Log.d("abc","khoi tao dau tien");
            return true;
        }else {
            element.mX=(int)event.getX()-element.bitmap.getWidth()/2;
            element.mY=(int)event.getY()-element.bitmap.getHeight()/2;
        }
        return true;
//        chamX=(int)event.getX() - bitmap.getHeight()/2;
//        chamY=(int)event.getY() - bitmap.getWidth()/2;


    }

    public GamePanel(Context context) {
        super(context);
        getHolder().addCallback(this);
        setFocusable(true);
        // khoi tao bien thread
        thread = new MainThread(getHolder(),this);
//        InputStream is = getResources().openRawResource(+ R.drawable.ic_icon_fuck);
//        bitmap = BitmapFactory.decodeStream(new BufferedInputStream(is));
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (element!=null){
            element.doDraw(canvas);
        }
//        canvas.drawBitmap(bitmap, chamX,chamY,null);
    }

    @Override
    public void surfaceCreated( SurfaceHolder holder) {
        // gan trang thai cho thread va kich cho thread chay
        thread.setRunning(true);
        thread.start();

    }

    @Override
    public void surfaceChanged( SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed( SurfaceHolder holder) {
        // huy thread
        if (thread.isAlive()){
            thread.setRunning(false);
        }

    }


}
    