package com.satyaraj.app.nytimes.fragment.section;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.satyaraj.app.nytimes.MainActivity;
import com.satyaraj.app.nytimes.R;
import com.satyaraj.app.nytimes.base.BaseFragment;
import com.satyaraj.app.nytimes.fragment.detail.DetailFragment;
import com.satyaraj.app.nytimes.fragment.section.di.DaggerSectionComponent;
import com.satyaraj.app.nytimes.fragment.section.di.SectionComponent;
import com.satyaraj.app.nytimes.fragment.section.di.SectionModule;
import com.satyaraj.app.nytimes.pojo.Stories;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class SectionFragment extends BaseFragment<MainActivity> implements ClickListener, SectionContract.View, View.OnClickListener {

    private RecyclerView mRecyclerView;
    private SectionAdapter mSectionAdapter;
    private TextView science, technology, business, world, movies, travel;
    private Animation fabOpen, fabClose;
    private ConstraintLayout fabBackground;
    private SwipeRefreshLayout swipeRefreshLayout;
    private String topic;
    private TextView loading;

    private boolean isOpen;

    @Inject
    SectionPresenter mSectionPresenter;

    public SectionFragment() {
    }

    public static SectionFragment getSectionNewInstance(){
        return new SectionFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_section, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        attachParent((MainActivity) getActivity());
        initViews(view);

         SectionComponent appComponent = DaggerSectionComponent
                .builder()
                .sectionModule(new SectionModule(this))
                .build();

         appComponent.injectSectionComponent(this);

         topic = "home";

         getStories(topic);
    }

    private void initViews(View view) {
        mRecyclerView = view.findViewById(R.id.recycler_view);
        science = view.findViewById(R.id.science);
        technology = view.findViewById(R.id.technology);
        travel = view.findViewById(R.id.travel);
        movies = view.findViewById(R.id.movies);
        world = view.findViewById(R.id.world);
        business = view.findViewById(R.id.business);
        loading = view.findViewById(R.id.loading);
        fabBackground = view.findViewById(R.id.fab_background);
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh);
        mSectionAdapter = new SectionAdapter(this);

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mSectionAdapter);
        FloatingActionButton floatingActionButton = view.findViewById(R.id.fab);
        fabOpen = AnimationUtils.loadAnimation(getContext(), R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(getContext(), R.anim.fab_close);
        floatingActionButton.setOnClickListener(this);
        science.setOnClickListener(this);
        technology.setOnClickListener(this);
        travel.setOnClickListener(this);
        movies.setOnClickListener(this);
        business.setOnClickListener(this);
        world.setOnClickListener(this);
        fabBackground.setOnClickListener(this);

        swipeRefreshLayout.setOnRefreshListener(() -> getStories(topic)
        );
    }

    private void getStories(String section) {
        mSectionPresenter.getStories(section);
    }

    @Override
    public void onItemClicked(Stories stories) {
        getParentActivity().switchFragment(DetailFragment.getDetailNewInstance(stories),false);
    }

    @Override
    public void displayStories(List<Stories> storiesList) {
        mSectionAdapter.updateList(storiesList);
        swipeRefreshLayout.setRefreshing(false);
        mRecyclerView.smoothScrollToPosition(0);
        loading.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        if (isOpen) {
            science.setAnimation(fabClose);
            technology.setAnimation(fabClose);
            business.setAnimation(fabClose);
            world.setAnimation(fabClose);
            movies.setAnimation(fabClose);
            travel.setAnimation(fabClose);
            setInvisible(science, technology, business, world, movies, travel);
            isOpen = false;
        } else {
            science.setAnimation(fabOpen);
            technology.setAnimation(fabOpen);
            business.setAnimation(fabOpen);
            world.setAnimation(fabOpen);
            movies.setAnimation(fabOpen);
            travel.setAnimation(fabOpen);

            setVisible(science, technology, business, world, movies, travel);

            isOpen = true;
        }

        switch (v.getId()){
            case R.id.business:
                topic = "business";
                getStories("business");
                break;
            case R.id.travel:
                topic = "travel";
                getStories("travel");
                break;
            case R.id.technology:
                topic = "technology";
                getStories("technology");
                break;
            case R.id.movies:
                topic = "movies";
                getStories("movies");
                break;
            case R.id.science:
                topic = "science";
                getStories("science");
                break;
            case R.id.world:
                topic = "world";
                getStories("world");
                break;
        }
    }

    private void setInvisible(TextView... textView) {
        for (TextView aTextView : textView) {
            aTextView.setVisibility(View.INVISIBLE);
        }
        fabBackground.setVisibility(View.GONE);
    }

    private void setVisible(TextView... textView) {
        for (TextView aTextView : textView) {
            aTextView.setVisibility(View.VISIBLE);
        }
        fabBackground.setVisibility(View.VISIBLE);
    }
}
