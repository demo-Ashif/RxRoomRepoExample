package com.example.jaerencoathup.exampleapp.repositories.weather;

import com.example.jaerencoathup.exampleapp.interactors.Database.WeatherDatabaseInteractor;
import com.example.jaerencoathup.exampleapp.interactors.Memory.WeatherMemoryInteractor;
import com.example.jaerencoathup.exampleapp.interactors.Network.WeatherNetworkInteractor;
import com.example.jaerencoathup.exampleapp.persistence.WeatherData;
import com.example.jaerencoathup.exampleapp.repositories.BaseRepository;
import com.example.jaerencoathup.exampleapp.session.SessionService;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;


/**
 * Created by jaerencoathup on 03/11/2017.
 */

public class WeatherRepositoryImpl extends BaseRepository implements WeatherRepository {

    private SessionService sessionService;

    private Disposable dataProviderDisposable;

    private WeatherNetworkInteractor networkInteractor;

    private WeatherDatabaseInteractor databaseInteractor;

    private WeatherMemoryInteractor memoryInteractor;

    @Inject
    public WeatherRepositoryImpl(SessionService sessionService,
                                 WeatherNetworkInteractor networkInteractor,
                                 WeatherDatabaseInteractor databaseInteractor,
                                 WeatherMemoryInteractor memoryInteractor) {
        this.sessionService = sessionService;
        this.networkInteractor = networkInteractor;
        this.databaseInteractor = databaseInteractor;
        this.memoryInteractor = memoryInteractor;
    }

    @Override
    public Observable<WeatherData> getForecastData() {
        Timber.d("class:%s method:%s", "WeatherRepositoryImpl", "getForecastData()");

        String currentLocationName = sessionService.getLocation();
        Observable<WeatherData> memoryObservable = memoryInteractor.getWeatherData().toObservable();
        Observable<WeatherData> databaseObservable = databaseInteractor.getWeatherData(currentLocationName).toObservable();
        Observable<WeatherData> networkObservable = networkInteractor.getWeatherData(currentLocationName).toObservable();
        if (!isNetworkInProgress()) {
            Timber.d("class:%s method:%s", "WeatherRepositoryImpl", "if (!isNetworkInProgress())");
            dataProviderDisposable = Observable.concat(memoryObservable, databaseObservable, networkObservable)
                    .filter(data -> data.name.equals(sessionService.getLocation()))
                    .filter(WeatherData::isDataInDate)
                    .firstElement()
                    .subscribe((boosterData) -> {
                    }, this::handleNonHttpException);
        }

        return memoryInteractor.getWeatherDataObservable();
    }

    private boolean isNetworkInProgress() {
        return dataProviderDisposable != null && !dataProviderDisposable.isDisposed();
    }
}
