package com.example.jaerencoathup.exampleapp.dagger.components;

import com.example.jaerencoathup.exampleapp.dagger.modules.WindModule;
import com.example.jaerencoathup.exampleapp.fragments.LocationFragment;

import dagger.Subcomponent;

/**
 * Created by jaerencoathup on 15/04/2017.
 */

@Subcomponent(modules = {WindModule.class})
public interface WindComponent {
    void inject(LocationFragment activity);
}
