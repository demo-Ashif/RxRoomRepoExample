package com.example.jaerencoathup.exampleapp.interactors.Network;

import com.example.jaerencoathup.exampleapp.interactors.Database.WeatherDatabaseInteractor;
import com.example.jaerencoathup.exampleapp.networking.ApiService;
import com.example.jaerencoathup.exampleapp.interactors.Memory.WeatherMemoryInteractor;
import com.example.jaerencoathup.exampleapp.persistence.WeatherData;
import com.example.jaerencoathup.exampleapp.session.SessionService;

import javax.inject.Inject;
import io.reactivex.Single;
import timber.log.Timber;

/**
 * Created by jaerencoathup on 25/03/2018.
 */

public class WeatherNetworkInteractorImpl implements WeatherNetworkInteractor {

    private ApiService apiService;
    private SessionService sessionService;
    private WeatherDatabaseInteractor databaseInteractor;
    private WeatherMemoryInteractor memoryInteractor;

    @Inject
    public WeatherNetworkInteractorImpl(ApiService apiService,
                                        SessionService sessionService,
                                        WeatherMemoryInteractor memoryInteractor,
                                        WeatherDatabaseInteractor databaseInteractor) {
        this.apiService = apiService;
        this.sessionService = sessionService;
        this.memoryInteractor = memoryInteractor;
        this.databaseInteractor = databaseInteractor;
    }

    @Override
    public Single<WeatherData> getWeatherData(String city) {
        Timber.d("class:%s method:%s", "WeatherNetworkInteractorImpl", "getWeatherData(String city)");

        return apiService.getWeather(city)
                 .map(WeatherData::copyFromResponse)
                .doOnSuccess(data -> sessionService.saveLocation(data.name))
                .doOnSuccess(databaseInteractor::saveData)
                .doOnSuccess(memoryInteractor::saveData);
    }
}
