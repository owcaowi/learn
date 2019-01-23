package com.example.owi.wallet.fonts;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

public class OSLightEditText extends EditText {

    public OSLightEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public OSLightEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public OSLightEditText(Context context) {
        super(context);
        init();
    }

    private void init() {
        Typeface OpenSansLight = Typeface.createFromAsset(getContext().getAssets(), "fonts/OS-Light.ttf");
        setTypeface(OpenSansLight);
    }

}