package com.example.lab6_kochnev_surface_view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    MySurfaceView surfaceView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        surfaceView = new MySurfaceView(this);
        setContentView(surfaceView);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.circle:
                surfaceView.drawCircle();
                return true;

            case R.id.line:
                surfaceView.drawLine();
                return true;

            case R.id.oval:
                surfaceView.drawOval();
                return true;

            case R.id.rect:
                surfaceView.drawRect();
                return true;

            case R.id.round_rect:
                surfaceView.drawRoundRect();
                return true;

            case R.id.arc:
                surfaceView.drawArc();
                return true;

            case R.id.path:
                surfaceView.drawPath();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}