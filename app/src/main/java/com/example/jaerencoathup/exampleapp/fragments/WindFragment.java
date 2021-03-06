package com.example.jaerencoathup.exampleapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jaerencoathup.exampleapp.dagger.modules.LocationModule;
import com.example.jaerencoathup.exampleapp.ExampleApplication;
import com.example.jaerencoathup.exampleapp.mvp.Wind;
import com.example.jaerencoathup.exampleapp.R;
import com.example.jaerencoathup.exampleapp.persistence.WeatherData;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WindFragment extends BaseFragment<Wind.Presenter> implements Wind.View {

    @BindView(R.id.tv_wind_speed)
    TextView tvWindSpeed;

    @BindView(R.id.tv_wind_direction)
    TextView tvWindDirection;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
        presenter.onBind();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        ButterKnife.bind(this, view);
        presenter.onBind();
        return view;
    }

    private void injectDependencies() {
        ExampleApplication.getApplicationComponent().plus(new LocationModule(this)).inject(this);
    }

    @Override
    public void showWind(WeatherData forecastEntity) {
        tvWindSpeed.setText(String.format(getString(R.string.wind_speed_format), forecastEntity.wind.speed));
        tvWindDirection.setText(String.format(getString(R.string.wind_direction_format), forecastEntity.wind.deg));
    }
}
