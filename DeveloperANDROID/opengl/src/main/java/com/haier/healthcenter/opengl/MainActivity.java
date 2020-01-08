package com.haier.healthcenter.opengl;

import android.Manifest;
import android.content.pm.PackageManager;
;
import android.content.res.Resources;
import android.graphics.Rect;
import android.hardware.Camera;
import android.media.MediaScannerConnection;
import android.opengl.GLSurfaceView;
import android.os.Environment;
import android.os.Looper;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.camerakit.CameraKitView;
import com.jpegkit.Jpeg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;



import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;
import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO;


public class MainActivity extends AppCompatActivity {
    private CameraKitView cameraKitView;
    private Button btncamema;
    private RelativeLayout relparent;
    private int wid,height;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testanim);
        //imageView=(ImageView)findViewById(R.id.tkp);
        //animation(imageView);

    }






        private void animation(ImageView imageView){
            AlphaAnimation alphaAnimation=new AlphaAnimation(0,1);
            alphaAnimation.setDuration(3000);
            alphaAnimation.setFillAfter(true);
            imageView.startAnimation(alphaAnimation);

        }















    private void dosomething(){
        cameraKitView = findViewById(R.id.camera);
        btncamema=(Button)findViewById(R.id.button_capture);
        cameraKitView.requestPermissions(this);
        relparent=(RelativeLayout)findViewById(R.id.relpa);
        Log.e("width",relparent.getMeasuredWidth()+"height:"+relparent.getMeasuredHeight());
        relparent.post(new Runnable() {
            @Override
            public void run() {
                wid= relparent.getMeasuredWidth();
                height=relparent.getMeasuredHeight();
                Log.e("onResume",wid+"height:"+height);
            }
        });
        btncamema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("onclick","**************");
                Log.e("hehe0",cameraKitView.getMeasuredWidth()+"height:"+cameraKitView.getMeasuredHeight());
                cameraKitView.captureImage(new CameraKitView.ImageCallback() {
                    @Override
                    public void onImage(CameraKitView cameraKitView, final byte[] capturedImage) {
                        Jpeg jpeg=new Jpeg(capturedImage);
                       // Log.e("heighttop",getStatusBarHeight()+""+"bottomheight:"+getNavBarHeight());
                        Log.e("hehe",cameraKitView.getMeasuredWidth()+"height:"+cameraKitView.getMeasuredHeight());
                        jpeg.crop(new Rect(200,200,wid-200,height-200));
                        byte [] carrent;
                        carrent= jpeg.getJpegBytes();
                        File savedPhoto = new File(Environment.getExternalStorageDirectory(), "photo.jpg");
                        try {
                            FileOutputStream outputStream = new FileOutputStream(savedPhoto.getPath());
                            outputStream.write(carrent);
                            outputStream.close();
                        } catch (java.io.IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        //cameraKitView.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //cameraKitView.onResume();

    }





    @Override
    protected void onPause() {
        //cameraKitView.onPause();
        super.onPause();
    }

    @Override
    protected void onStop() {
        //cameraKitView.onStop();
        super.onStop();
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        cameraKitView.onRequestPermissionsResult(requestCode, permissions, grantResults);
//    }
//
//    public static int getStatusBarHeight() {
//        Resources resources = Resources.getSystem();
//        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
//        return resources.getDimensionPixelSize(resourceId);
//    }
//
//
//    public static int getNavBarHeight() {
//        Resources res = Resources.getSystem();
//        int resourceId = res.getIdentifier("navigation_bar_height", "dimen", "android");
//        if (resourceId != 0) {
//            return res.getDimensionPixelSize(resourceId);
//        } else {
//            return 0;
//        }
//    }


}
