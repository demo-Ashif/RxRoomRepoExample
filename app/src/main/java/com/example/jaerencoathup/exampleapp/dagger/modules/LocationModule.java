package com.example.jaerencoathup.exampleapp.dagger.modules;

import com.example.jaerencoathup.exampleapp.mvp.Wind;
import com.example.jaerencoathup.exampleapp.mvp.presenters.WindPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jaerencoathup on 15/04/2017.
 */

@Module
public class LocationModule {

    Wind.View view;

    public LocationModule(Wind.View view) {
        this.view = view;
    }

    @Provides
    public Wind.View providesView() {
        return view;
    }

    @Provides
    public Wind.Presenter providesPresenter(WindPresenter followersPresenter) {
        return followersPresenter;
    }
}
