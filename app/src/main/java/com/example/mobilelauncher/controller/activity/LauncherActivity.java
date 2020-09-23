package com.example.mobilelauncher.controller.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.mobilelauncher.R;
import com.example.mobilelauncher.controller.fragment.LauncherFragment;

public class LauncherActivity extends SingleFragmentActivity {


    @Override
    public Fragment createFragment() {
        return LauncherFragment.newInstance();
    }

    @Override
    public void onBackPressed() {

    }
}