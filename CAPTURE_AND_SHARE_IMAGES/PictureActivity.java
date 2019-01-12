package com.example.aprzybylo.learn;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ShareCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.aprzybylo.learn.Utils.PictureUtils;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PictureActivity extends AppCompatActivity {

    private static final int REQUEST_CAPTURE_PHOTO = 1;

    @BindView(R.id.capture_photo)
    public ImageButton mPhotoButton;

    @BindView(R.id.share_photo)
    public Button mShareButton;

    @BindView(R.id.photo)
    public ImageView mPhotoView;

    private File mPhotoFile;
    private Uri mUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);
        ButterKnife.bind(this);

        mPhotoFile = getPhotoFile();
        mUri = FileProvider.getUriForFile(this, getPackageName(), mPhotoFile);

        captureImage();
        shareImage();

    }

    public File getPhotoFile() {
        File filesDir = getFilesDir();
        return new File(filesDir, "image.jpg");
    }


    public void captureImage(){

        final Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mUri);

        List<ResolveInfo> cameraActivities = getPackageManager().queryIntentActivities(captureIntent,
                PackageManager.MATCH_DEFAULT_ONLY);

        for (ResolveInfo activity : cameraActivities) {
            grantUriPermission(activity.activityInfo.packageName, mUri,
                    Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        }

        mPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult( captureIntent, REQUEST_CAPTURE_PHOTO );
            }
        });

    }


    public void shareImage() {

        final Intent shareIntent = ShareCompat.IntentBuilder.from(this)
                .setStream(mUri)
                .getIntent();
        shareIntent.setData(mUri);
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        boolean canSharePhoto = mPhotoFile != null &&
                shareIntent.resolveActivity(getPackageManager()) != null;
        mShareButton.setEnabled(canSharePhoto);

        mShareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(shareIntent);
            }
        });

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == REQUEST_CAPTURE_PHOTO) {
            revokeUriPermission(mUri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

            updatePhotoView();
        }
    }


    private void updatePhotoView() {

        if (mPhotoFile == null || !mPhotoFile.exists()) {
            mPhotoView.setImageDrawable(null);
        } else {
            Bitmap bitmap = PictureUtils.getScaledBitmap(mPhotoFile.getPath(), this);
            mPhotoView.setImageBitmap(bitmap);
        }
    }

}
