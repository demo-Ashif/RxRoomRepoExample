package com.example.jaerencoathup.exampleapp.dagger.components;

import com.example.jaerencoathup.exampleapp.dagger.modules.ApplicationModule;
import com.example.jaerencoathup.exampleapp.dagger.modules.DatabaseModule;
import com.example.jaerencoathup.exampleapp.dagger.modules.WindModule;
import com.example.jaerencoathup.exampleapp.dagger.modules.LocationModule;
import com.example.jaerencoathup.exampleapp.dagger.modules.WeatherModule;
import com.example.jaerencoathup.exampleapp.dagger.modules.NetworkingModule;
import com.example.jaerencoathup.exampleapp.dagger.modules.WeatherRepositoryModule;
import com.example.jaerencoathup.exampleapp.ExampleApplication;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by jaerencoathup on 05/05/2017.
 */
@Component(modules = {
        ApplicationModule.class,
        NetworkingModule.class,
        DatabaseModule.class,
        WeatherRepositoryModule.class})
@Singleton
public interface ApplicationComponent {
    void inject(ExampleApplication exampleApplication);
    WindComponent plus(WindModule module);
    WeatherComponent plus(WeatherModule module);
    LocationComponent plus(LocationModule module);
}
