package com.example.zennex.presenter;

import android.app.Activity;
import android.content.Intent;
import android.provider.MediaStore;
import android.widget.ImageButton;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.zennex.activity.ScalingActivity;
import com.example.zennex.R;
import com.example.zennex.view.ScalingViewInterface;

@InjectViewState
public class ScalingPresenter extends MvpPresenter<ScalingViewInterface> {

//    private ScalingActivity mScalingActivity;
//
//    public ScalingPresenter(ScalingActivity scalingActivity) {
//        this.mScalingActivity = scalingActivity;
//    }


    public void setOnClickButton(ImageButton imageButton) {
        switch (imageButton.getId()) {
            case R.id.ib_camera:
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                getViewState().onActivityResult(Activity.RESULT_OK, ScalingActivity.CAMERA_REQUEST, cameraIntent);
//                getViewState().
//                .startActivityForResult(cameraIntent, ScalingActivity.CAMERA_REQUEST);
                break;
            case R.id.ib_gallery:
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);

//                mScalingActivity.startActivityForResult(Intent.createChooser(intent,"Select Picture"), ScalingActivity.SELECT_PICTURE);
                break;
        }
    }
}
