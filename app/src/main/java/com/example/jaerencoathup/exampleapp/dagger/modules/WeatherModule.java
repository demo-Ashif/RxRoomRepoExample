package com.example.jaerencoathup.exampleapp.dagger.modules;

import com.example.jaerencoathup.exampleapp.mvp.Weather;
import com.example.jaerencoathup.exampleapp.mvp.presenters.WeatherPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jaerencoathup on 15/04/2017.
 */

@Module
public class WeatherModule {

    Weather.View view;

    public WeatherModule(Weather.View view) {
        this.view = view;
    }

    @Provides
    public Weather.View providesView() {
        return view;
    }

    @Provides
    public Weather.Presenter providesPresenter(WeatherPresenter presenter) {
        return presenter;
    }
}
