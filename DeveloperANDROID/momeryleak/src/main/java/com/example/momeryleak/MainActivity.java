package com.example.momeryleak;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
Button button,btnStart;
SingletonManager singletonManager;
Handler mLeakyHandler=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("on-Create","***********");
        setContentView(R.layout.activity_main);
        button=(Button)findViewById(R.id.btnEnd);
        btnStart=(Button)findViewById(R.id.btnStart);
        new SampleTask().execute();
//        mLeakyHandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                button.setText("Done");
//            }
//        }, 3000 * 10);

        singletonManager=SingletonManager.getInstance(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("handle-finish","********command-finish*******");
                System.exit(0);
                Log.e("after-handle-finish","********after-command-finish*******");
            }
        });
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("go-toAnotherAc","*********");
              //new LeakTread().run();
                Intent intent=new Intent(MainActivity.this,LoginActivity.class);
               startActivity(intent);
               finish();


            }
        });

    }

    private class LeakTread extends Thread{
        @Override
        public void run() {
            Log.e("sleep-start","sleep-start");
            SystemClock.sleep(20*1000);
            Log.e("sleep-end","sleep-over");
            button.setText("20 S hava passed");


//            while(true){
//                Log.e("do-something","*****Id--***"+Thread.currentThread().getId());
//                try {
//                    sleep(200);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
        }



    }

    @Override
    protected void onDestroy() {
        Log.e("onDestroy","******onDestroy****");
        super.onDestroy();
    }


    @Override
    protected void onResume() {
        Log.e("onResume","******onResume****");
        super.onResume();
    }

    private class SampleTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(1000 * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            button.setText("Done " + new Date().getTime());
        }

    }
}
