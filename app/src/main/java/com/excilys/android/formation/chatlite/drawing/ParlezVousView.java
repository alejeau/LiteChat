package com.excilys.android.formation.chatlite.drawing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceView;

import java.util.LinkedList;
import java.util.List;

public class ParlezVousView extends SurfaceView {
    Paint paint = new Paint();
    private float x, y;
    private List<Circle> circles;

    Circle drag = null;

    public ParlezVousView(Context context, AttributeSet attrs) {
        super(context, attrs);
        circles = new LinkedList<>();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setColor(Color.RED);

        //Painting all registered circles
        for (Circle c : circles) {
            canvas.drawCircle(c.getX(), c.getY(), c.getRadius(), paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        // Gérer les actions ici !
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                this.x = event.getX();
                this.y = event.getY();
                //Relachement du doigt
                break;
            case MotionEvent.ACTION_DOWN:
                //Appui du doigt
                this.x = event.getX();
                this.y = event.getY();

                Circle tmp = new Circle(this.x, this.y, (float) 30.0);
                drag = null;
                for (Circle c : circles) {
                    //if there is already a circle at tmp position we set drag parameter
                    if (c.distance(tmp) <= c.getRadius()) {
                        drag = c;
                    }
                }

                if (drag != null) {
                    System.out.println("Catching Circle !");
                }

                //if there is no circle at tmp position, we create a new one
                else {
                    circles.add(new Circle(this.x, this.y, (float) 30.0));
                }

                postInvalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                //Deplacement du doigt
                this.x = event.getX();
                this.y = event.getY();

                //if a circle is catch -> updating position
                if (drag != null) {
                    drag.setX(this.x);
                    drag.setY(this.y);
                }
                postInvalidate();
                break;
        }

        return true;
    }
}
