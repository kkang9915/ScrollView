package com.kkang.scrollview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Main2Activity extends AppCompatActivity {


    ImageView iv;
    LinearLayout llMain;

    int windowWidth;
    int windowheight;

    int xPosition;
    int yPosition;

    float oldX = 0;
    float oldY = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        setUI();
        setEvnet();
    }

    private void setUI() {
        llMain = (LinearLayout) findViewById(R.id.llMain);
        iv = (ImageView) findViewById(R.id.iv);

        llMain.postDelayed(new Runnable() {
            @Override
            public void run() {
                windowWidth = llMain.getWidth();
                windowheight = llMain.getHeight();

            }
        }, 500);


    }

    private void setEvnet() {

        iv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                final int X = (int) event.getRawX();
                final int Y = (int) event.getRawY();

                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                        xPosition = X - view.getLeft();
                        yPosition = Y - view.getTop();
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_POINTER_DOWN:
                    case MotionEvent.ACTION_POINTER_UP:
                        break;
                    case MotionEvent.ACTION_MOVE:
                        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) view.getLayoutParams();

                        if (view.getWidth() < (Math.abs(lp.leftMargin) + windowWidth) && oldX >= event.getRawX()) {

                        }else{
                            int leftMargin = X - xPosition;
                            if (leftMargin > 0) {
                                lp.leftMargin = 0;
                            } else {
                                lp.leftMargin = leftMargin;
                            }
                        }

                        if (view.getHeight() < (Math.abs(lp.topMargin) + windowheight) && oldY >= event.getRawY()) {

                        }else{
                            int topMargin = Y - yPosition;
                            if (topMargin > 0) {
                                lp.topMargin = 0;
                            } else {
                                lp.topMargin = topMargin;
                            }
                            setLog("(Math.abs(lp.leftMargin) + windowheight)  : : " + (Math.abs(topMargin) + windowheight));
                        }

                        setLog("oldX  : : " + (oldX - event.getRawX()));
                        setLog("oldY  : : " + (oldY - event.getRawY()));
                        setLog("oldY  : : " + (event.getRawY()));

                        view.setLayoutParams(lp);
                        setLog("getLeft  : : " + view.getLeft());
                        setLog("getRight  : : " + view.getRight());
                        setLog("windowheight  : : " + windowheight);
                        setLog("-----------------------------------------------------------");

                        break;
                }
                oldX = event.getRawX();
                oldY = event.getRawY();
                return true;
            }
        });

    }

    public void setLog(String log) {
        Log.d("KKANG  ", buildLogMsg(log));
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
