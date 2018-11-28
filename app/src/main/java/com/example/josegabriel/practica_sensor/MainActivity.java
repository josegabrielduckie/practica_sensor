package com.example.josegabriel.practica_sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    MediaPlayer mp;
    SensorManager sm;
    TextView vx,vy,vz,estado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mp=MediaPlayer.create(this,R.raw.tapicero);

        sm=(SensorManager)getSystemService(SENSOR_SERVICE);

        sm.registerListener((SensorEventListener)this,sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_NORMAL);

        vx=findViewById(R.id.valx);
        vy=findViewById(R.id.valy);
        vz=findViewById(R.id.valz);

        estado=findViewById(R.id.stado);
        mp.start();


    }
    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){

            float x,y,z;

            x=event.values[0];
            y=event.values[1];
            z=event.values[2];

            vx.setText(x+"");
            vy.setText(y+"");
            vz.setText(z+"");
            String state="REPRODUCIENDO";
            if(z<=0){
                mp.pause();
                state="PAUSADO";
            }
            else{
                if(mp.isPlaying()){

                }else {
                    mp.start();
                }
            }




            estado.setText(state);


        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}
