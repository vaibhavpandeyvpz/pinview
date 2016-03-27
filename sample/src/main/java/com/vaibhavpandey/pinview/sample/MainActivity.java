package com.vaibhavpandey.pinview.sample;

import android.support.design.widget.Snackbar;
import android.view.View;

import com.vaibhavpandey.pinview.PinViewActivity;

public class MainActivity extends PinViewActivity {

    @Override
    public void onPinEntered(String pin) {
        View view = findViewById(R.id.pv_activity_layout);
        assert view != null;
        Snackbar.make(view, getString(R.string.message_entered, pin), Snackbar.LENGTH_LONG).show();
    }
}
