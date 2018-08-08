package com.kkang.scrollview;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    LinearLayout llMain;
    ImageView iv;
    float dX, dY;

    int width;
    int height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUI();
        setEvent();

    }

    private void setUI() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y;
        iv = (ImageView) findViewById(R.id.iv);
        llMain = (LinearLayout) findViewById(R.id.llMain);
        llMain.postDelayed(new Runnable() {
            @Override
            public void run() {
                width = llMain.getWidth();
                height = llMain.getHeight();
                //setLog(" width :  " + width + " ,  height :  " + height);
            }
        }, 500);
    }

    private void setEvent() {
        iv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:

                        dX = iv.getX() - event.getRawX();
                        dY = iv.getY() - event.getRawY();
                        break;

                    case MotionEvent.ACTION_MOVE:
                        float x = event.getRawX() + dX;
                        float y = event.getRawY() + dY;
                        if (x < 0) {
                            x = 0;
                        }

                        if (y < 0) {
                            y = 0;
                        }

                        if (x + iv.getWidth() > width) {
                            x = width - iv.getWidth();
                        }
                        if (y + iv.getHeight() > height) {
                            y = height - iv.getHeight();
                        }
                        iv.animate()
                                .x(x)
                                .y(y)
                                .setDuration(0)
                                .start();
                        //setLog(" iv.getX() :  " + iv.getX() + " ,  iv.getY() :  " + iv.getY());
                        //setLog(" iv.getWidth() :  " + iv.getWidth() + " ,  iv.getHeight() :  " + iv.getHeight());
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });
    }

    public void setLog(String log) {
        Log.d("PROJECT  ", buildLogMsg(log));
    }

    private static String buildLogMsg(String message) {
        StackTraceElement ste = Thread.currentThread().getStackTrace()[4];
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(ste.getFileName());
        sb.append(" > ");
        sb.append(ste.getMethodName());
        sb.append(" > #");
        sb.append(ste.getLineNumber());
        sb.append("] ");
        sb.append(message);

        return sb.toString();
    }

}
