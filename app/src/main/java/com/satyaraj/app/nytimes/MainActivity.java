package com.satyaraj.app.nytimes;

import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.satyaraj.app.nytimes.base.BaseActivity;
import com.satyaraj.app.nytimes.custom.FragmentTransactionManager;
import com.satyaraj.app.nytimes.fragment.section.SectionFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            switchFragment(SectionFragment.getSectionNewInstance(), false);
        }
    }

    public void switchFragment(Fragment fragment, boolean shouldPop){
        FragmentTransactionManager.addFragment(getSupportFragmentManager(), fragment, shouldPop, R.id.container);
    }
}
