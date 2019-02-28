package com.satyaraj.app.nytimes.base;

import androidx.fragment.app.Fragment;

public abstract class BaseFragment<T extends BaseActivity> extends Fragment {

    private T parentActivity;

    public BaseFragment() {
        //Required empty public constructor
    }

    public T getParentActivity() {
        return parentActivity;
    }

    protected void attachParent(T parentActivity) {
        this.parentActivity = parentActivity;
    }

}