package leapfrog_inc.putipro.Fragment.Registration;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.NumberPicker;

import leapfrog_inc.putipro.Fragment.BaseFragment;
import leapfrog_inc.putipro.R;

public class RegistrationProfileFragment extends BaseFragment {

    public static int requestCodeGallery = 1000;
    public static int requestCodePermission = 1001;

    private Uri mImageUri;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {

        View view = inflater.inflate(R.layout.fragment_registration_profile, null);

        initAction(view);

        return view;
    }

    private void initAction(View view) {

        ((ImageButton)view.findViewById(R.id.faceImageView)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickFace();
            }
        });

        ((Button)view.findViewById(R.id.genderButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stackFragment(new RegistrationGenderPickerFragment(), AnimationType.none);
            }
        });

        ((ImageButton)view.findViewById(R.id.backButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popFragment(AnimationType.horizontal);
            }
        });

        ((Button)view.findViewById(R.id.nextButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stackFragment(new RegistrationInterviewFragment(), AnimationType.horizontal);
            }
        });
    }

    private void onClickFace() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                openGallery();
            }
            else{
                ActivityCompat.requestPermissions(getActivity(), new String[]{ Manifest.permission.WRITE_EXTERNAL_STORAGE }, requestCodePermission);
            }
        } else {
            openGallery();
        }
    }

    private void openGallery() {

        Intent intentGallery = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intentGallery.addCategory(Intent.CATEGORY_OPENABLE);
        intentGallery.setType("image/jpeg");

        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Images.Media.TITLE, "tmp.jpg");
        contentValues.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        mImageUri = getActivity().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
        Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);

        Intent intent = Intent.createChooser(intentCamera, "画像の選択");
        intent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {intentGallery});
        getActivity().startActivityForResult(intent, requestCodeGallery);
    }

    public void didGrantPermission() {
        openGallery();
    }

    public void didSelectImage(Intent data) {

        Uri uri = null;
        if (data != null) {
            Uri dataUri = data.getData();
            if (dataUri != null) {
                uri = dataUri;
            }
        }
        if (uri == null) {
            uri = mImageUri;
        }
        if(uri == null) {
            return;
        }
        MediaScannerConnection.scanFile(getActivity(), new String[]{ uri.getPath() }, new String[]{"image/jpeg"}, null);

        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
        } catch (Exception e) {
            return;
        }
        int bmpWidth = bitmap.getWidth();
        Matrix scale = new Matrix();
        scale.postScale((300.0f / (float)bmpWidth), (300.0f / (float)bmpWidth));
        Bitmap resizeBmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), scale, false);
        bitmap.recycle();
        bitmap = null;

        View view = getView();

        ((ImageButton)view.findViewById(R.id.faceImageView)).setImageBitmap(resizeBmp);
    }
}
