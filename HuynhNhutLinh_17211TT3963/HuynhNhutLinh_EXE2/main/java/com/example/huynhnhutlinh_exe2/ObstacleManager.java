package com.example.huynhnhutlinh_exe2;

import android.graphics.Canvas;

import java.util.ArrayList;

public class ObstacleManager {
    private ArrayList<Obstacle> obstacles;
    private int playerGap;
    private int obstacleGap;
    private int obsracleHeight;
    private int color;
    private long startTime;

    public ObstacleManager(int playerGap, int obstacleGap, int obsracleHeight, int color){
        this.playerGap =playerGap;
        this.obstacleGap = obstacleGap;
        this.color = color;

        startTime = System.currentTimeMillis();

        obstacles = new ArrayList<>();

        this.obsracleHeight = obsracleHeight;

        poppulateObstacles();
    }

    private void poppulateObstacles() {
        int currY = -5*Constants.SCREEN_HEIGHT/4;
        while (currY < 0){
            int xStart = (int) (Math.random()*(Constants.SCREEN_WIDTH - playerGap));
            obstacles.add(new Obstacle(obsracleHeight, color, xStart , currY, playerGap));
            currY += obsracleHeight + obstacleGap;
        }
    }

    public void update(){
        if (startTime < Constants.INIT_TIME)
            startTime = Constants.INIT_TIME;
        int elapsedTime = (int) (System.currentTimeMillis() - startTime);
        startTime = System.currentTimeMillis();
        float speed = Constants.SCREEN_HEIGHT/10000.0f;
        for(Obstacle ob : obstacles){
                ob.incrementY(speed * elapsedTime);
        }

        if (obstacles.get(obstacles.size() -1).getRectangle().top >=   Constants.SCREEN_HEIGHT){
            int xStart = (int) (Math.random()*(Constants.SCREEN_WIDTH - playerGap));
            obstacles.add(0, new Obstacle(obsracleHeight, color, xStart , obstacles.get(0).getRectangle().top - obsracleHeight - obstacleGap, playerGap));
            obstacles.remove(obstacles.size() -1);
        }

    }

    public void draw(Canvas canvas){
        for (Obstacle ob: obstacles){
            ob.draw(canvas);
        }
    }
}
