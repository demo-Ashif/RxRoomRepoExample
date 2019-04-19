package com.example.jaerencoathup.exampleapp.dagger.components;

import com.example.jaerencoathup.exampleapp.dagger.modules.WeatherModule;
import com.example.jaerencoathup.exampleapp.fragments.WeatherFragment;

import dagger.Subcomponent;

/**
 * Created by jaerencoathup on 15/04/2017.
 */

@Subcomponent(modules = {WeatherModule.class})
public interface WeatherComponent {
    void inject(WeatherFragment activity);
}
