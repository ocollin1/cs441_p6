package com.example.cs441_p6;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements OptionsFragment.OnFragmentInteractionListener, TestsFragment.OnListFragmentInteractionListener, ResultsFragment.OnFragmentInteractionListener {

    private TextView mTextMessage;

    OptionsFragment optionsFragment = new OptionsFragment();
    TestsFragment testsFragment = new TestsFragment();
    ResultsFragment resultsFragment = new ResultsFragment();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {


            switch (item.getItemId()) {
                case R.id.navigation_options:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, optionsFragment).commit();
                    mTextMessage.setText(R.string.title_options);
                    return true;
                case R.id.navigation_tests:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, testsFragment).commit();
                    mTextMessage.setText(R.string.title_test);
                    return true;
                case R.id.navigation_results:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, resultsFragment).commit();
                    mTextMessage.setText(R.string.title_results);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
