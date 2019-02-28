package com.satyaraj.app.nytimes.fragment.section;

import com.satyaraj.app.nytimes.pojo.Stories;

import java.util.List;

public interface SectionContract {

    interface View{
        void displayStories(List<Stories> stories);
    }

    interface Action{
        void getStories(String section);
    }
}
