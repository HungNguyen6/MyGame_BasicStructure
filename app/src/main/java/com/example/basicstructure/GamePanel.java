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
import java.util.ArrayList;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    // khoi tao bien thread
    private MainThread thread;
    Element element;
    ParallaxBackGround parallaxBackGround;
    ArrayList<KeThu>keThu=new ArrayList<KeThu>();
    int thoigiankethu =0;
    ArrayList<Bullet>bullet=new ArrayList<Bullet>();
//    Bullet bullet;
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
        parallaxBackGround=new ParallaxBackGround(this.getResources());
//        bullet=new Bullet(getResources(),0,0,R.drawable.bulet);
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
        parallaxBackGround.doDrawRunning(canvas);
        if (element!=null){
            element.doDraw(canvas);
            this.doDrawBullet(canvas);
            this.doDrawKeThu(canvas);
            xetvacham(canvas);
//            bullet.doDraw(canvas);
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
    public void doDrawBullet(Canvas canvas){
        Bullet bullet1 = new Bullet(getResources(),element.mX,element.mY,R.drawable.bulet);
        bullet.add(bullet1);
        for (int i = 0; i<bullet.size();i++){
            bullet.get(i).doDraw(canvas);
        }
    }
    public void doDrawKeThu(Canvas canvas){
        if(thoigiankethu>=10)
        {
            thoigiankethu=0;
            KeThu motkethu=new KeThu(getResources(),
                    canvas.getWidth(),canvas.getHeight());
            keThu.add(motkethu);
        }
        for(int i=0;i<keThu.size();i++)
            keThu.get(i).doDraw(canvas);
        for(int i=0;i<keThu.size();i++)
            if(keThu.get(i).y<0)
                keThu.remove(i);
        Log.d("viendan","so vien: "+keThu.size());
    }


    public boolean KeThuTrungDan(Bullet bullet,KeThu keThu)
    {
        float rong_bullet=(float)bullet.getWidth()/2;
        int cao_bullet=bullet.getHeight()/2;
        float nuarong_e=(float)keThu.getWidth()/2;
        int cao_kethu=keThu.getHeight()/2;
        int kc_ht_x=Math.abs(bullet.gettamX()-keThu.gettamX());
        int kc_ht_y=Math.abs(bullet.gettamY()-keThu.gettamY());
        if(kc_ht_x<=rong_bullet+nuarong_e && kc_ht_y<=cao_bullet+cao_kethu)
            return true;
        else
            return false;
    }


    public void xetvacham(Canvas canvas)
    {
        try{
            for(int i=0;i<bullet.size();i++)
                for(int j=0;j<keThu.size();j++)
                {
                    if(KeThuTrungDan(bullet.get(i), keThu.get(j))==true)
                    {
                        bullet.remove(i);
                        keThu.remove(j);
                    }
                }
        }catch(Exception e)
        {
            Log.d("loi",e.toString());
        }
    }



}
    