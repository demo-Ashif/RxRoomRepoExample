package com.example.jaerencoathup.exampleapp.interactors.Memory;

import com.example.jaerencoathup.exampleapp.persistence.WeatherData;
import javax.inject.Inject;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import timber.log.Timber;

/**
 * Created by jaerencoathup on 26/03/2018.
 */

public class WeatherMemoryInteractorImpl implements WeatherMemoryInteractor {

    private BehaviorSubject<WeatherData> observable;
    private WeatherData weatherData;

    @Inject
    public WeatherMemoryInteractorImpl() {
        observable = BehaviorSubject.create();
    }

    @Override
    public void saveData(WeatherData weatherData) {
        Timber.d("class:%s method:%s", "WeatherMemoryInteractorImpl",
                "saveData(WeatherData weatherData)");
        this.weatherData = weatherData;
        observable.onNext(weatherData);
    }

    @Override
    public Maybe<WeatherData> getWeatherData() {
        Timber.d("class:%s method:%s", "WeatherMemoryInteractorImpl",
                "getWeatherData()");
        return weatherData == null ? Maybe.empty() : Maybe.just(weatherData);
    }

    @Override
    public Observable<WeatherData> getWeatherDataObservable() {
        Timber.d("class:%s method:%s", "WeatherMemoryInteractorImpl",
                "getWeatherDataObservable()");
        return observable;
    }


}
