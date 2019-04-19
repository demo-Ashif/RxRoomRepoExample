package com.example.jaerencoathup.exampleapp.dagger.components;

import com.example.jaerencoathup.exampleapp.dagger.modules.LocationModule;
import com.example.jaerencoathup.exampleapp.fragments.WindFragment;

import dagger.Subcomponent;

/**
 * Created by jaerencoathup on 15/04/2017.
 */

@Subcomponent(modules = {LocationModule.class})
public interface LocationComponent {
    void inject(WindFragment activity);
}
