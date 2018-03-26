package com.example.jaerencoathup.exampleapp.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jaerencoathup.exampleapp.Dagger.Modules.SecondModule;
import com.example.jaerencoathup.exampleapp.ExampleApplication;
import com.example.jaerencoathup.exampleapp.Mvp.Second;
import com.example.jaerencoathup.exampleapp.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SecondFragment extends ExampleFragment<Second.Presenter> implements Second.View {

    @BindView(R.id.tv_id)
    TextView tvId;

    @BindView(R.id.tv_login)
    TextView tvLogin;

    @BindView(R.id.tv_location)
    TextView tvLocation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
        presenter.onCreate();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    private void injectDependencies() {
        ExampleApplication.getApplicationComponent()
                .plus(new SecondModule(this))
                .inject(this);
    }

    public void onStop() {
        super.onStop();
    }

}
