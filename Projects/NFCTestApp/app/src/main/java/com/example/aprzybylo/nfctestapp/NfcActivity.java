package com.example.aprzybylo.nfctestapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.MifareUltralight;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import java.util.Arrays;
import java.util.List;

/**
 * Class responsible for reading NFC TAG
 *
 */

public class NfcActivity extends AppCompatActivity {

    public static final String TAG = "NfcActivity";

    private String mRFid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc_background);

        mRFid = performTagReading(getIntent());
        if( mRFid != null ) {
            serverValidation(mRFid);
        }
    }

    private String performTagReading(Intent intent) {

        String mifareUltralightType = MifareUltralight.class.getName();

        if (NfcAdapter.ACTION_TECH_DISCOVERED.equals(intent.getAction())) {
            Tag nfcTag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);

            if (nfcTag != null) {
                List<String> techList = Arrays.asList(nfcTag.getTechList());

                if (techList.contains(mifareUltralightType)) {
                    return readMifareUltralight(nfcTag);
                }
            }
        }

        return null;
    }

    private String readMifareUltralight(Tag tag) {

        byte[] payload = tag.getId();
        String rfid = bytesToHex(payload);

        return rfid;
    }

    /* Conversion to the right format */
    private static String bytesToHex(byte[] payload) {
        StringBuilder builder = new StringBuilder();
        for (byte b : payload) {
            builder.insert(0, String.format("%02x", b));
        }
        return builder.toString();
    }


    private void serverValidation(String mRFid) {
        // Server Validation
        // Show dialog depending on result
        showDialogMessage(R.string.nfc_dialog_title, R.string.nfc_dialog_success);
    }

    private void showDialogMessage(int title, int message){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }

}
