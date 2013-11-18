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
import android.widget.EditText;
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

import com.tabituiuc.tabit.TunerModule;

public class MainActivity extends ActionBarActivity {

    private TableLayout scorePrintingXScrollView;
    private List<Integer> rawFreqArray;
    private TimerTask recordingTask;
    private Boolean recorderState = false;
    private EditText[] highEString;
    private EditText[] highBString;
    private EditText[] highGString;
    private EditText[] highDString;
    private EditText[] highAString;
    private EditText[] lowEString;
    private Spinner rhythmSelection;

    private TableRow highETable;
    private TableRow highBTable;
    private TableRow highGTable;
    private TableRow highDTable;
    private TableRow highATable;
    private TableRow lowETable;
    private SeekBar tempoSeek;
    private TextView tempoValue;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scorePrintingXScrollView = (TableLayout) findViewById(R.id.scorePrintingXScrollView);
        highETable = (TableRow) findViewById(R.id.tableRowhE);
        highBTable = (TableRow) findViewById(R.id.tableRowB);
        highGTable = (TableRow) findViewById(R.id.tableRowG);
        highDTable = (TableRow) findViewById(R.id.tableRowD);
        highATable = (TableRow) findViewById(R.id.tableRowA);
        lowETable = (TableRow) findViewById(R.id.tableRowlE);
        tempoSeek = (SeekBar) findViewById(R.id.tempoBar);
        tempoValue = (TextView) findViewById(R.id.tempoText);

        if (savedInstanceState == null) {
            /* getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
                    */

        }

        setButtonOnClickListeners();
        tempoSeek.setOnSeekBarChangeListener(tempoChangeListner);
    }

    private SeekBar.OnSeekBarChangeListener tempoChangeListner = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int stringValue = seekBar.getProgress() + 80; // to be tested
                tempoValue.setText(stringValue);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            // do nothing
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            // do nothing
        }
    };

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

        if(!recorderState)
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

        highEString = new EditText[results.length];
        highBString = new EditText[results.length];
        highGString = new EditText[results.length];
        highDString = new EditText[results.length];
        highAString = new EditText[results.length];
         lowEString = new EditText[results.length];

        EditText[][] wrapper = new EditText[][]{highEString, highBString, highGString, highDString, highAString, lowEString};
        EditText empty = new EditText(this);
        empty.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT
                ,ViewGroup.LayoutParams.WRAP_CONTENT));
        empty.setText(" ");


        for (int i = 0; i < results.length; i++)
        {
            int encoded = results[i];
            int string = encoded/100;
            int fretBox = encoded % 100;
            EditText output = new EditText(this);
            output.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT
                    ,ViewGroup.LayoutParams.WRAP_CONTENT));
            output.setText(fretBox);

            switch(string) {
                case 1:
                    highETable.addView(output);
                    highBTable.addView(empty);
                    highGTable.addView(empty);
                    highDTable.addView(empty);
                    highATable.addView(empty);
                    lowETable.addView(empty);

                case 2:
                    highETable.addView(empty);
                    highBTable.addView(output);
                    highGTable.addView(empty);
                    highDTable.addView(empty);
                    highATable.addView(empty);
                    lowETable.addView(empty);

                case 3:
                    highETable.addView(empty);
                    highBTable.addView(empty);
                    highGTable.addView(output);
                    highDTable.addView(empty);
                    highATable.addView(empty);
                    lowETable.addView(empty);

                case 4:
                    highETable.addView(empty);
                    highBTable.addView(empty);
                    highGTable.addView(empty);
                    highDTable.addView(output);
                    highATable.addView(empty);
                    lowETable.addView(empty);

                case 5:
                    highETable.addView(empty);
                    highBTable.addView(empty);
                    highGTable.addView(empty);
                    highDTable.addView(empty);
                    highATable.addView(output);
                    lowETable.addView(empty);

                case 6:
                    highETable.addView(empty);
                    highBTable.addView(empty);
                    highGTable.addView(empty);
                    highDTable.addView(empty);
                    highATable.addView(empty);
                    lowETable.addView(output);
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
