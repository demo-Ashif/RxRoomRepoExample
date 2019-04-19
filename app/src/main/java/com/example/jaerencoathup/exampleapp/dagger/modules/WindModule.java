package com.example.jaerencoathup.exampleapp.dagger.modules;

import com.example.jaerencoathup.exampleapp.mvp.Location;
import com.example.jaerencoathup.exampleapp.mvp.presenters.LocationPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jaerencoathup on 15/04/2017.
 */

@Module
public class WindModule {

    Location.View view;

    public WindModule(Location.View view) {
        this.view = view;
    }

    @Provides
    public Location.View providesView() {
        return view;
    }

    @Provides
    public Location.Presenter providesPresenter(LocationPresenter mainPresenter) {
        return mainPresenter;
    }
}
