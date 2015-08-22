package com.adrenergic.tremorsense;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class settings extends Activity {

    //Variable declarations
    int countdownSetting;
    RadioGroup countdownRadioGroup = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //Load Preferences
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        //Handling the countdown timer
        countdownSetting = Integer.parseInt(prefs.getString("record_countdown_length", "3000"));
        countdownRadioGroup = (RadioGroup) findViewById(R.id.countdownSettingsGroup);
        countdownRadioGroup.clearCheck();
        RadioButton rb_countdown;

        switch(countdownSetting){
            case 2000:  rb_countdown = (RadioButton) findViewById(R.id.cd_2secs);
                        break;
            case 3000:  rb_countdown = (RadioButton) findViewById(R.id.cd_3secs);
                        break;
            case 5000:  rb_countdown = (RadioButton) findViewById(R.id.cd_5secs);
                        break;
            case 7000:  rb_countdown = (RadioButton) findViewById(R.id.cd_7secs);
                        break;
            case 10000: rb_countdown = (RadioButton) findViewById(R.id.cd_10secs);
                        break;
            default:    rb_countdown = (RadioButton) findViewById(R.id.cd_3secs);
                        break;
        }

        rb_countdown.setChecked(true);

        countdownRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId){
                switch(checkedId){
                    case R.id.cd_2secs: prefs.edit().putString("record_countdown_length", "2000").apply();
                                        break;
                    case R.id.cd_3secs: prefs.edit().putString("record_countdown_length", "3000").apply();
                                        break;
                    case R.id.cd_5secs: prefs.edit().putString("record_countdown_length", "5000").apply();
                                        break;
                    case R.id.cd_7secs: prefs.edit().putString("record_countdown_length", "7000").apply();
                                        break;
                    case R.id.cd_10secs:prefs.edit().putString("record_countdown_length", "10000").apply();
                                        break;
                    default:            prefs.edit().putString("record_countdown_length", "3000").apply();
                                        break;
                }
            }
        });
    }
}