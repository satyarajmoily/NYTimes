package com.satyaraj.app.nytimes.base;

public class BaseRepository<T extends BasePresenter> {

    private T actions;

    public void onAttach(T actions) {
        this.actions = actions;
    }

    public T getActions() {
        return actions;
    }

}
