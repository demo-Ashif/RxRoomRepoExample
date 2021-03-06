package com.example.jaerencoathup.exampleapp.fragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;

import com.example.jaerencoathup.exampleapp.activities.BaseView;

import javax.inject.Inject;

import androidx.fragment.app.Fragment;

/**
 * Created by jaerencoathup on 15/05/2017.
 */

public class BaseFragment<T extends BaseView.Presenter> extends Fragment implements BaseView.View {

    @Inject
    T presenter;

    ProgressDialog progressDialog;
    public void showLoading(boolean show) {

        if (show) {
            if (progressDialog == null) {
                progressDialog = ProgressDialog.show(getActivity(), null, "Loading");
            } else {
                progressDialog.show();
            }

        } else {
            progressDialog.dismiss();
        }

    }

    @Override
    public void showError() {
        AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();
        dialog.setMessage("ERROR");
        dialog.show();
    }

    @Override
    public void showMessage(String message) {
        AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();
        dialog.setMessage(message);
        dialog.show();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (presenter != null) {
            presenter.onStop();
        }
    }
}
