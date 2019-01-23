package com.example.owi.wallet.fonts;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

public class OSRegEditText extends EditText {

    public OSRegEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public OSRegEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public OSRegEditText(Context context) {
        super(context);
        init();
    }

    private void init() {
        Typeface OpenSansReg = Typeface.createFromAsset(getContext().getAssets(), "fonts/OS-Reg.ttf");
        setTypeface(OpenSansReg);
    }

}