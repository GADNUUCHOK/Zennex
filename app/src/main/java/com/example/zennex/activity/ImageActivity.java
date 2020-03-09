package com.example.zennex.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.zennex.R;

public class ImageActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView mImageView;
    ImageButton mImageButtonPlus;
    ImageButton mImageButtonMinus;
    public static final String TAG = "Tag";
    Matrix matrix = new Matrix();
    Matrix savedMatrix = new Matrix();

    // We can be in one of these 3 states
    static final int NONE = 0;
    static final int DRAG = 1;
    static final int ZOOM = 2;
    int mode = NONE;

    // Remember some things for zooming
    PointF start = new PointF();
    PointF mid = new PointF();
    float oldDist = 1f;
    String savedItemClicked;

    int offset = 0, duration = 100;
    float scaleX = 1.0f, scaleY = 1.0f;
    float maxZoomLimit = 2.6f, minZoomLimit = 1.0f;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        mImageView = findViewById(R.id.iv_image);
        mImageButtonPlus = findViewById(R.id.ib_plus);
        mImageButtonPlus.setOnClickListener(this);
        mImageButtonMinus = findViewById(R.id.ib_minus);
        mImageButtonMinus.setOnClickListener(this);
        mImageView.setOnTouchListener(onTouchListener);
        Intent intent = getIntent();
//        Bundle bundle = intent.getExtras();
        Uri uri = intent.getData();
        if (uri != null) {
//        Log.d(TAG, intent.getData().toString());
//        Uri uri = (Uri)bundle.get(Intent.EXTRA_STREAM);
            Log.d(TAG, "Data intent " + uri);
            mImageView.setImageURI(uri);
        } else {
            Bitmap bitmap = intent.getParcelableExtra(ScalingActivity.IMAGE);
            mImageView.setImageBitmap(bitmap);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_plus:
                zoomIn(mImageView);
                break;
            case R.id.ib_minus:
                zoomOut(mImageView);
                break;
        }
    }

    public View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            // TODO Auto-generated method stub

            ImageView view = (ImageView) v;
            dumpEvent(event);

            // Handle touch events here...
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:
                    savedMatrix.set(matrix);
                    start.set(event.getX(), event.getY());
                    Log.d(TAG, "mode=DRAG");
                    mode = DRAG;
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    oldDist = spacing(event);
                    Log.d(TAG, "oldDist=" + oldDist);
                    if (oldDist > 10f) {
                        savedMatrix.set(matrix);
                        midPoint(mid, event);
                        mode = ZOOM;
                        Log.d(TAG, "mode=ZOOM");
                    }
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_POINTER_UP:
                    mode = NONE;
                    Log.d(TAG, "mode=NONE");
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (mode == DRAG) {
                        // ...
                        matrix.set(savedMatrix);
                        matrix.postTranslate(event.getX() - start.x, event.getY()
                                - start.y);
                    } else if (mode == ZOOM) {
                        float newDist = spacing(event);
                        Log.d(TAG, "newDist=" + newDist);
                        if (newDist > 10f) {
                            matrix.set(savedMatrix);
                            float scale = newDist / oldDist;
                            matrix.postScale(scale, scale, mid.x, mid.y);
                        }
                    }
                    break;
            }

            view.setImageMatrix(matrix);
            return true;
        }
    };

    private void dumpEvent(MotionEvent event) {
        String names[] = { "DOWN", "UP", "MOVE", "CANCEL", "OUTSIDE",
                "POINTER_DOWN", "POINTER_UP", "7?", "8?", "9?" };
        StringBuilder sb = new StringBuilder();
        int action = event.getAction();
        int actionCode = action & MotionEvent.ACTION_MASK;
        sb.append("event ACTION_").append(names[actionCode]);
        if (actionCode == MotionEvent.ACTION_POINTER_DOWN
                || actionCode == MotionEvent.ACTION_POINTER_UP) {
            sb.append("(pid ").append(
                    action >> MotionEvent.ACTION_POINTER_ID_SHIFT);
            sb.append(")");
        }
        sb.append("[");
        for (int i = 0; i < event.getPointerCount(); i++) {
            sb.append("#").append(i);
            sb.append("(pid ").append(event.getPointerId(i));
            sb.append(")=").append((int) event.getX(i));
            sb.append(",").append((int) event.getY(i));
            if (i + 1 < event.getPointerCount())
                sb.append(";");
        }
        sb.append("]");
        Log.d(TAG, sb.toString());
    }

    /** Determine the space between the first two fingers */
    private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(x * x + y * y);
    }

    /** Calculate the mid point of the first two fingers */
    private void midPoint(PointF point, MotionEvent event) {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        point.set(x / 2, y / 2);
    }

    private void zoomIn(View v) {
        if (scaleX < maxZoomLimit && scaleY < maxZoomLimit) {
            Animation animation = new ScaleAnimation(scaleX, (scaleX + 0.2f), scaleY, (scaleY + 0.2f), 50, 50);
            scaleX += 0.2f;
            scaleY += 0.2f;
            animation.setInterpolator(new DecelerateInterpolator());
            animation.setDuration(duration);
            animation.setStartOffset(offset);
            animation.setFillAfter(true);
            v.startAnimation(animation);
        }
    }

    private void zoomOut(View v) {
        if (scaleX > minZoomLimit && scaleY > minZoomLimit) {
            float x = v.getScaleX();
            float y = v.getScaleY();
            Animation animation = new ScaleAnimation(scaleX, (scaleX - 0.2f), scaleY, (scaleY - 0.2f), 50, 50);
            scaleY -= 0.2f;
            scaleX -= 0.2f;
            animation.setInterpolator(new DecelerateInterpolator());
            animation.setDuration(duration);
            animation.setStartOffset(offset);
            animation.setFillAfter(true);
            v.startAnimation(animation);
        }
    }
}
