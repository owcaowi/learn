package com.example.owi.wallet.fonts;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

public class OSRegButton extends Button {

    public OSRegButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public OSRegButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public OSRegButton(Context context) {
        super(context);
        init();
    }

    private void init() {
        Typeface OpenSansReg = Typeface.createFromAsset(getContext().getAssets(), "fonts/OS-Reg.ttf");
        setTypeface(OpenSansReg);
    }

}