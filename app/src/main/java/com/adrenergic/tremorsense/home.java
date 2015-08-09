package com.adrenergic.tremorsense;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;


public class home extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    /** Called when the user clicks the "Record Accelerometer Data" button */
    public void startAccelActivity(View view) {
        Intent intent = new Intent(this, RecordAccelData.class);
        startActivity(intent);
    }

    /**Called when user clicks "Settings" button */
    public void startSettingsActivity(View view){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    /**Called when user clicks "Info" button */
    public void startInfoActivity(View view){
        Intent intent = new Intent(this, info.class);
        startActivity(intent);
    }
}
