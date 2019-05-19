package hungle.example.vehinh;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DrawingView extends View{
    private Paint drawPaint;
    Point point = new Point();

    public DrawingView(Context context, AttributeSet attrrs){
        super(context, attrrs);
        setPaint();
    }

    public void setPaint(){
        drawPaint = new Paint();
        drawPaint.setAntiAlias(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();
        point = new Point(Math.round(touchX), Math.round(touchY));
        invalidate();
        return true;
    }

    @Override
    public void onDraw(Canvas canvas) {
       drawPaint.setColor(Color.BLUE);
       canvas.drawRect(100, 100, 200, 200, drawPaint);
       drawPaint.setColor(Color.YELLOW);
       canvas.drawCircle(point.x, point.y, 20, drawPaint);
    }

    public void move(int dk){
        switch (dk){
            case 1: point.y -= 5; break;
            case 2: point.y += 5; break;
            case 3: point.x -= 5; break;
            case 4: point.x += 5; break;
        }
        invalidate();
    }
}
