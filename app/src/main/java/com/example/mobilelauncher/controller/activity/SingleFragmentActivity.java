package com.example.mobilelauncher.controller.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.mobilelauncher.R;

public abstract class SingleFragmentActivity extends AppCompatActivity {

    public abstract Fragment createFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //inflate layout for activity
        setContentView(R.layout.activity_launcher);

        FragmentManager fragmentManager = getSupportFragmentManager();

        //check if fragment exists in container (configuration changes save the fragments)
        Fragment fragment = fragmentManager.findFragmentById(R.id.activityLauncher_frameLayout_container_listApp);

        //create an add fragment transaction for CrimeDetailFragment
        if (fragment == null) {
            fragmentManager
                    .beginTransaction()
                    .add(R.id.activityLauncher_frameLayout_container_listApp, createFragment())
                    .commit();
        }
    }
}
