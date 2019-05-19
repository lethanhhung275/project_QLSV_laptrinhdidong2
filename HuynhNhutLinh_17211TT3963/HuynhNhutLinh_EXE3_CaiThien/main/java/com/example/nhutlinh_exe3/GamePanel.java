package com.example.nhutlinh_exe3;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.Random;


public class GamePanel extends SurfaceView implements SurfaceHolder.Callback
{
    public static final int WIDTH = 856;
    public static final int HEIGHT = 480;
    public static final int MOVESPEED = -5;
    private long missileStartTime;
    private MainThread thread;
    private Background bg;
    private Player player;
    private ArrayList<Missile> missiles;
    private Random rand = new Random();


    public GamePanel(Context context)
    {
        super(context);


        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);

        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){}

    @Override
    public void surfaceDestroyed(SurfaceHolder holder){
        boolean retry = true;
        int counter = 0;
        while(retry && counter<1000)
        {
            counter++;
            try{thread.setRunning(false);
                thread.join();
                retry = false;

            }catch(InterruptedException e){e.printStackTrace();}

        }

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){

        bg = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.grassbg1));
        player = new Player(BitmapFactory.decodeResource(getResources(), R.drawable.helicopter), 65, 25, 3);
        missiles = new ArrayList<Missile>();
        missileStartTime = System.nanoTime();



        //Bắt đầu vòng lặp game
        thread.setRunning(true);
        thread.start();

    }
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            if(!player.getPlaying())
            {
                player.setPlaying(true);
            }
            else
            {
                player.setUp(true);
            }
            return true;
        }
        if(event.getAction()==MotionEvent.ACTION_UP)
        {
            player.setUp(false);
            return true;
        }

        return super.onTouchEvent(event);
    }

    public void update()

    {
        if(player.getPlaying()) {

            bg.update();
            player.update();

            //thêm tên lửa vào bộ đếm thời gian
            long missileElapsed = (System.nanoTime()-missileStartTime)/1000000;
            if(missileElapsed >(2000 - player.getScore()/4)){

                System.out.println("making missile");

                //tên lửa đầu tiên luôn đi xuống giữa
                if(missiles.size()==0)
                {
                    missiles.add(new Missile(BitmapFactory.decodeResource(getResources(),R.drawable.
                            missile),WIDTH + 10, HEIGHT/2, 45, 15, player.getScore(), 13));
                }
                else
                {

                    missiles.add(new Missile(BitmapFactory.decodeResource(getResources(),R.drawable.missile),
                            WIDTH+10, (int)(rand.nextDouble()*(HEIGHT)),45,15, player.getScore(),13));
                }

                //đặt lại bộ đếm thời gian
                missileStartTime = System.nanoTime();
            }

            //lặp qua mọi tên lửa và kiểm tra va chạm và loại bỏ
            for(int i = 0; i<missiles.size();i++)
            {
                //update missile
                missiles.get(i).update();

                if(collision(missiles.get(i),player))
                {
                    missiles.remove(i);
                    player.setPlaying(false);
                    break;
                }
                //loại bỏ tên lửa nếu nó ra khỏi màn hình
                if(missiles.get(i).getX()<-100)
                {
                    missiles.remove(i);
                    break;
                }
            }


        }
    }
    public boolean collision(GameObject a, GameObject b)
    {
        if(Rect.intersects(a.getRectangle(),b.getRectangle()))
        {
            return true;
        }
        return false;
    }
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        final float scaleFactorX = getWidth() / (WIDTH * 1.f);
        final float scaleFactorY = getHeight() / (HEIGHT * 1.f);

        if (canvas != null) {
            final int savedState = canvas.save();


            canvas.scale(scaleFactorX, scaleFactorY);
            bg.draw(canvas);
            player.draw(canvas);

            //draw missiles
            for (Missile m : missiles) {
                m.draw(canvas);
            }


            canvas.restoreToCount(savedState);
        }
    }


}