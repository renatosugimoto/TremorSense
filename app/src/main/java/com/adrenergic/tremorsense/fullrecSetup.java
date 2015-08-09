package com.adrenergic.tremorsense;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ViewFlipper;


public class fullrecSetup extends ActionBarActivity {

    private String note = "";
    private int mood;
    private int hunger;
    private boolean hasCaffeine;
    private ViewFlipper viewFlipper;

    public void showNextView(View view){
        viewFlipper.showNext();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullrec_setup);
        viewFlipper = (ViewFlipper) findViewById(R.id.setuppageview);
        viewFlipper.setInAnimation(this, R.anim.abc_grow_fade_in_from_bottom);
        viewFlipper.setOutAnimation(this, R.anim.abc_shrink_fade_out_from_bottom);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
