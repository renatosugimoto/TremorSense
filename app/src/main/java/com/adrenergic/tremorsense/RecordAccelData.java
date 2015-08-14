package com.adrenergic.tremorsense;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.SensorEvent;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class RecordAccelData extends Activity implements SensorEventListener {
    //Variable Declarations:
    private SensorManager senSensorManager;
    private Sensor senAccelerometer;
    float accelZ = 0;
    long startTime = System.currentTimeMillis();
    long time = System.currentTimeMillis();
    long[] timeArray = new long[1000];
    double[] recArray = new double[1000];
    double[] dXArray = new double[1000];
    int recCount = 0;
    circleGraph progressCircle;
    int bmpWidth = 480;
    int bmpHeight = 480;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Activity and layout initialisation
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_accel_data);
        //Check timer length
        int timerLength = 3000;
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        timerLength = Integer.parseInt(prefs.getString("record_countdown_length", "3000"));
        //Circle graph initiation
        progressCircle = (circleGraph) findViewById(R.id.progressgraph);
        new CountDownTimer(timerLength, 100) {
            public void onTick(long millisUntilFinished){
                final TextView accZText = (TextView) findViewById(R.id.accelText);
                accZText.setText(Integer.toString((int) Math.ceil(millisUntilFinished/1000)+1));
                accZText.setTextSize(100);
            }

            public void onFinish(){
               initAccelerometer();
               final TextView accZText = (TextView) findViewById(R.id.accelText);
               accZText.setText(R.string.recording);
               accZText.setTextSize(20);
            }
        }.start();
    }

    public void initAccelerometer(){
        //Accelerometer Initialisation
        senSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_FASTEST);
    }

    protected void onPause() {
        super.onPause();
        if(senSensorManager!=null) {
            senSensorManager.unregisterListener(this);
        }
    }

    protected void onStop(){
        super.onStop();
        if(senSensorManager!=null) {
            senSensorManager.unregisterListener(this);
        }
    }

    protected void onResume() {
        super.onResume();
        if(senSensorManager!=null) {
            senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_FASTEST);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (recCount < 1000) {
            //check that it's coming from the accelo
            Sensor mySensor = event.sensor;
            if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                time = System.currentTimeMillis();
                accelZ = event.values[2];
                timeArray[recCount] = time - startTime;
                recArray[recCount] = accelZ;
                double r = bmpWidth/2*0.8*((recArray[recCount]+20)/40);
                double newX = (bmpWidth/2) + r*Math.cos((2*Math.PI*recCount/1000)-Math.PI/2);
                double newY = (bmpHeight/2) + r*Math.sin((2*Math.PI*recCount/1000)-Math.PI/2);
                boolean newPath=true;
                if(recCount!=0) {
                    newPath = false;
                }
                addGraphPoint(newX, newY, newPath);
                recCount += 1;
            }
        } else {
            regressionCalc();
            senSensorManager.unregisterListener(this);
        }
    }

    public void addGraphPoint(double addX, double addY, boolean reset){
        progressCircle.appendToPath(addX, addY, reset);
        progressCircle.invalidate();
    }

    public void regressionCalc() {
        //Calculating sum values
        double sumZ;
        double meanZ;
        sumZ = 0;
        for (int r = 0; r < recArray.length; r++) {
            sumZ = sumZ + recArray[r];
        }
        meanZ = sumZ/recArray.length;

        //Calculating standard deviation
        double sumXu2; //this represents E((x-xbar)^2)
        sumXu2 = 0;
        for (int x = 0; x < recArray.length; x++) {
            sumXu2 += ((recArray[x]-meanZ)*(recArray[x]-meanZ));
        }
        double SD;
        SD = Math.sqrt(sumXu2/(recArray.length-1));
        //The following scales SD to a modified arctan BECAUSE SCIENCE
        double tremorVal;
        tremorVal = (7 * Math.atan(0.05 * SD)) * 100;
        //Set the number in the view
        final TextView accZText = (TextView) findViewById(R.id.accelText);
        accZText.setText(Math.round(tremorVal) + "");
        accZText.setTextSize(80);

        /* ALTERNATE CALCULATION
        double[] dXArray = new double[999];
        double lastVal = recArray[0];
        double sumdX = 0;
        for(int r=1;r<recArray.length;r++){
            dXArray[r-1]=Math.abs(lastVal-recArray[r]);
            lastVal = recArray[r];
            sumdX += dXArray[r-1];
        }
        double tremorVal = sumdX/dXArray.length;
        final TextView accZText = (TextView) findViewById(R.id.accelText);
        accZText.setText(Math.round(sumdX) + "");
        accZText.setTextSize(80);
        Log.d("tremor",Double.toString(tremorVal));
        */
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //Accuracy change content goes here
    }

    public void goBack(View view){
        if(senSensorManager!=null) {
            senSensorManager.unregisterListener(this);
        }
        finish();
    }
}