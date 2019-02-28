package com.satyaraj.app.nytimes.fragment.section;

import com.satyaraj.app.nytimes.base.BasePresenter;
import com.satyaraj.app.nytimes.pojo.Stories;

import java.util.List;

import javax.inject.Inject;

public class SectionPresenter extends BasePresenter implements SectionContract.Action{

    private SectionContract.View mView;
    private SectionRepository mRepository;

    @Inject
    public SectionPresenter(SectionFragment view, SectionRepository mRepository) {
        this.mView = view;
        this.mRepository = mRepository;
        this.mRepository.onAttach(this);
    }

    @Override
    public void getStories(String section) {
        mRepository.getStoriesFromDatabase(section);
    }

    public void fetchedStories(List<Stories> stories) {
        mView.displayStories(stories);
    }
}
