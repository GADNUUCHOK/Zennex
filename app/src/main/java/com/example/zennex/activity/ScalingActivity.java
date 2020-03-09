package com.example.zennex.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.zennex.presenter.ScalingPresenter;
import com.example.zennex.R;
import com.example.zennex.view.ScalingViewInterface;

import java.util.Objects;

public class ScalingActivity extends MvpAppCompatActivity implements ScalingViewInterface, View.OnClickListener {

    public static final String IMAGE = "image";

    private ImageButton mImageButtonCamera;
    private ImageButton mImageButtonGallery;
    @InjectPresenter
    ScalingPresenter mScalingPresenter;
    public static final String TAG = "Tag";
    /** Вызов камеры */
    public static final int CAMERA_REQUEST = 0;
    /** Вызов изображений из памяти смартфона */
    public static final int SELECT_PICTURE = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scaling);
        mImageButtonCamera = findViewById(R.id.ib_camera);
        mImageButtonGallery = findViewById(R.id.ib_gallery);
        mImageButtonCamera.setOnClickListener(this);
        mImageButtonGallery.setOnClickListener(this);
//        mScalingPresenter = new ScalingPresenter(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_camera:
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
                break;
                case R.id.ib_gallery:
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent,"Select Picture"), ScalingActivity.SELECT_PICTURE);
                    break;
        }
//        mScalingPresenter.setOnClickButton((ImageButton) v);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) Objects.requireNonNull(data.getExtras()).get("data");
            Log.d(TAG, "Data intent " + photo);
            Intent intent = new Intent(this, ImageActivity.class);
            intent.putExtra(IMAGE, photo);
            Log.d(TAG, "Data intent " + intent.getData());
            startActivity(intent);
//            startActivityForResult(intent, CAMERA_REQUEST);

//            OrderOperationProduction.Image makeImage = new OrderOperationProduction.Image(
//                    0, "", "", photo);
//            sOrderOperation.addImage(makeImage);
//            Log.d(ExtraMethods.LOG_TAG, "onActivityResult() " + sOrderOperation.getNotDeletedImagesList().size());
//            currentImage = makeImage;
//            mPhotoComment.setImageBitmap(photo);
//            mRelativeImagesComment.setVisibility(com.example.zennex.View.VISIBLE);
        }
        if (resultCode == Activity.RESULT_OK && requestCode == SELECT_PICTURE) {
            Uri selectedImageUri = data.getData();
            Log.d(TAG, "Data intent " + data);
            Intent intent = new Intent(this, ImageActivity.class);
            //intent.putExtra(IMAGE, selectedImageUri);
            intent.setData(selectedImageUri);
            Log.d(TAG, "Data intent " + intent.getData());
            startActivity(intent);
//            mPhotoComment.setImageURI(selectedImageUri);
//            mRelativeImagesComment.setVisibility(com.example.zennex.View.VISIBLE);
        }
    }
}
