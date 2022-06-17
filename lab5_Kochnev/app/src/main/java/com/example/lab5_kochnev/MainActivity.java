package com.example.lab5_kochnev;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;
import android.view.GestureDetector;

public class MainActivity extends AppCompatActivity implements
        OnTouchListener {
    private GestureDetectorCompat myDetector;
    int upIndex = 0;
    int downIndex = 0;
    boolean touchFlag = false;
    TextView[] idView = new TextView[10];
    TextView[] xView = new TextView[10];
    TextView[] yView = new TextView[10];
    TextView myText;
    TextView myTextGestures;
    View myView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myView = findViewById(R.id.myView);
        myText = findViewById(R.id.myText);
        myTextGestures = findViewById(R.id.myTextGestures);
        String myPackage = getPackageName();
        Resources myResources = getResources();
        for (int i = 0; i < 10; i++) {
            idView[i] = findViewById(myResources.getIdentifier("id"+i, "id",
                    myPackage));
            xView[i] = findViewById(myResources.getIdentifier("x"+i, "id",
                    myPackage));
            yView[i] = findViewById(myResources.getIdentifier("y"+i, "id",
                    myPackage));
        }

        myView.setOnTouchListener(this);


        GestureListener myGestures = new GestureListener();
        myDetector = new GestureDetectorCompat(this, myGestures);

        myDetector.setOnDoubleTapListener(myGestures);

    }
    @Override
    public boolean onTouch(View view, MotionEvent event) {
        int actionMask = event.getActionMasked();
        int pointerIndex = event.getActionIndex();
        int pointerCount = event.getPointerCount();
        switch (actionMask) {
            case MotionEvent.ACTION_DOWN:
                touchFlag = true;
            case MotionEvent.ACTION_POINTER_DOWN:
                downIndex = pointerIndex;
                break;
            case MotionEvent.ACTION_UP:
                touchFlag = false;
                idView[0].setText("");
                for (int i = 0; i < 10; i++) {
                    idView[i].setText("");
                    xView[i].setText("");
                    yView[i].setText("");
                    myText.setText("Координаты касаний");
                }
            case MotionEvent.ACTION_POINTER_UP:
                upIndex = pointerIndex;
                break;
            case MotionEvent.ACTION_MOVE:
                for (int i = 0; i < 10; i++) {
                    idView[i].setText("");
                    xView[i].setText("");
                    yView[i].setText("");
                    if (i < pointerCount) {
                        idView[i].setText(Integer.toString(event.getPointerId(i)));
                        xView[i].setText(Integer.toString((int)event.getX(i)));
                        yView[i].setText(Integer.toString((int)event.getY(i)));
                    } else {
                        idView[i].setText("");
                        xView[i].setText("");
                        yView[i].setText("");
                    }
                }
                break;
        }
        if (touchFlag) {
            myText.setText("Количество касаний: " +
                    Integer.toString(pointerCount) + "\n" + "Индекс последнего касания: " +
                    downIndex + "\n" + "Индекс последнего отпускания: " + upIndex );
        }
        return myDetector.onTouchEvent(event);
    }
    public class GestureListener implements
            GestureDetector.OnGestureListener,
            GestureDetector.OnDoubleTapListener {
        @Override
        public boolean onDown(MotionEvent event) {
            myTextGestures.append("\n onDown");
            return true;
        }

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {
            myTextGestures.append("\n onFling");
            return true;
        }

        @Override
        public void onLongPress(MotionEvent event) {
            myTextGestures.append("\n onLongPress");
        }

        @Override
        public boolean onScroll(MotionEvent event1, MotionEvent event2,
                                float distanceX, float distanceY) {
            myTextGestures.append("\n onScroll");
            return true;
        }

        @Override
        public void onShowPress(MotionEvent event) {
            myTextGestures.append("\n onShowPress");
        }

        @Override
        public boolean onSingleTapUp(MotionEvent event) {
            myTextGestures.append("\n onSingleTapUp");
            return true;
        }

        @Override
        public boolean onDoubleTap(MotionEvent event) {
            myTextGestures.append("\n onDoubleTap");
            return true;
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent event) {
            myTextGestures.append("\n onDoubleTapEvent");
            return true;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent event) {
            myTextGestures.append("\n onSingleTapConfirmed");
            return true;
        }
    }
}