package com.example.jaerencoathup.exampleapp.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import androidx.appcompat.app.AppCompatActivity;

import javax.inject.Inject;

/**
 * Created by jaerencoathup on 15/05/2017.
 */

public class BaseActivity<T extends BaseView.Presenter> extends AppCompatActivity implements BaseView.View {

    @Inject
    T presenter;

    ProgressDialog progressDialog;
    public void showLoading(boolean show) {

        if (show) {
            if (progressDialog == null) {
                progressDialog = ProgressDialog.show(this, null, "Loading");
            } else {
                progressDialog.show();
            }

        } else {
            progressDialog.dismiss();
        }

    }

    @Override
    public void showError() {
        AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.setMessage("ERROR");
        dialog.show();
    }

    @Override
    public void showMessage(String message) {
        AlertDialog dialog = new AlertDialog.Builder(this).create();
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
