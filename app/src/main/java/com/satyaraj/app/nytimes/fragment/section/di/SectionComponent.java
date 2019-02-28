package com.satyaraj.app.nytimes.fragment.section.di;

import com.satyaraj.app.nytimes.fragment.section.SectionFragment;

import dagger.Component;

@Component(modules = SectionModule.class)
public interface SectionComponent {
    void injectSectionComponent(SectionFragment sectionFragment);
}
