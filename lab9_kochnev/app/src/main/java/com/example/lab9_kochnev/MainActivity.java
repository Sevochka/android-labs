package com.example.lab9_kochnev;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager smm;
    List<Sensor> sensor;
    private Sensor mLight;
    private Sensor mGravity;
    private Sensor mAccel;

    ListView lv;
    TextView textLight;
    TextView textGravity;
    TextView textAccel;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        smm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        lv = (ListView) findViewById(R.id.list);
        textLight = (TextView) findViewById(R.id.textLight);
        textGravity = (TextView) findViewById(R.id.textGravity);
        textAccel = (TextView) findViewById(R.id.textAccel);

        sensor = smm.getSensorList(Sensor.TYPE_ALL);
        lv.setAdapter(new ArrayAdapter<Sensor>(this, android.R.layout.simple_list_item_1, sensor));

        mLight = smm.getDefaultSensor(Sensor.TYPE_LIGHT);
        mGravity = smm.getDefaultSensor(Sensor.TYPE_GRAVITY);
        mAccel = smm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    public final void onSensorChanged(SensorEvent event) {
        if (event.sensor == mLight){
            float lux = event.values[0];
            textLight.setText("Датчик освещения: "+Float.toString(lux));
        }
        if (event.sensor == mGravity){
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            textGravity.setText("Gravity: \n" +
                    "x: "+Float.toString(x) + "; \n" +
                    "y: "+Float.toString(y) + "; \n" +
                    "z: "+Float.toString(z) + ";");
        }

        if (event.sensor == mAccel){
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            textAccel.setText("Accelerometr: \n" +
                    "x: "+Float.toString(x) + "; \n" +
                    "y: "+Float.toString(y) + "; \n" +
                    "z: "+Float.toString(z) + ";");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        smm.registerListener(this, mLight,
                SensorManager.SENSOR_DELAY_NORMAL);

        smm.registerListener(this, mGravity,
                SensorManager.SENSOR_DELAY_NORMAL);

        smm.registerListener(this, mAccel,
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        smm.unregisterListener(this);
    }
}