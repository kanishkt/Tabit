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
import android.widget.TextView;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.ArrayList;

import com.tabituiuc.tabit.TunerModule;

public class MainActivity extends ActionBarActivity {

    private TableLayout scorePrintingXScrollView;
    private List<Integer> rawFreqArray;
    private TimerTask recordingTask;
    private Boolean recorderState = false;
    private TextView[] highEString;
    private TextView[] highBString;
    private TextView[] highGString;
    private TextView[] highDString;
    private TextView[] highAString;
    private TextView[] lowEString;

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
            recordingTask = new TimerTask(){

            public void run(){
                int raw = TunerModule.accessRecording();
            Integer rawFreq = new Integer(raw);
            rawFreqArray.add(rawFreq);
            }

            };


        t.scheduleAtFixedRate(recordingTask, 0 ,10);



        }

        else {

            printer(returningResults());

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
        return resultArray.getResults();

    }

    private void printer(int[] results){

        highEString = new TextView[results.length];
        highBString = new TextView[results.length];
        highGString = new TextView[results.length];
        highDString = new TextView[results.length];
        highAString = new TextView[results.length];
        lowEString = new TextView[results.length];

        TextView[][] wrapper = new TextView[][]{highEString, highBString, highGString, highDString, highAString, lowEString};


        for (int i = 0; i < results.length; i++)
        {
            int encoded = results[i];
            int string = encoded/100;
            int fretBox = encoded % 100;

            switch(string) {
                case 1:

                case 2:

                case 3:

                case 4:

                case 5:

                case 6:
            }

            for (TextView[] repeats: wrapper)
            {
                if(repeats[i] == null)
                {
                    repeats[i];
                }
            }
        }
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
