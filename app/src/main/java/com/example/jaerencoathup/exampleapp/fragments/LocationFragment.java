package com.example.jaerencoathup.exampleapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jaerencoathup.exampleapp.dagger.modules.WindModule;
import com.example.jaerencoathup.exampleapp.ExampleApplication;
import com.example.jaerencoathup.exampleapp.mvp.Location;
import com.example.jaerencoathup.exampleapp.R;
import com.example.jaerencoathup.exampleapp.persistence.WeatherData;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LocationFragment extends BaseFragment<Location.Presenter> implements Location.View {

    @BindView(R.id.tv_location_name)
    TextView tvLocationName;

    @BindView(R.id.tv_location_coords)
    TextView tvLocationCoords;

    @BindView(R.id.et_city_name)
    EditText etCityName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
        presenter.onBind();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_third, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    private void injectDependencies() {
        ExampleApplication.getApplicationComponent()
                .plus(new WindModule(this))
                .inject(this);
    }

    @Override
    public void showLocation(WeatherData forecastEntity) {
        tvLocationName.setText(String.format(getString(R.string.location_format), forecastEntity.name, forecastEntity.country));
        tvLocationCoords.setText(String.format(getString(R.string.coords_format), forecastEntity.coord.lat, forecastEntity.coord.lon));
    }

    @OnClick(R.id.btn_login)
    public void btnLocationClick() {
        presenter.changeLocation(etCityName.getText().toString());
    }
}
