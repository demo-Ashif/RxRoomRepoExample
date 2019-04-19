package com.example.jaerencoathup.exampleapp.mvp;

import com.example.jaerencoathup.exampleapp.activities.BaseView;
import com.example.jaerencoathup.exampleapp.persistence.WeatherData;

/**
 * Created by jaerencoathup on 15/04/2017.
 */

public interface Location {
    interface View extends BaseView.View{
        void showLocation(WeatherData forecastEntity);
    }

    interface Presenter extends BaseView.Presenter {
        void onBind();
        void changeLocation(String name);
    }
}
