package com.tabituiuc.tabit;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.view.ViewGroup.*;


import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.ArrayList;

public class MainActivity extends ActionBarActivity {

    // private TableLayout scorePrintingXScrollView;
    private List<Integer> rawFreqArray;
    private TimerTask recordingTask;
    private Boolean recorderState = false;

    private LinearLayout highETable;
    private LinearLayout highBTable;
    private LinearLayout highGTable;
    private LinearLayout highDTable;
    private LinearLayout highATable;
    private LinearLayout lowETable;

    TunerModule module;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        module = new TunerModule();


        highETable = (LinearLayout) findViewById(R.id.highELayout);
        highBTable = (LinearLayout) findViewById(R.id.BLayout);
        highGTable = (LinearLayout) findViewById(R.id.GLayout);
        highDTable = (LinearLayout) findViewById(R.id.DLayout);
        highATable = (LinearLayout) findViewById(R.id.ALayout);
        lowETable = (LinearLayout) findViewById(R.id.lowELayout);
        if (savedInstanceState == null) {

        }

        setButtonOnClickListeners();
    }
    private void setButtonOnClickListeners(){
        ((Button) findViewById(R.id.startButton)).setOnClickListener(startClicker);
        ((Button) findViewById(R.id.clearButton)).setOnClickListener(clearClicker);
    }

    private View.OnClickListener startClicker = new View.OnClickListener(){

        @Override
        public void onClick(View view) {
                wrapperFunc();
        }
    };


    private View.OnClickListener clearClicker = new View.OnClickListener(){

        @Override
        public void onClick(View view) {
            highBTable.removeAllViews();
            lowETable.removeAllViews();
            highGTable.removeAllViews();
            highDTable.removeAllViews();
            highATable.removeAllViews();
            highETable.removeAllViews();
        }
    };



    private void wrapperFunc(){

        if(!recorderState)
        {


        rawFreqArray = new ArrayList<Integer>();


            Timer t = new Timer();
            recordingTask = new TimerTask(){

            public void run(){
                // TunerModule module = new TunerModule();
                int raw = module.getFrequency();
            Integer rawFreq = new Integer(raw);
            if(rawFreq.intValue() == 0) {
                rawFreqArray.add(new Integer(800)); // 800
            }
            else {
                rawFreqArray.add(rawFreq);
            }

            }

            };


        t.scheduleAtFixedRate(recordingTask, 0 ,200);



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
        ShortestDistCalculations resultArray = new ShortestDistCalculations(rawFreqCoverted);
        return resultArray.getResults();

    }

    private EditText emptyConstructor(){
        EditText empty = new EditText(MainActivity.this);
        empty.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT
                ,ViewGroup.LayoutParams.WRAP_CONTENT));
        empty.setText("  ", TextView.BufferType.EDITABLE);
        empty.setWidth(100);

        return empty;
    }

    private EditText outputConst(String a){

        EditText output = new EditText(MainActivity.this);
        output.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT
                ,ViewGroup.LayoutParams.WRAP_CONTENT));
        output.setText(a, TextView.BufferType.EDITABLE);
        output.setWidth(100);
        return output;

    }

    private void printer(int[] results){


        for (int i = 0; i < results.length; i++)
        {
            int encoded = results[i];
            int string = encoded/100;
            int fretBoxraw = encoded % 100;
            String fretBox = Integer.toString(fretBoxraw);

            if (string == 8) {
                highETable.addView(emptyConstructor());
                highBTable.addView(emptyConstructor());
                highGTable.addView(emptyConstructor());
                highDTable.addView(emptyConstructor());
                highATable.addView(emptyConstructor());
                lowETable.addView(emptyConstructor());
            }

            else {
            switch(string) {
                case 1:
                    highETable.addView(emptyConstructor());
                    highBTable.addView(emptyConstructor());
                    highGTable.addView(emptyConstructor());
                    highDTable.addView(emptyConstructor());
                    highATable.addView(emptyConstructor());
                    lowETable.addView(outputConst(fretBox));
                    break;


                case 2:
                    highETable.addView(emptyConstructor());
                    highBTable.addView(emptyConstructor());
                    highGTable.addView(emptyConstructor());
                    highDTable.addView(emptyConstructor());
                    highATable.addView(outputConst(fretBox));
                    lowETable.addView(emptyConstructor());
                    break;


                case 3:
                    highETable.addView(emptyConstructor());
                    highBTable.addView(emptyConstructor());
                    highGTable.addView(emptyConstructor());
                    highDTable.addView(outputConst(fretBox));
                    highATable.addView(emptyConstructor());
                    lowETable.addView(emptyConstructor());
                    break;


                case 4:
                    highETable.addView(emptyConstructor());
                    highBTable.addView(emptyConstructor());
                    highGTable.addView(outputConst(fretBox));
                    highDTable.addView(emptyConstructor());
                    highATable.addView(emptyConstructor());
                    lowETable.addView(emptyConstructor());
                    break;


                case 5:
                    highETable.addView(emptyConstructor());
                    highBTable.addView(outputConst(fretBox));
                    highGTable.addView(emptyConstructor());
                    highDTable.addView(emptyConstructor());
                    highATable.addView(emptyConstructor());
                    lowETable.addView(emptyConstructor());
                    break;


                case 6:
                    highETable.addView(outputConst(fretBox));
                    highBTable.addView(emptyConstructor());
                    highGTable.addView(emptyConstructor());
                    highDTable.addView(emptyConstructor());
                    highATable.addView(emptyConstructor());
                    lowETable.addView(emptyConstructor());
                    break;

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
