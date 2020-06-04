package com.example.projectmayham;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private Camera mCamera;
    private CameraPreview mPreview;
    private Context myContext;
    private LinearLayout cameraPreview;
    private boolean cameraFront = false;
    private Switch switchView;
    Runnable runnable;
    int delay = 5000;
    HashMap<Integer, String> randomMap = new HashMap<>();
    Handler handler = new Handler();
    boolean sw = false;
    MediaPlayer one, two, three;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        myContext = this;
        if (ContextCompat.checkSelfPermission(myContext, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.CAMERA}, 1);
        }else{
            mCamera =  Camera.open();
            mCamera.setDisplayOrientation(90);
            cameraPreview = findViewById(R.id.cPreview);
            mPreview = new CameraPreview(myContext, mCamera);
            cameraPreview.addView(mPreview);
            mCamera.startPreview();

        }

        switchView = findViewById(R.id.idSwitch);

        if (switchView.isChecked()) {
            sw = true;
        } else {
            sw = false;
        }

        switchView.setOnCheckedChangeListener((buttonView, isChecked) -> {
            sw = isChecked;
            Log.e("sw", String.valueOf(isChecked));
        });
        one = MediaPlayer.create(MainActivity.this, R.raw.one);
        two = MediaPlayer.create(MainActivity.this, R.raw.two);
        three = MediaPlayer.create(MainActivity.this, R.raw.three);
        randomMap.put(1,"I Love Java");
        randomMap.put(2,"I Hate Java");
        randomMap.put(3,"I Love Kotlin");
    }

    public void onResume() {
            handler.postDelayed( runnable = () -> {
                if(sw){
                int i = (int)(Math.random()*(3-1+1)+1);
                Toast.makeText(myContext,  randomMap.get(i), Toast.LENGTH_SHORT).show();
                if(i == 1){
                    one.start();
                }else if(i == 2){
                    two.start();
                }else
                    three.start();
                    }
                handler.postDelayed(runnable, delay);
            }, delay);
        super.onResume();
        if(mCamera == null) {
            mCamera = Camera.open();
            mCamera.setDisplayOrientation(90);
            mPreview.refreshCamera(mCamera);
            Log.d("nu", "null");
        }else {
            Log.d("nu","no null");
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        releaseCamera();
    }

    private void releaseCamera() {
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.setPreviewCallback(null);
            mCamera.release();
            mCamera = null;
        }
    }


}