package com.example.jaerencoathup.exampleapp.persistence;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.jaerencoathup.exampleapp.networking.models.CityForecastResponse;

import org.joda.time.DateTime;

/**
 * Created by jaerencoathup on 25/03/2018.
 */
@Entity
public class WeatherData {

    @PrimaryKey
    public int id;
    public DateTime dt;
    public String country;
    public float temp;
    public float pressure;
    public float humidity;
    public float tempMin;
    public float tempMax;
    public String name;
    public int cod;
    @Embedded
    public WeatherData.Coord coord;
    @Embedded
    public WeatherData.Wind wind;
    @Embedded
    public WeatherData.Weather weather;

    public static WeatherData copyFromResponse(CityForecastResponse response) {
        WeatherData weatherEntity = new WeatherData();
        weatherEntity.name = response.name;
        weatherEntity.country = response.sys.country;
        weatherEntity.dt = DateTime.now();
        weatherEntity.temp = response.main.temp;
        weatherEntity.pressure = response.main.pressure;
        weatherEntity.humidity = response.main.humidity;
        weatherEntity.tempMin = response.main.tempMin;
        weatherEntity.tempMax = response.main.tempMax;
        weatherEntity.coord = new Coord(response.coord.lon, response.coord.lat);
        weatherEntity.wind = new Wind(response.wind.speed, response.wind.deg);
        CityForecastResponse.Weather recentWeather = response.weather.get(0);
        weatherEntity.weather = new Weather(recentWeather.main, recentWeather.description, recentWeather.icon);

        return weatherEntity;
    }

    @Entity
    public static class Coord {
        public float lon;
        public float lat;

        public Coord(float lon, float lat) {
            this.lon = lon;
            this.lat = lat;
        }
    }

    @Entity
    public static class Wind {
        public float speed;
        public float deg;

        public Wind(float speed, float deg) {
            this.speed = speed;
            this.deg = deg;
        }
    }

    @Entity
    public static class Weather {
        public String main;
        public String description;
        public String icon;

        public Weather(String main, String description, String icon) {
            this.main = main;
            this.description = description;
            this.icon = icon;
        }
    }

    public boolean isDataInDate() {
        return dt.plusMinutes(1).isAfter(DateTime.now());
    }
}
