package com.vaibhavpandey.pinview;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.GridLayout;
import android.widget.EditText;

public class PinView extends GridLayout implements View.OnClickListener {

    protected Button mButtonCancel, mButtonClear;

    protected Button mButtonDigit0, mButtonDigit1, mButtonDigit2, mButtonDigit3, mButtonDigit4,
            mButtonDigit5, mButtonDigit6, mButtonDigit7, mButtonDigit8, mButtonDigit9;

    protected int mLimit;

    protected PinViewListener mPinViewListener;

    protected EditText mTextPin;

    public interface PinViewListener {

        void onPinCancelled();

        void onPinEntered(String pin);

    }

    public PinView(Context context) {
        this(context, null);
    }

    public PinView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PinView(Context context, AttributeSet attrs, int attr) {
        super(context, attrs, attr);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.PinView, 0, 0);
        try {
            mLimit = a.getInt(R.styleable.PinView_limit, 4);
        } finally {
            a.recycle();
        }
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.pv_view, this);
    }

    public int getLimit() {
        return mLimit;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.pv_keypad_digit_0) {
            mTextPin.append("0");
        } else if (i == R.id.pv_keypad_digit_1) {
            mTextPin.append("1");
        } else if (i == R.id.pv_keypad_digit_2) {
            mTextPin.append("2");
        } else if (i == R.id.pv_keypad_digit_3) {
            mTextPin.append("3");
        } else if (i == R.id.pv_keypad_digit_4) {
            mTextPin.append("4");
        } else if (i == R.id.pv_keypad_digit_5) {
            mTextPin.append("5");
        } else if (i == R.id.pv_keypad_digit_6) {
            mTextPin.append("6");
        } else if (i == R.id.pv_keypad_digit_7) {
            mTextPin.append("7");
        } else if (i == R.id.pv_keypad_digit_8) {
            mTextPin.append("8");
        } else if (i == R.id.pv_keypad_digit_9) {
            mTextPin.append("9");
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mButtonCancel = (Button) findViewById(R.id.pv_keypad_cancel);
        mButtonCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPinViewListener != null) {
                    mPinViewListener.onPinCancelled();
                }
            }
        });
        mButtonClear = (Button) findViewById(R.id.pv_keypad_clear);
        mButtonClear.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mTextPin.setText(null);
            }
        });
        mButtonDigit0 = (Button) findViewById(R.id.pv_keypad_digit_0);
        mButtonDigit0.setOnClickListener(this);
        mButtonDigit1 = (Button) findViewById(R.id.pv_keypad_digit_1);
        mButtonDigit1.setOnClickListener(this);
        mButtonDigit2 = (Button) findViewById(R.id.pv_keypad_digit_2);
        mButtonDigit2.setOnClickListener(this);
        mButtonDigit3 = (Button) findViewById(R.id.pv_keypad_digit_3);
        mButtonDigit3.setOnClickListener(this);
        mButtonDigit4 = (Button) findViewById(R.id.pv_keypad_digit_4);
        mButtonDigit4.setOnClickListener(this);
        mButtonDigit5 = (Button) findViewById(R.id.pv_keypad_digit_5);
        mButtonDigit5.setOnClickListener(this);
        mButtonDigit6 = (Button) findViewById(R.id.pv_keypad_digit_6);
        mButtonDigit6.setOnClickListener(this);
        mButtonDigit7 = (Button) findViewById(R.id.pv_keypad_digit_7);
        mButtonDigit7.setOnClickListener(this);
        mButtonDigit8 = (Button) findViewById(R.id.pv_keypad_digit_8);
        mButtonDigit8.setOnClickListener(this);
        mButtonDigit9 = (Button) findViewById(R.id.pv_keypad_digit_9);
        mButtonDigit9.setOnClickListener(this);
        mTextPin = (EditText) findViewById(R.id.pv_pin_text);
        mTextPin.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > mLimit) {
                    mTextPin.setText(s.replace(mLimit, s.length(), ""));
                } else if (s.length() == mLimit) {
                    mTextPin.setText(null);
                    if (mPinViewListener != null) {
                        mPinViewListener.onPinEntered(s.toString());
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
    }

    public void setLimit(int limit) {
        mLimit = limit;
        requestLayout();
        invalidate();
    }

    public void setPinViewListener(PinViewListener listener) {
        mPinViewListener = listener;
    }
}
