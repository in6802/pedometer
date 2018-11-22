package com.example.edu.pedometer;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.view.View;

public class MainActivity extends AppCompatActivity implements SensorEventListener, View.OnClickListener{

    TextView textViewStep, textViewX, textViewY, textViewZ;
    Button buttonReset;
    SeekBar seekBar;
    int threshold;
    int previousY, currentY, steps;
    float acceleration;
    SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewStep = findViewById(R.id.textViewStep);
        textViewX = findViewById(R.id.textViewX);
        textViewY = findViewById(R.id.textViewY);
        textViewZ = findViewById(R.id.textViewZ);
        buttonReset = findViewById(R.id.buttonReset);
        buttonReset.setOnClickListener(this);
        seekBar = findViewById(R.id.seekBar);

        //seekBar.setOnSeekBarChangeListener(this);

        threshold = 3;
        previousY = currentY = steps = 0;
        acceleration = 0.0f;
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener((SensorEventListener) this, sensorManager.getDefaultSensor(
                Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];
        currentY =(int)y;
        if(Math.abs(currentY - previousY) > threshold) {
            steps++;
            textViewStep.setText(String.valueOf(steps));
        }
        previousY = (int)y;
        textViewX.setText(String.valueOf(x));
        textViewY.setText(String.valueOf(y));
        textViewZ.setText(String.valueOf(z));



    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onClick(View v) {
        previousY = currentY = steps = 0;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
