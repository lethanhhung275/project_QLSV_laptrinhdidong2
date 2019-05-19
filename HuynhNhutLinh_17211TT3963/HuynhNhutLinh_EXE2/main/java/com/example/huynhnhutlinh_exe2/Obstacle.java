package com.example.huynhnhutlinh_exe2;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
public class Obstacle implements GameObject{

    private Rect rectangle;
    private int color;
    private Rect rectangle2;

    public Rect getRectangle() {
        return rectangle;
    }

    public void incrementY(float y){
        rectangle.top += y;
        rectangle.bottom += y;
        rectangle2.top += y;
        rectangle2.bottom += y;
    }

    public Obstacle(int rectHieght, int color, int startX, int startY, int playerGap){

        this.color = color;
        //l,t,r,b
        rectangle = new Rect(0, startY, startX,startY + rectHieght);
        rectangle2 = new Rect(startX + playerGap, startY, Constants.SCREEN_WIDTH, startY + rectHieght);
    }

    public  boolean playerCollide(RectPlayer player){
        if(rectangle.contains(player.getRectangle().left, player.getRectangle().top)
                || rectangle.contains(player.getRectangle().right, player.getRectangle().top)
                || rectangle.contains(player.getRectangle().left, player.getRectangle().bottom)
                || rectangle.contains(player.getRectangle().right, player.getRectangle().bottom))
            return true;
        return false;

    }

    @Override
    public void draw(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(rectangle,paint);
        canvas.drawRect(rectangle2,paint);
    }

    @Override
    public void update(){

    }

}
