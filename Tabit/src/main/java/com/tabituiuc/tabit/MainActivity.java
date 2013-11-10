package com.tabituiuc.tabit;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.TableLayout;
import java.util.*;
import com.tabituiuc.tabit.TunerModule;

public class MainActivity extends ActionBarActivity {

    private TableLayout scorePrintingXScrollView;
    private ArrayList<Integer> rawFreqArray;
    private TimerTask recordingTask;
    private Boolean recorderState = false;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scorePrintingXScrollView = (TableLayout) findViewById(R.id.scorePrintingXScrollView);

        if (savedInstanceState == null) {
            /* getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
                    */

        }

        setButtonOnClickListeners();
    }



    private void setButtonOnClickListeners(){
        ((Button) findViewById(R.id.startButton)).setOnClickListener(startClicker);
    }

    private View.OnClickListener startClicker = new View.OnClickListener(){

        @Override
        public void onClick(View view) {
                wrapperFunc();
        }
    };


    private void wrapperFunc(){

        if(recorderState == false)
        {


        rawFreqArray = new ArrayList<Integer>();


        Timer t = new Timer();
        recordingTask = new TimerTask(){int rawFreq = TunerModule.accessRecording();
            rawFreqArray.add(rawFreq);};


        t.scheduleAtFixedRate(recordingTask, 0 ,10);


        }

        else {

            returningResults();

        }

        recorderState = !recorderState;

    }

    private int[] returningResults(){

        recordingTask.cancel();
        int[] rawFreqCoverted = new int[rawFreqArray.size()];
        for(int i = 0; i < rawFreqArray.size(); i++) {
            if (rawFreqArray.get(i) != null) {
                rawFreqCoverted[i] = rawFreqArray.get(i);
            }
        }
        ShortestDistCalculations_Forrest resultArray = new ShortestDistCalculations_Forrest(rawFreqCoverted); // Forrest to be removed and discussed about actual method
        return resultArray.results();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

}
