package com.example.aprzybylo.nfctestapp;

import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.MifareClassic;
import android.nfc.tech.MifareUltralight;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

public class NfcBackgroundActivity extends AppCompatActivity {

    public static final String TAG = "NFCActivity";

    private String mRFid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc_background);

        mRFid = performTagReading(getIntent());
        checkLicenseId(mRFid);
    }

    private String performTagReading(Intent intent) {

        String mifareUltralightType = MifareUltralight.class.getName();
        String mifareClassicType = MifareClassic.class.getName();

        if (NfcAdapter.ACTION_TECH_DISCOVERED.equals(intent.getAction())) {
            Tag nfcTag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);

            if (nfcTag != null) {
                List<String> techList = Arrays.asList(nfcTag.getTechList());

                if (techList.contains(mifareUltralightType)) {
                    return readMifareUltralight(nfcTag);
                }

                if (techList.contains(mifareClassicType)) {
                    return readMifareClassic(nfcTag);
                }
            }
        }

        return null;
    }

    public String readMifareUltralight(Tag tag) {

        MifareUltralight mifare = MifareUltralight.get(tag);
        String rfid = null;

        try {
            mifare.connect();

            byte[] payload = mifare.readPages(4);
            rfid = getFormattedCode(payload);


        } catch (IOException e) {
            Log.d(TAG, Log.getStackTraceString(e));
        } finally {
            if (mifare != null) {
                try {
                    mifare.close();
                } catch (IOException e) {
                    Log.d(TAG, Log.getStackTraceString(e));
                }
            }
        }
        return rfid;
    }

    private String getFormattedCode(byte[] payload) {

        String format = "%0" + (payload.length * 2) + "X";
        String rfidOriginal = String.format(format, new BigInteger(1, payload));
        String separator = "-";

        String rfidFormatted = new StringBuilder()
                .append(rfidOriginal.substring(10, 12))
                .append(separator)
                .append(rfidOriginal.substring(8, 10))
                .append(separator)
                .append(rfidOriginal.substring(4, 6))
                .append(separator)
                .append(rfidOriginal.substring(2, 4))
                .append(separator)
                .append(rfidOriginal.substring(0, 2))
                .toString();

        return rfidFormatted;
    }

    private void checkLicenseId(String mRFid) {
        //...
    }

    private String readMifareClassic(Tag nfcTag) {
        return new String();
    }

}
