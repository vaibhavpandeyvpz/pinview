package com.vaibhavpandey.pinview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

abstract public class PinViewActivity extends AppCompatActivity implements PinView.PinViewListener {

    @Override
    public void onBackPressed() {
        onPinCancelled();
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.pv_activity);
        PinView view = (PinView) findViewById(R.id.pv_activity_view);
        assert view != null;
        view.setPinViewListener(this);
    }

    @Override
    public void onPinCancelled() {
        setResult(RESULT_CANCELED);
        finish();
    }
}
