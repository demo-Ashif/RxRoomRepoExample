package com.example.jaerencoathup.exampleapp.mvp.presenters;

import com.example.jaerencoathup.exampleapp.mvp.Weather;
import com.example.jaerencoathup.exampleapp.repositories.weather.WeatherRepository;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class WeatherPresenter extends BasePresenter<Weather.View> implements Weather.Presenter {

    WeatherRepository weatherRepository;

    @Inject
    public WeatherPresenter(Weather.View view, WeatherRepository weatherRepository) {
        this.view = view;
        this.weatherRepository = weatherRepository;
    }

    @Override
    public void onBind() {
        composites.add(weatherRepository.getForecastData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(view::showWeather));
    }
}