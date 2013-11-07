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

import com.tabituiuc.tabit.TunerModule;

//test edit
//test edit

public class MainActivity extends ActionBarActivity {

    @Override

    private TableLayout scorePrintingXScrollView;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scorePrintingXScrollView = (TableLayout) findViewById(R.id.scorePrintingXScrollView);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

        setButtonOnClickListeners();
    }

    private void setButtonOnClickListeners(){
        ((Button) findViewById(R.id.startButton)).setOnClickListener(startClicker);
    }

    private View.OnClickListener startClicker = new View.OnClickListener(){

        @Override
        public void onClick(View view) {
           startNewIntent();
        }
    }

    private void startNewIntent(){
        Intent scorePrinting = new Intent(MainActivity.this, ScorePrintingActivity.class);
        startActivity(scorePrinting);
        //  TunerModule.accessRecording(); to be fixed
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
