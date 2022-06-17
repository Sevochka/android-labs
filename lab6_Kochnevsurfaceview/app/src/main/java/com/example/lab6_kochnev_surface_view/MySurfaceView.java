package com.example.lab6_kochnev_surface_view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    Paint paint;
    SurfaceHolder surfaceHolder;

    public MySurfaceView(Context context) {
        super(context);
        getHolder().addCallback(this);
    }
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int
            width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(8);
        surfaceHolder = holder;

    }
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    public void clearCanvas(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
    }
    public void drawCircle() {
        Canvas canvas = surfaceHolder.lockCanvas();
        clearCanvas(canvas);
        canvas.drawCircle(canvas.getWidth()/2, canvas.getHeight()/2, canvas.getWidth()/5, paint);
        surfaceHolder.unlockCanvasAndPost(canvas);
    }

    public void drawLine() {
        Canvas canvas = surfaceHolder.lockCanvas();
        clearCanvas(canvas);
        canvas.drawLine(
                canvas.getWidth()/4,
                canvas.getHeight()/4,
                canvas.getWidth() - canvas.getWidth()/4,
                canvas.getHeight()/4,
                paint);
        surfaceHolder.unlockCanvasAndPost(canvas);
    }

    public void drawOval() {
        Canvas canvas = surfaceHolder.lockCanvas();
        clearCanvas(canvas);
        canvas.drawOval(
                canvas.getWidth()/4,
                canvas.getHeight()/2,
                canvas.getHeight()/2,
                canvas.getHeight()/4,
                paint);
        surfaceHolder.unlockCanvasAndPost(canvas);
    }

    public void drawRect(){
        Canvas canvas = surfaceHolder.lockCanvas();
        clearCanvas(canvas);
        canvas.drawRect(
                canvas.getWidth()/4,
                canvas.getHeight()/2,
                canvas.getWidth() - canvas.getWidth()/4,
                canvas.getHeight()/4,
                paint);
        surfaceHolder.unlockCanvasAndPost(canvas);
    }

    public void drawRoundRect() {
        Canvas canvas = surfaceHolder.lockCanvas();
        clearCanvas(canvas);
        canvas.drawRoundRect(
                canvas.getWidth()/4,
                canvas.getHeight()/2,
                canvas.getWidth() - canvas.getWidth()/4,
                canvas.getWidth() - canvas.getWidth()/4,
                50,
                50,
                paint);

        paint.setColor(Color.BLUE);
        canvas.drawRoundRect(
                canvas.getWidth()/4 + 40,
                canvas.getHeight()/2 - 20 ,
                canvas.getWidth() - canvas.getWidth()/4 - 40,
                canvas.getWidth() - canvas.getWidth()/4 + 20,
                25,
                25,
                paint);
        surfaceHolder.unlockCanvasAndPost(canvas);
    }

    public void drawPath() {
        Canvas canvas = surfaceHolder.lockCanvas();
        clearCanvas(canvas);
        Path path = new Path();

        int centerWidth = canvas.getWidth() / 2;
        int centerHeight = canvas.getHeight() / 2;

        path.moveTo(centerWidth, centerHeight);
        path.lineTo(centerWidth + 30 * 3, centerHeight - 140 * 3);
        path.lineTo(centerWidth + 60 * 3, centerHeight);
        path.lineTo(centerWidth - 20 * 3, centerHeight - 80 * 3);
        path.lineTo(centerWidth + 100 * 3, centerHeight - 80 * 3);

        path.close();
        paint.setStrokeWidth(5);
        paint.setPathEffect(null);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, paint);

        surfaceHolder.unlockCanvasAndPost(canvas);
    }

    public void drawArc() {
        Canvas canvas = surfaceHolder.lockCanvas();
        clearCanvas(canvas);
        canvas.drawArc(
                70,
                20 ,
                600,
                600,
                10,
                90,
                true,
                paint
        );
        surfaceHolder.unlockCanvasAndPost(canvas);
    }
}

