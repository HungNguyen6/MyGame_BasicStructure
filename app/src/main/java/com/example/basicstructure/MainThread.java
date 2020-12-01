package com.example.basicstructure;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

public class MainThread extends Thread {
    private boolean running;
    private SurfaceHolder surfaceHolder;
    private GamePanel gamePanel;


    public MainThread(SurfaceHolder surfaceHolder, GamePanel gamePanel){
        this.surfaceHolder=surfaceHolder;
        this.gamePanel=gamePanel;

    }


    public void setRunning(boolean b){
        running = b;
    }

    @Override
    public void run() {
        super.run();
        long dem =0L;
        Canvas canvas = null;
        while (running){
            // cap nhat lai trang thai game, render du lieu ra man hinh
            canvas=surfaceHolder.lockCanvas();
            if (canvas!=null){
                gamePanel.draw(canvas);
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
            Log.d("testloop", "loop "+(dem++));

        }
    }
}
