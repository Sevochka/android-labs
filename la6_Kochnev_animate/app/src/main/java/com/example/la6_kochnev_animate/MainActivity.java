package com.example.la6_kochnev_animate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
//extends SurfaceView implements SurfaceHolder.Callback
public class MainActivity
        extends AppCompatActivity
        implements Animation.AnimationListener {

    private ImageView imageView;
    Bitmap bitmap;
    Paint paint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.image);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.alpha:
                startAnim(R.anim.alpha);
                return true;

            case R.id.scale:
                startAnim(R.anim.scale);
                return true;

            case R.id.translate:
                startAnim(R.anim.translate);
                return true;

            case R.id.rotate:
                startAnim(R.anim.rotate);
                return true;

            case R.id.total:
                startAnim(R.anim.total);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void initImg(){
        bitmap = Bitmap.createBitmap(imageView.getWidth(), imageView.getHeight(), Bitmap.Config.ARGB_8888);
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(8);
    }

    public void startAnim(int animName){
        Animation animation = AnimationUtils.loadAnimation(this, animName);
        animation.setAnimationListener(this);
        imageView.startAnimation(animation);
    }


    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}